/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.world.classic.ChunkGenTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

public class WorldGenLooseRocks implements IWorldGenerator
{
    protected double factor;

    public WorldGenLooseRocks()
    {
        factor = 1;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (chunkGenerator instanceof ChunkGenTFC && world.provider.getDimension() == 0)
        {
            final BlockPos chunkBlockPos = new BlockPos(chunkX << 4, 0, chunkZ << 4);
            final ChunkDataTFC baseChunkData = ChunkDataTFC.get(world, chunkBlockPos);

            // Get the proper list of veins
            int xoff = chunkX * 16 + 8;
            int zoff = chunkZ * 16 + 8;

            for (int i = 0; i < ConfigTFC.General.WORLD.looseRocksFrequency * factor; i++)
            {
                BlockPos pos = new BlockPos(
                    xoff + random.nextInt(16),
                    0,
                    zoff + random.nextInt(16)
                );
                Rock rock = baseChunkData.getRock1(pos);
                generateRock(world, pos.up(world.getTopSolidOrLiquidBlock(pos).getY()), rock);
            }
        }
    }

    protected void generateRock(World world, BlockPos pos, Rock rock)
    {
        // Use air, so it doesn't replace other replaceable world gen
        // This matches the check in BlockPlacedItemFlat for if the block can stay
        // Also, only add on soil, since this is called by the world regen handler later
        if (world.isAirBlock(pos) && world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP) && BlocksTFC.isSoil(world.getBlockState(pos.down())))
        {
            world.setBlockState(pos, BlocksTFC.PLACED_ITEM_FLAT.getDefaultState(), 2);
            TEPlacedItemFlat tile = Helpers.getTE(world, pos, TEPlacedItemFlat.class);
            if (tile != null)
            {
                ItemStack stack = ItemStack.EMPTY;
                if (stack.isEmpty())
                {
                    if (ConfigTFC.General.WORLD.enableLooseRocks)
                    {
                        stack = ItemRock.get(rock, 1);
                    }
                }
                if (!stack.isEmpty())
                {
                    tile.setStack(stack);
                }
            }
        }
    }
}
