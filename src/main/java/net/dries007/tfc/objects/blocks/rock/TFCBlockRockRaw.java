/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.rock;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.compat.tfc.TFGUtils;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TFCBlockRockRaw extends TFCBlockRockVariant {
    /* This is for the not-surrounded-on-all-sides-pop-off mechanic. It's a dirty fix to the stack overflow caused by placement during water / lava collisions in world gen */
    public static final PropertyBool CAN_FALL = PropertyBool.create("can_fall");

    public TFCBlockRockRaw(Type type, Rock rock) {
        super(type, rock);

        FallingBlockManager.Specification spec = new FallingBlockManager.Specification(type.getFallingSpecification()); // Copy as each raw stone has an unique resultingState
        // spec.setResultingState(BlockRockVariant.get(rock, Type.COBBLE).getDefaultState());
        FallingBlockManager.registerFallable(this, spec);

        setDefaultState(getBlockState().getBaseState().withProperty(CAN_FALL, true));

        OreDictionaryHelper.register(this, "raw", rock);
        OreDictionaryHelper.register(this, "raw", rock.getRockCategory());
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(CAN_FALL, meta == 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        if (state.getBlock() != this) {
            return 0;
        } else {
            return state.getValue(CAN_FALL) ? 0 : 1;
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        // Raw blocks that can't fall also can't pop off
        if (state.getValue(CAN_FALL)) {
            for (EnumFacing face : EnumFacing.VALUES) {
                BlockPos offsetPos = pos.offset(face);
                IBlockState faceState = worldIn.getBlockState(offsetPos);
                if (faceState.getBlock().isSideSolid(faceState, worldIn, offsetPos, face.getOpposite())) {
                    return;
                }
            }

            // No supporting solid blocks, so pop off as an item
            worldIn.setBlockToAir(pos);
            Helpers.spawnItemStack(worldIn, pos, new ItemStack(state.getBlock(), 1));
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = playerIn.getHeldItemMainhand();
        if (TFCConfig.General.OVERRIDES.enableStoneAnvil && OreDictionaryHelper.doesStackMatchOre(stack, "craftingToolHammer") && !worldIn.isBlockNormalCube(pos.up(), true)) {
            if (!worldIn.isRemote) {
                // Create a stone anvil
                TFCBlockRockVariant anvil = TFCBlockRockVariant.get(this.rock, Type.ANVIL);
                if (anvil instanceof TFCBlockRockAnvil) {
                    worldIn.setBlockState(pos, anvil.getDefaultState());
                }
            }
            return true;
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CAN_FALL);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);
        // Raw rocks drop random gems
        if (RANDOM.nextDouble() < TFCConfig.General.MISC.stoneGemDropChance) {
            drops.add(TFGUtils.getRandomGem());
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return 0;
    }
}