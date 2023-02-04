/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.dries007.tfc.api.types.Wood;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;

public class TFCBlockWoodTrapDoor extends BlockTrapDoor
{
    private static final Map<Wood, TFCBlockWoodTrapDoor> MAP = new HashMap<>();

    public static TFCBlockWoodTrapDoor get(Wood wood)
    {
        return MAP.get(wood);
    }

    public final Wood wood;

    public TFCBlockWoodTrapDoor(Wood wood)
    {
        super(Material.WOOD);
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        OreDictionaryHelper.register(this, "trapdoor", "wood");
        //noinspection ConstantConditions
        OreDictionaryHelper.register(this, "trapdoor", "wood", wood.getRegistryName().getPath());
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }
}
