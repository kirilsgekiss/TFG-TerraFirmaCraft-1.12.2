/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.metal;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.dries007.tfc.util.OreDictionaryHelper;

import java.util.HashMap;
import java.util.Map;

public class BlockTrapDoorTFC extends BlockTrapDoor
{
    private static final Map<gregtech.api.unification.material.Material, BlockTrapDoorTFC> MAP = new HashMap<>();

    public static BlockTrapDoorTFC get(gregtech.api.unification.material.Material metal) {
        return MAP.get(metal);
    }

    public final gregtech.api.unification.material.Material metal;

    public BlockTrapDoorTFC(gregtech.api.unification.material.Material metal)
    {
        super(Material.IRON);

        this.metal = metal;
        if (MAP.put(metal, this) != null) throw new IllegalStateException("There can only be one.");

        setHardness(1F);
        setSoundType(SoundType.METAL);
        OreDictionaryHelper.register(this, "trapdoorMetal");
    }
}
