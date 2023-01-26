package net.dries007.tfc.objects.blocks.plants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.objects.blocks.plants.BlockPlant.BlockPlantDummy1;
import net.dries007.tfc.objects.items.food.TFCItemFood;
import net.dries007.tfc.util.agriculture.Food;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;
import net.dries007.tfc.api.capability.player.CapabilityPlayerData;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Plant;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.blocks.property.ITallPlant;
import net.dries007.tfc.objects.te.TETickCounter;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.climate.ClimateTFC;
import net.dries007.tfc.util.skills.SimpleSkill;
import net.dries007.tfc.util.skills.Skill;
import net.dries007.tfc.util.skills.SkillType;
import net.dries007.tfc.world.classic.WorldTypeTFC;
import net.dries007.tfc.world.classic.chunkdata.ChunkDataTFC;

import net.dries007.tfc.types.DefaultPlants;
import net.dries007.tfc.util.OreDictionaryHelper;
import org.jetbrains.annotations.NotNull;

@ParametersAreNonnullByDefault
public class TFCBlockHangingGlowingPlant extends BlockPlantDummy1 implements IGrowable, ITallPlant
{
    private static final PropertyEnum<EnumBlockPart> PART = PropertyEnum.create("part", EnumBlockPart.class);

	public static final AxisAlignedBB AABB = new AxisAlignedBB(0.25F, 0, 0.25F, 0.75F, 1, 0.75F);

    private static final Map<Plant, TFCBlockHangingGlowingPlant> MAP = new HashMap<>();

    public static TFCBlockHangingGlowingPlant get(Plant plant)
    {
        return TFCBlockHangingGlowingPlant.MAP.get(plant);
    }

