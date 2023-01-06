/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.ridge;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;

public class GenLayerRidgeMixTFC extends GenLayerTFC
{
    private GenLayer biomePatternGeneratorChain;
    private GenLayer ridgePatternGeneratorChain;

    public GenLayerRidgeMixTFC(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer)
    {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.ridgePatternGeneratorChain = par4GenLayer;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    @Override
    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.ridgePatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] biomeIds = this.biomePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] ridgeValues = this.ridgePatternGeneratorChain.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            Biome biome = Biome.getBiome(biomeIds[i]);
            if (!isWaterBiome(biomeIds[i]) && !isWetBiome(biomeIds[i]) && biomeIds[i] != fieldsID && biomeIds[i] != meadowsID && biome != null)
            {
                if (ridgeValues[i] == faultLineID)
                {
                    //out[i] = faultLineID;
                    out[i] = ridgeValues[i] & 255;
                }
                else
                {
                    out[i] = biomeIds[i];
                }
            }
            else
            {
                out[i] = biomeIds[i];
            }
        }
        return out;
    }
}
