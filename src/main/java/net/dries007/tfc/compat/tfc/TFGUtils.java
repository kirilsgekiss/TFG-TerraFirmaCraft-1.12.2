package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.ore.OrePrefix;
import net.dries007.tfc.compat.gregtech.oreprefix.TFCOrePrefix;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static gregtech.api.unification.material.Materials.*;
import static net.dries007.tfc.compat.gregtech.materials.TFCMaterials.*;

public final class TFGUtils {

    private final static List<Material> ALLOWED_MATERIALS_FOR_GEMS = Arrays.asList(
            Amethyst,
            Diamond,
            Emerald,
            GarnetRed,
            Opal,
            Ruby,
            Sapphire,
            Topaz,
            GreenSapphire,
            BlueTopaz,
            Cinnabar,
            Olivine
    );

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
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadSword, 288, new String[]{"XXX  ", "XX   ", "X   X", "X  XX", " XXXX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadPickaxe, 432, new String[]{"XXXXX", "X   X", " XXX ", "XXXXX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadAxe, 432, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}, new String[]{" X   ", "XXXX ", "XXXXX", "XXXX ", " X   "}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadShovel, 144, new String[]{"X XXX", "    X", "     ", "    X", "X XXX"}, new String[]{"XXX", "XXX", "XXX", "XXX", " X "}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadHoe, 144, new String[]{"X   X", "X   X", "X   X", "X   X", "XX XX"}, new String[]{"XXXXX", "   XX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadSaw, 288, new String[]{"XXXXX", "     ", "  XXX", "XXXXX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadHammer, 864, new String[]{"     ", "     ", "XX XX", "XXXXX"}, new String[]{"XXXXX", "XXXXX", "  X  "}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadSense, 432, new String[]{"XXXXX", "X    ", "    X", "  XXX", "XXXXX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadKnife, 144, new String[]{"XX X", "X  X", "X  X", "X  X", "X  X"}, new String[]{"X ", "XX", "XX", "XX", "XX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadPropick, 432, new String[]{"XXXXX", "    X", " XXX ", " XXXX", "XXXXX"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadChisel, 288, new String[]{"X X", "X X", "X X", "X X", "X X"}),
                    new TFCOrePrefixExtended(TFCOrePrefix.toolHeadJavelin, 432, new String[]{"XX   ", "X    ", "     ", "X   X", "XX XX"}, new String[]{"XXX  ", "XXXX ", "XXXXX", " XXX ", "  X  "})
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

    public static boolean isAtLeast(int value, int requiredInclusive) {
        return value >= requiredInclusive;
    }

    public static boolean isAtMost(int value, int requiredInclusive) {
        return value <= requiredInclusive;
    }


    public static ItemStack getRandomGem() {
        int lower_bound = 0;
        int upper_bound = 100;

        int random_value = new Random().nextInt(upper_bound - lower_bound) + lower_bound;

        if (random_value <= 5) {
            return OreDictUnifier.get(OrePrefix.gemExquisite, ALLOWED_MATERIALS_FOR_GEMS.get(new Random().nextInt(ALLOWED_MATERIALS_FOR_GEMS.size())));
        } else if (random_value < 20) {
            return OreDictUnifier.get(OrePrefix.gemFlawless, ALLOWED_MATERIALS_FOR_GEMS.get(new Random().nextInt(ALLOWED_MATERIALS_FOR_GEMS.size())));
        } else if (random_value < 50) {
            return OreDictUnifier.get(OrePrefix.gemFlawed, ALLOWED_MATERIALS_FOR_GEMS.get(new Random().nextInt(ALLOWED_MATERIALS_FOR_GEMS.size())));
        } else {
            return OreDictUnifier.get(OrePrefix.gemChipped, ALLOWED_MATERIALS_FOR_GEMS.get(new Random().nextInt(ALLOWED_MATERIALS_FOR_GEMS.size())));
        }
    }
}
