package net.dries007.tfc.objects.blocks;

import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.objects.items.ItemsTFC;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;

import tfcflorae.objects.items.ItemsTFCF;
import net.dries007.tfc.util.OreDictionaryHelper;

@ParametersAreNonnullByDefault
public class BlockStonewareClay extends Block implements IItemSize
{
    public BlockStonewareClay()
    {
        super(Material.CLAY);
        setSoundType(SoundType.GROUND);
        setHardness(1.0F);
        OreDictionaryHelper.register(this, "block", "Clay");
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 4;
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemsTFC.STONEWARE_CLAY;
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }
}
