package net.dries007.tfc.objects.blocks.plants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.util.OreDictionaryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.dries007.tfc.Constants;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.property.ITallPlant;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

@ParametersAreNonnullByDefault
public class BlockTallGrassWater extends BlockShortGrassTFCF implements IGrowable, ITallPlant
{
    private static final PropertyEnum<BlockTallGrassWater.EnumBlockPart> PART = PropertyEnum.create("part", BlockTallGrassWater.EnumBlockPart.class);
    private static final Map<Plant, BlockTallGrassWater> MAP = new HashMap<>();

    public static BlockTallGrassWater get(Plant plant)
    {
        return BlockTallGrassWater.MAP.get(plant);
    }

    public BlockTallGrassWater(Plant plant)
    {
        super(plant);
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");
    }

    @Override
    @Nonnull
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return super.getActualState(state, worldIn, pos).withProperty(PART, getPlantPart(worldIn, pos));
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return (TFCBlocks.isWater(state) || state.getMaterial() == Material.ICE && state == plant.getWaterType()) || (state.getMaterial() == Material.CORAL && !(state.getBlock() instanceof BlockEmergentTallWaterPlantTFC));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());

        if (worldIn.getBlockState(pos.down(plant.getMaxHeight())).getBlock() == this) return false;
        if (soil.getBlock() instanceof BlockWaterPlantTFCF || soil.getBlock() instanceof BlockWaterPlantTFC) return false;
        if (state.getBlock() == this)
        {
            IBlockState stateDown = worldIn.getBlockState(pos.down());
            Material material = stateDown.getMaterial();
            return ((soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this)) || ((material == Material.WATER && stateDown.getValue(BlockLiquid.LEVEL) == 0 && stateDown == plant.getWaterType()) || material == Material.ICE || (material == Material.CORAL && !(state.getBlock() instanceof BlockEmergentTallWaterPlantTFC)))) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos));
        }
        else
        {
            return this.canSustainBush(soil);
        }
    }

    /*@Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
    }

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        if (worldIn.getBlockState(pos.down(plant.getMaxHeight())).getBlock() == this) return false;

        IBlockState stateDown = worldIn.getBlockState(pos.down());
        if (state.getBlock() == this)
        {
            return sustain(stateDown) || (stateDown.getBlock().canSustainPlant(stateDown, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos)));
        }
        return sustain(stateDown);
    }

    private boolean sustain(IBlockState state)
    {
        Material material = state.getMaterial();
        return (material == Material.WATER && state.getValue(BlockLiquid.LEVEL) == 0 && state == FRESH_WATER) || material == Material.ICE || (material == Material.CORAL && !(state.getBlock() instanceof BlockEmergentTallWaterPlantTFC));
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return (BlocksTFC.isWater(state) || state.getMaterial() == Material.ICE && state == plant.getWaterType()) || (state.getMaterial() == Material.CORAL && !(state.getBlock() instanceof BlockEmergentTallWaterPlantTFC));
    }*/

    /*public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());

        if (worldIn.getBlockState(pos.down(plant.getMaxHeight())).getBlock() == this) return false;
        if (state.getBlock() == this)
        {
            return soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos));
        }
        return this.canSustainBush(soil);
    }*/

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (getPlantPart(worldIn, pos) == EnumBlockPart.LOWER)
        {
            worldIn.setBlockState(pos, state.withProperty(AGE, worldIn.getBlockState(pos.up()).getValue(AGE)));
        }

        if (!this.canBlockStay(worldIn, pos, state))
        {
            worldIn.destroyBlock(pos, true);
        }
    }

    @Override
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            if (getPlantPart(worldIn, pos) != EnumBlockPart.UPPER)
            {
                this.dropBlockAsItem(worldIn, pos, state, 0);
            }
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        int i;
        //noinspection StatementWithEmptyBody
        for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) ;
        return i < plant.getMaxHeight() && worldIn.isAirBlock(pos.up()) && canBlockStay(worldIn, pos.up(), state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.up(), this.getDefaultState());
        IBlockState iblockstate = state.withProperty(AGE, 0).withProperty(growthStageProperty, plant.getStageForMonth()).withProperty(PART, getPlantPart(worldIn, pos));
        worldIn.setBlockState(pos, iblockstate);
        iblockstate.neighborChanged(worldIn, pos.up(), this, pos);
    }

    public void shrink(World worldIn, BlockPos pos)
    {
        worldIn.setBlockToAir(pos);
        worldIn.getBlockState(pos).neighborChanged(worldIn, pos.down(), this, pos);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (!worldIn.isRemote && player != null)
        {
            ItemStack stack = player.getHeldItemMainhand();
            if (OreDictionaryHelper.doesStackMatchOre(stack, "craftingToolKnife"))
            {
                for (int i = 1; worldIn.getBlockState(pos.up(i)).getBlock() == this; ++i)
                {
                    if (Constants.RNG.nextDouble() <= (worldIn.getBlockState(pos.up(i)).getValue(AGE) + 1) / 4.0D) //+25% change for each age
                    {
                        spawnAsEntity(worldIn, pos, new ItemStack(ItemsTFC.STRAW, 1));
                    }
                }
            }
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        this.onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, net.minecraft.init.Blocks.AIR.getDefaultState(), world.isRemote ? 11 : 3);
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable)
    {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));

        if (plant.getBlock() == this)
        {
            return true;
        }
        return super.canSustainPlant(state, world, pos, direction, plantable);
    }

    @Override
    @Nonnull
    public Block.EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;

        if (plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidSunlight(Math.subtractExact(worldIn.getLightFor(EnumSkyBlock.SKY, pos), worldIn.getSkylightSubtracted())))
        {
            int j = state.getValue(AGE);

            if (rand.nextDouble() < getGrowthRate(worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.up(), state, true))
            {
                if (j == 3 && canGrow(worldIn, pos, state, worldIn.isRemote))
                {
                    grow(worldIn, rand, pos, state);
                }
                else if (j < 3)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, j + 1).withProperty(PART, getPlantPart(worldIn, pos)));
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
        else if (!plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, pos)) || !plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, pos)))
        {
            int j = state.getValue(AGE);

            if (rand.nextDouble() < getGrowthRate(worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
            {
                if (j == 0 && canShrink(worldIn, pos))
                {
                    shrink(worldIn, pos);
                }
                else if (j > 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, j - 1).withProperty(PART, getPlantPart(worldIn, pos)));
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }

        checkAndDropBlock(worldIn, pos, state);
    }

    @Override
    @Nonnull
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return getTallBoundingBax(state.getValue(AGE), state, source, pos);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createPlantBlockState()
    {
        return new BlockStateContainer(this, AGE, growthStageProperty, DAYPERIOD, PART);
    }

    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @Override
    @Nonnull
    public NonNullList<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune)
    {
        return NonNullList.withSize(1, new ItemStack(this, 1));
    }

    private boolean canShrink(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.down()).getBlock() == this && worldIn.getBlockState(pos.up()).getBlock() != this;
    }
}
