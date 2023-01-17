/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.wood;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.util.OreDictionaryHelper;

public class TFCBlockWoodPressurePlate extends BlockPressurePlate
{
    private static final Map<Tree, TFCBlockWoodPressurePlate> MAP = new HashMap<>();

    public static TFCBlockWoodPressurePlate get(Tree wood)
    {
        return MAP.get(wood);
    }

    public final Tree wood;

    public TFCBlockWoodPressurePlate(Tree wood)
    {
        super(Material.WOOD, Sensitivity.EVERYTHING);
        this.wood = wood;
        if (MAP.put(wood, this) != null) throw new IllegalStateException("There can only be one.");
        setHardness(0.5F);
        setSoundType(SoundType.WOOD);
        Blocks.FIRE.setFireInfo(this, 5, 20);

        OreDictionaryHelper.register(this, "pressure_plate_wood");
    }
}
