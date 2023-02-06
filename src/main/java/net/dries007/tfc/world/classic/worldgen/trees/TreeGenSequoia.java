/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.world.classic.worldgen.trees;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.Tree;
import net.dries007.tfc.api.util.ITreeGenerator;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLeaves;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockSapling;
import net.dries007.tfc.world.classic.StructureHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

import java.util.Random;

import static net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog.PLACED;

public class TreeGenSequoia implements ITreeGenerator {
    private static final BlockPos[] OFFSETS = new BlockPos[]{
            new BlockPos(0, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1), new BlockPos(-1, 0, -1)
    };
    private final PlacementSettings settings = StructureHelper.getDefaultSettings();
    private IBlockState trunk;

    @Override
    public void generateTree(TemplateManager manager, World world, BlockPos pos, Tree tree, Random random, boolean isWorldGen) {
        final int baseVariant = 1 + random.nextInt(3);
        final int topVariant = 1 + random.nextInt(3);
        final int layers = 4 + random.nextInt(3);
        final int height = 3 + random.nextInt(4);

        trunk = TFCBlockLog.get(tree).getDefaultState().withProperty(PLACED, false);

        for (int i = -2; i < height; i++) {
            placeTrunk(world, pos.add(0, i, 0));
        }

        int k = height;
        for (int j = 0; j < layers; j++) {
            if (j == layers - 1 || (j == layers - 2 && random.nextBoolean())) {
                k += placeLayer(manager, world, pos.up(k), tree.getRegistryName() + "/mid" + baseVariant);
            } else {
                k += placeLayer(manager, world, pos.up(k), tree.getRegistryName() + "/base" + baseVariant);
            }
        }
        placeLayer(manager, world, pos.up(k), tree.getRegistryName() + "/top" + topVariant);

    }

    @Override
    public boolean canGenerateTree(World world, BlockPos pos, Tree treeType) {
        for (BlockPos p1 : OFFSETS) {
            if (!TFCBlocks.isSoil(world.getBlockState(pos.add(p1).down()))) {
                if (world.getBlockState(pos.add(p1)).getMaterial().isReplaceable()) {
                    if (TFCBlocks.isSoil(world.getBlockState(pos.add(p1).down(1))))
                        continue;
                    if (TFCBlocks.isSoil(world.getBlockState(pos.add(p1).down(2))) && world.getBlockState(pos.add(p1.down(1))).getMaterial().isReplaceable())
                        continue;
                }
                return false;
            }
        }

        return ITreeGenerator.super.canGenerateTree(world, pos, treeType);
    }

    private int placeLayer(TemplateManager manager, World world, BlockPos pos, String name) {
        ResourceLocation base = new ResourceLocation(name);
        Template structureBase = manager.get(world.getMinecraftServer(), base);

        if (structureBase == null) {
            TerraFirmaCraft.getLog().warn("Unable to find a template for " + base.toString());
            return -1;
        }
        BlockPos size = structureBase.getSize();
        pos = pos.add(-size.getX() / 2, 0, -size.getZ() / 2);

        StructureHelper.addStructureToWorld(world, pos, structureBase, settings);
        return size.getY();
    }

    private void placeTrunk(World world, BlockPos pos) {
        for (BlockPos p1 : OFFSETS) {
            checkAndPlace(world, pos.add(p1));
        }
    }

    private void checkAndPlace(World world, BlockPos pos) {
        if (world.getBlockState(pos).getMaterial().isReplaceable() || world.getBlockState(pos).getBlock() instanceof TFCBlockSapling || world.getBlockState(pos).getBlock() instanceof TFCBlockLeaves) {
            world.setBlockState(pos, trunk);
        }
    }

}
