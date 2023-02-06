/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.te;

import net.dries007.tfc.util.calendar.TFCCalendar;
import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class TETickCounter extends TEBase {
    private long lastUpdateTick;

    public long getTicksSinceUpdate() {
        return TFCCalendar.PLAYER_TIME.getTicks() - lastUpdateTick;
    }

    public void resetCounter() {
        lastUpdateTick = TFCCalendar.PLAYER_TIME.getTicks();
        markForSync();
    }

    public void reduceCounter(long amount) {
        lastUpdateTick += amount;
        markForSync();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        lastUpdateTick = nbt.getLong("tick");
        super.readFromNBT(nbt);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setLong("tick", lastUpdateTick);
        return super.writeToNBT(nbt);
    }
}
