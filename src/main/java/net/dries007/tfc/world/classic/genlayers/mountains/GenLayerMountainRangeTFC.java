/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.mountains;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.DimensionManager;

import java.util.Random;

public class GenLayerMountainRangeTFC extends GenLayerTFC {
    public GenLayerMountainRangeTFC(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int x, int z, int sizeX, int sizeZ) {
        World world = null;
        float rainfall = 0f;

        if (Minecraft.getMinecraft().world != null) {
            world = Minecraft.getMinecraft().world;

            long seed = world.getSeed();
            NoiseGeneratorPerlin noiseGen7 = new NoiseGeneratorPerlin(new Random(seed + 4), 4);
            float rainfallSpread = (float) TFCConfig.General.WORLD.rainfallSpreadFactor;
            rainfall = MathHelper.clamp(250f + 250f * rainfallSpread * (float) noiseGen7.getValue(x * 0.005, z * 0.005), 0, 500);
        } else if (DimensionManager.getWorld(0) != null) {
            world = DimensionManager.getWorld(0);

            long seed = world.getSeed();
            NoiseGeneratorPerlin noiseGen7 = new NoiseGeneratorPerlin(new Random(seed + 4), 4);
            float rainfallSpread = (float) TFCConfig.General.WORLD.rainfallSpreadFactor;
            rainfall = MathHelper.clamp(250f + 250f * rainfallSpread * (float) noiseGen7.getValue(x * 0.005, z * 0.005), 0, 500);
        }
        //else if (world == null) return null;

        rainfall += 80;
        int bankWidth = ((this.nextInt(2) + 1) * 5);
        int areaRadius = ((this.nextInt(2) + 1) * 15) * Math.max(Math.min((int) (rainfall / 80), 10), 4);
        int parentXCoord = x - areaRadius - bankWidth;
        int parentZCoord = z - areaRadius - bankWidth;
        int parentXSize = sizeX + (2 * (areaRadius + bankWidth));
        int parentZSize = sizeZ + (2 * (areaRadius + bankWidth));

        int[] ints = this.parent.getInts(parentXCoord, parentZCoord, parentXSize, parentZSize);
        int[] out = IntCache.getIntCache(sizeX * sizeZ);

        for (int zz = 0; zz < sizeZ; ++zz) {
            for (int xx = 0; xx < sizeX; ++xx) {
                int k2 = this.calcWidth(ints[xx + 0 + (zz + 1) * parentXSize]);
                int l2 = this.calcWidth(ints[xx + 2 + (zz + 1) * parentXSize]);
                int i3 = this.calcWidth(ints[xx + 1 + (zz + 0) * parentXSize]);
                int j3 = this.calcWidth(ints[xx + 1 + (zz + 2) * parentXSize]);
                int k3 = this.calcWidth(ints[xx + 1 + (zz + 1) * parentXSize]);

                if (k3 == k2 && k3 == i3 && k3 == l2 && k3 == j3) {
                    out[xx + zz * sizeX] = plainsID;
                } else {
                    out[xx + zz * sizeX] = mountainRangeID;
                }
            }
        }

        return out;
    }

    private int calcWidth(int i) {
        return i >= 2 ? 2 + (i & 1) : i; // Spits back 2 for even numbers >= 2 and 3 for odd numbers.
    }
}
