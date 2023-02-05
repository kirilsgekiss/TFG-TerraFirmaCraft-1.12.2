/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.mountains;

import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerMountainRangeMixTFC extends GenLayerTFC {
    private GenLayer biomePatternGeneratorChain;
    private GenLayer mountainPatternGeneratorChain;

    public GenLayerMountainRangeMixTFC(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.mountainPatternGeneratorChain = par4GenLayer;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    @Override
    public void initWorldGenSeed(long par1) {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.mountainPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomeIds = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] mountainValues = this.mountainPatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i) {
            Biome biome = Biome.getBiome(biomeIds[i]);
            if (!isWaterBiome(biomeIds[i]) && !isWetBiome(biomeIds[i]) && !isFlatBiome(biomeIds[i]) && biome != null) {
                if (mountainValues[i] == mountainRangeID) {
                    //out[i] = mountainRangeID;
                    out[i] = mountainValues[i] & 255;
                } else {
                    out[i] = biomeIds[i];
                }
            } else {
                out[i] = biomeIds[i];
            }
        }
        return out;
    }
}
