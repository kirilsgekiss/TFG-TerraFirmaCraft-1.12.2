package net.dries007.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.TFGOrePrefix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class TFGUtils {
    public static final List<Material> materialListForUnmold = new ArrayList<Material>()
    {
        {
            add(Materials.Copper);
            add(Materials.BismuthBronze);
            add(Materials.Bronze);
            add(Materials.BlackBronze);
        }
    };

    public static final HashMap<OrePrefix, Integer> orePrefixListForUnmold = new HashMap<OrePrefix, Integer>()
    {
        {
            put(OrePrefix.toolHeadSword, 288);
            put(OrePrefix.toolHeadPickaxe, 288);
            put(OrePrefix.toolHeadAxe, 288);
            put(OrePrefix.toolHeadShovel, 144);
            put(OrePrefix.toolHeadHoe, 144);
            put(OrePrefix.toolHeadSaw, 144);
            put(OrePrefix.toolHeadHammer, 432);
            put(OrePrefix.toolHeadSense, 432);
            put(TFGOrePrefix.toolHeadKnife, 144);
        }
    };
}
