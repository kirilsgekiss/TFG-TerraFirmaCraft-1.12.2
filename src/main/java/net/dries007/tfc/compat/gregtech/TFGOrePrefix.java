package net.dries007.tfc.compat.gregtech;

import gregtech.api.unification.ore.OrePrefix;

import static gregtech.api.GTValues.M;
import static gregtech.api.unification.ore.OrePrefix.Conditions.hasOreProperty;
import static gregtech.api.unification.ore.OrePrefix.Conditions.hasToolProperty;
import static gregtech.api.unification.ore.OrePrefix.Flags.ENABLE_UNIFICATION;

public class TFGOrePrefix {
    public static final OrePrefix toolHeadKnife = new OrePrefix("toolHeadKnife", M, null, TFGMaterialIconType.toolHeadKnife, ENABLE_UNIFICATION, hasToolProperty);
    public static final OrePrefix oreChunk = new OrePrefix("oreChunk", -1, null, TFGMaterialIconType.oreChunk, ENABLE_UNIFICATION, hasOreProperty);
}
