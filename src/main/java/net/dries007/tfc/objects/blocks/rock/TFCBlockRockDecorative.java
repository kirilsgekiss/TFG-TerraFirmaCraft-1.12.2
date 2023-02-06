/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.rock;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public class TFCBlockRockDecorative extends Block implements IItemSize {
    public static final Map<EnumDyeColor, TFCBlockRockDecorative> ALABASTER_BRICKS = new HashMap<>();
    public static final Map<EnumDyeColor, TFCBlockRockDecorative> ALABASTER_POLISHED = new HashMap<>();
    public static final Map<EnumDyeColor, TFCBlockRockDecorative> ALABASTER_RAW = new HashMap<>();

    public TFCBlockRockDecorative(MapColor blockMapColorIn) {
        super(Material.ROCK, blockMapColorIn);
        setSoundType(SoundType.STONE);
        setHardness(1.0F);
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack) {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack) {
        return Weight.LIGHT;
    }
}