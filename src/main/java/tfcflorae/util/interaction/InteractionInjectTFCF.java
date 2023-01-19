package tfcflorae.util.interaction;

import javax.annotation.Nonnull;

import net.dries007.tfc.TerraFirmaCraft;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.dries007.tfc.api.types.ICrop;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropTFC;
import net.dries007.tfc.objects.items.ItemSeedsTFC;
import net.dries007.tfc.util.agriculture.Crop;

import tfcflorae.TFCFlorae;
import net.dries007.tfc.objects.blocks.stone.farmland.BlockFarmlandTFC;

public class InteractionInjectTFCF
{
    @Nonnull
    public static EnumActionResult onItemUse(ItemSeedsTFC itemSeed, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() instanceof BlockFarmlandTFC)
        {
            return itemstack.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        }
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, itemSeed) && worldIn.isAirBlock(pos.up()) && state.getBlock() instanceof BlockFarmlandTFC)
        {
            ICrop seedCrop = null;

            for (Crop crop : Crop.values())
                if (itemSeed == ItemSeedsTFC.get(crop))
                    seedCrop = crop;

            if (seedCrop == null)
            {
                TerraFirmaCraft.getLog().error("Couldn't find crop to place in farmland");
                return EnumActionResult.FAIL;
            }

            worldIn.setBlockState(pos.up(), BlockCropTFC.get(seedCrop).getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
}
