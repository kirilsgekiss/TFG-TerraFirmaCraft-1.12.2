/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.genlayers.river;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.world.classic.genlayers.GenLayerTFC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.DimensionManager;

import java.util.Random;

public class GenLayerRiverTFC extends GenLayerTFC {
    public GenLayerRiverTFC(long par1, GenLayer par3GenLayer) {
        super(par1);
        super.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int xCoord, int zCoord, int xSize, int zSize) {
        World world = null;
        float rainfall = 0f;

        if (Minecraft.getMinecraft().world != null) {
            world = Minecraft.getMinecraft().world;

            long seed = world.getSeed();
            NoiseGeneratorPerlin noiseGen7 = new NoiseGeneratorPerlin(new Random(seed + 4), 4);
            float rainfallSpread = (float) ConfigTFC.General.WORLD.rainfallSpreadFactor;
            rainfall = MathHelper.clamp(250f + 250f * rainfallSpread * (float) noiseGen7.getValue(xCoord * 0.005, zCoord * 0.005), 0, 500) * 24;
        } else if (DimensionManager.getWorld(0) != null) {
            world = DimensionManager.getWorld(0);

            long seed = world.getSeed();
            NoiseGeneratorPerlin noiseGen7 = new NoiseGeneratorPerlin(new Random(seed + 4), 4);
            float rainfallSpread = (float) ConfigTFC.General.WORLD.rainfallSpreadFactor;
            rainfall = MathHelper.clamp(250f + 250f * rainfallSpread * (float) noiseGen7.getValue(xCoord * 0.005, zCoord * 0.005), 0, 500) * 24;
        }

        rainfall += 800;
        int bankWidth = Math.max(Math.min((int) (rainfall / 800), 6), 3);
        //int areaRadius = (MathHelper.clamp((int) Math.pow(1.05, rainfall / 10), 1, 16)) * Math.max(Math.min((int)(rainfall / 800), 5), 2);
        int areaRadius = Math.max(Math.min((int) (rainfall / 800), 8), 2);
        float actualRadius = areaRadius;
        boolean initActualRadius = false;
        int parentXCoord = xCoord - areaRadius - bankWidth;
        int parentZCoord = zCoord - areaRadius - bankWidth;
        int parentXSize = xSize + (2 * (areaRadius + bankWidth));
        int parentZSize = zSize + (2 * (areaRadius + bankWidth));

        int[] parentCache = this.parent.getInts(parentXCoord, parentZCoord, parentXSize, parentZSize);
        int[] outCache = IntCache.getIntCache((xSize + 0) * (zSize + 0));
        //int[] outCache = IntCache.getIntCache(xSize * zSize);

        for (int z = 0; z < zSize + 0; ++z) {
            for (int x = 0; x < xSize + 0; ++x) {
                int[][] areas = new int[(areaRadius * 2) + bankWidth][(areaRadius * 2) + bankWidth];
                boolean same = true;
                boolean bankSame = true;
                boolean initialVal = false;
                boolean killRiver = false;
                boolean riverSource = false;
                int initialValue = -1; //lakeID
                for (int rX = 0; rX < ((areaRadius + bankWidth) * 2) + 1 && !(killRiver && riverSource) && initialValue != 0; rX++) {
                    for (int rZ = 0; rZ < ((areaRadius + bankWidth) * 2) + 1 && !(killRiver && riverSource) && initialValue != 0; rZ++) {
                        int dist = ((rX - (areaRadius + bankWidth)) * (rX - (areaRadius + bankWidth))) + ((rZ - (areaRadius + bankWidth)) * (rZ - (areaRadius + bankWidth)));
                        int w = this.calcWidth(parentCache[x + rX + (z + rZ) * parentXSize]);
                        if (initialVal == false) {
                            initialValue = this.calcWidth(parentCache[x + areaRadius + bankWidth + ((z + areaRadius + bankWidth) * parentXSize)]);
                            initialVal = true;
                            continue;
                        }
                        if ((initialValue == 0 || w == 0)) {
                            killRiver = true;
                            if (!same) {
                                riverSource = true;
                            }
                        }
                        if (same && dist <= actualRadius * actualRadius && !(initialValue == 0 || w == 0)) {
                            same = w == initialValue;
                            if (!initActualRadius && !same) {
                                float riverVals = w + initialValue;
                                actualRadius -= (riverVals - 3) / 2f;
                                initActualRadius = true;
                                if (dist >= actualRadius * actualRadius) {
                                    same = true;
                                }
                            }
                            if (killRiver) {
                                riverSource = true;
                            }
                        }
                        if (bankSame && dist <= (actualRadius + bankWidth) * (actualRadius + bankWidth) && !(initialValue == 0 || w == 0)) {
                            bankSame = w == initialValue;
                            if (!initActualRadius && !bankSame) {
                                float riverVals = w + initialValue;
                                actualRadius -= (riverVals - 3) / 2f;
                                initActualRadius = true;
                                if (dist >= (actualRadius + bankWidth) * (actualRadius + bankWidth)) {
                                    bankSame = true;
                                }
                            }
                        }

                    }
                }
                if ((same && bankSame) || (killRiver && !riverSource)) {
                    outCache[x + z * xSize] = parentCache[(x + areaRadius) + (z + areaRadius) * (areaRadius * 2 + xSize)];
                    //outCache[x + z * xSize] = 0; //plainsID
                } else if (riverSource) {
                    outCache[x + z * xSize] = lakeID;
                } else if (same && !bankSame) {
                    outCache[x + z * xSize] = riverBankID;
                } else {
                    outCache[x + z * xSize] = riverID;
                }
            }
        }
        return outCache;
    }

    private int calcWidth(int i) {
        return i; // Spits back 2 for even numbers >= 2 and 3 for odd numbers.
    }
}
