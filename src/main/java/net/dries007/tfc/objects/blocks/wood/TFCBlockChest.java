/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import git.jbredwards.fluidlogged_api.api.util.FluidState;
import git.jbredwards.fluidlogged_api.api.util.FluidloggedUtils;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Wood;
import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.objects.inventory.capability.TFCInventoryLargeChest;
import net.dries007.tfc.objects.te.TEChest;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public class TFCBlockChest extends BlockChest implements IItemSize {
    // Using custom types here to make our chests not connect to vanilla's (fixes https://github.com/TerraFirmaCraft/TerraFirmaCraft/issues/855)
    // in 1.15, if this is still needed we should re-evaluate the option to not extend vanilla's BlockChest and make one of our own
    public static final Type TFCBASIC = EnumHelper.addEnum(BlockChest.Type.class, "TFCBASIC", new Class<?>[0]);
    public static final Type TFCTRAP = EnumHelper.addEnum(BlockChest.Type.class, "TFCTRAP", new Class<?>[0]);

    private static final Map<Wood, TFCBlockChest> MAP_BASIC = new HashMap<>();
    private static final Map<Wood, TFCBlockChest> MAP_TRAP = new HashMap<>();

    public static TFCBlockChest getBasic(Wood wood) {
        return MAP_BASIC.get(wood);
    }

    public static TFCBlockChest getTrap(Wood wood) {
        return MAP_TRAP.get(wood);
    }

    public final Wood wood;

    public TFCBlockChest(Type type, Wood wood) {
        super(type);
        this.wood = wood;
        setHardness(2.5F);
        setSoundType(SoundType.WOOD);
        if (type == TFCBASIC) {
            if (MAP_BASIC.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
            OreDictionaryHelper.register(this, "chest");
            OreDictionaryHelper.register(this, "chest", "wood");
            //noinspection ConstantConditions
            OreDictionaryHelper.register(this, "chest", wood.getRegistryName().getPath());
        } else if (type == TFCTRAP) {
            if (MAP_TRAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
            OreDictionaryHelper.register(this, "chest", "trapped");
            OreDictionaryHelper.register(this, "chest", "wood");
            //noinspection ConstantConditions
            OreDictionaryHelper.register(this, "chest", "trapped", wood.getRegistryName().getPath());
        } else {
            throw new IllegalStateException("TFC Chest must use TFC chest type");
        }
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TFCGuiHandler.openGui(worldIn, pos, playerIn, TFCGuiHandler.Type.CHEST);
        }
        return true;
    }

    /**
     * This and the following methods are copied from vanilla to allow us to hook into vanilla's chest stuff
     * Hoppers are hardcoded for vanilla chest insertions, which means we need to block them (to stop inserting items that aren't the correct size)
     */
    @Nullable
    public ILockableContainer getContainer(World worldIn, BlockPos pos, boolean allowBlocking) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (!(tileentity instanceof TileEntityChest)) {
            return null;
        } else {
            ILockableContainer ilockablecontainer = (TileEntityChest) tileentity;

            if (!allowBlocking && isBlocked(worldIn, pos)) {
                return null;
            } else {
                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                    BlockPos blockpos = pos.offset(enumfacing);
                    Block block = worldIn.getBlockState(blockpos).getBlock();

                    if (block == this) {
                        if (!allowBlocking && isBlocked(worldIn, blockpos)) // Forge: fix MC-99321
                        {
                            return null;
                        }

                        TileEntity tileentity1 = worldIn.getTileEntity(blockpos);

                        if (tileentity1 instanceof TileEntityChest) {
                            if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH) {
                                ilockablecontainer = new TFCInventoryLargeChest("container.chestDouble", ilockablecontainer, (TileEntityChest) tileentity1);
                            } else {
                                ilockablecontainer = new TFCInventoryLargeChest("container.chestDouble", (TileEntityChest) tileentity1, ilockablecontainer);
                            }
                        }
                    }
                }

                return ilockablecontainer;
            }
        }
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEChest();
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack) {
        return Size.LARGE; // Can only be stored in itself (and since this can't be carried with items, makes sense
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack) {
        return Weight.LIGHT; // Stacksize = 32
    }

    private boolean isBlocked(World worldIn, BlockPos pos) {
        return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
    }

    private boolean isBelowSolidBlock(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.up()).doesSideBlockChestOpening(worldIn, pos.up(), EnumFacing.DOWN);
    }

    private boolean isOcelotSittingOnChest(World worldIn, BlockPos pos) {
        for (Entity entity : worldIn.getEntitiesWithinAABB(EntityOcelot.class, new AxisAlignedBB(pos.getX(), pos.getY() + 1, pos.getZ(), pos.getX() + 1, pos.getY() + 2, pos.getZ() + 1))) {
            EntityOcelot entityocelot = (EntityOcelot) entity;

            if (entityocelot.isSitting()) {
                return true;
            }
        }

        return false;
    }

    public interface IFluidloggable {
        /**
         * @return true if the IBlockState is fluidloggable
         */
        default boolean isFluidloggable(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
            return true;
        }

        /**
         * @return true if the IBlockState can be fluidlogged with the input fluid
         */
        default boolean isFluidValid(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Fluid fluid) {
            return isFluidloggable(state, world, pos);
        }

        /**
         * called by {@link FluidloggedUtils#canFluidFlow},
         * which is invoked a lot, so try to keep the code for this fairly light.
         *
         * @return true if the contained fluid can flow from the specified side,
         * or if a fluid can flow into this block from the specified side
         */
        default boolean canFluidFlow(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull EnumFacing side) {
            return here.getBlockFaceShape(world, pos, side) != BlockFaceShape.SOLID;
        }

        /**
         * @return true if the FluidState should be visible while this is fluidlogged
         */
        @SideOnly(Side.CLIENT)
        default boolean shouldFluidRender(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState fluidState) {
            return true;
        }

        /**
         * called by {@link FluidloggedUtils#setFluidState}
         * when the stored FluidState is changed
         *
         * @return PASS - run & return {@link FluidloggedUtils#setFluidState_Internal},
         * FAIL - assume the change never happened,
         * SUCCESS - assume the change happened
         */
        @Nonnull
        default EnumActionResult onFluidChange(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
            return newFluid.isEmpty() ? onFluidDrain(world, pos, here, blockFlags) : onFluidFill(world, pos, here, newFluid, blockFlags);
        }

        /**
         * convenience method called by {@link IFluidloggable#onFluidChange} when a new FluidState is put here
         */
        @Nonnull
        default EnumActionResult onFluidFill(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
            return EnumActionResult.PASS;
        }

        /**
         * convenience method called by {@link IFluidloggable#onFluidChange} when the stored FluidState is removed
         */
        @Nonnull
        default EnumActionResult onFluidDrain(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, int blockFlags) {
            return EnumActionResult.PASS;
        }
    }
}
