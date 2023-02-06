package net.dries007.tfc.objects.blocks.rock;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.objects.items.rock.ItemMud;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TFCBlockRockMud extends TFCBlockRockVariant {
    protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.6D, 1.0D);

    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return MUD_AABB;
    }

    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
        entityIn.motionX *= 0.7D;
        entityIn.motionZ *= 0.7D;
    }

    public static boolean isSupportingSideBlock(IBlockState state) {
        return state.isNormalCube() || (state.getBlock() instanceof TFCBlockRockVariant);
    }

    public TFCBlockRockMud(Type type, Rock rock) {
        super(type, rock);

        if (type.canFall()) {
            FallingBlockManager.Specification spec = new FallingBlockManager.Specification(type.getFallingSpecification());
            FallingBlockManager.registerFallable(this, type.getFallingSpecification());
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
        if (this.type.canFall() && rand.nextInt(16) == 0 && FallingBlockManager.shouldFall(world, pos, pos, state, false)) {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (float) pos.getZ() + rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(state));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (type == Type.MUD) {
            if (fortune > 3) {
                fortune = 3;
            }

            if (rand.nextInt(10 - fortune * 3) == 0) {
                return ItemMud.get(rock);
            }
        }
        return super.getItemDropped(state, rand, fortune);
    }

    @Nullable
    private BlockPos checkAreaClear(World world, BlockPos pos) {
        // Check that there are no entities in the area, otherwise it would collide with them
        if (!world.getEntitiesWithinAABB(EntityFallingBlock.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))).isEmpty()) {
            // If we can't fall due to a collision, wait for the block to move out of the way and try again later
            world.scheduleUpdate(pos, this, 20);
            return null;
        }
        return pos;
    }
}
