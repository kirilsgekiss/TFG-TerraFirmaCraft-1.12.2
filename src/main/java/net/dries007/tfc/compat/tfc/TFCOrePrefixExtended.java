package net.dries007.tfc.compat.tfc;

import gregtech.api.unification.ore.OrePrefix;

public class TFCOrePrefixExtended {

    private final OrePrefix orePrefix;
    private final int metalUnits;
    private String[] knappingRecipe;
    private String[] stoneKnappingRecipe;
    private boolean hasMold;
    private boolean hasStoneKnappingRecipe;

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits) {
        this.orePrefix = orePrefix;
        this.metalUnits = metalUnits;
        this.hasMold = false;
        this.hasStoneKnappingRecipe = false;
        this.knappingRecipe = new String[0];
        this.stoneKnappingRecipe = new String[0];
    }

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits, String[] knappingRecipe) {
        this(orePrefix, metalUnits);
        this.hasMold = true;
        this.hasStoneKnappingRecipe = false;
        this.knappingRecipe = knappingRecipe;
        this.stoneKnappingRecipe = new String[0];
    }

    public TFCOrePrefixExtended(OrePrefix orePrefix, int metalUnits, String[] knappingRecipe, String[] stoneKnappingRecipe) {
        this(orePrefix, metalUnits);
        this.hasMold = true;
        this.hasStoneKnappingRecipe = true;
        this.knappingRecipe = knappingRecipe;
        this.stoneKnappingRecipe = stoneKnappingRecipe;
    }

    public OrePrefix getOrePrefix() {
        return orePrefix;
    }

    public String[] getKnappingRecipe() {
        return knappingRecipe;
    }

    public String[] getStoneKnappingRecipe() {
        return stoneKnappingRecipe;
    }

    public int getMetalUnits() {
        return metalUnits;
    }

    public boolean isHasMold() {
        return hasMold;
    }

    public boolean isHasStoneKnappingRecipe() {
        return hasStoneKnappingRecipe;
    }
}
