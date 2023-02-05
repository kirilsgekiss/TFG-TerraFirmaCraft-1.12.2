/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.mountains;

import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerMountainRangeInitTFC extends GenLayerTFC {
    public GenLayerMountainRangeInitTFC(long par1, GenLayer par3GenLayer) {
        super(par1);
        this.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int xCoord, int zCoord, int xSize, int zSize) {
        int[] parentCache = this.parent.getInts(xCoord, zCoord, xSize, zSize);
        int[] outCache = IntCache.getIntCache(xSize * zSize);

        for (int z = 0; z < zSize; ++z) {
            for (int x = 0; x < xSize; ++x) {
                /*this.initChunkSeed(x + xCoord, z + zCoord);
                int index = x + z * xSize;
                int id = parentCache[index];
                outCache[index] = (!isWaterBiome(id) && !isWetBiome(id) && !isFlatBiome(id)) ? 1 : 0;*/

                this.initChunkSeed((long) (x + xCoord), (long) (z + zCoord));
                outCache[x + z * xSize] = parentCache[x + z * xSize] > 0 ? this.nextInt(299999) + 2 : 0;
            }
        }
        return outCache;
    }
}
