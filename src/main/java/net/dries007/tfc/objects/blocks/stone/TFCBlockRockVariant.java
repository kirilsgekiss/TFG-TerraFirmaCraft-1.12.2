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
import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
import net.dries007.tfc.objects.items.ItemsTFC;
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
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.objects.blocks.stone.farmland.*;
import net.dries007.tfc.objects.blocks.stone.path.*;
import net.dries007.tfc.objects.items.rock.ItemMud;

import static net.dries007.tfc.objects.blocks.agriculture.TFCBlockCrop.WILD;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TFCBlockRockVariant extends Block implements IItemSize {
    private static final Map<Rock, EnumMap<Type, TFCBlockRockVariant>> TABLE = new HashMap<>();

    public static TFCBlockRockVariant get(Rock rock, Type type) {
        // noinspection ConstantConditions
        if (rock == null) {
            return TABLE.get(Rock.GRANITE).get(type);
        }
        return TABLE.get(rock).get(type);
    }

    public static TFCBlockRockVariant create(Rock rock, Type type) {
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
            case ROOTED_LOAMY_SAND:
            case ROOTED_SANDY_LOAM:
            case ROOTED_LOAM:
            case ROOTED_SILT_LOAM:
            case ROOTED_SILT:
            case ROOTED_HUMUS:
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
                return new TFCBlockRockVariant(type, rock);
        }
    }

    protected final Type type;
    protected final Rock rock;

    public TFCBlockRockVariant(Type type, Rock rock) {
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
            case ROOTED_LOAMY_SAND:
            case ROOTED_SANDY_LOAM:
            case ROOTED_LOAM:
            case ROOTED_SILT_LOAM:
            case ROOTED_SILT:
            case ROOTED_HUMUS:
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
        if (type != Type.SPIKE && type != Type.ANVIL) // since spikes and anvils don't generate ItemBlocks
        {
            OreDictionaryHelper.registerRockType(this, type);
        }
    }

    public TFCBlockRockVariant getVariant(Type t) {
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
                        if (block instanceof TFCBlockRockVariant) {
                            switch (((TFCBlockRockVariant) block).type) {
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
                return ItemsTFC.EARTHENWARE_CLAY;
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
                return ItemsTFC.KAOLINITE_CLAY;
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
                return ItemsTFC.STONEWARE_CLAY;
            default:
                return super.getItemDropped(state, rand, fortune);
            case GRASS:
            case DRY_GRASS:
            case PATH:
            case PODZOL:
            case SPARSE_GRASS:
                return Item.getItemFromBlock(get(rock, Type.DIRT));
            case BOG_IRON:
            case BOG_IRON_GRASS:
            case DRY_BOG_IRON_GRASS:
            case SPARSE_BOG_IRON_GRASS:
            case SPARSE_LOAMY_SAND_GRASS:
            case DRY_LOAMY_SAND_GRASS:
            case LOAMY_SAND_GRASS:
            case LOAMY_SAND_PODZOL:
            case LOAMY_SAND_PATH:
                return Item.getItemFromBlock(get(null, Type.LOAMY_SAND));
            case SPARSE_SANDY_LOAM_GRASS:
            case DRY_SANDY_LOAM_GRASS:
            case SANDY_LOAM_GRASS:
            case SANDY_LOAM_PODZOL:
            case SANDY_LOAM_PATH:
                return Item.getItemFromBlock(get(null, Type.SANDY_LOAM));
            case SPARSE_LOAM_GRASS:
            case DRY_LOAM_GRASS:
            case LOAM_GRASS:
            case LOAM_PODZOL:
            case LOAM_PATH:
                return Item.getItemFromBlock(get(null, Type.LOAM));
            case SPARSE_SILT_LOAM_GRASS:
            case DRY_SILT_LOAM_GRASS:
            case SILT_LOAM_GRASS:
            case SILT_LOAM_PODZOL:
            case SILT_LOAM_PATH:
                return Item.getItemFromBlock(get(null, Type.SILT_LOAM));
            case SPARSE_SILT_GRASS:
            case DRY_SILT_GRASS:
            case SILT_GRASS:
            case SILT_PODZOL:
            case SILT_PATH:
                return Item.getItemFromBlock(get(null, Type.SILT));
            case SPARSE_HUMUS_GRASS:
            case HUMUS:
            case HUMUS_GRASS:
            case DRY_HUMUS_GRASS:
            case HUMUS_PATH:
                return Item.getItemFromBlock(get(null, Type.HUMUS));
            case COARSE_DIRT:
                return Item.getItemFromBlock(get(rock, Type.COARSE_DIRT));
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

        if (plantable instanceof BlockPlantTFC) {
            switch (((BlockPlantTFC) plantable).getPlantTypeTFC()) {
                case CLAY:
                    return type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.DRY_GRASS ||
                            type == Type.CLAY ||
                            type == Type.CLAY_GRASS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.SANDY_CLAY_LOAM ||
                            type == Type.SANDY_CLAY ||
                            type == Type.CLAY_LOAM ||
                            type == Type.SILTY_CLAY_LOAM ||
                            type == Type.SILTY_CLAY ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_CLAY_LOAM ||
                            type == Type.COARSE_CLAY ||
                            type == Type.COARSE_SILTY_CLAY ||
                            type == Type.COARSE_SILTY_CLAY_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_GRASS ||
                            type == Type.SANDY_CLAY_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.CLAY_LOAM_GRASS ||
                            type == Type.CLAY_LOAM_PODZOL ||
                            type == Type.CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_GRASS ||
                            type == Type.SILTY_CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_CLAY_LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.COARSE_CLAY_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.CLAY_HUMUS ||
                            type == Type.CLAY_HUMUS_GRASS ||
                            type == Type.DRY_CLAY_HUMUS_GRASS ||
                            type == Type.EARTHENWARE_CLAY ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.SANDY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == Type.EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.EARTHENWARE_CLAY_HUMUS ||
                            type == Type.COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == Type.EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.KAOLINITE_CLAY ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.SANDY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY ||
                            type == Type.KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.KAOLINITE_CLAY_HUMUS ||
                            type == Type.COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == Type.KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.STONEWARE_CLAY ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.SANDY_STONEWARE_CLAY ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY ||
                            type == Type.STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.STONEWARE_CLAY_HUMUS ||
                            type == Type.COARSE_STONEWARE_CLAY_HUMUS ||
                            type == Type.STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_PODZOL ||
                            type == Type.STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_CLAY_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS ||
                            type == Type.SPARSE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
                case DESERT_CLAY:
                    return type == Type.SAND ||
                            type == Type.CLAY ||
                            type == Type.CLAY_GRASS ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.SANDY_CLAY_LOAM ||
                            type == Type.SANDY_CLAY ||
                            type == Type.CLAY_LOAM ||
                            type == Type.SILTY_CLAY_LOAM ||
                            type == Type.SILTY_CLAY ||
                            type == Type.CLAY_HUMUS ||
                            type == Type.COARSE_SANDY_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY ||
                            type == Type.COARSE_CLAY_LOAM ||
                            type == Type.COARSE_CLAY ||
                            type == Type.COARSE_SILTY_CLAY ||
                            type == Type.COARSE_SILTY_CLAY_LOAM ||
                            type == Type.COARSE_CLAY_HUMUS ||
                            type == Type.COARSE_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == Type.COARSE_KAOLINITE_CLAY ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY ||
                            type == Type.COARSE_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == Type.COARSE_STONEWARE_CLAY ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY |
                                    type == Type.COARSE_STONEWARE_CLAY_LOAM
                            ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY_HUMUS;
                case DRY_CLAY:
                    return type == Type.DIRT ||
                            type == Type.DRY_GRASS ||
                            type == Type.SAND ||
                            type == Type.CLAY ||
                            type == Type.CLAY_GRASS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.SANDY_CLAY_LOAM ||
                            type == Type.SANDY_CLAY ||
                            type == Type.CLAY_LOAM ||
                            type == Type.SILTY_CLAY_LOAM ||
                            type == Type.SILTY_CLAY ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_CLAY_LOAM ||
                            type == Type.COARSE_CLAY ||
                            type == Type.COARSE_SILTY_CLAY ||
                            type == Type.COARSE_SILTY_CLAY_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.COARSE_CLAY_HUMUS ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_GRASS ||
                            type == Type.SANDY_CLAY_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.CLAY_LOAM_GRASS ||
                            type == Type.CLAY_LOAM_PODZOL ||
                            type == Type.CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_GRASS ||
                            type == Type.SILTY_CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_CLAY_LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == Type.COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == Type.COARSE_STONEWARE_CLAY_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.CLAY_HUMUS ||
                            type == Type.CLAY_HUMUS_GRASS ||
                            type == Type.DRY_CLAY_HUMUS_GRASS ||
                            type == Type.EARTHENWARE_CLAY ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.SANDY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == Type.EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.EARTHENWARE_CLAY_HUMUS ||
                            type == Type.EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.KAOLINITE_CLAY ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.SANDY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY ||
                            type == Type.KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.KAOLINITE_CLAY_HUMUS ||
                            type == Type.KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.STONEWARE_CLAY ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.SANDY_STONEWARE_CLAY ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY ||
                            type == Type.STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.STONEWARE_CLAY_HUMUS ||
                            type == Type.STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_PODZOL ||
                            type == Type.STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_CLAY_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS ||
                            type == Type.SPARSE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
                case DRY:
                    return type == Type.DIRT ||
                            type == Type.DRY_GRASS ||
                            type == Type.SAND ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS;
                case WATER:
                    return type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.DRY_GRASS ||
                            type == Type.GRAVEL ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS;
                case SEA_WATER:
                    return type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.DRY_GRASS ||
                            type == Type.SAND ||
                            type == Type.GRAVEL ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS;
                case FRESH_BEACH: {
                    boolean flag = false;
                    for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                        for (int i = 1; i <= beachDistance; i++) {
                            if (TFCBlocks.isFreshWaterOrIce(world.getBlockState(pos.offset(facing, i)))) {
                                flag = true;
                                break;
                            }
                        }
                    }
                    return (type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.SAND ||
                            type == Type.DRY_GRASS ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS) && flag;
                }
                case SALT_BEACH: {
                    boolean flag = false;
                    for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                        for (int i = 1; i <= beachDistance; i++)
                            if (TFCBlocks.isSeaWater(world.getBlockState(pos.offset(facing, i)))) {
                                flag = true;
                            }
                    }
                    return (type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.SAND ||
                            type == Type.DRY_GRASS ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.COARSE_DIRT ||
                            type == Type.MUD ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS) && flag;
                }
            }
        } else if (plantable instanceof TFCBlockCrop) {
            IBlockState cropState = world.getBlockState(pos.up());
            if (cropState.getBlock() instanceof TFCBlockCrop) {
                boolean isWild = cropState.getValue(WILD);
                if (isWild) {
                    if (type == Type.DIRT ||
                            type == Type.GRASS ||
                            type == Type.DRY_GRASS ||
                            type == Type.CLAY_GRASS ||
                            type == Type.ROOTED_DIRT ||
                            type == Type.ROOTED_LOAMY_SAND ||
                            type == Type.ROOTED_SANDY_LOAM ||
                            type == Type.ROOTED_LOAM ||
                            type == Type.ROOTED_SILT_LOAM ||
                            type == Type.ROOTED_SILT ||
                            type == Type.ROOTED_HUMUS ||
                            type == Type.BOG_IRON ||
                            type == Type.BOG_IRON_GRASS ||
                            type == Type.DRY_BOG_IRON_GRASS ||
                            type == Type.SPARSE_BOG_IRON_GRASS ||
                            type == Type.BOG_IRON_PODZOL ||
                            type == Type.COARSE_DIRT ||
                            type == Type.LOAMY_SAND ||
                            type == Type.SANDY_LOAM ||
                            type == Type.LOAM ||
                            type == Type.SILT_LOAM ||
                            type == Type.SILT ||
                            type == Type.SANDY_CLAY_LOAM ||
                            type == Type.SANDY_CLAY ||
                            type == Type.CLAY_LOAM ||
                            type == Type.SILTY_CLAY_LOAM ||
                            type == Type.SILTY_CLAY ||
                            type == Type.COARSE_LOAMY_SAND ||
                            type == Type.COARSE_SANDY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_CLAY ||
                            type == Type.COARSE_LOAM ||
                            type == Type.COARSE_CLAY_LOAM ||
                            type == Type.COARSE_CLAY ||
                            type == Type.COARSE_SILTY_CLAY ||
                            type == Type.COARSE_SILTY_CLAY_LOAM ||
                            type == Type.COARSE_SILT_LOAM ||
                            type == Type.COARSE_SILT ||
                            type == Type.COARSE_CLAY_HUMUS ||
                            type == Type.PODZOL ||
                            type == Type.LOAMY_SAND_GRASS ||
                            type == Type.LOAMY_SAND_PODZOL ||
                            type == Type.SANDY_LOAM_GRASS ||
                            type == Type.SANDY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_CLAY_GRASS ||
                            type == Type.SANDY_CLAY_PODZOL ||
                            type == Type.LOAM_GRASS ||
                            type == Type.LOAM_PODZOL ||
                            type == Type.CLAY_LOAM_GRASS ||
                            type == Type.CLAY_LOAM_PODZOL ||
                            type == Type.CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_GRASS ||
                            type == Type.SILTY_CLAY_PODZOL ||
                            type == Type.SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_CLAY_LOAM_PODZOL ||
                            type == Type.SILT_LOAM_GRASS ||
                            type == Type.SILT_LOAM_PODZOL ||
                            type == Type.SILT_GRASS ||
                            type == Type.SILT_PODZOL ||
                            type == Type.DRY_LOAMY_SAND_GRASS ||
                            type == Type.DRY_SANDY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_CLAY_GRASS ||
                            type == Type.DRY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_GRASS ||
                            type == Type.DRY_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SILT_LOAM_GRASS ||
                            type == Type.DRY_SILT_GRASS ||
                            type == Type.HUMUS ||
                            type == Type.COARSE_HUMUS ||
                            type == Type.HUMUS_GRASS ||
                            type == Type.DRY_HUMUS_GRASS ||
                            type == Type.CLAY_HUMUS ||
                            type == Type.CLAY_HUMUS_GRASS ||
                            type == Type.DRY_CLAY_HUMUS_GRASS ||
                            type == Type.EARTHENWARE_CLAY ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.SANDY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SANDY_EARTHENWARE_CLAY ||
                            type == Type.EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                            type == Type.EARTHENWARE_CLAY_HUMUS ||
                            type == Type.COARSE_EARTHENWARE_CLAY_HUMUS ||
                            type == Type.EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SANDY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_PODZOL ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.KAOLINITE_CLAY ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                            type == Type.SANDY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SANDY_KAOLINITE_CLAY ||
                            type == Type.KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                            type == Type.KAOLINITE_CLAY_HUMUS ||
                            type == Type.COARSE_KAOLINITE_CLAY_HUMUS ||
                            type == Type.KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SANDY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_PODZOL ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.STONEWARE_CLAY ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                            type == Type.SANDY_STONEWARE_CLAY ||
                            type == Type.COARSE_SANDY_STONEWARE_CLAY ||
                            type == Type.STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                            type == Type.STONEWARE_CLAY_HUMUS ||
                            type == Type.COARSE_STONEWARE_CLAY_HUMUS ||
                            type == Type.STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SANDY_STONEWARE_CLAY_PODZOL ||
                            type == Type.STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_PODZOL ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_GRASS ||
                            type == Type.SPARSE_CLAY_GRASS ||
                            type == Type.SPARSE_LOAMY_SAND_GRASS ||
                            type == Type.SPARSE_SANDY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_CLAY_GRASS ||
                            type == Type.SPARSE_LOAM_GRASS ||
                            type == Type.SPARSE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_LOAM_GRASS ||
                            type == Type.SPARSE_SILT_GRASS ||
                            type == Type.SPARSE_HUMUS_GRASS ||
                            type == Type.SPARSE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                            type == Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                            type == Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS) {
                        return true;
                    }
                }
                return type == Type.FARMLAND ||
                        type == Type.LOAMY_SAND_FARMLAND ||
                        type == Type.SANDY_LOAM_FARMLAND ||
                        type == Type.LOAM_FARMLAND ||
                        type == Type.SILT_LOAM_FARMLAND ||
                        type == Type.SILT_FARMLAND ||
                        type == Type.HUMUS_FARMLAND;
            }
        }

        switch (plantable.getPlantType(world, pos.offset(direction))) {
            case Plains:
                return type == Type.DIRT ||
                        type == Type.GRASS ||
                        type == Type.FARMLAND ||
                        type == Type.DRY_GRASS ||
                        type == Type.CLAY ||
                        type == Type.CLAY_GRASS ||
                        type == Type.ROOTED_DIRT ||
                        type == Type.ROOTED_LOAMY_SAND ||
                        type == Type.ROOTED_SANDY_LOAM ||
                        type == Type.ROOTED_LOAM ||
                        type == Type.ROOTED_SILT_LOAM ||
                        type == Type.ROOTED_SILT ||
                        type == Type.ROOTED_HUMUS ||
                        type == Type.COARSE_DIRT ||
                        type == Type.MUD ||
                        type == Type.BOG_IRON ||
                        type == Type.BOG_IRON_GRASS ||
                        type == Type.DRY_BOG_IRON_GRASS ||
                        type == Type.SPARSE_BOG_IRON_GRASS ||
                        type == Type.BOG_IRON_PODZOL ||
                        type == Type.LOAMY_SAND ||
                        type == Type.SANDY_LOAM ||
                        type == Type.LOAM ||
                        type == Type.SILT_LOAM ||
                        type == Type.SILT ||
                        type == Type.SANDY_CLAY_LOAM ||
                        type == Type.SANDY_CLAY ||
                        type == Type.CLAY_LOAM ||
                        type == Type.SILTY_CLAY_LOAM ||
                        type == Type.SILTY_CLAY ||
                        type == Type.COARSE_LOAMY_SAND ||
                        type == Type.COARSE_SANDY_LOAM ||
                        type == Type.COARSE_SANDY_CLAY_LOAM ||
                        type == Type.COARSE_SANDY_CLAY ||
                        type == Type.COARSE_LOAM ||
                        type == Type.COARSE_CLAY_LOAM ||
                        type == Type.COARSE_CLAY ||
                        type == Type.COARSE_SILTY_CLAY ||
                        type == Type.COARSE_SILTY_CLAY_LOAM ||
                        type == Type.COARSE_SILT_LOAM ||
                        type == Type.COARSE_SILT ||
                        type == Type.COARSE_CLAY_HUMUS ||
                        type == Type.PODZOL ||
                        type == Type.LOAMY_SAND_GRASS ||
                        type == Type.LOAMY_SAND_PODZOL ||
                        type == Type.SANDY_LOAM_GRASS ||
                        type == Type.SANDY_LOAM_PODZOL ||
                        type == Type.SANDY_CLAY_LOAM_GRASS ||
                        type == Type.SANDY_CLAY_LOAM_PODZOL ||
                        type == Type.SANDY_CLAY_GRASS ||
                        type == Type.SANDY_CLAY_PODZOL ||
                        type == Type.LOAM_GRASS ||
                        type == Type.LOAM_PODZOL ||
                        type == Type.CLAY_LOAM_GRASS ||
                        type == Type.CLAY_LOAM_PODZOL ||
                        type == Type.CLAY_PODZOL ||
                        type == Type.SILTY_CLAY_GRASS ||
                        type == Type.SILTY_CLAY_PODZOL ||
                        type == Type.SILTY_CLAY_LOAM_GRASS ||
                        type == Type.SILTY_CLAY_LOAM_PODZOL ||
                        type == Type.SILT_LOAM_GRASS ||
                        type == Type.SILT_LOAM_PODZOL ||
                        type == Type.SILT_GRASS ||
                        type == Type.SILT_PODZOL ||
                        type == Type.DRY_LOAMY_SAND_GRASS ||
                        type == Type.DRY_SANDY_LOAM_GRASS ||
                        type == Type.DRY_SANDY_CLAY_LOAM_GRASS ||
                        type == Type.DRY_SANDY_CLAY_GRASS ||
                        type == Type.DRY_LOAM_GRASS ||
                        type == Type.DRY_CLAY_LOAM_GRASS ||
                        type == Type.DRY_CLAY_GRASS ||
                        type == Type.DRY_SILTY_CLAY_GRASS ||
                        type == Type.DRY_SILTY_CLAY_LOAM_GRASS ||
                        type == Type.DRY_SILT_LOAM_GRASS ||
                        type == Type.DRY_SILT_GRASS ||
                        type == Type.HUMUS ||
                        type == Type.COARSE_HUMUS ||
                        type == Type.HUMUS_GRASS ||
                        type == Type.DRY_HUMUS_GRASS ||
                        type == Type.CLAY_HUMUS ||
                        type == Type.CLAY_HUMUS_GRASS ||
                        type == Type.DRY_CLAY_HUMUS_GRASS ||
                        type == Type.EARTHENWARE_CLAY ||
                        type == Type.SANDY_EARTHENWARE_CLAY_LOAM ||
                        type == Type.COARSE_SANDY_EARTHENWARE_CLAY_LOAM ||
                        type == Type.SANDY_EARTHENWARE_CLAY ||
                        type == Type.COARSE_SANDY_EARTHENWARE_CLAY ||
                        type == Type.EARTHENWARE_CLAY_LOAM ||
                        type == Type.COARSE_EARTHENWARE_CLAY_LOAM ||
                        type == Type.COARSE_EARTHENWARE_CLAY ||
                        type == Type.SILTY_EARTHENWARE_CLAY ||
                        type == Type.COARSE_SILTY_EARTHENWARE_CLAY ||
                        type == Type.SILTY_EARTHENWARE_CLAY_LOAM ||
                        type == Type.COARSE_SILTY_EARTHENWARE_CLAY_LOAM ||
                        type == Type.EARTHENWARE_CLAY_HUMUS ||
                        type == Type.COARSE_EARTHENWARE_CLAY_HUMUS ||
                        type == Type.EARTHENWARE_CLAY_GRASS ||
                        type == Type.SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.SANDY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == Type.SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.SANDY_EARTHENWARE_CLAY_PODZOL ||
                        type == Type.EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == Type.EARTHENWARE_CLAY_PODZOL ||
                        type == Type.SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.SILTY_EARTHENWARE_CLAY_PODZOL ||
                        type == Type.SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.SILTY_EARTHENWARE_CLAY_LOAM_PODZOL ||
                        type == Type.DRY_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.DRY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == Type.DRY_EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == Type.KAOLINITE_CLAY ||
                        type == Type.SANDY_KAOLINITE_CLAY_LOAM ||
                        type == Type.COARSE_SANDY_KAOLINITE_CLAY_LOAM ||
                        type == Type.SANDY_KAOLINITE_CLAY ||
                        type == Type.COARSE_SANDY_KAOLINITE_CLAY ||
                        type == Type.KAOLINITE_CLAY_LOAM ||
                        type == Type.COARSE_KAOLINITE_CLAY_LOAM ||
                        type == Type.COARSE_KAOLINITE_CLAY ||
                        type == Type.SILTY_KAOLINITE_CLAY ||
                        type == Type.COARSE_SILTY_KAOLINITE_CLAY ||
                        type == Type.SILTY_KAOLINITE_CLAY_LOAM ||
                        type == Type.COARSE_SILTY_KAOLINITE_CLAY_LOAM ||
                        type == Type.KAOLINITE_CLAY_HUMUS ||
                        type == Type.COARSE_KAOLINITE_CLAY_HUMUS ||
                        type == Type.KAOLINITE_CLAY_GRASS ||
                        type == Type.SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.SANDY_KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == Type.SANDY_KAOLINITE_CLAY_GRASS ||
                        type == Type.SANDY_KAOLINITE_CLAY_PODZOL ||
                        type == Type.KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == Type.KAOLINITE_CLAY_PODZOL ||
                        type == Type.SILTY_KAOLINITE_CLAY_GRASS ||
                        type == Type.SILTY_KAOLINITE_CLAY_PODZOL ||
                        type == Type.SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.SILTY_KAOLINITE_CLAY_LOAM_PODZOL ||
                        type == Type.DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_SANDY_KAOLINITE_CLAY_GRASS ||
                        type == Type.DRY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_KAOLINITE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_KAOLINITE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == Type.DRY_KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == Type.STONEWARE_CLAY ||
                        type == Type.SANDY_STONEWARE_CLAY_LOAM ||
                        type == Type.COARSE_SANDY_STONEWARE_CLAY_LOAM ||
                        type == Type.SANDY_STONEWARE_CLAY ||
                        type == Type.COARSE_SANDY_STONEWARE_CLAY ||
                        type == Type.STONEWARE_CLAY_LOAM ||
                        type == Type.COARSE_STONEWARE_CLAY_LOAM ||
                        type == Type.COARSE_STONEWARE_CLAY ||
                        type == Type.SILTY_STONEWARE_CLAY ||
                        type == Type.COARSE_SILTY_STONEWARE_CLAY ||
                        type == Type.SILTY_STONEWARE_CLAY_LOAM ||
                        type == Type.COARSE_SILTY_STONEWARE_CLAY_LOAM ||
                        type == Type.STONEWARE_CLAY_HUMUS ||
                        type == Type.COARSE_STONEWARE_CLAY_HUMUS ||
                        type == Type.STONEWARE_CLAY_GRASS ||
                        type == Type.SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.SANDY_STONEWARE_CLAY_LOAM_PODZOL ||
                        type == Type.SANDY_STONEWARE_CLAY_GRASS ||
                        type == Type.SANDY_STONEWARE_CLAY_PODZOL ||
                        type == Type.STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.STONEWARE_CLAY_LOAM_PODZOL ||
                        type == Type.STONEWARE_CLAY_PODZOL ||
                        type == Type.SILTY_STONEWARE_CLAY_GRASS ||
                        type == Type.SILTY_STONEWARE_CLAY_PODZOL ||
                        type == Type.SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.SILTY_STONEWARE_CLAY_LOAM_PODZOL ||
                        type == Type.DRY_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_SANDY_STONEWARE_CLAY_GRASS ||
                        type == Type.DRY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.DRY_STONEWARE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_STONEWARE_CLAY_GRASS ||
                        type == Type.DRY_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.STONEWARE_CLAY_HUMUS_GRASS ||
                        type == Type.DRY_STONEWARE_CLAY_HUMUS_GRASS ||
                        type == Type.LOAMY_SAND_FARMLAND ||
                        type == Type.SANDY_LOAM_FARMLAND ||
                        type == Type.LOAM_FARMLAND ||
                        type == Type.SILT_LOAM_FARMLAND ||
                        type == Type.SILT_FARMLAND ||
                        type == Type.HUMUS_FARMLAND ||
                        type == Type.SPARSE_GRASS ||
                        type == Type.SPARSE_CLAY_GRASS ||
                        type == Type.SPARSE_LOAMY_SAND_GRASS ||
                        type == Type.SPARSE_SANDY_LOAM_GRASS ||
                        type == Type.SPARSE_SANDY_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SANDY_CLAY_GRASS ||
                        type == Type.SPARSE_LOAM_GRASS ||
                        type == Type.SPARSE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SILTY_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SILT_LOAM_GRASS ||
                        type == Type.SPARSE_SILT_GRASS ||
                        type == Type.SPARSE_HUMUS_GRASS ||
                        type == Type.SPARSE_CLAY_HUMUS_GRASS ||
                        type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SANDY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.SPARSE_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_EARTHENWARE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_EARTHENWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_EARTHENWARE_CLAY_HUMUS_GRASS ||
                        type == Type.SPARSE_SANDY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SANDY_KAOLINITE_CLAY_GRASS ||
                        type == Type.SPARSE_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_KAOLINITE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_KAOLINITE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_KAOLINITE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_KAOLINITE_CLAY_HUMUS_GRASS ||
                        type == Type.SPARSE_SANDY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_SANDY_STONEWARE_CLAY_GRASS ||
                        type == Type.SPARSE_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_STONEWARE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_STONEWARE_CLAY_GRASS ||
                        type == Type.SPARSE_SILTY_STONEWARE_CLAY_LOAM_GRASS ||
                        type == Type.SPARSE_STONEWARE_CLAY_HUMUS_GRASS;
            case Crop:
                return type == Type.FARMLAND ||
                        type == Type.LOAMY_SAND_FARMLAND ||
                        type == Type.SANDY_LOAM_FARMLAND ||
                        type == Type.LOAM_FARMLAND ||
                        type == Type.SILT_LOAM_FARMLAND ||
                        type == Type.SILT_FARMLAND ||
                        type == Type.HUMUS_FARMLAND;
            case Desert:
                return type == Type.SAND;
            case Cave:
                return true;
            case Water:
                return false;
            case Beach: {
                boolean flag = false;
                for (EnumFacing facing : EnumFacing.HORIZONTALS) {
                    for (int i = 1; i <= beachDistance; i++)
                        if (TFCBlocks.isWater(world.getBlockState(pos.offset(facing, i)))) {
                            flag = true;
                        }
                }
                return (type == Type.DIRT ||
                        type == Type.GRASS ||
                        type == Type.SAND ||
                        type == Type.DRY_GRASS ||
                        type == Type.ROOTED_DIRT ||
                        type == Type.ROOTED_LOAMY_SAND ||
                        type == Type.ROOTED_SANDY_LOAM ||
                        type == Type.ROOTED_LOAM ||
                        type == Type.ROOTED_SILT_LOAM ||
                        type == Type.ROOTED_SILT ||
                        type == Type.ROOTED_HUMUS ||
                        type == Type.COARSE_DIRT ||
                        type == Type.MUD ||
                        type == Type.BOG_IRON ||
                        type == Type.BOG_IRON_GRASS ||
                        type == Type.DRY_BOG_IRON_GRASS ||
                        type == Type.SPARSE_BOG_IRON_GRASS ||
                        type == Type.BOG_IRON_PODZOL ||
                        type == Type.LOAMY_SAND ||
                        type == Type.SANDY_LOAM ||
                        type == Type.LOAM ||
                        type == Type.SILT_LOAM ||
                        type == Type.SILT ||
                        type == Type.COARSE_LOAMY_SAND ||
                        type == Type.COARSE_SANDY_LOAM ||
                        type == Type.COARSE_LOAM ||
                        type == Type.COARSE_SILT_LOAM ||
                        type == Type.COARSE_SILT ||
                        type == Type.PODZOL ||
                        type == Type.LOAMY_SAND_GRASS ||
                        type == Type.LOAMY_SAND_PODZOL ||
                        type == Type.SANDY_LOAM_GRASS ||
                        type == Type.SANDY_LOAM_PODZOL ||
                        type == Type.LOAM_GRASS ||
                        type == Type.LOAM_PODZOL ||
                        type == Type.SILT_LOAM_GRASS ||
                        type == Type.SILT_LOAM_PODZOL ||
                        type == Type.SILT_GRASS ||
                        type == Type.SILT_PODZOL ||
                        type == Type.DRY_LOAMY_SAND_GRASS ||
                        type == Type.DRY_SANDY_LOAM_GRASS ||
                        type == Type.DRY_LOAM_GRASS ||
                        type == Type.DRY_SILT_LOAM_GRASS ||
                        type == Type.DRY_SILT_GRASS ||
                        type == Type.HUMUS ||
                        type == Type.COARSE_HUMUS ||
                        type == Type.HUMUS_GRASS ||
                        type == Type.DRY_HUMUS_GRASS ||
                        type == Type.SPARSE_GRASS ||
                        type == Type.SPARSE_LOAMY_SAND_GRASS ||
                        type == Type.SPARSE_SANDY_LOAM_GRASS ||
                        type == Type.SPARSE_LOAM_GRASS ||
                        type == Type.SPARSE_SILT_LOAM_GRASS ||
                        type == Type.SPARSE_SILT_GRASS ||
                        type == Type.SPARSE_HUMUS_GRASS
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
            case ROOTED_LOAMY_SAND:
            case ROOTED_SANDY_LOAM:
            case ROOTED_LOAM:
            case ROOTED_SILT_LOAM:
            case ROOTED_SILT:
            case ROOTED_HUMUS:
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
