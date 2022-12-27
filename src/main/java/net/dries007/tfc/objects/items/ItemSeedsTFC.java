/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.dries007.tfc.api.types.ICrop;
import net.dries007.tfc.objects.blocks.agriculture.BlockCropTFC;
import net.dries007.tfc.objects.blocks.stone.BlockFarmlandTFC;
import net.dries007.tfc.util.agriculture.Crop;

import static net.dries007.tfc.world.classic.ChunkGenTFC.WATER;

public class ItemSeedsTFC extends Item implements IPlantable
{
    private static final Map<ICrop, ItemSeedsTFC> MAP = new HashMap<>();

    public static ItemSeedsTFC get(ICrop crop)
    {
        return MAP.get(crop);
    }

    public static ItemStack get(ICrop crop, int amount)
    {
        return new ItemStack(MAP.get(crop), amount);
    }

    private final ICrop crop;

    public ItemSeedsTFC(ICrop crop)
    {
        this.crop = crop;
        if (MAP.put(crop, this) != null)
        {
            throw new IllegalStateException("There can only be one.");
        }
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        BlockCropTFC cropBlock = BlockCropTFC.get(crop);
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);

        if (crop != Crop.RICE && facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()) && state.getBlock() instanceof BlockFarmlandTFC)
        {
            worldIn.setBlockState(pos.up(), BlockCropTFC.get(crop).getDefaultState());

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, pos.up(), itemstack);
            }

            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else if (crop == Crop.RICE && cropBlock.canPlaceBlockAt(worldIn, pos))
        {
            RayTraceResult raytraceresult = this.rayTrace(worldIn, player, true);

            //noinspection ConstantConditions
            if (raytraceresult == null)
            {
                return EnumActionResult.PASS;
            }
            else
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos blockpos = raytraceresult.getBlockPos();
                    Material material = worldIn.getBlockState(blockpos.down()).getMaterial();

                    if ((!worldIn.isBlockModifiable(player, blockpos) || !player.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) && material == Material.WATER)
                    {
                        return EnumActionResult.FAIL;
                    }

                    BlockPos blockpos1 = blockpos.up();
                    IBlockState iblockstate = worldIn.getBlockState(blockpos);

                    if ((iblockstate.getMaterial() == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0 && worldIn.isAirBlock(blockpos1) && iblockstate == WATER) && material != Material.WATER)
                    {
                        // special case for handling block placement with water lilies
                        net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
                        worldIn.setBlockState(blockpos1, BlockCropTFC.get(crop).getDefaultState());
                        if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(player, blocksnapshot, net.minecraft.util.EnumFacing.UP, hand).isCanceled())
                        {
                            blocksnapshot.restore(true, false);
                            return EnumActionResult.FAIL;
                        }

                        worldIn.setBlockState(blockpos1, BlockCropTFC.get(crop).getDefaultState(), 11);

                        if (player instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) player, blockpos1, itemstack);
                        }

                        if (!player.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }

                        //noinspection ConstantConditions
                        player.addStat(StatList.getObjectUseStats(this));
                        worldIn.playSound(player, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return EnumActionResult.SUCCESS;
                    }
                }

                return EnumActionResult.FAIL;
            }
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

        if (crop == Crop.RICE)
        {
            //noinspection ConstantConditions
            if (raytraceresult == null)
            {
                return new ActionResult<>(EnumActionResult.PASS, itemstack);
            }
            else
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos blockpos = raytraceresult.getBlockPos();
                    Material material = worldIn.getBlockState(blockpos.down()).getMaterial();

                    if ((!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) && material == Material.WATER)
                    {
                        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                    }

                    BlockPos blockpos1 = blockpos.up();
                    IBlockState iblockstate = worldIn.getBlockState(blockpos);

                    if ((iblockstate.getMaterial() == Material.WATER && iblockstate.getValue(BlockLiquid.LEVEL) == 0 && worldIn.isAirBlock(blockpos1) && iblockstate == WATER) && material != Material.WATER)
                    {
                        // special case for handling block placement with water lilies
                        net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
                        worldIn.setBlockState(blockpos1, BlockCropTFC.get(crop).getDefaultState());
                        if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP, handIn).isCanceled())
                        {
                            blocksnapshot.restore(true, false);
                            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
                        }

                        worldIn.setBlockState(blockpos1, BlockCropTFC.get(crop).getDefaultState(), 11);

                        if (playerIn instanceof EntityPlayerMP)
                        {
                            CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP) playerIn, blockpos1, itemstack);
                        }

                        if (!playerIn.capabilities.isCreativeMode)
                        {
                            itemstack.shrink(1);
                        }

                        //noinspection ConstantConditions
                        playerIn.addStat(StatList.getObjectUseStats(this));
                        worldIn.playSound(playerIn, blockpos, SoundEvents.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        return new ActionResult<>(EnumActionResult.SUCCESS, itemstack);
                    }
                }

                return new ActionResult<>(EnumActionResult.FAIL, itemstack);
            }
        }
        return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() instanceof BlockCropTFC && ((BlockCropTFC) state.getBlock()).getCrop() == this.crop)
        {
            return state;
        }
        return BlockCropTFC.get(crop).getDefaultState();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        crop.addInfo(stack, worldIn, tooltip, flagIn);
    }
}
