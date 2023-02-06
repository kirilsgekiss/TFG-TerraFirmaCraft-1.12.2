/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.entity.animal;

import net.dries007.tfc.api.types.IAnimalTFC;
import net.dries007.tfc.objects.entity.TFCEntities;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Implements pregnancy for mammals in TFC
 */
@ParametersAreNonnullByDefault
public abstract class TFCEntityAnimalMammal extends TFCEntityAnimal {
    // The time(in days) this entity became pregnant
    private static final DataParameter<Long> PREGNANT_TIME = EntityDataManager.createKey(TFCEntityAnimalMammal.class, TFCEntities.getLongDataSerializer());

    @SuppressWarnings("unused")
    public TFCEntityAnimalMammal(World worldIn) {
        super(worldIn);
    }

    public TFCEntityAnimalMammal(World worldIn, Gender gender, int birthDay) {
        super(worldIn, gender, birthDay);
        setPregnantTime(-1);
    }

    public long getPregnantTime() {
        return dataManager.get(PREGNANT_TIME);
    }

    private void setPregnantTime(long day) {
        dataManager.set(PREGNANT_TIME, day);
    }

    @Override
    public void onFertilized(IAnimalTFC male) {
        //Mark the day this female became pregnant
        setPregnantTime(TFCCalendar.PLAYER_TIME.getTotalDays());
    }

    @Override
    public Type getType() {
        return Type.MAMMAL;
    }

    /**
     * Spawns children of this animal
     */
    public abstract void birthChildren();

    /**
     * Return the number of days for a full gestation
     *
     * @return long value in days
     */
    public abstract long gestationDays();

    @Override
    protected void entityInit() {
        super.entityInit();
        getDataManager().register(PREGNANT_TIME, -1L);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (!this.world.isRemote) {
            if (this.isFertilized() && TFCCalendar.PLAYER_TIME.getTotalDays() >= getPregnantTime() + gestationDays()) {
                birthChildren();
                this.setFertilized(false);
            }
        }
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound nbt) {
        super.writeEntityToNBT(nbt);
        nbt.setLong("pregnant", getPregnantTime());
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound nbt) {
        super.readEntityFromNBT(nbt);
        this.setPregnantTime(nbt.getLong("pregnant"));
    }
}
