/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen.vein;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.util.collections.WeightedCollection;
import net.dries007.tfc.world.classic.worldgen.WorldGenOreVeins;

import static net.dries007.tfc.Constants.GSON;

public enum VeinRegistry
{
    INSTANCE;

    private final WeightedCollection<VeinType> weightedVeinTypes = new WeightedCollection<>();
    private final Map<String, VeinType> veinTypeRegistry = new HashMap<>();

    @Nonnull
    public WeightedCollection<VeinType> getVeins()
    {
        return weightedVeinTypes;
    }

    @Nonnull
    public Set<String> keySet()
    {
        return veinTypeRegistry.keySet();
    }

    @Nullable
    public VeinType getVein(String name)
    {
        return veinTypeRegistry.get(name);
    }

}
