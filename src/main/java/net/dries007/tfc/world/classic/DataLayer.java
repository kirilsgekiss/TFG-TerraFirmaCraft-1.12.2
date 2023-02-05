/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic;

import net.dries007.tfc.objects.blocks.stone.TFCBlockRockVariant;

/**
 * Todo: Get rid. PH is already obselete / Siesmic and drainage can be done much easier similar to rainfall / temperature
 */
@SuppressWarnings("WeakerAccess")
public final class DataLayer {
    public static final DataLayer ERROR = new DataLayer(-1, null, "ERROR", Integer.MIN_VALUE, Float.NaN);
    public static DataLayer[] LAYERS = new DataLayer[256];

    public static final DataLayer SEISMIC_STABLE = newIntDataLayer(110, "Stable", 0);
    public static final DataLayer SEISMIC_UNSTABLE = newIntDataLayer(111, "Unstable", 1);

    public static final DataLayer DRAINAGE_NONE = newIntDataLayer(120, "None", 0);
    public static final DataLayer DRAINAGE_VERY_POOR = newIntDataLayer(121, "Very Poor", 1);
    public static final DataLayer DRAINAGE_POOR = newIntDataLayer(122, "Poor", 2);
    public static final DataLayer DRAINAGE_NORMAL = newIntDataLayer(123, "Normal", 3);
    public static final DataLayer DRAINAGE_GOOD = newIntDataLayer(124, "Good", 4);
    public static final DataLayer DRAINAGE_VERY_GOOD = newIntDataLayer(125, "Very Good", 5);

    public static final DataLayer PH_ACID_HIGH = newIntDataLayer(130, "High Acidity", 0);
    public static final DataLayer PH_ACID_LOW = newIntDataLayer(131, "Low acidity", 1);
    public static final DataLayer PH_NEUTRAL = newIntDataLayer(132, "Neutral", 2);
    public static final DataLayer PH_ALKALINE_LOW = newIntDataLayer(133, "Low Alkalinity", 3);
    public static final DataLayer PH_ALKALINE_HIGH = newIntDataLayer(134, "High Alkalinity", 4);
    public static final DataLayer PH_ALKALINE_VERY_HIGH = newIntDataLayer(135, "Very High Alkalinity", 5);

	/*public static final DataLayer AMERICAS = newIntDataLayer(248, "Americas", 0);
	public static final DataLayer EUROPE = newIntDataLayer(249, "Europe", 1);
	public static final DataLayer AFRICA = newIntDataLayer(250, "Africa", 2);
	public static final DataLayer ASIA = newIntDataLayer(251, "Asia", 3);

	public static final DataLayer RIVER0 = newIntDataLayer(253, "River0", 1);
	public static final DataLayer RIVER1 = newIntDataLayer(254, "River1", 2);
	public static final DataLayer RIVER2 = newIntDataLayer(255, "River2", 3);

	public static final int[] REGION_ARRAY = new int[] {AMERICAS.valueInt, AFRICA.valueInt, EUROPE.valueInt, ASIA.valueInt};
	public static final int[] RIVER_ARRAY = new int[] {RIVER0.valueInt, RIVER1.valueInt, RIVER2.valueInt};*/

    public static DataLayer get(int i) {
        if (LAYERS[i] == null) throw new IllegalArgumentException("Layer " + i + " not used.");
        return LAYERS[i];
    }

    private static DataLayer newIntDataLayer(int i, String name, int value) {
        if (LAYERS[i] != null) throw new IllegalArgumentException("Layer " + i + " already in use.");
        return LAYERS[i] = new DataLayer(i, null, name, value, Float.NaN);
    }

    public final int layerID;
    public final TFCBlockRockVariant block;
    public final String name;
    public final int valueInt;
    public final float valueFloat;

    private DataLayer(int i, TFCBlockRockVariant block, String name, int valueInt, float valueFloat) {
        this.layerID = i;
        this.block = block;
        this.name = name;
        this.valueInt = valueInt;
        this.valueFloat = valueFloat;
    }
}
