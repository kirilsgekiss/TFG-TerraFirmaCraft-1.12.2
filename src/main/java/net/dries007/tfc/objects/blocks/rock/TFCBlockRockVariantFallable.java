/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.blocks.rock;

import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import net.dries007.tfc.api.types.Rock.*;
import net.dries007.tfc.api.types.Rock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import mcp.MethodsReturnNonnullByDefault;
import net.dries007.tfc.api.util.FallingBlockManager;
import net.dries007.tfc.objects.items.rock.ItemMud;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class TFCBlockRockVariantFallable extends TFCBlockRockVariant
{
    public TFCBlockRockVariantFallable(Type type, Rock rock)
    {
        super(type, rock);
        if (type.canFall())
        {
            FallingBlockManager.registerFallable(this, type.getFallingSpecification());
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        if (this.type.canFall() && rand.nextInt(16) == 0 && FallingBlockManager.shouldFall(world, pos, pos, state, false))
        {
            double d0 = (float) pos.getX() + rand.nextFloat();
            double d1 = (double) pos.getY() - 0.05D;
            double d2 = (float) pos.getZ() + rand.nextFloat();
            world.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(state));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        if (type == Type.GRAVEL)
        {
            if (fortune > 3)
            {
                fortune = 3;
            }

            if (rand.nextInt(10 - fortune * 3) == 0)
            {
                return Items.FLINT;
            }
        }
        if (type == Type.MUD)
        {
            if (fortune > 3)
            {
                fortune = 3;
            }

            if (rand.nextInt(10 - fortune * 3) == 0)
            {
                return ItemMud.get(rock);
            }
        }
        return super.getItemDropped(state, rand, fortune);
    }
    @Nullable
    private BlockPos checkAreaClear(World world, BlockPos pos)
    {
        // Check that there are no entities in the area, otherwise it would collide with them
        if (!world.getEntitiesWithinAABB(EntityFallingBlock.class, new AxisAlignedBB(pos, pos.add(1, 1, 1))).isEmpty())
        {
            // If we can't fall due to a collision, wait for the block to move out of the way and try again later
            world.scheduleUpdate(pos, this, 20);
            return null;
        }
        return pos;
    }

}