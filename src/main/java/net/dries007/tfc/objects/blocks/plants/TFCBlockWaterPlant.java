/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.plants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import git.jbredwards.fluidlogged_api.api.block.IFluidloggable;
import git.jbredwards.fluidlogged_api.api.util.FluidState;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.Constants;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.util.agriculture.Food;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.Month;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;

import static net.dries007.tfc.world.classic.ChunkGenTFC.SEA_WATER;

@ParametersAreNonnullByDefault
public class TFCBlockWaterPlant extends TFCBlockPlant implements IFluidloggable
{
    private static final AxisAlignedBB PLANT_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 1.0D, 0.875D);
    private static final Map<Plant, TFCBlockWaterPlant> MAP = new HashMap<>();

    public static TFCBlockWaterPlant get(Plant plant)
    {
        return MAP.get(plant);
    }

    public TFCBlockWaterPlant(Plant plant)
    {
        super(plant);
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");

        // plant.getOreDictName().ifPresent(name -> OreDictionaryHelper.register(this, name));
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(AGE, meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE);
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(DAYPERIOD, getDayPeriod()).withProperty(growthStageProperty, plant.getStageForMonth());
    }

    @Override
    protected boolean canSustainBush(IBlockState state)
    {
        return BlocksTFC.isGround(state) || (state.getBlock() == TFCBlockTallWaterPlant.get(plant));
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, BlockPos pos)
    {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isBlockNormalCube(IBlockState blockState)
    {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;
        Month currentMonth = CalendarTFC.CALENDAR_TIME.getMonthOfYear();
        int currentStage = state.getValue(growthStageProperty);
        int expectedStage = plant.getStageForMonth(currentMonth);
        int currentTime = state.getValue(DAYPERIOD);
        int expectedTime = getDayPeriod();

        if (currentTime != expectedTime)
        {
            worldIn.setBlockState(pos, state.withProperty(DAYPERIOD, expectedTime).withProperty(growthStageProperty, currentStage));
        }
        if (currentStage != expectedStage && random.nextDouble() < 0.5)
        {
            worldIn.setBlockState(pos, state.withProperty(DAYPERIOD, expectedTime).withProperty(growthStageProperty, expectedStage));
        }

        this.updateTick(worldIn, pos, state, random);
    }

    @Override
    public int tickRate(World worldIn)
    {
        return 10;
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, state.withProperty(DAYPERIOD, getDayPeriod()).withProperty(growthStageProperty, plant.getStageForMonth()));
        checkAndDropBlock(world, pos, state);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        // Entity X/Z motion is reduced by plants. Affine combination of age modifier and actual modifier
        if (!(entityIn instanceof EntityPlayer && ((EntityPlayer) entityIn).isCreative()))
        {
            double modifier = 0.25 * (4 - state.getValue(AGE));
            modifier = (1 - modifier) * plant.getMovementMod() + modifier;
            if (modifier < ConfigTFC.General.MISC.minimumPlantMovementModifier)
            {
                modifier = ConfigTFC.General.MISC.minimumPlantMovementModifier;
            }
            entityIn.motionX *= modifier;
            entityIn.motionZ *= modifier;
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!worldIn.isRemote)
        {
            if (!canBlockStay(worldIn, pos, state))
            {
                worldIn.destroyBlock(pos, true);
            }
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());

        if (plant.getWaterType() == SEA_WATER)
            return BlocksTFC.isSeaWater(worldIn.getBlockState(pos)) && (this.canSustainBush(soil) || BlocksTFC.isGround(soil)) && BlocksTFC.isSeaWater(worldIn.getBlockState(pos.up()));
        return BlocksTFC.isFreshWater(worldIn.getBlockState(pos)) && (this.canSustainBush(soil) || BlocksTFC.isGround(soil)) && BlocksTFC.isFreshWater(worldIn.getBlockState(pos.up()));
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        this.onBlockHarvested(world, pos, state, player);
        return world.setBlockState(pos, plant.getWaterType(), world.isRemote ? 11 : 3);
    }

    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            // this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, plant.getWaterType());
        }
    }

    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState soil = worldIn.getBlockState(pos.down());
        IBlockState up = worldIn.getBlockState(pos.up());

        if (worldIn.isAirBlock(pos.up())) return false;
        if (up.getBlock() instanceof TFCBlockTallGrassWater) return false;
        if (state.getBlock() == this)
        {
            return (soil.getBlock().canSustainPlant(soil, worldIn, pos.down(), net.minecraft.util.EnumFacing.UP, this) || BlocksTFC.isGround(soil)) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos));
        }
        return this.canSustainBush(soil);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote && (stack.getItem().getHarvestLevel(stack, "knife", player, state) != -1 || stack.getItem().getHarvestLevel(stack, "scythe", player, state) != -1))
        {
            if (Constants.RNG.nextDouble() <= (state.getValue(AGE) + 1) / 3.0D) //+33% change for each age
            {
                spawnAsEntity(worldIn, pos, new ItemStack(TFCItemFood.get(Food.SEAWEED), 1));
            }
        }
        else if (!worldIn.isRemote)
        {
            spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
        }
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (!canBlockStay(worldIn, pos, state) && placer instanceof EntityPlayer)
        {
            if (!((EntityPlayer) placer).isCreative() && !plant.getOreDictName().isPresent())
            {
                spawnAsEntity(worldIn, pos, new ItemStack(this));
            }
        }
    }

    @Override
    public @NotNull BlockStateContainer getBlockState()
    {
        return this.blockState;
    }

    @Override
    public Block.@NotNull EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    public Plant getPlant()
    {
        return plant;
    }

    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.TINY; // Store anywhere
    }

    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.VERY_LIGHT; // Stacksize = 64
    }

    public double getGrowthRate(World world, BlockPos pos)
    {
        if (world.isRainingAt(pos)) return ConfigTFC.General.MISC.plantGrowthRate * 5d;
        else return ConfigTFC.General.MISC.plantGrowthRate;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;

        if (plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidSunlight(Math.subtractExact(worldIn.getLightFor(EnumSkyBlock.SKY, pos), worldIn.getSkylightSubtracted())))
        {
            int j = state.getValue(AGE);

            if (rand.nextDouble() < getGrowthRate(worldIn, pos) && ForgeHooks.onCropsGrowPre(worldIn, pos.up(), state, true))
            {
                if (j < 3)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, j + 1));
                }
                ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }
        else if (!plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, pos)) || !plant.isValidSunlight(worldIn.getLightFor(EnumSkyBlock.SKY, pos)))
        {
            int j = state.getValue(AGE);

            if (rand.nextDouble() < getGrowthRate(worldIn, pos) && ForgeHooks.onCropsGrowPre(worldIn, pos, state, true))
            {
                if (j > 0)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, j - 1));
                }
                ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }

        checkAndDropBlock(worldIn, pos, state);
    }

    @SuppressWarnings("deprecation")
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return PLANT_AABB.offset(state.getOffset(source, pos));
    }

    @SuppressWarnings("deprecation")
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        if (plant.getMovementMod() == 0.0D)
        {
            return blockState.getBoundingBox(worldIn, pos);
        }
        else
        {
            return NULL_AABB;
        }
    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        return getDefaultState();
    }

    public Plant.TFCEnumPlantType getPlantTypeTFC()
    {
        return plant.getEnumPlantTypeTFC();
    }

    int getDayPeriod()
    {
        return CalendarTFC.CALENDAR_TIME.getHourOfDay() / (ICalendar.HOURS_IN_DAY / 4);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer)
    {
        return layer == BlockRenderLayer.TRANSLUCENT || layer == BlockRenderLayer.CUTOUT;
    }

    @SuppressWarnings("deprecation")
    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos)
    {
        if (state.getBlock() instanceof TFCBlockWaterPlant)
            return state.getBoundingBox(worldIn, pos).offset(pos);
        return null;
    }

    @Override
    public boolean canCollideCheck(IBlockState state, boolean fullHit)
    {
        return state.getBlock() instanceof TFCBlockWaterPlant && super.canCollideCheck(state, fullHit);
    }

    @Override
    public boolean isFluidloggable(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos) {
        return true;
    }

    @Override
    public boolean isFluidValid(@Nonnull IBlockState state, @Nonnull World world, @Nonnull BlockPos pos, @Nonnull Fluid fluid) {
        return isFluidloggable(state, world, pos);
    }

    @Override
    public boolean canFluidFlow(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull EnumFacing side) {
        return here.getBlockFaceShape(world, pos, side) != BlockFaceShape.SOLID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldFluidRender(@Nonnull IBlockAccess world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState fluidState) { return true; }

    @Override
    @Nonnull
    public EnumActionResult onFluidChange(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
        return newFluid.isEmpty() ? onFluidDrain(world, pos, here, blockFlags) : onFluidFill(world, pos, here, newFluid, blockFlags);
    }

    @Override
    @Nonnull
    public EnumActionResult onFluidFill(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, @Nonnull FluidState newFluid, int blockFlags) {
        return EnumActionResult.PASS;
    }

    @Override
    @Nonnull
    public EnumActionResult onFluidDrain(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState here, int blockFlags) {
        return EnumActionResult.PASS;
    }
}
