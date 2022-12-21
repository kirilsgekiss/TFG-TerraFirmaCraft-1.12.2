package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.TFCOrePrefix;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static gregtech.api.unification.material.Materials.*;
import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public final class TFGUtils {

    public static final List<TFCOrePrefixExtended> EXTENDED_OREPREFIXES = Arrays.asList
    (
            new TFCOrePrefixExtended(OrePrefix.ingot, 144, true, new String[]{"XXXX", "X  X", "X  X", "X  X", "XXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotDouble, 288),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotTriple, 432),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotHex, 864),
            new TFCOrePrefixExtended(OrePrefix.stick, 72),
            new TFCOrePrefixExtended(OrePrefix.dust, 144),
            new TFCOrePrefixExtended(OrePrefix.nugget, 16),
            new TFCOrePrefixExtended(OrePrefix.plate, 144),
            new TFCOrePrefixExtended(OrePrefix.plateDouble, 288),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSword, 288, true, new String[]{"XXX  ", "XX   ", "X   X", "X  XX", " XXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadPickaxe, 432, true, new String[]{"XXXXX", "X   X", " XXX ", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadAxe, 432, true, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadShovel, 144, true, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHoe, 144, true, new String[]{"X   X", "X   X", "X   X", "X   X", "XX XX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSaw, 288, true, new String[]{"XXXXX", "     ", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHammer, 864, true, new String[]{"     ", "     ", "XX XX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSense, 432, true, new String[]{"XXXXX", "X    ", "    X", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadKnife, 144, true, new String[]{"XX X", "X  X", "X  X", "X  X", "X  X"})
    );

    public static final List<TFCMaterialExtended> EXTENDED_MATERIALS = Arrays.asList
    (
            new TFCMaterialExtended(Unknown, 0.5F, 1),
            new TFCMaterialExtended(Copper, 1080, 0.35F, 1),
            new TFCMaterialExtended(Bismuth, 270, 0.35F, 1),
            new TFCMaterialExtended(Brass, 930, 0.35F, 1),
            new TFCMaterialExtended(Lead, 328, 0.22F, 1),
            new TFCMaterialExtended(Nickel, 1453, 0.48F, 1),
            new TFCMaterialExtended(RoseGold, 960, 0.35F, 1),
            new TFCMaterialExtended(Silver, 961, 0.48F, 1),
            new TFCMaterialExtended(Tin, 230,0.14F, 1),
            new TFCMaterialExtended(Zinc, 420, 0.21F, 1),
            new TFCMaterialExtended(SterlingSilver, 900, 0.35F, 1),
            new TFCMaterialExtended(Bronze, 950, 0.35F, 2),
            new TFCMaterialExtended(BlackBronze, 1070, 0.35F, 2),
            new TFCMaterialExtended(BismuthBronze, 985, 0.35F, 2),
            new TFCMaterialExtended(Gold, 1060, 0.6F, 2),
            new TFCMaterialExtended(PigIron, 0.35F, 3),
            new TFCMaterialExtended(HighCarbonSteel,  0.35F, 3),
            new TFCMaterialExtended(WroughtIron, 1535, 0.35F, 3),
            new TFCMaterialExtended(HighCarbonBlackSteel,  0.35F, 4),
            new TFCMaterialExtended(Steel, 1540, 0.35F, 4),
            new TFCMaterialExtended(WeakSteel,  0.35F, 4),
            new TFCMaterialExtended(Platinum, 1730, 0.35F, 5),
            new TFCMaterialExtended(BlackSteel, 1485, 0.35F, 5),
            new TFCMaterialExtended(WeakBlueSteel,  0.35F, 5),
            new TFCMaterialExtended(WeakRedSteel,  0.35F, 5),
            new TFCMaterialExtended(HighCarbonBlueSteel,  0.35F, 5),
            new TFCMaterialExtended(HighCarbonRedSteel,  0.35F, 5),
            new TFCMaterialExtended(BlueSteel, 1540, 0.35F, 6),
            new TFCMaterialExtended(RedSteel, 1540, 0.35F, 6)
    );

    public static int getMetalAmountFromOrePrefix(OrePrefix orePrefix) {
        return EXTENDED_OREPREFIXES.stream()
                .filter(s -> s.getOrePrefix() == orePrefix)
                .findFirst()
                .map(TFCOrePrefixExtended::getMetalUnits).orElse(0);
    }

    public static float getHeatCapacityFromMaterial(Material material) {
        return EXTENDED_MATERIALS.stream()
                .filter(s -> s.getMaterial() == material)
                .findFirst()
                .map(TFCMaterialExtended::getHeatCapacity).orElse(0F);
    }

    public static int getTierFromMaterial(Material material) {
        return EXTENDED_MATERIALS.stream()
                .filter(s -> s.getMaterial() == material)
                .findFirst()
                .map(TFCMaterialExtended::getMaterialTier).orElse(0);
    }

    public static Material getMaterialFromName(String name) {
        return EXTENDED_MATERIALS.stream()
                .filter(s -> Objects.equals(s.getMaterial().getUnlocalizedName(), name))
                .findFirst()
                .map(TFCMaterialExtended::getMaterial).orElse(null);
    }

    public static boolean isAtLeast(int value, int requiredInclusive)
    {
        return value >= requiredInclusive;
    }

    public static boolean isAtMost(int value, int requiredInclusive)
    {
        return value <= requiredInclusive;
    }
}
