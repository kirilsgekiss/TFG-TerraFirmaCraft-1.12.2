/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Generic entity used for sitting on top of blocks
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TFCEntitySeatOn extends Entity {
    private BlockPos pos;

    public TFCEntitySeatOn(World world) {
        super(world);
        this.noClip = true;
        this.height = 0.01F;
        this.width = 0.01F;
    }

    public TFCEntitySeatOn(World world, BlockPos pos, double y0ffset) {
        this(world);
        this.pos = pos;
        setPosition(pos.getX() + 0.5D, pos.getY() + y0ffset, pos.getZ() + 0.5D);
    }

    public BlockPos getPos() {
        return this.pos;
    }

    @Nullable
    public Entity getSittingEntity() {
        for (Entity ent : this.getPassengers()) {
            if (ent instanceof EntityLiving) return ent;
        }
        return null;
    }

    @Override
    protected void entityInit() {
    }

    @Override
    public void onEntityUpdate() {
        if (!this.world.isRemote) {
            if (pos == null || !this.isBeingRidden() || this.world.isAirBlock(pos)) {
                this.setDead();
            }
        }
    }

    @Override
    protected boolean shouldSetPosAfterLoading() {
        return false;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
    }

    @Override
    public double getMountedYOffset() {
        return this.height * 0.0D;
    }

}
