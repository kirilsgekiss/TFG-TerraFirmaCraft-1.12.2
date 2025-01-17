package net.dries007.tfc.objects.blocks.plants.BlockPlant;

import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.util.OreDictionaryHelper;

import java.util.HashMap;
import java.util.Map;

public class TFCBlockPlantDummy1 extends TFCBlockPlant {
    private static final Map<Plant, TFCBlockPlantDummy1> MAP = new HashMap<>();

    public static TFCBlockPlantDummy1 get(Plant plant) {
        return MAP.get(plant);
    }

    public TFCBlockPlantDummy1(Plant plant) {
        super(plant);
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");

        plant.getOreDictName().ifPresent(name -> OreDictionaryHelper.register(this, name));
    }
}
