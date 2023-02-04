/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.stone;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.api.types.Rock.*;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.client.TFCSounds;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.items.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.objects.blocks.stone.farmland.*;
import net.dries007.tfc.objects.blocks.stone.path.*;
import net.dries007.tfc.objects.items.rock.ItemMud;

import static net.dries007.tfc.api.types.Rock.*;
import static net.dries007.tfc.api.types.Rock.Type.*;
import static net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop.*;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class BlockRockVariant extends Block implements IItemSize {
    private static final Map<Rock, EnumMap<Type, BlockRockVariant>> TABLE = new HashMap<>();

    public static BlockRockVariant get(Rock rock, Type type) {
        // noinspection ConstantConditions
        if (rock == null) {
            return TABLE.get(Rock.GRANITE).get(type);
        }
        return TABLE.get(rock).get(type);
    }

    public static BlockRockVariant create(Rock rock, Type type) {
        switch (type) {
            case RAW:
            case MOSSY_RAW:
                return new TFCBlockRockRaw(type, rock);
            case MUD:
                return new TFCBlockRockMud(type, rock);
            case SMOOTH:
                return new TFCBlockRockSmooth(type, rock);
            case ANVIL:
                return new TFCBlockRockAnvil(type, rock);
            case SPIKE:
                return new TFCBlockRockSpike(type, rock);
            case FARMLAND:
                return new TFCBlockFarmland(type, rock);
            case LOAMY_SAND_FARMLAND:
                return new BlockLoamySandFarmland(type, rock);
            case SANDY_LOAM_FARMLAND:
                return new BlockSandyLoamFarmland(type, rock);
            case LOAM_FARMLAND:
                return new BlockLoamFarmland(type, rock);
            case SILT_LOAM_FARMLAND:
                return new BlockSiltLoamFarmland(type, rock);
            case SILT_FARMLAND:
                return new BlockSiltFarmland(type, rock);
            case HUMUS_FARMLAND:
                return new BlockHumusFarmland(type, rock);
            case PATH:
                return new TFCBlockPath(type, rock);
            case LOAMY_SAND_PATH:
                return new BlockLoamySandPath(type, rock);
            case SANDY_LOAM_PATH:
                return new BlockSandyLoamPath(type, rock);
            case LOAM_PATH:
                return new BlockLoamPath(type, rock);
            case SILT_LOAM_PATH:
                return new BlockSiltLoamPath(type, rock);
            case SILT_PATH:
                return new BlockSiltPath(type, rock);
            case HUMUS_PATH:
                return new BlockHumusPath(type, rock);
            case GRASS:
            case DRY_GRASS:
            case CLAY_GRASS:
            case PODZOL:
            case LOAMY_SAND_GRASS:
            case LOAMY_SAND_PODZOL:
            case SANDY_LOAM_GRASS:
            case SANDY_LOAM_PODZOL:
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_GRASS:
            case SANDY_CLAY_PODZOL:
            case LOAM_GRASS:
            case LOAM_PODZOL:
            case CLAY_LOAM_GRASS:
            case CLAY_LOAM_PODZOL:
            case CLAY_PODZOL:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_GRASS:
            case SILTY_CLAY_LOAM_PODZOL:
            case SILT_LOAM_GRASS:
            case SILT_LOAM_PODZOL:
            case SILT_GRASS:
            case SILT_PODZOL:
            case DRY_LOAMY_SAND_GRASS:
            case DRY_SANDY_LOAM_GRASS:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_LOAM_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_CLAY_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case DRY_SILT_LOAM_GRASS:
            case DRY_SILT_GRASS:
            case HUMUS_GRASS:
            case DRY_HUMUS_GRASS:
            case CLAY_HUMUS_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
            case EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case SANDY_EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_PODZOL:
            case EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_LOAM_PODZOL:
            case EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_GRASS:
            case SILTY_EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
            case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_HUMUS_GRASS:
            case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
            case KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
            case STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
            case SANDY_STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_PODZOL:
            case STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_LOAM_PODZOL:
            case STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_GRASS:
            case SILTY_STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_STONEWARE_CLAY_GRASS:
            case DRY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_HUMUS_GRASS:
            case DRY_STONEWARE_CLAY_HUMUS_GRASS:
            case SPARSE_GRASS:
            case SPARSE_CLAY_GRASS:
            case SPARSE_LOAMY_SAND_GRASS:
            case SPARSE_SANDY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_GRASS:
            case SPARSE_LOAM_GRASS:
            case SPARSE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_CLAY_GRASS:
            case SPARSE_SILTY_CLAY_LOAM_GRASS:
            case SPARSE_SILT_LOAM_GRASS:
            case SPARSE_SILT_GRASS:
            case SPARSE_HUMUS_GRASS:
            case SPARSE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
                return new TFCBlockRockVariantConnected(type, rock);
            case SAND:
            case DIRT:
            case CLAY:
            case GRAVEL:
            case COBBLE:
            case ROOTED_DIRT:
            case COARSE_DIRT:
            case BOG_IRON:
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case BOG_IRON_PODZOL:
            case LOAMY_SAND:
            case COARSE_LOAMY_SAND:
            case SANDY_LOAM:
            case COARSE_SANDY_LOAM:
            case SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case COARSE_SANDY_CLAY:
            case LOAM:
            case COARSE_LOAM:
            case CLAY_LOAM:
            case COARSE_CLAY_LOAM:
            case COARSE_CLAY:
            case SILTY_CLAY:
            case COARSE_SILTY_CLAY:
            case SILTY_CLAY_LOAM:
            case COARSE_SILTY_CLAY_LOAM:
            case SILT_LOAM:
            case COARSE_SILT_LOAM:
            case SILT:
            case COARSE_SILT:
            case HUMUS:
            case COARSE_HUMUS:
            case CLAY_HUMUS:
            case COARSE_CLAY_HUMUS:
            case EARTHENWARE_CLAY:
            case SANDY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SANDY_EARTHENWARE_CLAY_LOAM:
            case SANDY_EARTHENWARE_CLAY:
            case COARSE_SANDY_EARTHENWARE_CLAY:
            case EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY:
            case COARSE_SILTY_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SILTY_EARTHENWARE_CLAY_LOAM:
            case EARTHENWARE_CLAY_HUMUS:
            case COARSE_EARTHENWARE_CLAY_HUMUS:
            case KAOLINITE_CLAY:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
            case COARSE_KAOLINITE_CLAY_HUMUS:
            case STONEWARE_CLAY:
            case SANDY_STONEWARE_CLAY_LOAM:
            case COARSE_SANDY_STONEWARE_CLAY_LOAM:
            case SANDY_STONEWARE_CLAY:
            case COARSE_SANDY_STONEWARE_CLAY:
            case STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY:
            case COARSE_SILTY_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY_LOAM:
            case COARSE_SILTY_STONEWARE_CLAY_LOAM:
            case STONEWARE_CLAY_HUMUS:
            case COARSE_STONEWARE_CLAY_HUMUS:
                return new TFCBlockRockVariantFallable(type, rock);
            default:
                return new BlockRockVariant(type, rock);
        }
    }

    protected final Type type;
    protected final Rock rock;

    public BlockRockVariant(Type type, Rock rock) {
        super(type.material);

        if (!TABLE.containsKey(rock)) {
            TABLE.put(rock, new EnumMap<>(Type.class));
        }
        TABLE.get(rock).put(type, this);

        this.type = type;
        this.rock = rock;
        if (type.isGrass)
            setTickRandomly(true);
        switch (type) {
            case BRICKS:
            case RAW:
            case ANVIL:
            case SPIKE:
            case SMOOTH:
            case MOSSY_RAW:
            case MUD_BRICKS:
                setSoundType(SoundType.STONE);
                setHardness(rock.getRockCategory().getHardness()).setResistance(rock.getRockCategory().getResistance());
                setHarvestLevel("pickaxe", 0);
                break;
            case COBBLE:
                setSoundType(SoundType.STONE);
                setHardness(rock.getRockCategory().getHardness() * 0.75F)
                        .setResistance(rock.getRockCategory().getResistance());
                setHarvestLevel("pickaxe", 0);
                break;
            case SAND:
                setSoundType(SoundType.SAND);
                setHardness(rock.getRockCategory().getHardness() * 0.05F);
                setHarvestLevel("shovel", 0);
                break;
            case LOAMY_SAND:
            case SANDY_LOAM:
            case LOAM:
            case SILT_LOAM:
            case SILT:
            case HUMUS:
            case LOAMY_SAND_FARMLAND:
            case SANDY_LOAM_FARMLAND:
            case LOAM_FARMLAND:
            case SILT_LOAM_FARMLAND:
            case SILT_FARMLAND:
            case HUMUS_FARMLAND:
            case LOAMY_SAND_PATH:
            case SANDY_LOAM_PATH:
            case LOAM_PATH:
            case SILT_LOAM_PATH:
            case SILT_PATH:
            case HUMUS_PATH:
                setSoundType(SoundType.GROUND);
                setHardness(1.5F * 0.15F);
                setHarvestLevel("shovel", 0);
                break;
            case ROOTED_DIRT:
            case BOG_IRON:
            case SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case CLAY_LOAM:
            case SILTY_CLAY_LOAM:
            case SILTY_CLAY:
            case COARSE_DIRT:
            case COARSE_LOAMY_SAND:
            case COARSE_SANDY_LOAM:
            case COARSE_SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY:
            case COARSE_LOAM:
            case COARSE_CLAY_LOAM:
            case COARSE_SILTY_CLAY:
            case COARSE_SILTY_CLAY_LOAM:
            case COARSE_SILT_LOAM:
            case COARSE_SILT:
            case COARSE_HUMUS:
            case COARSE_CLAY_HUMUS:
            case CLAY_HUMUS:
            case SANDY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SANDY_EARTHENWARE_CLAY_LOAM:
            case SANDY_EARTHENWARE_CLAY:
            case COARSE_SANDY_EARTHENWARE_CLAY:
            case EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY_LOAM:
            case SILTY_EARTHENWARE_CLAY:
            case COARSE_SILTY_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SILTY_EARTHENWARE_CLAY_LOAM:
            case EARTHENWARE_CLAY_HUMUS:
            case COARSE_EARTHENWARE_CLAY_HUMUS:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
            case COARSE_KAOLINITE_CLAY_HUMUS:
            case SANDY_STONEWARE_CLAY_LOAM:
            case COARSE_SANDY_STONEWARE_CLAY_LOAM:
            case SANDY_STONEWARE_CLAY:
            case COARSE_SANDY_STONEWARE_CLAY:
            case STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY_LOAM:
            case SILTY_STONEWARE_CLAY:
            case COARSE_SILTY_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY_LOAM:
            case COARSE_SILTY_STONEWARE_CLAY_LOAM:
            case STONEWARE_CLAY_HUMUS:
            case COARSE_STONEWARE_CLAY_HUMUS:
                setSoundType(SoundType.GROUND);
                setHardness(1.5F * 0.2F);
                setHarvestLevel("shovel", 0);
                break;
            case DIRT:
            case PATH:
            case FARMLAND:
            case MUD:
                setSoundType(SoundType.GROUND);
                setHardness(rock.getRockCategory().getHardness() * 0.15F);
                setHarvestLevel("shovel", 0);
                break;
            case GRAVEL:
            case CLAY:
            case COARSE_CLAY:
            case EARTHENWARE_CLAY:
            case COARSE_EARTHENWARE_CLAY:
            case KAOLINITE_CLAY:
            case COARSE_KAOLINITE_CLAY:
            case STONEWARE_CLAY:
            case COARSE_STONEWARE_CLAY:
                setSoundType(SoundType.GROUND);
                setHardness(rock.getRockCategory().getHardness() * 0.2F);
                setHarvestLevel("shovel", 0);
                break;
            case CLAY_GRASS:
            case GRASS:
            case DRY_GRASS:
            case PODZOL:
            case CLAY_PODZOL:
            case DRY_CLAY_GRASS:
            case EARTHENWARE_CLAY_GRASS:
            case EARTHENWARE_CLAY_PODZOL:
            case DRY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_GRASS:
            case KAOLINITE_CLAY_GRASS:
            case KAOLINITE_CLAY_PODZOL:
            case DRY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_GRASS:
            case STONEWARE_CLAY_GRASS:
            case STONEWARE_CLAY_PODZOL:
            case DRY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_GRASS:
            case SPARSE_GRASS:
            case SPARSE_CLAY_GRASS:
                setSoundType(SoundType.PLANT);
                setHardness(rock.getRockCategory().getHardness() * 0.2F);
                setHarvestLevel("shovel", 0);
                break;
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case BOG_IRON_PODZOL:
            case LOAMY_SAND_GRASS:
            case LOAMY_SAND_PODZOL:
            case SANDY_LOAM_GRASS:
            case SANDY_LOAM_PODZOL:
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_GRASS:
            case SANDY_CLAY_PODZOL:
            case LOAM_GRASS:
            case LOAM_PODZOL:
            case CLAY_LOAM_GRASS:
            case CLAY_LOAM_PODZOL:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_GRASS:
            case SILTY_CLAY_LOAM_PODZOL:
            case SILT_LOAM_GRASS:
            case SILT_LOAM_PODZOL:
            case SILT_GRASS:
            case SILT_PODZOL:
            case DRY_LOAMY_SAND_GRASS:
            case DRY_SANDY_LOAM_GRASS:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_LOAM_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case DRY_SILT_LOAM_GRASS:
            case DRY_SILT_GRASS:
            case HUMUS_GRASS:
            case DRY_HUMUS_GRASS:
            case CLAY_HUMUS_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case SANDY_EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_PODZOL:
            case EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_LOAM_PODZOL:
            case SILTY_EARTHENWARE_CLAY_GRASS:
            case SILTY_EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
            case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_HUMUS_GRASS:
            case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
            case SANDY_STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_PODZOL:
            case STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_LOAM_PODZOL:
            case SILTY_STONEWARE_CLAY_GRASS:
            case SILTY_STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_STONEWARE_CLAY_GRASS:
            case DRY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_HUMUS_GRASS:
            case DRY_STONEWARE_CLAY_HUMUS_GRASS:
            case SPARSE_LOAMY_SAND_GRASS:
            case SPARSE_SANDY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_GRASS:
            case SPARSE_LOAM_GRASS:
            case SPARSE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_CLAY_GRASS:
            case SPARSE_SILTY_CLAY_LOAM_GRASS:
            case SPARSE_SILT_LOAM_GRASS:
            case SPARSE_SILT_GRASS:
            case SPARSE_HUMUS_GRASS:
            case SPARSE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
                setSoundType(SoundType.PLANT);
                setHardness(1.5F * 0.2F);
                setHarvestLevel("shovel", 0);
                break;
        }
        if (type != SPIKE && type != ANVIL) // since spikes and anvils don't generate ItemBlocks
        {
            OreDictionaryHelper.registerRockType(this, type);
        }
    }

    public BlockRockVariant getVariant(Type t) {
        return TABLE.get(rock).get(t);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("deprecation")
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess world, BlockPos pos, EnumFacing side) {
        switch (this.type) {
            case PATH:
            case FARMLAND:
            case LOAMY_SAND_PATH:
            case SANDY_LOAM_PATH:
            case LOAM_PATH:
            case SILT_LOAM_PATH:
            case SILT_PATH:
            case HUMUS_PATH:
            case LOAMY_SAND_FARMLAND:
            case SANDY_LOAM_FARMLAND:
            case LOAM_FARMLAND:
            case SILT_LOAM_FARMLAND:
            case SILT_FARMLAND:
            case HUMUS_FARMLAND:
                switch (side) {
                    case UP:
                        return true;
                    case NORTH:
                    case SOUTH:
                    case WEST:
                    case EAST:
                        IBlockState state = world.getBlockState(pos.offset(side));
                        Block block = state.getBlock();
                        if (state.isOpaqueCube())
                            return false;
                        if (block instanceof BlockFarmland ||
                                block instanceof BlockGrassPath ||
                                block instanceof BlockLoamySandFarmland ||
                                block instanceof BlockSandyLoamFarmland ||
                                block instanceof BlockLoamFarmland ||
                                block instanceof BlockSiltLoamFarmland ||
                                block instanceof BlockSiltFarmland ||
                                block instanceof BlockHumusFarmland /*
                                                                     * ||
                                                                     * block instanceof BlockLoamySandPath ||
                                                                     * block instanceof BlockSandyLoamPath ||
                                                                     * block instanceof BlockLoamPath ||
                                                                     * block instanceof BlockSiltLoamPath ||
                                                                     * block instanceof BlockSiltPath ||
                                                                     * block instanceof BlockHumusPath
                                                                     */)
                            return false;
                        if (block instanceof BlockRockVariant) {
                            switch (((BlockRockVariant) block).type) {
                                case FARMLAND:
                                case PATH:
                                case LOAMY_SAND_PATH:
                                case SANDY_LOAM_PATH:
                                case LOAM_PATH:
                                case SILT_LOAM_PATH:
                                case SILT_PATH:
                                case HUMUS_PATH:
                                case LOAMY_SAND_FARMLAND:
                                case SANDY_LOAM_FARMLAND:
                                case LOAM_FARMLAND:
                                case SILT_LOAM_FARMLAND:
                                case SILT_FARMLAND:
                                case HUMUS_FARMLAND:
                                    return false;
                                default:
                                    return true;
                            }
                        }
                        return true;
                    case DOWN:
                        return super.shouldSideBeRendered(blockState, world, pos, side);
                }
            default:
                return super.shouldSideBeRendered(blockState, world, pos, side);
        }
    }

    @Override
    public void randomTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (world.isRemote) return;
        if (type.isGrass) TFCBlockRockVariantConnected.spreadGrass(world, pos, state, rand);
        super.randomTick(world, pos, state, rand);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        switch (type) {
            case RAW:
            case SPIKE:
            case MOSSY_RAW:
                return ItemRock.get(rock);
            case MUD:
                return ItemMud.get(rock);
            case CLAY:
            case CLAY_GRASS:
            case SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case CLAY_LOAM:
            case SILTY_CLAY_LOAM:
            case SILTY_CLAY:
            case COARSE_SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY:
            case COARSE_CLAY_LOAM:
            case COARSE_CLAY:
            case COARSE_SILTY_CLAY:
            case COARSE_SILTY_CLAY_LOAM:
            case COARSE_CLAY_HUMUS:
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_GRASS:
            case SANDY_CLAY_PODZOL:
            case CLAY_LOAM_GRASS:
            case CLAY_LOAM_PODZOL:
            case CLAY_PODZOL:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_GRASS:
            case SILTY_CLAY_LOAM_PODZOL:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_CLAY_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case CLAY_HUMUS:
            case CLAY_HUMUS_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_GRASS:
            case SPARSE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_CLAY_GRASS:
            case SPARSE_SILTY_CLAY_LOAM_GRASS:
            case SPARSE_CLAY_HUMUS_GRASS:
            case SPARSE_CLAY_GRASS:
                return Items.CLAY_BALL;
            case EARTHENWARE_CLAY:
            case SANDY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SANDY_EARTHENWARE_CLAY_LOAM:
            case SANDY_EARTHENWARE_CLAY:
            case COARSE_SANDY_EARTHENWARE_CLAY:
            case EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY:
            case COARSE_SILTY_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SILTY_EARTHENWARE_CLAY_LOAM:
            case EARTHENWARE_CLAY_HUMUS:
            case COARSE_EARTHENWARE_CLAY_HUMUS:
            case EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case SANDY_EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_PODZOL:
            case EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_LOAM_PODZOL:
            case EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_GRASS:
            case SILTY_EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
            case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_HUMUS_GRASS:
            case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
                return TFCItems.EARTHENWARE_CLAY;
            case KAOLINITE_CLAY:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
            case COARSE_KAOLINITE_CLAY_HUMUS:
            case KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
                return TFCItems.KAOLINITE_CLAY;
            case STONEWARE_CLAY:
            case SANDY_STONEWARE_CLAY_LOAM:
            case COARSE_SANDY_STONEWARE_CLAY_LOAM:
            case SANDY_STONEWARE_CLAY:
            case COARSE_SANDY_STONEWARE_CLAY:
            case STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY:
            case COARSE_SILTY_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY_LOAM:
            case COARSE_SILTY_STONEWARE_CLAY_LOAM:
            case STONEWARE_CLAY_HUMUS:
            case COARSE_STONEWARE_CLAY_HUMUS:
            case STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
            case SANDY_STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_PODZOL:
            case STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_LOAM_PODZOL:
            case STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_GRASS:
            case SILTY_STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_STONEWARE_CLAY_GRASS:
            case DRY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_HUMUS_GRASS:
            case DRY_STONEWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
                return TFCItems.STONEWARE_CLAY;
            default:
                return super.getItemDropped(state, rand, fortune);
            case GRASS:
            case DRY_GRASS:
            case PATH:
            case PODZOL:
            case SPARSE_GRASS:
                return Item.getItemFromBlock(get(rock, DIRT));
            case BOG_IRON:
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case SPARSE_LOAMY_SAND_GRASS:
            case DRY_LOAMY_SAND_GRASS:
            case LOAMY_SAND_GRASS:
            case LOAMY_SAND_PODZOL:
            case LOAMY_SAND_PATH:
                return Item.getItemFromBlock(get(null, LOAMY_SAND));
            case SPARSE_SANDY_LOAM_GRASS:
            case DRY_SANDY_LOAM_GRASS:
            case SANDY_LOAM_GRASS:
            case SANDY_LOAM_PODZOL:
            case SANDY_LOAM_PATH:
                return Item.getItemFromBlock(get(null, SANDY_LOAM));
            case SPARSE_LOAM_GRASS:
            case DRY_LOAM_GRASS:
            case LOAM_GRASS:
            case LOAM_PODZOL:
            case LOAM_PATH:
                return Item.getItemFromBlock(get(null, LOAM));
            case SPARSE_SILT_LOAM_GRASS:
            case DRY_SILT_LOAM_GRASS:
            case SILT_LOAM_GRASS:
            case SILT_LOAM_PODZOL:
            case SILT_LOAM_PATH:
                return Item.getItemFromBlock(get(null, SILT_LOAM));
            case SPARSE_SILT_GRASS:
            case DRY_SILT_GRASS:
            case SILT_GRASS:
            case SILT_PODZOL:
            case SILT_PATH:
                return Item.getItemFromBlock(get(null, SILT));
            case SPARSE_HUMUS_GRASS:
            case HUMUS:
            case HUMUS_GRASS:
            case DRY_HUMUS_GRASS:
            case HUMUS_PATH:
                return Item.getItemFromBlock(get(null, HUMUS));
            case COARSE_DIRT:
                return Item.getItemFromBlock(get(rock, COARSE_DIRT));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return type.isGrass ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        switch (type) {
            case CLAY:
            case CLAY_GRASS:
                return 4;
            case RAW:
            case SPIKE:
                return 1 + random.nextInt(3);
            case SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case CLAY_LOAM:
            case SILTY_CLAY_LOAM:
            case SILTY_CLAY:
            case COARSE_SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY:
            case COARSE_CLAY_LOAM:
            case COARSE_CLAY:
            case COARSE_SILTY_CLAY:
            case COARSE_SILTY_CLAY_LOAM:
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_GRASS:
            case SANDY_CLAY_PODZOL:
            case CLAY_LOAM_GRASS:
            case CLAY_LOAM_PODZOL:
            case CLAY_PODZOL:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_GRASS:
            case SILTY_CLAY_LOAM_PODZOL:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_CLAY_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case CLAY_HUMUS:
            case COARSE_CLAY_HUMUS:
            case CLAY_HUMUS_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
            case EARTHENWARE_CLAY:
            case SANDY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SANDY_EARTHENWARE_CLAY_LOAM:
            case SANDY_EARTHENWARE_CLAY:
            case COARSE_SANDY_EARTHENWARE_CLAY:
            case EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY:
            case COARSE_SILTY_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SILTY_EARTHENWARE_CLAY_LOAM:
            case EARTHENWARE_CLAY_HUMUS:
            case COARSE_EARTHENWARE_CLAY_HUMUS:
            case EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case SANDY_EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_PODZOL:
            case EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_LOAM_PODZOL:
            case EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_GRASS:
            case SILTY_EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
            case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_HUMUS_GRASS:
            case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
            case KAOLINITE_CLAY:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
            case COARSE_KAOLINITE_CLAY_HUMUS:
            case KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
            case STONEWARE_CLAY:
            case SANDY_STONEWARE_CLAY_LOAM:
            case COARSE_SANDY_STONEWARE_CLAY_LOAM:
            case SANDY_STONEWARE_CLAY:
            case COARSE_SANDY_STONEWARE_CLAY:
            case STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY:
            case COARSE_SILTY_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY_LOAM:
            case COARSE_SILTY_STONEWARE_CLAY_LOAM:
            case STONEWARE_CLAY_HUMUS:
            case COARSE_STONEWARE_CLAY_HUMUS:
            case STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
            case SANDY_STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_PODZOL:
            case STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_LOAM_PODZOL:
            case STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_GRASS:
            case SILTY_STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_STONEWARE_CLAY_GRASS:
            case DRY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_HUMUS_GRASS:
            case DRY_STONEWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_GRASS:
            case SPARSE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_CLAY_GRASS:
            case SPARSE_SILTY_CLAY_LOAM_GRASS:
            case SPARSE_CLAY_HUMUS_GRASS:
            case SPARSE_CLAY_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
                return 2 + random.nextInt(2);
            case MOSSY_RAW:
                return 1 + random.nextInt(3);
            case MUD:
                return 1 + random.nextInt(3);
            case BOG_IRON:
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case BOG_IRON_PODZOL:
                return 1 + random.nextInt(1);
            default:
                return super.quantityDropped(state, fortune, random);
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
            IPlantable plantable) {
        int beachDistance = 2;

        if (plantable instanceof TFCBlockPlant) {
            switch (((TFCBlockPlant) plantable).getPlantTypeTFC()) {
                case CLAY:
                    return type == DIRT ||
                            type == GRASS ||
                            type == DRY_GRASS ||
                            type == CLAY ||
                            type == CLAY_GRASS ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == ROOTED_DIRT ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == SANDY_CLAY_LOAM ||
                            type == SANDY_CLAY ||
                            type == CLAY_LOAM ||
                            type == SILTY_CLAY_LOAM ||
                            type == SILTY_CLAY ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_SANDY_CLAY_LOAM ||
                            type == COARSE_SANDY_CLAY ||
                            type == COARSE_LOAM ||
                            type == COARSE_CLAY_LOAM ||
                            type == COARSE_CLAY ||
                            type == COARSE_SILTY_CLAY ||
                            type == COARSE_SILTY_CLAY_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == SANDY_CLAY_LOAM_GRASS ||
                            type == SANDY_CLAY_LOAM_PODZOL ||
                            type == SANDY_CLAY_GRASS ||
                            type == SANDY_CLAY_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == CLAY_LOAM_GRASS ||
                            type == CLAY_LOAM_PODZOL ||
                            type == CLAY_PODZOL ||
                            type == SILTY_CLAY_GRASS ||
                            type == SILTY_CLAY_PODZOL ||
                            type == SILTY_CLAY_LOAM_GRASS ||
                            type == SILTY_CLAY_LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_CLAY_LOAM_GRASS ||
                            type == DRY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == COARSE_CLAY_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == CLAY_HUMUS ||
                            type == CLAY_HUMUS_GRASS ||
                            type == DRY_CLAY_HUMUS_GRASS ||
                            type == EARTHENWARE_CLAY ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == SANDY_EARTHENWARE_CLAY ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == EARTHENWARE_CLAY_HUMUS ||
                            type == COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == KAOLINITE_CLAY ||
                            type == SANDY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == SANDY_KAOLINITE_CLAY ||
                            type == COARSE_SANDY_KAOLINITE_CLAY ||
                            type == KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY ||
                            type == COARSE_SILTY_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == KAOLINITE_CLAY_HUMUS ||
                            type == COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == STONEWARE_CLAY ||
                            type == SANDY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == SANDY_STONEWARE_CLAY ||
                            type == COARSE_SANDY_STONEWARE_CLAY ||
                            type == STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY ||
                            type == COARSE_SILTY_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == STONEWARE_CLAY_HUMUS ||
                            type == COARSE_STONEWARE_CLAY_HUMUS ||
                            type == STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_PODZOL ||
                            type == STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_LOAM_PODZOL ||
                            type == STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_GRASS ||
                            type == SILTY_STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_CLAY_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILTY_CLAY_GRASS ||
                            type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS ||
                            type == SPARSE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
                case DESERT_CLAY:
                    return type == SAND ||
                            type == CLAY ||
                            type == CLAY_GRASS ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == SANDY_CLAY_LOAM ||
                            type == SANDY_CLAY ||
                            type == CLAY_LOAM ||
                            type == SILTY_CLAY_LOAM ||
                            type == SILTY_CLAY ||
                            type == CLAY_HUMUS ||
                            type == COARSE_SANDY_CLAY_LOAM ||
                            type == COARSE_SANDY_CLAY ||
                            type == COARSE_CLAY_LOAM ||
                            type == COARSE_CLAY ||
                            type == COARSE_SILTY_CLAY ||
                            type == COARSE_SILTY_CLAY_LOAM ||
                            type == COARSE_CLAY_HUMUS ||
                            type == COARSE_EARTHENWARE_CLAY ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == COARSE_KAOLINITE_CLAY ||
                            type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SANDY_KAOLINITE_CLAY ||
                            type == COARSE_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SILTY_KAOLINITE_CLAY ||
                            type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == COARSE_STONEWARE_CLAY ||
                            type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_STONEWARE_CLAY |
                            type == COARSE_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_STONEWARE_CLAY ||
                            type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY_HUMUS;
                case DRY_CLAY:
                    return type == DIRT ||
                            type == DRY_GRASS ||
                            type == SAND ||
                            type == CLAY ||
                            type == CLAY_GRASS ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == SANDY_CLAY_LOAM ||
                            type == SANDY_CLAY ||
                            type == CLAY_LOAM ||
                            type == SILTY_CLAY_LOAM ||
                            type == SILTY_CLAY ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_SANDY_CLAY_LOAM ||
                            type == COARSE_SANDY_CLAY ||
                            type == COARSE_LOAM ||
                            type == COARSE_CLAY_LOAM ||
                            type == COARSE_CLAY ||
                            type == COARSE_SILTY_CLAY ||
                            type == COARSE_SILTY_CLAY_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == COARSE_CLAY_HUMUS ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == SANDY_CLAY_LOAM_GRASS ||
                            type == SANDY_CLAY_LOAM_PODZOL ||
                            type == SANDY_CLAY_GRASS ||
                            type == SANDY_CLAY_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == CLAY_LOAM_GRASS ||
                            type == CLAY_LOAM_PODZOL ||
                            type == CLAY_PODZOL ||
                            type == SILTY_CLAY_GRASS ||
                            type == SILTY_CLAY_PODZOL ||
                            type == SILTY_CLAY_LOAM_GRASS ||
                            type == SILTY_CLAY_LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_CLAY_LOAM_GRASS ||
                            type == DRY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == COARSE_STONEWARE_CLAY_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == CLAY_HUMUS ||
                            type == CLAY_HUMUS_GRASS ||
                            type == DRY_CLAY_HUMUS_GRASS ||
                            type == EARTHENWARE_CLAY ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == SANDY_EARTHENWARE_CLAY ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == EARTHENWARE_CLAY_HUMUS ||
                            type == EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == KAOLINITE_CLAY ||
                            type == SANDY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == SANDY_KAOLINITE_CLAY ||
                            type == COARSE_SANDY_KAOLINITE_CLAY ||
                            type == KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY ||
                            type == COARSE_SILTY_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == KAOLINITE_CLAY_HUMUS ||
                            type == KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == STONEWARE_CLAY ||
                            type == SANDY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == SANDY_STONEWARE_CLAY ||
                            type == COARSE_SANDY_STONEWARE_CLAY ||
                            type == STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY ||
                            type == COARSE_SILTY_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == STONEWARE_CLAY_HUMUS ||
                            type == STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_PODZOL ||
                            type == STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_LOAM_PODZOL ||
                            type == STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_GRASS ||
                            type == SILTY_STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_CLAY_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILTY_CLAY_GRASS ||
                            type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS ||
                            type == SPARSE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
                case DRY:
                    return type == DIRT ||
                            type == DRY_GRASS ||
                            type == SAND ||
                            type == ROOTED_DIRT ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == COARSE_HUMUS ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS;
                case WATER:
                    return type == DIRT ||
                            type == GRASS ||
                            type == DRY_GRASS ||
                            type == GRAVEL ||
                            type == ROOTED_DIRT ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == COARSE_HUMUS ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS;
                case SEA_WATER:
                    return type == DIRT ||
                            type == GRASS ||
                            type == DRY_GRASS ||
                            type == SAND ||
                            type == GRAVEL ||
                            type == ROOTED_DIRT ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS;
                case FRESH_BEACH: {
                    boolean flag = false;
                    for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                        for (int i = 1; i <= beachDistance; i++) {
                            if (BlocksTFC.isFreshWaterOrIce(world.getBlockState(pos.offset(facing, i)))) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    return (type == DIRT ||
                            type == GRASS ||
                            type == SAND ||
                            type == DRY_GRASS ||
                            type == ROOTED_DIRT ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS) && flag;
                }
                case SALT_BEACH: {
                    boolean flag = false;
                    for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                        for (int i = 1; i <= beachDistance; i++)
                            if (BlocksTFC.isSeaWater(world.getBlockState(pos.offset(facing, i)))) {
                                flag = true;
                            }
                    }
                    return (type == DIRT ||
                            type == GRASS ||
                            type == SAND ||
                            type == DRY_GRASS ||
                            type == ROOTED_DIRT ||
                            type == COARSE_DIRT ||
                            type == MUD ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS) && flag;
                }
            }
        } else if (plantable instanceof TFCBlockCrop) {
            IBlockState cropState = world.getBlockState(pos.up());
            if (cropState.getBlock() instanceof TFCBlockCrop) {
                boolean isWild = cropState.getValue(WILD);
                if (isWild) {
                    if (type == DIRT ||
                            type == GRASS ||
                            type == DRY_GRASS ||
                            type == CLAY_GRASS ||
                            type == ROOTED_DIRT ||
                            type == BOG_IRON ||
                            type == BOG_IRON_GRASS ||
                            type == DRY_BOG_IRON_GRASS ||
                            type == SPARSE_BOG_IRON_GRASS ||
                            type == BOG_IRON_PODZOL ||
                            type == COARSE_DIRT ||
                            type == LOAMY_SAND ||
                            type == SANDY_LOAM ||
                            type == LOAM ||
                            type == SILT_LOAM ||
                            type == SILT ||
                            type == SANDY_CLAY_LOAM ||
                            type == SANDY_CLAY ||
                            type == CLAY_LOAM ||
                            type == SILTY_CLAY_LOAM ||
                            type == SILTY_CLAY ||
                            type == COARSE_LOAMY_SAND ||
                            type == COARSE_SANDY_LOAM ||
                            type == COARSE_SANDY_CLAY_LOAM ||
                            type == COARSE_SANDY_CLAY ||
                            type == COARSE_LOAM ||
                            type == COARSE_CLAY_LOAM ||
                            type == COARSE_CLAY ||
                            type == COARSE_SILTY_CLAY ||
                            type == COARSE_SILTY_CLAY_LOAM ||
                            type == COARSE_SILT_LOAM ||
                            type == COARSE_SILT ||
                            type == COARSE_CLAY_HUMUS ||
                            type == PODZOL ||
                            type == LOAMY_SAND_GRASS ||
                            type == LOAMY_SAND_PODZOL ||
                            type == SANDY_LOAM_GRASS ||
                            type == SANDY_LOAM_PODZOL ||
                            type == SANDY_CLAY_LOAM_GRASS ||
                            type == SANDY_CLAY_LOAM_PODZOL ||
                            type == SANDY_CLAY_GRASS ||
                            type == SANDY_CLAY_PODZOL ||
                            type == LOAM_GRASS ||
                            type == LOAM_PODZOL ||
                            type == CLAY_LOAM_GRASS ||
                            type == CLAY_LOAM_PODZOL ||
                            type == CLAY_PODZOL ||
                            type == SILTY_CLAY_GRASS ||
                            type == SILTY_CLAY_PODZOL ||
                            type == SILTY_CLAY_LOAM_GRASS ||
                            type == SILTY_CLAY_LOAM_PODZOL ||
                            type == SILT_LOAM_GRASS ||
                            type == SILT_LOAM_PODZOL ||
                            type == SILT_GRASS ||
                            type == SILT_PODZOL ||
                            type == DRY_LOAMY_SAND_GRASS ||
                            type == DRY_SANDY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_CLAY_GRASS ||
                            type == DRY_LOAM_GRASS ||
                            type == DRY_CLAY_LOAM_GRASS ||
                            type == DRY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_GRASS ||
                            type == DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == DRY_SILT_LOAM_GRASS ||
                            type == DRY_SILT_GRASS ||
                            type == HUMUS ||
                            type == COARSE_HUMUS ||
                            type == HUMUS_GRASS ||
                            type == DRY_HUMUS_GRASS ||
                            type == CLAY_HUMUS ||
                            type == CLAY_HUMUS_GRASS ||
                            type == DRY_CLAY_HUMUS_GRASS ||
                            type == EARTHENWARE_CLAY ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == SANDY_EARTHENWARE_CLAY ||
                            type == COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == EARTHENWARE_CLAY_HUMUS ||
                            type == COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == KAOLINITE_CLAY ||
                            type == SANDY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == SANDY_KAOLINITE_CLAY ||
                            type == COARSE_SANDY_KAOLINITE_CLAY ||
                            type == KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY ||
                            type == COARSE_SILTY_KAOLINITE_CLAY ||
                            type == SILTY_KAOLINITE_CLAY_LOAM ||
                            type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == KAOLINITE_CLAY_HUMUS ||
                            type == COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == DRY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == STONEWARE_CLAY ||
                            type == SANDY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == SANDY_STONEWARE_CLAY ||
                            type == COARSE_SANDY_STONEWARE_CLAY ||
                            type == STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY_LOAM ||
                            type == COARSE_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY ||
                            type == COARSE_SILTY_STONEWARE_CLAY ||
                            type == SILTY_STONEWARE_CLAY_LOAM ||
                            type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == STONEWARE_CLAY_HUMUS ||
                            type == COARSE_STONEWARE_CLAY_HUMUS ||
                            type == STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == SANDY_STONEWARE_CLAY_GRASS ||
                            type == SANDY_STONEWARE_CLAY_PODZOL ||
                            type == STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_LOAM_PODZOL ||
                            type == STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_GRASS ||
                            type == SILTY_STONEWARE_CLAY_PODZOL ||
                            type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == DRY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == STONEWARE_CLAY_HUMUS_GRASS ||
                            type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_GRASS ||
                            type == SPARSE_CLAY_GRASS ||
                            type == SPARSE_LOAMY_SAND_GRASS ||
                            type == SPARSE_SANDY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_CLAY_GRASS ||
                            type == SPARSE_LOAM_GRASS ||
                            type == SPARSE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILTY_CLAY_GRASS ||
                            type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == SPARSE_SILT_LOAM_GRASS ||
                            type == SPARSE_SILT_GRASS ||
                            type == SPARSE_HUMUS_GRASS ||
                            type == SPARSE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS) {
                        return true;
                    }
                }
                return type == FARMLAND ||
                        type == LOAMY_SAND_FARMLAND ||
                        type == SANDY_LOAM_FARMLAND ||
                        type == LOAM_FARMLAND ||
                        type == SILT_LOAM_FARMLAND ||
                        type == SILT_FARMLAND ||
                        type == HUMUS_FARMLAND;
            }
        }

        switch (plantable.getPlantType(world, pos.offset(direction))) {
            case Plains:
                return type == DIRT ||
                        type == GRASS ||
                        type == FARMLAND ||
                        type == DRY_GRASS ||
                        type == CLAY ||
                        type == CLAY_GRASS ||
                        type == ROOTED_DIRT ||
                        type == COARSE_DIRT ||
                        type == MUD ||
                        type == BOG_IRON ||
                        type == BOG_IRON_GRASS ||
                        type == DRY_BOG_IRON_GRASS ||
                        type == SPARSE_BOG_IRON_GRASS ||
                        type == BOG_IRON_PODZOL ||
                        type == LOAMY_SAND ||
                        type == SANDY_LOAM ||
                        type == LOAM ||
                        type == SILT_LOAM ||
                        type == SILT ||
                        type == SANDY_CLAY_LOAM ||
                        type == SANDY_CLAY ||
                        type == CLAY_LOAM ||
                        type == SILTY_CLAY_LOAM ||
                        type == SILTY_CLAY ||
                        type == COARSE_LOAMY_SAND ||
                        type == COARSE_SANDY_LOAM ||
                        type == COARSE_SANDY_CLAY_LOAM ||
                        type == COARSE_SANDY_CLAY ||
                        type == COARSE_LOAM ||
                        type == COARSE_CLAY_LOAM ||
                        type == COARSE_CLAY ||
                        type == COARSE_SILTY_CLAY ||
                        type == COARSE_SILTY_CLAY_LOAM ||
                        type == COARSE_SILT_LOAM ||
                        type == COARSE_SILT ||
                        type == COARSE_CLAY_HUMUS ||
                        type == PODZOL ||
                        type == LOAMY_SAND_GRASS ||
                        type == LOAMY_SAND_PODZOL ||
                        type == SANDY_LOAM_GRASS ||
                        type == SANDY_LOAM_PODZOL ||
                        type == SANDY_CLAY_LOAM_GRASS ||
                        type == SANDY_CLAY_LOAM_PODZOL ||
                        type == SANDY_CLAY_GRASS ||
                        type == SANDY_CLAY_PODZOL ||
                        type == LOAM_GRASS ||
                        type == LOAM_PODZOL ||
                        type == CLAY_LOAM_GRASS ||
                        type == CLAY_LOAM_PODZOL ||
                        type == CLAY_PODZOL ||
                        type == SILTY_CLAY_GRASS ||
                        type == SILTY_CLAY_PODZOL ||
                        type == SILTY_CLAY_LOAM_GRASS ||
                        type == SILTY_CLAY_LOAM_PODZOL ||
                        type == SILT_LOAM_GRASS ||
                        type == SILT_LOAM_PODZOL ||
                        type == SILT_GRASS ||
                        type == SILT_PODZOL ||
                        type == DRY_LOAMY_SAND_GRASS ||
                        type == DRY_SANDY_LOAM_GRASS ||
                        type == DRY_SANDY_CLAY_LOAM_GRASS ||
                        type == DRY_SANDY_CLAY_GRASS ||
                        type == DRY_LOAM_GRASS ||
                        type == DRY_CLAY_LOAM_GRASS ||
                        type == DRY_CLAY_GRASS ||
                        type == DRY_SILTY_CLAY_GRASS ||
                        type == DRY_SILTY_CLAY_LOAM_GRASS ||
                        type == DRY_SILT_LOAM_GRASS ||
                        type == DRY_SILT_GRASS ||
                        type == HUMUS ||
                        type == COARSE_HUMUS ||
                        type == HUMUS_GRASS ||
                        type == DRY_HUMUS_GRASS ||
                        type == CLAY_HUMUS ||
                        type == CLAY_HUMUS_GRASS ||
                        type == DRY_CLAY_HUMUS_GRASS ||
                        type == EARTHENWARE_CLAY ||
                        type == SANDY_EARTHENWARE_CLAY_LOAM ||
                        type == COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                        type == SANDY_EARTHENWARE_CLAY ||
                        type == COARSE_SANDY_EARTHENWARE_CLAY ||
                        type == EARTHENWARE_CLAY_LOAM ||
                        type == COARSE_EARTHENWARE_CLAY_LOAM ||
                        type == COARSE_EARTHENWARE_CLAY ||
                        type == SILTY_EARTHENWARE_CLAY ||
                        type == COARSE_SILTY_EARTHENWARE_CLAY ||
                        type == SILTY_EARTHENWARE_CLAY_LOAM ||
                        type == COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                        type == EARTHENWARE_CLAY_HUMUS ||
                        type == COARSE_EARTHENWARE_CLAY_HUMUS ||
                        type == EARTHENWARE_CLAY_GRASS ||
                        type == SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == SANDY_EARTHENWARE_CLAY_PODZOL ||
                        type == EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == EARTHENWARE_CLAY_PODZOL ||
                        type == SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == SILTY_EARTHENWARE_CLAY_PODZOL ||
                        type == SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == DRY_EARTHENWARE_CLAY_GRASS ||
                        type == DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == KAOLINITE_CLAY ||
                        type == SANDY_KAOLINITE_CLAY_LOAM ||
                        type == COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                        type == SANDY_KAOLINITE_CLAY ||
                        type == COARSE_SANDY_KAOLINITE_CLAY ||
                        type == KAOLINITE_CLAY_LOAM ||
                        type == COARSE_KAOLINITE_CLAY_LOAM ||
                        type == COARSE_KAOLINITE_CLAY ||
                        type == SILTY_KAOLINITE_CLAY ||
                        type == COARSE_SILTY_KAOLINITE_CLAY ||
                        type == SILTY_KAOLINITE_CLAY_LOAM ||
                        type == COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                        type == KAOLINITE_CLAY_HUMUS ||
                        type == COARSE_KAOLINITE_CLAY_HUMUS ||
                        type == KAOLINITE_CLAY_GRASS ||
                        type == SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == SANDY_KAOLINITE_CLAY_GRASS ||
                        type == SANDY_KAOLINITE_CLAY_PODZOL ||
                        type == KAOLINITE_CLAY_LOAM_GRASS ||
                        type == KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == KAOLINITE_CLAY_PODZOL ||
                        type == SILTY_KAOLINITE_CLAY_GRASS ||
                        type == SILTY_KAOLINITE_CLAY_PODZOL ||
                        type == SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                        type == DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == DRY_KAOLINITE_CLAY_GRASS ||
                        type == DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                        type == DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == STONEWARE_CLAY ||
                        type == SANDY_STONEWARE_CLAY_LOAM ||
                        type == COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                        type == SANDY_STONEWARE_CLAY ||
                        type == COARSE_SANDY_STONEWARE_CLAY ||
                        type == STONEWARE_CLAY_LOAM ||
                        type == COARSE_STONEWARE_CLAY_LOAM ||
                        type == COARSE_STONEWARE_CLAY ||
                        type == SILTY_STONEWARE_CLAY ||
                        type == COARSE_SILTY_STONEWARE_CLAY ||
                        type == SILTY_STONEWARE_CLAY_LOAM ||
                        type == COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                        type == STONEWARE_CLAY_HUMUS ||
                        type == COARSE_STONEWARE_CLAY_HUMUS ||
                        type == STONEWARE_CLAY_GRASS ||
                        type == SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                        type == SANDY_STONEWARE_CLAY_GRASS ||
                        type == SANDY_STONEWARE_CLAY_PODZOL ||
                        type == STONEWARE_CLAY_LOAM_GRASS ||
                        type == STONEWARE_CLAY_LOAM_PODZOL ||
                        type == STONEWARE_CLAY_PODZOL ||
                        type == SILTY_STONEWARE_CLAY_GRASS ||
                        type == SILTY_STONEWARE_CLAY_PODZOL ||
                        type == SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                        type == DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == DRY_SANDY_STONEWARE_CLAY_GRASS ||
                        type == DRY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == DRY_STONEWARE_CLAY_GRASS ||
                        type == DRY_SILTY_STONEWARE_CLAY_GRASS ||
                        type == DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == STONEWARE_CLAY_HUMUS_GRASS ||
                        type == DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                        type == LOAMY_SAND_FARMLAND ||
                        type == SANDY_LOAM_FARMLAND ||
                        type == LOAM_FARMLAND ||
                        type == SILT_LOAM_FARMLAND ||
                        type == SILT_FARMLAND ||
                        type == HUMUS_FARMLAND ||
                        type == SPARSE_GRASS ||
                        type == SPARSE_CLAY_GRASS ||
                        type == SPARSE_LOAMY_SAND_GRASS ||
                        type == SPARSE_SANDY_LOAM_GRASS ||
                        type == SPARSE_SANDY_CLAY_LOAM_GRASS ||
                        type == SPARSE_SANDY_CLAY_GRASS ||
                        type == SPARSE_LOAM_GRASS ||
                        type == SPARSE_CLAY_LOAM_GRASS ||
                        type == SPARSE_SILTY_CLAY_GRASS ||
                        type == SPARSE_SILTY_CLAY_LOAM_GRASS ||
                        type == SPARSE_SILT_LOAM_GRASS ||
                        type == SPARSE_SILT_GRASS ||
                        type == SPARSE_HUMUS_GRASS ||
                        type == SPARSE_CLAY_HUMUS_GRASS ||
                        type == SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_EARTHENWARE_CLAY_GRASS ||
                        type == SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                        type == SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == SPARSE_KAOLINITE_CLAY_GRASS ||
                        type == SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                        type == SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                        type == SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_STONEWARE_CLAY_GRASS ||
                        type == SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                        type == SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
            case Crop:
                return type == FARMLAND ||
                        type == LOAMY_SAND_FARMLAND ||
                        type == SANDY_LOAM_FARMLAND ||
                        type == LOAM_FARMLAND ||
                        type == SILT_LOAM_FARMLAND ||
                        type == SILT_FARMLAND ||
                        type == HUMUS_FARMLAND;
            case Desert:
                return type == SAND;
            case Cave:
                return true;
            case Water:
                return false;
            case Beach: {
                boolean flag = false;
                for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                    for (int i = 1; i <= beachDistance; i++)
                        if (BlocksTFC.isWater(world.getBlockState(pos.offset(facing, i)))) {
                            flag = true;
                        }
                }
                return (type == DIRT ||
                        type == GRASS ||
                        type == SAND ||
                        type == DRY_GRASS ||
                        type == ROOTED_DIRT ||
                        type == COARSE_DIRT ||
                        type == MUD ||
                        type == BOG_IRON ||
                        type == BOG_IRON_GRASS ||
                        type == DRY_BOG_IRON_GRASS ||
                        type == SPARSE_BOG_IRON_GRASS ||
                        type == BOG_IRON_PODZOL ||
                        type == LOAMY_SAND ||
                        type == SANDY_LOAM ||
                        type == LOAM ||
                        type == SILT_LOAM ||
                        type == SILT ||
                        type == COARSE_LOAMY_SAND ||
                        type == COARSE_SANDY_LOAM ||
                        type == COARSE_LOAM ||
                        type == COARSE_SILT_LOAM ||
                        type == COARSE_SILT ||
                        type == PODZOL ||
                        type == LOAMY_SAND_GRASS ||
                        type == LOAMY_SAND_PODZOL ||
                        type == SANDY_LOAM_GRASS ||
                        type == SANDY_LOAM_PODZOL ||
                        type == LOAM_GRASS ||
                        type == LOAM_PODZOL ||
                        type == SILT_LOAM_GRASS ||
                        type == SILT_LOAM_PODZOL ||
                        type == SILT_GRASS ||
                        type == SILT_PODZOL ||
                        type == DRY_LOAMY_SAND_GRASS ||
                        type == DRY_SANDY_LOAM_GRASS ||
                        type == DRY_LOAM_GRASS ||
                        type == DRY_SILT_LOAM_GRASS ||
                        type == DRY_SILT_GRASS ||
                        type == HUMUS ||
                        type == COARSE_HUMUS ||
                        type == HUMUS_GRASS ||
                        type == DRY_HUMUS_GRASS ||
                        type == SPARSE_GRASS ||
                        type == SPARSE_LOAMY_SAND_GRASS ||
                        type == SPARSE_SANDY_LOAM_GRASS ||
                        type == SPARSE_LOAM_GRASS ||
                        type == SPARSE_SILT_LOAM_GRASS ||
                        type == SPARSE_SILT_GRASS ||
                        type == SPARSE_HUMUS_GRASS
                ) && flag;
            }
            case Nether:
                return false;
        }

        return false;
    }

    public Type getType() {
        return type;
    }

    public Rock getRock() {
        return rock;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack) {
        return Size.SMALL; // Store anywhere
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack) {
        return Weight.LIGHT; // Stacksize = 32
    }

    protected void onRockSlide(World world, BlockPos pos)
    {
        switch (type)
        {
            case COARSE_DIRT:
            case MUD:
            case ROOTED_DIRT:
            case BOG_IRON:
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case BOG_IRON_PODZOL:
            case LOAMY_SAND:
            case COARSE_LOAMY_SAND:
            case SANDY_LOAM:
            case COARSE_SANDY_LOAM:
            case SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case COARSE_SANDY_CLAY:
            case LOAM:
            case COARSE_LOAM:
            case CLAY_LOAM:
            case COARSE_CLAY_LOAM:
            case COARSE_CLAY:
            case SILTY_CLAY:
            case COARSE_SILTY_CLAY:
            case SILTY_CLAY_LOAM:
            case COARSE_SILTY_CLAY_LOAM:
            case SILT_LOAM:
            case COARSE_SILT_LOAM:
            case SILT:
            case COARSE_SILT:
            case COARSE_CLAY_HUMUS:
            case PODZOL:
            case LOAMY_SAND_GRASS:
            case LOAMY_SAND_PODZOL:
            case SANDY_LOAM_GRASS:
            case SANDY_LOAM_PODZOL:
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_GRASS:
            case SANDY_CLAY_PODZOL:
            case LOAM_GRASS:
            case LOAM_PODZOL:
            case CLAY_LOAM_GRASS:
            case CLAY_LOAM_PODZOL:
            case CLAY_PODZOL:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_GRASS:
            case SILTY_CLAY_LOAM_PODZOL:
            case SILT_LOAM_GRASS:
            case SILT_LOAM_PODZOL:
            case SILT_GRASS:
            case SILT_PODZOL:
            case DRY_LOAMY_SAND_GRASS:
            case DRY_SANDY_LOAM_GRASS:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_LOAM_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_CLAY_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case DRY_SILT_LOAM_GRASS:
            case DRY_SILT_GRASS:
            case HUMUS:
            case COARSE_HUMUS:
            case COARSE_EARTHENWARE_CLAY_HUMUS:
            case COARSE_KAOLINITE_CLAY_HUMUS:
            case COARSE_STONEWARE_CLAY_HUMUS:
            case HUMUS_GRASS:
            case DRY_HUMUS_GRASS:
            case CLAY_HUMUS:
            case CLAY_HUMUS_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
            case EARTHENWARE_CLAY:
            case SANDY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SANDY_EARTHENWARE_CLAY_LOAM:
            case SANDY_EARTHENWARE_CLAY:
            case COARSE_SANDY_EARTHENWARE_CLAY:
            case EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY_LOAM:
            case COARSE_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY:
            case COARSE_SILTY_EARTHENWARE_CLAY:
            case SILTY_EARTHENWARE_CLAY_LOAM:
            case COARSE_SILTY_EARTHENWARE_CLAY_LOAM:
            case EARTHENWARE_CLAY_HUMUS:
            case EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SANDY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case SANDY_EARTHENWARE_CLAY_GRASS:
            case SANDY_EARTHENWARE_CLAY_PODZOL:
            case EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_LOAM_PODZOL:
            case EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_GRASS:
            case SILTY_EARTHENWARE_CLAY_PODZOL:
            case SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SILTY_EARTHENWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_EARTHENWARE_CLAY_GRASS:
            case DRY_EARTHENWARE_CLAY_LOAM_GRASS:
            case DRY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_GRASS:
            case DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case EARTHENWARE_CLAY_HUMUS_GRASS:
            case DRY_EARTHENWARE_CLAY_HUMUS_GRASS:
            case KAOLINITE_CLAY:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
            case KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
            case STONEWARE_CLAY:
            case SANDY_STONEWARE_CLAY_LOAM:
            case COARSE_SANDY_STONEWARE_CLAY_LOAM:
            case SANDY_STONEWARE_CLAY:
            case COARSE_SANDY_STONEWARE_CLAY:
            case STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY_LOAM:
            case COARSE_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY:
            case COARSE_SILTY_STONEWARE_CLAY:
            case SILTY_STONEWARE_CLAY_LOAM:
            case COARSE_SILTY_STONEWARE_CLAY_LOAM:
            case STONEWARE_CLAY_HUMUS:
            case STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SANDY_STONEWARE_CLAY_LOAM_PODZOL:
            case SANDY_STONEWARE_CLAY_GRASS:
            case SANDY_STONEWARE_CLAY_PODZOL:
            case STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_LOAM_PODZOL:
            case STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_GRASS:
            case SILTY_STONEWARE_CLAY_PODZOL:
            case SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SILTY_STONEWARE_CLAY_LOAM_PODZOL:
            case DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_SANDY_STONEWARE_CLAY_GRASS:
            case DRY_STONEWARE_CLAY_LOAM_GRASS:
            case DRY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_GRASS:
            case DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case STONEWARE_CLAY_HUMUS_GRASS:
            case DRY_STONEWARE_CLAY_HUMUS_GRASS:
            case SPARSE_GRASS:
            case SPARSE_CLAY_GRASS:
            case SPARSE_LOAMY_SAND_GRASS:
            case SPARSE_SANDY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_CLAY_GRASS:
            case SPARSE_LOAM_GRASS:
            case SPARSE_CLAY_LOAM_GRASS:
            case SPARSE_SILTY_CLAY_GRASS:
            case SPARSE_SILTY_CLAY_LOAM_GRASS:
            case SPARSE_SILT_LOAM_GRASS:
            case SPARSE_SILT_GRASS:
            case SPARSE_HUMUS_GRASS:
            case SPARSE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_GRASS:
            case SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS:
            case SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_KAOLINITE_CLAY_GRASS:
            case SPARSE_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_GRASS:
            case SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case SPARSE_KAOLINITE_CLAY_HUMUS_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_SANDY_STONEWARE_CLAY_GRASS:
            case SPARSE_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_GRASS:
            case SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS:
            case SPARSE_STONEWARE_CLAY_HUMUS_GRASS:
            case LOAMY_SAND_FARMLAND:
            case SANDY_LOAM_FARMLAND:
            case LOAM_FARMLAND:
            case SILT_LOAM_FARMLAND:
            case SILT_FARMLAND:
            case HUMUS_FARMLAND:
                world.playSound(null, pos, TFCSounds.DIRT_SLIDE_SHORT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                break;
        }
    }

}
