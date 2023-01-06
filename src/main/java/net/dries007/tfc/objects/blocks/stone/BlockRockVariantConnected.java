/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.stone;

import java.util.Random;
import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.api.types.Rock.*;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.blocks.BlockPeat;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.plants.BlockShortGrassTFC;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import tfcflorae.objects.blocks.BlocksTFCF;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockRockVariantConnected extends BlockRockVariantFallable
{
    // Used for connected textures only.
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");

    @SuppressWarnings("ConstantConditions")
    public static void spreadGrass(World world, BlockPos pos, IBlockState us, Random rand) {
        BlockPos upPos = pos.up();
        IBlockState stateUp = world.getBlockState(upPos);
        Block usBlock = us.getBlock();

        if ((world.getLightFromNeighbors(upPos) < 4 && stateUp.getLightOpacity(world, upPos) > 2) || stateUp.getMaterial().isLiquid())
        {

            if (usBlock instanceof BlockRockVariant)
            {
                BlockRockVariant block = ((BlockRockVariant) usBlock);
                world.setBlockState(pos, block.getVariant(block.getType().getNonGrassVersion()).getDefaultState());
            }
        }
        else
        {
            if (world.getLightFromNeighbors(upPos) < 9 || stateUp.getMaterial().isLiquid()) return;

            for (int i = 0; i < 4; ++i)
            {
                BlockPos target = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                if (world.isOutsideBuildHeight(target) || !world.isBlockLoaded(target)) return;
                BlockPos up = target.add(0, 1, 0);

                IBlockState current = world.getBlockState(target);
                if (!BlocksTFC.isSoil(current) || BlocksTFC.isGrass(current)) continue;
                if (world.getLightFromNeighbors(up) < 4 || world.getBlockState(up).getLightOpacity(world, up) > 3 || world.getBlockState(up).getMaterial().isLiquid())
                    continue;

                // TFC Grass
                if (current.getBlock() instanceof BlockRockVariant && usBlock instanceof BlockRockVariant) {
                    Type spreader = Type.GRASS;

                    if (((BlockRockVariant) usBlock).getType() == Type.DRY_GRASS)
                        spreader = Type.DRY_GRASS;
                    else if (((BlockRockVariant) usBlock).getType() == Type.SPARSE_GRASS)
                        spreader = Type.SPARSE_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant && usBlock instanceof BlockRockVariant) {
                    Type spreader = Type.CLAY_GRASS;

                    if (((BlockRockVariant) usBlock).getType() == Type.DRY_CLAY_GRASS)
                        spreader = Type.DRY_CLAY_GRASS;
                    else if (((BlockRockVariant) usBlock).getType() == Type.SPARSE_CLAY_GRASS)
                        spreader = Type.SPARSE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.BOG_IRON_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_BOG_IRON_GRASS)
                        spreader = Type.DRY_BOG_IRON_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_BOG_IRON_GRASS)
                        spreader = Type.SPARSE_BOG_IRON_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.LOAMY_SAND_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_LOAMY_SAND_GRASS)
                        spreader = Type.DRY_LOAMY_SAND_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_LOAMY_SAND_GRASS)
                        spreader = Type.SPARSE_LOAMY_SAND_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_LOAM_GRASS)
                        spreader = Type.DRY_SANDY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_LOAM_GRASS)
                        spreader = Type.SPARSE_SANDY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_LOAM_GRASS)
                        spreader = Type.DRY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_LOAM_GRASS)
                        spreader = Type.SPARSE_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILT_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILT_LOAM_GRASS)
                        spreader = Type.DRY_SILT_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILT_LOAM_GRASS)
                        spreader = Type.SPARSE_SILT_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILT_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILT_GRASS)
                        spreader = Type.DRY_SILT_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILT_GRASS)
                        spreader = Type.SPARSE_SILT_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.HUMUS_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_HUMUS_GRASS)
                        spreader = Type.DRY_HUMUS_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_HUMUS_GRASS)
                        spreader = Type.SPARSE_HUMUS_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                // Clay Grass
                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SANDY_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SANDY_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_CLAY_GRASS)
                        spreader = Type.DRY_SANDY_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_CLAY_GRASS)
                        spreader = Type.SPARSE_SANDY_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_CLAY_GRASS)
                        spreader = Type.DRY_SILTY_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_CLAY_GRASS)
                        spreader = Type.SPARSE_SILTY_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SILTY_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SILTY_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.CLAY_HUMUS_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_CLAY_HUMUS_GRASS)
                        spreader = Type.DRY_CLAY_HUMUS_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_CLAY_HUMUS_GRASS)
                        spreader = Type.SPARSE_CLAY_HUMUS_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                // Earthenware Clay Grass
                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_EARTHENWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.EARTHENWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.EARTHENWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.EARTHENWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_EARTHENWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_EARTHENWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.EARTHENWARE_CLAY_HUMUS_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS)
                        spreader = Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS)
                        spreader = Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                // Kaolinite Clay Grass
                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_KAOLINITE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_KAOLINITE_CLAY_GRASS)
                        spreader = Type.DRY_SANDY_KAOLINITE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS)
                        spreader = Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.KAOLINITE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.KAOLINITE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_KAOLINITE_CLAY_GRASS)
                        spreader = Type.KAOLINITE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_KAOLINITE_CLAY_GRASS)
                        spreader = Type.SPARSE_KAOLINITE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_KAOLINITE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_KAOLINITE_CLAY_GRASS)
                        spreader = Type.DRY_SILTY_KAOLINITE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS)
                        spreader = Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.KAOLINITE_CLAY_HUMUS_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS)
                        spreader = Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS)
                        spreader = Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                // Stoneware Clay Grass
                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_STONEWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SANDY_STONEWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SANDY_STONEWARE_CLAY_GRASS)
                        spreader = Type.DRY_SANDY_STONEWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.STONEWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_STONEWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.STONEWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_STONEWARE_CLAY_GRASS)
                        spreader = Type.STONEWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_STONEWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_STONEWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_STONEWARE_CLAY_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_STONEWARE_CLAY_GRASS)
                        spreader = Type.DRY_SILTY_STONEWARE_CLAY_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS)
                        spreader = Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.SILTY_STONEWARE_CLAY_LOAM_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS)
                        spreader = Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

                if (current.getBlock() instanceof BlockRockVariant) {
                    Type spreader = Type.STONEWARE_CLAY_HUMUS_GRASS;

                    if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.DRY_STONEWARE_CLAY_HUMUS_GRASS)
                        spreader = Type.DRY_STONEWARE_CLAY_HUMUS_GRASS;
                    else if ((usBlock instanceof BlockRockVariant) && ((BlockRockVariant) usBlock).getType() == Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS)
                        spreader = Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS;

                    BlockRockVariant block = ((BlockRockVariant) current.getBlock());

                    if (block.getType().getGrassVersion(spreader) != null) {
                        world.setBlockState(target, block.getVariant(block.getType().getGrassVersion(spreader)).getDefaultState());
                    }
                }

            }
            for (Plant plant : TFCRegistries.PLANTS.getValuesCollection())
            {
                if (plant.getPlantType() == Plant.PlantType.SHORT_GRASS && rand.nextFloat() < 0.5f)
                {
                    float temp = ClimateTFC.getActualTemp(world, upPos);
                    BlockShortGrassTFC plantBlock = BlockShortGrassTFC.get(plant);

                    if (world.isAirBlock(upPos) &&
                            plant.isValidLocation(temp, ChunkDataTFC.getRainfall(world, upPos), Math.subtractExact(world.getLightFor(EnumSkyBlock.SKY, upPos), world.getSkylightSubtracted())) &&
                            plant.isValidGrowthTemp(temp) &&
                            rand.nextDouble() < plantBlock.getGrowthRate(world, upPos))
                    {
                        world.setBlockState(upPos, plantBlock.getDefaultState());
                    }
                }
            }
        }
    }

    public BlockRockVariantConnected(Type type, Rock rock)
    {
        super(type, rock);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        pos = pos.add(0, -1, 0);
        return state.withProperty(NORTH, BlocksTFC.isGrass(world.getBlockState(pos.offset(EnumFacing.NORTH))))
            .withProperty(EAST, BlocksTFC.isGrass(world.getBlockState(pos.offset(EnumFacing.EAST))))
            .withProperty(SOUTH, BlocksTFC.isGrass(world.getBlockState(pos.offset(EnumFacing.SOUTH))))
            .withProperty(WEST, BlocksTFC.isGrass(world.getBlockState(pos.offset(EnumFacing.WEST))));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH);
    }
}