    public TFCBlockHangingGlowingPlant(Plant plant)
    {
        super(plant);
        if (MAP.put(plant, this) != null) throw new IllegalStateException("There can only be one.");

        plant.getOreDictName().ifPresent(name -> OreDictionaryHelper.register(this, name));
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE) && state.getValue(AGE) >= 3)
            return 14;
        else
            return 0;
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos.down(2));
        Material material = iblockstate.getMaterial();

        int i;
        //noinspection StatementWithEmptyBody
        for (i = 1; worldIn.getBlockState(pos.up(i)).getBlock() == this; ++i) ;
        return i < plant.getMaxHeight() && worldIn.isAirBlock(pos.down()) && ((!material.isSolid() || material == Material.LEAVES)) && canBlockStay(worldIn, pos.down(), state);
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }

    @Override
    @Nonnull
    public Block.@NotNull EnumOffsetType getOffsetType()
    {
        return Block.EnumOffsetType.XZ;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        worldIn.setBlockState(pos.down(), this.getDefaultState(), 2);
        IBlockState iblockstate = state.withProperty(AGE, 0).withProperty(growthStageProperty, plant.getStageForMonth()).withProperty(PART, getPlantPart(worldIn, pos));
        worldIn.setBlockState(pos, iblockstate, 2);
        iblockstate.neighborChanged(worldIn, pos.down(), this, pos);
    }

    public void shrink(World worldIn, BlockPos pos)
    {
        worldIn.setBlockToAir(pos);
        worldIn.getBlockState(pos).neighborChanged(worldIn, pos.up(), this, pos);
    }

    @Override
    @Nonnull
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return super.getActualState(state, worldIn, pos).withProperty(PART, getPlantPart(worldIn, pos));
    }

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
		return AABB;
	}

	@Override
	public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity)
    {
		return true;
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
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        IBlockState up = worldIn.getBlockState(pos.up());
        return (up.getBlock().canSustainPlant(up, worldIn, pos.up(), net.minecraft.util.EnumFacing.DOWN, this) || isValidBlock(worldIn, pos.up(), worldIn.getBlockState(pos.up())) || worldIn.getBlockState(pos.up()).getBlock() == this) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos));
        //return this.canBlockStay(worldIn, pos, worldIn.getBlockState(pos));
        //return true;
	}

    @Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        IBlockState up = worldIn.getBlockState(pos.up());

        if (worldIn.getBlockState(pos.up(plant.getMaxHeight())).getBlock() == this) return false;
        if (state.getBlock() == this)
        {
            return (up.getBlock().canSustainPlant(up, worldIn, pos.up(), net.minecraft.util.EnumFacing.DOWN, this) || isValidBlock(worldIn, pos.up(), worldIn.getBlockState(pos.up())) || worldIn.getBlockState(pos.up()).getBlock() == this) && plant.isValidTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidRain(ChunkDataTFC.getRainfall(worldIn, pos));
        }
        return this.canSustainBush(up);
    }

	@Override
	protected BlockStateContainer createBlockState()
    {
		return new BlockStateContainer(this, new IProperty[]{PART});
	}

    @Override
    @Nonnull
    protected BlockStateContainer createPlantBlockState()
    {
        return new BlockStateContainer(this, growthStageProperty, DAYPERIOD, AGE, PART);
    }

	@Override
	public int getMetaFromState(IBlockState state)
    {
		return state.getValue(AGE);
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
    {
		return this.getDefaultState().withProperty(PART, EnumBlockPart.LOWER);
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
    {
		return this.getDefaultState().withProperty(AGE, meta);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            worldIn.destroyBlock(pos, false);
        }
	}

	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
		if (pos.getY() < WorldTypeTFC.SEALEVEL)
        {
            if (rand.nextInt(40) == 0)
            {
                float dripRange = 0.4F;
                float px = rand.nextFloat() - 0.5F;
                float py = rand.nextFloat();
                float pz = rand.nextFloat() - 0.5F;
                float u = Math.max(Math.abs(px), Math.abs(pz));
                px = px / u * dripRange + 0.5F;
                pz = pz / u * dripRange + 0.5F;
                worldIn.spawnParticle(EnumParticleTypes.DRIP_WATER, pos.getX() + px, pos.getY() + py, pos.getZ() + pz, 0, -1, 0);
            }
        }
	}

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        if (!worldIn.isAreaLoaded(pos, 1)) return;

        if (plant.isValidGrowthTemp(ClimateTFC.getActualTemp(worldIn, pos)) && plant.isValidSunlight(Math.subtractExact(worldIn.getLightFor(EnumSkyBlock.SKY, pos), worldIn.getSkylightSubtracted())))
        {
            int j = state.getValue(AGE);

            if (rand.nextDouble() < getGrowthRate(worldIn, pos) && net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos.down(), state, true))
            {
                if (j == 3 && canGrow(worldIn, pos, state, worldIn.isRemote))
                {
                    grow(worldIn, rand, pos, state);
                }
                else if (j < 3)
                {
                    worldIn.setBlockState(pos, state.withProperty(AGE, j + 1).withProperty(PART, getPlantPart(worldIn, pos)), 2);
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
                    worldIn.setBlockState(pos, state.withProperty(AGE, j - 1).withProperty(PART, getPlantPart(worldIn, pos)), 2);
                }
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state, worldIn.getBlockState(pos));
            }
        }

        checkAndDropBlock(worldIn, pos, state);
    }

    private boolean canShrink(World worldIn, BlockPos pos)
    {
        return worldIn.getBlockState(pos.up()).getBlock() == this && worldIn.getBlockState(pos.down()).getBlock() != this;
    }

	protected boolean isValidBlock(World world, BlockPos pos, IBlockState blockState)
    {
        IBlockState iblockstate = world.getBlockState(pos);
        Material material = iblockstate.getMaterial();

		return blockState.isSideSolid(world, pos, EnumFacing.DOWN) || material == Material.LEAVES || material == Material.GROUND || material == Material.ROCK || material == Material.WOOD || BlocksTFC.isGround(iblockstate) || blockState.getBlock() == this;
	}

    @Override
    public void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!this.canBlockStay(worldIn, pos, state))
        {
            worldIn.destroyBlock(pos, false);
        }
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack)
    {
        if (!worldIn.isRemote)
        {
            if (stack.getItem() == Items.SHEARS || stack.getItem().getHarvestLevel(stack, "knife", player, state) != -1 || stack.getItem().getHarvestLevel(stack, "scythe", player, state) != -1)
            {
                spawnAsEntity(worldIn, pos, new ItemStack(this, 1));
            }
        }
    }

    public static int getSkillFoodBonus(Skill skill, Random random)
    {
        return random.nextInt(2 + (int) (6 * skill.getTotalLevel()));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (playerIn != null)
        {
            SimpleSkill skill = CapabilityPlayerData.getSkill(playerIn, SkillType.AGRICULTURE);

            if (skill != null && worldIn.getBlockState(pos).getValue(AGE) >= 3 && plant == TFCRegistries.PLANTS.getValue(DefaultPlants.GLOW_VINE))
            {
                if (!worldIn.isRemote)
                {
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(TFCItemFood.get(Food.GLOWBERRY), 1 + TFCBlockHangingGlowingPlant.getSkillFoodBonus(skill, RANDOM)));
                    worldIn.setBlockState(pos, worldIn.getBlockState(pos).withProperty(AGE, 0), 2);
                    TETickCounter te = Helpers.getTE(worldIn, pos, TETickCounter.class);
                    if (te != null)
                    {
                        te.resetCounter();
                    }
                }
                return true;
            }
        }
        return false;
    }
}
