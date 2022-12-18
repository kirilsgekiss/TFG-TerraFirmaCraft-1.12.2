package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.TFCOrePrefix;

import java.util.Arrays;
import java.util.List;

import static gregtech.api.unification.material.Materials.*;
import static net.dries007.tfc.compat.gregtech.TFCMaterials.*;

public final class TFGUtils {

    public static final List<TFCOrePrefixExtended> EXTENDED_OREPREFIXES = Arrays.asList
    (
            new TFCOrePrefixExtended(OrePrefix.ingot, 144, true, new String[]{"XXXX", "X  X", "X  X", "X  X", "XXXX"}),
            new TFCOrePrefixExtended(OrePrefix.dust, 144),
            new TFCOrePrefixExtended(OrePrefix.nugget, 16),
            new TFCOrePrefixExtended(OrePrefix.plate, 144),
            new TFCOrePrefixExtended(OrePrefix.plateDouble, 288),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSword, 288, true, new String[]{"XXX  ", "XX   ", "X   X", "X  XX", " XXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadPickaxe, 288, true, new String[]{"XXXXX", "X   X", " XXX ", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadAxe, 288, true, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadShovel, 288, true, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHoe, 288, true, new String[]{"X   X", "X   X", "X   X", "X   X", "XX XX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSaw, 288, true, new String[]{"XXXXX", "     ", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadHammer, 288, true, new String[]{"     ", "     ", "XX XX", "XXXXX"}),
            new TFCOrePrefixExtended(OrePrefix.toolHeadSense, 288, true, new String[]{"XXXXX", "X    ", "    X", "  XXX", "XXXXX"}),
            new TFCOrePrefixExtended(TFCOrePrefix.toolHeadKnife, 288, true, new String[]{"XX X", "X  X", "X  X", "X  X", "X  X"})
    );

    public static final List<TFCMaterialExtended> EXTENDED_MATERIALS = Arrays.asList
    (
            new TFCMaterialExtended(Unknown, false, 0.5F, 1),
            new TFCMaterialExtended(Copper, true, 1080, 0.35F, 1),
            new TFCMaterialExtended(Bismuth,false, 270, 0.35F, 1),
            new TFCMaterialExtended(Brass, false, 930, 0.35F, 1),
            new TFCMaterialExtended(Lead, false, 328, 0.22F, 1),
            new TFCMaterialExtended(Nickel, false, 1453, 0.48F, 1),
            new TFCMaterialExtended(RoseGold, false, 960, 0.35F, 1),
            new TFCMaterialExtended(Silver, false, 961, 0.48F, 1),
            new TFCMaterialExtended(Tin, false, 230,0.14F, 1),
            new TFCMaterialExtended(Zinc, false, 420, 0.21F, 1),
            new TFCMaterialExtended(SterlingSilver, false, 900, 0.35F,1),
            new TFCMaterialExtended(Bronze, true, 950, 0.35F,2),
            new TFCMaterialExtended(BlackBronze, true, 1070, 0.35F,2),
            new TFCMaterialExtended(BismuthBronze, true, 985, 0.35F,2),
            new TFCMaterialExtended(Gold, false, 1060, 0.6F,2),
            new TFCMaterialExtended(PigIron, false, 0.35F,3),
            new TFCMaterialExtended(HighCarbonSteel, false, 0.35F,3),
            new TFCMaterialExtended(WroughtIron, true, 1535, 0.35F,3),
            new TFCMaterialExtended(HighCarbonBlackSteel, false, 0.35F,4),
            new TFCMaterialExtended(Steel, true, 1540, 0.35F,4),
            new TFCMaterialExtended(WeakSteel, false, 0.35F,4),
            new TFCMaterialExtended(Platinum, false, 1730, 0.35F,5),
            new TFCMaterialExtended(BlackSteel, true, 1485, 0.35F,5),
            new TFCMaterialExtended(WeakBlueSteel, false, 0.35F,5),
            new TFCMaterialExtended(WeakRedSteel, false, 0.35F,5),
            new TFCMaterialExtended(HighCarbonBlueSteel, false, 0.35F,5),
            new TFCMaterialExtended(HighCarbonRedSteel, false, 0.35F,5),
            new TFCMaterialExtended(BlueSteel, true, 1540, 0.35F,6),
            new TFCMaterialExtended(RedSteel, true, 1540, 0.35F,6)
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
}
