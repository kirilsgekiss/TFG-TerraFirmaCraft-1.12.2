/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen;

import java.util.Random;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockVariant;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

import static net.dries007.tfc.api.types.Rock.Type.SAND;

@ParametersAreNonnullByDefault
public class WorldGenSandTFC extends WorldGenerator
{
    private final int radius;

    public WorldGenSandTFC(int radius)
    {
        this.radius = radius;
    }

    @Override
    public boolean generate(World world, Random rng, BlockPos pos)
    {
        if (TFCBlocks.isWater(world.getBlockState(pos))) return false;

        final TFCBlockRockVariant sand = TFCBlockRockVariant.get(ChunkDataTFC.getRock1(world, pos), SAND);
        final int rnd = rng.nextInt(this.radius - 2) + 2;

        for (int x = -rnd; x <= rnd; x++)
        {
            for (int z = -rnd; z <= rnd; z++)
            {
                if (x * x + z * z > rnd * rnd) continue;
                for (int y = -2; y <= 2; y++)
                {
                    final IBlockState s = world.getBlockState(pos.add(x, y, z));
                    if (TFCBlocks.isSoil(s) || TFCBlocks.isSand(s))
                        world.setBlockState(pos.add(x, y, z), sand.getDefaultState(), 2);
                }
            }
        }

        return true;
    }
}
