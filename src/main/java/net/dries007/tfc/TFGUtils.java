package net.dries007.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.TFGOrePrefix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class TFGUtils {

    public static final HashMap<Material, Integer> MATERIALS_TO_TIER = new HashMap<Material, Integer>()
    {
        {
            put(Materials.Copper, 1);
            put(Materials.Bronze, 2);
            put(Materials.BlackBronze, 2);
            put(Materials.BismuthBronze, 2);
            put(Materials.WroughtIron, 3);
        }
    };

    public static final HashMap<OrePrefix, Integer> ORE_PREFIX_TO_METAL_UNITS = new HashMap<OrePrefix, Integer>()
    {
        {
            put(OrePrefix.plate, 144);
            put(OrePrefix.ingot, 144);
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
            put(TFGOrePrefix.toolHeadKnife, 144);
        }
    };

    // TODO
    private static final HashMap<OrePrefix, String> ORE_PREFIX_TO_KNAPPING_RECIPE = new HashMap<OrePrefix, String>()
    {
        {
            put(OrePrefix.ingot, "");

            put(OrePrefix.toolHeadSword, "");
            put(OrePrefix.toolHeadPickaxe, "");
            put(OrePrefix.toolHeadAxe, "");
            put(OrePrefix.toolHeadShovel, "");
            put(OrePrefix.toolHeadHoe, "");
            put(OrePrefix.toolHeadSaw, "");
            put(OrePrefix.toolHeadHammer, "");
            put(OrePrefix.toolHeadSense, "");
            put(TFGOrePrefix.toolHeadKnife, "");
        }
    };

    private static final List<OrePrefix> OREPREFIXES_HAS_MOLD = Arrays.asList
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
            TFGOrePrefix.toolHeadKnife
    );

    public static boolean isOrePrefixHasMold(OrePrefix orePrefix)
    {
        return OREPREFIXES_HAS_MOLD.contains(orePrefix);
    }

    public static int getMetalAmountForOrePrefix(OrePrefix orePrefix)
    {
        return ORE_PREFIX_TO_METAL_UNITS.get(orePrefix);
    }

    public static String getKnappingRecipe(OrePrefix orePrefix)
    {
        return ORE_PREFIX_TO_KNAPPING_RECIPE.get(orePrefix);
    }

    public static int getMaterialTier(Material material)
    {
        return MATERIALS_TO_TIER.get(material);
    }
}
