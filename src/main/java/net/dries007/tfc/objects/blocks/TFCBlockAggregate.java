/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks;

import net.minecraft.block.BlockGravel;
import net.minecraft.block.SoundType;

public class TFCBlockAggregate extends BlockGravel {
    public TFCBlockAggregate() {
        setSoundType(SoundType.SAND);
        setHardness(0.4f);
    }
}
