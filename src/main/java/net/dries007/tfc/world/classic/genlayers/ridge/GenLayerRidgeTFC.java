/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.ridge;

import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRidgeTFC extends GenLayerTFC {
    public GenLayerRidgeTFC(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int ridgeWidth = (this.nextInt(2) + 1) * 10;
        int ridgeRadius = (this.nextInt(2) + 1) * 25;

        int parentXCoord = areaX - ridgeRadius - ridgeWidth;
        int parentZCoord = areaY - ridgeRadius - ridgeWidth;
        int parentXSize = areaWidth + (2 * (ridgeRadius + ridgeWidth));
        int parentZSize = areaHeight + (2 * (ridgeRadius + ridgeWidth));
        int[] parentVals = this.parent.getInts(parentXCoord, parentZCoord, parentXSize, parentZSize);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y) {
            for (int x = 0; x < areaWidth; ++x) {
                int westVal = this.calcWidth(parentVals[x + 0 + (y + 1) * parentXSize]);
                int eastVal = this.calcWidth(parentVals[x + 2 + (y + 1) * parentXSize]);
                int northVal = this.calcWidth(parentVals[x + 1 + (y + 0) * parentXSize]);
                int southVal = this.calcWidth(parentVals[x + 1 + (y + 2) * parentXSize]);
                int val = this.calcWidth(parentVals[x + 1 + (y + 1) * parentXSize]);

                if (val == westVal && val == northVal && val == eastVal && val == southVal) {
                    out[x + y * areaWidth] = plainsID;
                } else {
                    out[x + y * areaWidth] = faultLineID;
                }
            }
        }

        return out;
    }

    private int calcWidth(int a) {
        return a >= 2 ? 2 + (a & 1) : a;
    }
}
