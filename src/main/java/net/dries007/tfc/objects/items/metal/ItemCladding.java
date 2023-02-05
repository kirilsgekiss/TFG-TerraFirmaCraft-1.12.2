package net.dries007.tfc.objects.items.metal;

import gregtech.api.unification.material.Material;
import gregtech.api.util.LocalizationUtils;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.compat.gregtech.materials.properties.TFCPropertyKey;
import net.dries007.tfc.objects.blocks.metal.TFCBlockCladding;
import net.dries007.tfc.objects.items.TFCItem;
import net.dries007.tfc.objects.te.TEMetalSheet;
import net.dries007.tfc.util.Helpers;
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
public class ItemCladding extends TFCItem implements IMetalItem {

    private final Material material;

    private static final Map<Material, ItemCladding> MAP = new HashMap<>();

    public static ItemCladding get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    public ItemCladding(Material material) {
        this.material = material;

        if (MAP.put(material, this) != null) throw new IllegalStateException("There can only be one.");
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        if (worldIn.getBlockState(pos).isNormalCube() && stack.getItem() instanceof ItemCladding) {
            if (!ItemStack.areItemStacksEqual(new ItemStack(stack.getItem(), stack.getCount()), stack)) {
                return EnumActionResult.FAIL;
            }
            ItemCladding sheet = (ItemCladding) stack.getItem();
            BlockPos posAt = pos.offset(facing);
            IBlockState stateAt = worldIn.getBlockState(posAt);

            if (stateAt.getBlock() instanceof TFCBlockCladding) {
                // Existing sheet block
                Material metal = ((TFCBlockCladding) stateAt.getBlock()).getMetal();
                if (metal == sheet.material) {
                    stack.shrink(1);
                    player.setHeldItem(hand, stack);
                    return placeSheet(worldIn, posAt, facing);
                }
            } else if (stateAt.getBlock().isReplaceable(worldIn, posAt)) {
                // Place a new block
                if (!worldIn.isRemote) {
                    worldIn.setBlockState(posAt, TFCBlockCladding.get(sheet.material).getDefaultState());
                    stack.shrink(1);
                    player.setHeldItem(hand, stack);
                    placeSheet(worldIn, posAt, facing);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    private EnumActionResult placeSheet(World world, BlockPos pos, EnumFacing facing) {
        TEMetalSheet tile = Helpers.getTE(world, pos, TEMetalSheet.class);
        if (tile != null && !tile.getFace(facing)) {
            if (!world.isRemote) {
                tile.setFace(facing, true);
                world.playSound(null, pos.offset(facing), SoundEvents.BLOCK_METAL_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.FAIL;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return LocalizationUtils.format("item.tfc.metal.cladding.name", material.getLocalizedName());
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
        return 288;
    } // todo

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack) {
        return Size.LARGE;
    } // todo

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack) {
        return Weight.HEAVY;
    } // todo
}
