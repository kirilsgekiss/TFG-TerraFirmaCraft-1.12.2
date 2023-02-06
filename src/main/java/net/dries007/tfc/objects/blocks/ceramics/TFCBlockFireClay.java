/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.ceramics;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
public class TFCBlockFireClay extends Block implements IItemSize {
    public TFCBlockFireClay() {
        super(Material.CLAY);
        setSoundType(SoundType.GROUND);
        setHardness(1.0F);
    }

    @Override
    public int quantityDropped(Random random) {
        return 4;
    }

    @Nonnull
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return TFCItems.FIRE_CLAY;
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack) {
        return Size.VERY_SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack) {
        return Weight.HEAVY;
    }
}
