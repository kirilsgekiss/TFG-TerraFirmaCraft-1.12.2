package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.ore.OrePrefix;

public class TFCOrePrefixExtended {

    private final OrePrefix orePrefix;
    private final int metalUnits;
    private final String[] knappingRecipe;
    private final boolean hasMold;

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits) {
        this.orePrefix = orePrefix;
        this.metalUnits = metalUnits;
        this.hasMold = false;
        this.knappingRecipe = new String[0];
    }

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits, boolean hasMold, String[] knappingRecipe) {
        this.orePrefix = orePrefix;
        this.metalUnits = metalUnits;
        this.hasMold = hasMold;
        this.knappingRecipe = knappingRecipe;
    }

    public OrePrefix getOrePrefix() {
        return orePrefix;
    }

    public String[] getKnappingRecipe() {
        return knappingRecipe;
    }

    public int getMetalUnits() {
        return metalUnits;
    }

    public boolean isHasMold() {
        return hasMold;
    }
}
