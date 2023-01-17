/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;

public class TFCBlockWoodTrapDoor extends BlockTrapDoor
{
    private static final Map<Tree, TFCBlockWoodTrapDoor> MAP = new HashMap<>();

    public static TFCBlockWoodTrapDoor get(Tree wood)
    {
        return MAP.get(wood);
    }

    public final Tree wood;

    public TFCBlockWoodTrapDoor(Tree wood)
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
