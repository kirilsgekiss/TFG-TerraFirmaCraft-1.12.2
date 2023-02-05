/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen;

import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.stone.BlockRockVariant;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockRaw;
import net.dries007.tfc.objects.blocks.stone.TFCBlockRockSpike;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenSpikes implements IWorldGenerator {
    private final boolean ceiling; //Is this a stalactite generator?
    private final int rarity;

    public WorldGenSpikes(boolean ceiling, int rarity) {
        this.ceiling = ceiling;
        this.rarity = rarity;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        for (int k5 = 0; k5 < rarity; ++k5) {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = random.nextInt(WorldTypeTFC.SEALEVEL - 50) + 30;
            BlockPos basePos = new BlockPos(chunkX << 4, y, chunkZ << 4).add(x, 0, z);
            BlockPos topPos = ceiling ? basePos.down() : basePos.up();
            BlockPos stoneAttach = ceiling ? basePos.up() : basePos.down();
            BlockPos freeSpace = ceiling ? topPos.down() : topPos.up();
            if (!BlocksTFC.isRawStone(world.getBlockState(stoneAttach)) || !world.isAirBlock(basePos) || !world.isAirBlock(topPos) || !world.isAirBlock(freeSpace)) {
                continue;
            }
            boolean canPlace = true;
            for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                if (!world.isAirBlock(basePos.offset(facing)) || !world.isAirBlock(topPos.offset(facing))) {
                    canPlace = false;
                    break;
                }
            }
            if (canPlace) {
                TFCBlockRockRaw rockBlock = (TFCBlockRockRaw) world.getBlockState(stoneAttach).getBlock();
                IBlockState baseState = BlockRockVariant.get(rockBlock.getRock(), Type.SPIKE).getDefaultState().withProperty(TFCBlockRockSpike.BASE, true).withProperty(TFCBlockRockSpike.CEILING, ceiling);
                IBlockState topState = BlockRockVariant.get(rockBlock.getRock(), Type.SPIKE).getDefaultState().withProperty(TFCBlockRockSpike.BASE, false).withProperty(TFCBlockRockSpike.CEILING, ceiling);
                world.setBlockState(basePos, baseState, 2);
                world.setBlockState(topPos, topState, 2);
            }
        }
    }
}
