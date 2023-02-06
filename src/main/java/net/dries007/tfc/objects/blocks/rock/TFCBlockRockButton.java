/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.rock;

import net.dries007.tfc.api.types.Rock;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.block.SoundType;

import java.util.HashMap;
import java.util.Map;

public class TFCBlockRockButton extends BlockButtonStone {
    private static final Map<Rock, TFCBlockRockButton> MAP = new HashMap<>();

    public static TFCBlockRockButton get(Rock rock) {
        return MAP.get(rock);
    }

    public final Rock rock;

    public TFCBlockRockButton(Rock rock) {
        this.rock = rock;
        if (MAP.put(rock, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.STONE);
    }
}
