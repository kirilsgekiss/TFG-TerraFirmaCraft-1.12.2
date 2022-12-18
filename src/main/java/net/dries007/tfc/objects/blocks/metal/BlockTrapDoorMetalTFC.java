/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.metal;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.dries007.tfc.util.OreDictionaryHelper;

public class BlockTrapDoorMetalTFC extends BlockTrapDoor
{
    public final gregtech.api.unification.material.Material metal;

    public BlockTrapDoorMetalTFC(gregtech.api.unification.material.Material metal)
    {
        super(Material.IRON);
        this.metal = metal;
        setHardness(1F);
        setSoundType(SoundType.METAL);
        OreDictionaryHelper.register(this, "trapdoorMetal");
    }
}
