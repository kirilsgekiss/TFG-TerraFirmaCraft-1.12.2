package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.ore.OrePrefix;

public class TFCOrePrefixExtended {

    private final OrePrefix orePrefix;
    private final int metalUnits;
    private String[] knappingRecipe;
    private boolean hasMold;

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits) {
        this.orePrefix = orePrefix;
        this.metalUnits = metalUnits;
        this.hasMold = false;
        this.knappingRecipe = new String[0];
    }

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits, String[] knappingRecipe) {
        this(orePrefix, metalUnits);
        this.hasMold = true;
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
