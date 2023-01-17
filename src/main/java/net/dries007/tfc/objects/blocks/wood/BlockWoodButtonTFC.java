/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockButtonWood;
import net.minecraft.block.SoundType;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;

public class BlockWoodButtonTFC extends BlockButtonWood
{
    private static final Map<Tree, BlockWoodButtonTFC> MAP = new HashMap<>();

    public static BlockWoodButtonTFC get(Tree wood)
    {
        return MAP.get(wood);
    }

    public final Tree wood;

    public BlockWoodButtonTFC(Tree wood)
    {
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }
}
