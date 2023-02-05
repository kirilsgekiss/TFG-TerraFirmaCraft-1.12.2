/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.biome;

import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerMountainRangeEdge extends GenLayerTFC {
    public GenLayerMountainRangeEdge(long seed, GenLayerTFC parent) {
        super(seed);
        this.parent = parent;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    @Override
    public int[] getInts(int xCoord, int zCoord, int xSize, int zSize) {
        int areaRadius = 5;
        int parentXCoord = xCoord - areaRadius;
        int parentZCoord = zCoord - areaRadius;
        int parentXSize = xSize + (2 * areaRadius);
        int parentZSize = zSize + (2 * areaRadius);
        int[] parentCache = this.parent.getInts(parentXCoord, parentZCoord, parentXSize, parentZSize);
        int[] outCache = IntCache.getIntCache(xSize * zSize);

        for (int z = 0; z < zSize + 0; ++z) {
            for (int x = 0; x < xSize + 0; ++x) {
                int[][] areas = new int[(areaRadius * 2) + 1][(areaRadius * 2) + 1];
                boolean same = true;
                boolean initialVal = false;
                int initialValue = parentCache[(x + areaRadius) + (z + areaRadius) * (areaRadius * 2 + xSize)];

                if (initialValue != mountainRangeID) {
                    outCache[x + z * xSize] = parentCache[(x + areaRadius) + (z + areaRadius) * (areaRadius * 2 + xSize)];
                    continue;
                }
                for (int rX = 0; rX < (areaRadius * 2) + 1 && same; rX++) {
                    for (int rZ = 0; rZ < (areaRadius * 2) + 1 && same; rZ++) {
                        if (Math.abs(rX - areaRadius) + Math.abs(rZ - areaRadius) <= areaRadius) {
                            same = !isWetBiome(parentCache[x + rX + (z + rZ) * parentXSize]) &&
                                    !isBeachBiome(parentCache[x + rX + (z + rZ) * parentXSize]);
                        }
                    }
                }
                if (same) {
                    outCache[x + z * xSize] = parentCache[(x + areaRadius) + (z + areaRadius) * (areaRadius * 2 + xSize)];
                } else {
                    outCache[x + z * xSize] = mountainRangeEdgeID;
                }
            }
        }
        return outCache;
    }

    /*@Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; ++z)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(x + areaX), (long)(z + areaY));
                //The biome we're going to attempt to put a bufferzone beside
                int biomeId = biomeIds[x + 1 + (z + 1) * (areaWidth + 2)];

                if (biomeId == mountainRangeID)
                {
                    setBiomeWithAdjacent(biomeIds, out, x, z, areaWidth, biomeId, mountainRangeEdgeID, LAND_BIOME);
                }
            }
        }
        return out;
    }

    private void setBiomeWithAdjacent(int[] biomeIds, int[] out, int x, int z, int areaWidth, int biomeId, int beachId, Predicate<Integer> adjacentPredicate)
    {
        int biomeNorth = biomeIds[x + 1 + (z + 1 - 1) * (areaWidth + 2)];
        int biomeEast = biomeIds[x + 1 + 1 + (z + 1) * (areaWidth + 2)];
        int biomeWest = biomeIds[x + 1 - 1 + (z + 1) * (areaWidth + 2)];
        int biomeSouth = biomeIds[x + 1 + (z + 1 + 1) * (areaWidth + 2)];

        if (adjacentPredicate.apply(biomeNorth) || adjacentPredicate.apply(biomeEast) || adjacentPredicate.apply(biomeWest) || adjacentPredicate.apply(biomeSouth))
        {
            out[x + z * areaWidth] = beachId;
        }
        else
        {
            out[x + z * areaWidth] = biomeId;
        }
    }

    private final Predicate<Integer> LAND_BIOME = new Predicate<Integer>()
    {
        @Override
        public boolean apply(Integer input) 
        {
            return isLandBiome(input);
        }
    };*/
}
