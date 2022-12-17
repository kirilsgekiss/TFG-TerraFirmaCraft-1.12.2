package net.dries007.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.TFCOrePrefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class TFGUtils {

    public final static HashMap<OrePrefix, Integer> ORE_PREFIX_TO_METAL_UNITS = new HashMap<OrePrefix, Integer>()
    {
        {
            put(OrePrefix.plate, 144);
            put(OrePrefix.ingot, 144);
            put(OrePrefix.plateDouble, 288);
            put(OrePrefix.dust, 144);
            put(OrePrefix.nugget, 16);

            put(OrePrefix.toolHeadSword, 288);
            put(OrePrefix.toolHeadPickaxe, 288);
            put(OrePrefix.toolHeadAxe, 288);
            put(OrePrefix.toolHeadShovel, 144);
            put(OrePrefix.toolHeadHoe, 144);
            put(OrePrefix.toolHeadSaw, 144);
            put(OrePrefix.toolHeadHammer, 432);
            put(OrePrefix.toolHeadSense, 432);
            put(TFCOrePrefix.toolHeadKnife, 144);
        }
    };

    // TODO
    private final static HashMap<OrePrefix, String[]> ORE_PREFIX_TO_KNAPPING_RECIPE = new HashMap<OrePrefix, String[]>()
    {
        {
            put(OrePrefix.ingot, new String[]{"XXXX", "X  X", "X  X", "X  X", "XXXX"});

            put(OrePrefix.toolHeadSword, new String[]{"XXX  ", "XX   ", "X   X", "X  XX", " XXXX"});
            put(OrePrefix.toolHeadPickaxe, new String[]{"XXXXX", "X   X", " XXX ", "XXXXX"});
            put(OrePrefix.toolHeadAxe, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"});
            put(OrePrefix.toolHeadShovel, new String[]{"X   X", "X   X", "X   X", "X   X", "XX XX"});
            put(OrePrefix.toolHeadHoe, new String[]{"XXXXX", "     ", "  XXX", "XXXXX"});
            put(OrePrefix.toolHeadSaw, new String[]{"XXX  ", "XX   ", "X   X", "    X", "  XXX"});
            put(OrePrefix.toolHeadHammer, new String[]{"     ", "     ", "XX XX", "XXXXX"});
            put(OrePrefix.toolHeadSense, new String[]{"XXXXX", "X    ", "    X", "  XXX", "XXXXX"});
            put(TFCOrePrefix.toolHeadKnife, new String[]{"XX X", "X  X", "X  X", "X  X", "X  X"});
        }
    };

    private final static List<OrePrefix> OREPREFIXES_HAS_MOLD = Arrays.asList
    (
            OrePrefix.ingot,
            OrePrefix.toolHeadSword,
            OrePrefix.toolHeadAxe,
            OrePrefix.toolHeadPickaxe,
            OrePrefix.toolHeadAxe,
            OrePrefix.toolHeadShovel,
            OrePrefix.toolHeadHoe,
            OrePrefix.toolHeadSaw,
            OrePrefix.toolHeadHammer,
            OrePrefix.toolHeadSense,
            TFCOrePrefix.toolHeadKnife
    );

    public final static HashMap<Material, Integer> MATERIALS_TO_TIER = new HashMap<Material, Integer>()
    {
        {
            put(Materials.Copper, 1);
            put(Materials.Bronze, 2);
            put(Materials.BlackBronze, 2);
            put(Materials.BismuthBronze, 2);
            put(Materials.WroughtIron, 3);
        }
    };

    public static boolean isOrePrefixHasMold(OrePrefix orePrefix)
    {
        return OREPREFIXES_HAS_MOLD.contains(orePrefix);
    }

    public static int getMetalAmountFromOrePrefix(OrePrefix orePrefix)
    {
        return ORE_PREFIX_TO_METAL_UNITS.get(orePrefix);
    }

    public static String[] getKnappingPattern(OrePrefix orePrefix)
    {
        return ORE_PREFIX_TO_KNAPPING_RECIPE.get(orePrefix);
    }

    public static int getMaterialTier(Material material)
    {
        return MATERIALS_TO_TIER.get(material);
    }
}
