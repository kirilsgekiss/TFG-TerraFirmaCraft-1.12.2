package net.dries007.tfc.objects.items.metal;

import gregtech.api.unification.material.Material;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.compat.gregtech.materials.properties.TFCPropertyKey;
import net.dries007.tfc.objects.blocks.metal.TFCBlockMetalTrapDoor;
import net.dries007.tfc.objects.items.TFCItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public class ItemMetalTrapdoor extends TFCItem implements IMetalItem {

    private final Material material;

    private static final Map<Material, ItemMetalTrapdoor> MAP = new HashMap<>();

    public static ItemMetalTrapdoor get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    public ItemMetalTrapdoor(Material material) {
        this.material = material;

        if (MAP.put(material, this) != null) throw new IllegalStateException("There can only be one.");
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (facing != null) {
            ItemStack stack = player.getHeldItem(hand);
            BlockPos placedPos = pos.offset(facing);
            BlockPos supportPos = placedPos.down();
            IBlockState state = worldIn.getBlockState(placedPos);
            IBlockState stateSupport = worldIn.getBlockState(supportPos);
            if (state.getBlock().isReplaceable(worldIn, placedPos) &&
                    stateSupport.isSideSolid(worldIn, supportPos, EnumFacing.UP)) //forge says to do it this way, IBlockProperties#isTopSolid
            {
                if (!worldIn.isRemote) {
                    ItemMetalTrapdoor trapdoor = (ItemMetalTrapdoor) stack.getItem();
                    worldIn.setBlockState(placedPos, TFCBlockMetalTrapDoor.get(trapdoor.material).getDefaultState());
                    worldIn.playSound(null, placedPos, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    stack.shrink(1);
                    player.setHeldItem(hand, stack);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalizationUtils.format("tile.tfc.trapdoor.name", material.getLocalizedName());
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ForgeableHeatableHandler(nbt, material.getProperty(TFCPropertyKey.TFC).getMaterialHeatCapacity(), material.getFluid().getTemperature());
    }

    @Nullable
    @Override
    public Material getMetal(ItemStack stack) {
        return material;
    }

    @Override
    public int getSmeltAmount(ItemStack stack) {
        return 144;
    } // todo

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack) {
        return Size.NORMAL;
    } // todo

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack) {
        return Weight.MEDIUM;
    } // todo
}
