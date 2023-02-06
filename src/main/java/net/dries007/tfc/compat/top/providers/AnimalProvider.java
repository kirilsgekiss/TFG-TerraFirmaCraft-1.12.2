package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitEntityData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoEntityProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.IAnimalTFC;
import net.dries007.tfc.objects.entity.animal.TFCEntityAnimalMammal;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.calendar.ICalendarFormatted;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class AnimalProvider implements IProbeInfoEntityProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":animal";
    }

    @Override
    public void addProbeEntityInfo(ProbeMode probeMode, IProbeInfo probeInfo, EntityPlayer entityPlayer, World world, Entity entity, IProbeHitEntityData probeHitInfo) {
        if (entity instanceof IAnimalTFC) {
            IAnimalTFC animal = (IAnimalTFC) entity;
            boolean familiarized = animal.getFamiliarity() > 0.15f;
            if (animal.getAdultFamiliarityCap() > 0) {
                probeInfo.text(new TextComponentTranslation(familiarized ? "waila.tfc.animal.familiarized" : "waila.tfc.animal.not_familiarized").getFormattedText());
            }
            switch (animal.getAge()) {
                case CHILD:
                    long endPlayerTick = (animal.getBirthDay() + animal.getDaysToAdulthood()) * ICalendar.TICKS_IN_DAY;
                    long delta = endPlayerTick - TFCCalendar.PLAYER_TIME.getTicks();
                    long endCalendarTick = TFCCalendar.CALENDAR_TIME.getTicks() + delta;
                    String date = ICalendarFormatted.getTimeAndDate(endCalendarTick, TFCCalendar.CALENDAR_TIME.getDaysInMonth());
                    probeInfo.text(new TextComponentTranslation("waila.tfc.animal.childhood_end", date).getFormattedText());
                    break;
                case OLD:
                    probeInfo.text(new TextComponentTranslation("waila.tfc.animal.old").getFormattedText());
                    // fall through here, can become old yet still be pregnant and give birth and/or give wool. All data retrieval below check correctly for age.
                case ADULT:
                    if (familiarized) {
                        if (animal.isReadyToMate()) {
                            probeInfo.text(new TextComponentTranslation("waila.tfc.animal.can_mate").getFormattedText());
                        }
                        if (animal.isFertilized()) {
                            if (animal.getType() == IAnimalTFC.Type.MAMMAL) {
                                probeInfo.text(new TextComponentTranslation("waila.tfc.animal.pregnant").getFormattedText());
                                // In 1.15+ this will move to AnimalProperties and everything needed will be there
                                // For 1.12, addons will need to either extend EntityAnimalMammal or handle the tooltip themselves
                                if (animal instanceof TFCEntityAnimalMammal) {
                                    TFCEntityAnimalMammal mother = (TFCEntityAnimalMammal) animal;
                                    long gestationDaysRemaining = mother.getPregnantTime() + mother.gestationDays() - TFCCalendar.PLAYER_TIME.getTotalDays();
                                    probeInfo.text(new TextComponentTranslation("waila.tfc.animal.pregnant_end", gestationDaysRemaining).getFormattedText());
                                }
                            } else {
                                probeInfo.text(new TextComponentTranslation("tfc.tooltip.fertilized").getFormattedText());
                            }
                        }
                        if (animal.isReadyForAnimalProduct()) {
                            if (animal instanceof IShearable) {
                                probeInfo.text(new TextComponentTranslation("waila.tfc.animal.can_shear").getFormattedText());
                            } else if (animal.getType() == IAnimalTFC.Type.OVIPAROUS) {
                                probeInfo.text(new TextComponentTranslation("waila.tfc.animal.has_eggs").getFormattedText());
                            } else {
                                probeInfo.text(new TextComponentTranslation("waila.tfc.animal.has_milk").getFormattedText());
                            }
                        }
                    }
                    break;
            }
        }
    }
}
