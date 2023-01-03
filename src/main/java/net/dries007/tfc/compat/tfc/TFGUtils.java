package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;

import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static net.dries007.tfc.compat.gregtech.materials.TFCMaterials.*;

public final class TFGUtils {

    public static final List<TFCOrePrefixExtended> TFC_OREPREFIX_REGISTRY = Arrays.asList
    (
            new TFCOrePrefixExtended(OrePrefix.ingot, 144, new String[]{"XXXX", "X  X", "X  X", "X  X", "XXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotDouble, 288),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotTriple, 432),
            new TFCOrePrefixExtended(TFCOrePrefix.ingotHex, 864),
            new TFCOrePrefixExtended(OrePrefix.stick, 72),
            new TFCOrePrefixExtended(OrePrefix.dust, 144),
            new TFCOrePrefixExtended(OrePrefix.nugget, 16),
            new TFCOrePrefixExtended(OrePrefix.plate, 144),
            new TFCOrePrefixExtended(OrePrefix.plateDouble, 288),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSword, 288, new String[]{"XXX  ", "XX   ", "X   X", "X  XX", " XXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadPickaxe, 432, new String[]{"XXXXX", "X   X", " XXX ", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadAxe, 432, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadShovel, 144, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHoe, 144, new String[]{"X   X", "X   X", "X   X", "X   X", "XX XX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSaw, 288, new String[]{"XXXXX", "     ", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHammer, 864, new String[]{"     ", "     ", "XX XX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSense, 432, new String[]{"XXXXX", "X    ", "    X", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadKnife, 144, new String[]{"XX X", "X  X", "X  X", "X  X", "X  X"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadPropick, 432, new String[]{"XXXXX", "    X", " XXX ", " XXXX", "XXXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadChisel, 288, new String[]{"X X", "X X", "X X", "X X", "X X"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadJavelin, 432, new String[]{"XX   ", "X    ", "     ", "X   X", "XX XX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.tuyere, 864)
    );

    public static final List<TFCMaterialExtended> TFC_MATERIAL_REGISTRY = Arrays.asList
    (
            new TFCMaterialExtended(Unknown),
            new TFCMaterialExtended(Copper, 1080),
            new TFCMaterialExtended(Bismuth, 270),
            new TFCMaterialExtended(Brass, 930),
            new TFCMaterialExtended(Lead, 328),
            new TFCMaterialExtended(Nickel, 1453),
            new TFCMaterialExtended(RoseGold, 960),
            new TFCMaterialExtended(Silver, 961),
            new TFCMaterialExtended(Tin, 230),
            new TFCMaterialExtended(Zinc, 420),
            new TFCMaterialExtended(SterlingSilver, 900),
            new TFCMaterialExtended(Bronze, 950),
            new TFCMaterialExtended(BlackBronze, 1070),
            new TFCMaterialExtended(BismuthBronze, 985),
            new TFCMaterialExtended(Gold, 1060),
            new TFCMaterialExtended(PigIron),
            new TFCMaterialExtended(HighCarbonSteel),
            new TFCMaterialExtended(WroughtIron, 1535),
            new TFCMaterialExtended(HighCarbonBlackSteel),
            new TFCMaterialExtended(Steel, 1540),
            new TFCMaterialExtended(WeakSteel),
            new TFCMaterialExtended(Platinum, 1730),
            new TFCMaterialExtended(BlackSteel, 1485),
            new TFCMaterialExtended(WeakBlueSteel),
            new TFCMaterialExtended(WeakRedSteel),
            new TFCMaterialExtended(HighCarbonBlueSteel),
            new TFCMaterialExtended(HighCarbonRedSteel),
            new TFCMaterialExtended(BlueSteel, 1540),
            new TFCMaterialExtended(RedSteel, 1540)
    );

    public static int getMetalAmountFromOrePrefix(OrePrefix orePrefix) {
        return TFC_OREPREFIX_REGISTRY.stream()
                .filter(s -> s.getOrePrefix() == orePrefix)
                .findFirst()
                .map(TFCOrePrefixExtended::getMetalUnits).orElse(0);
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
