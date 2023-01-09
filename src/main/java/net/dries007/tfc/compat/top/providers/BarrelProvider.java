package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.objects.te.TEBarrel;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public class BarrelProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":barrel";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        List<String> currentTooltip = new ArrayList<>();
        TEBarrel te = Helpers.getTE(world, data.getPos(), TEBarrel.class);
        if (te != null)
        {
            IFluidHandler fluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
            FluidStack fluid = fluidHandler != null ? fluidHandler.drain(Integer.MAX_VALUE, false) : null;

            if (te.isSealed())
            {
                currentTooltip.add(new TextComponentTranslation("waila.tfc.barrel.sealed", te.getSealedDate()).getFormattedText());
                BarrelRecipe recipe = te.getRecipe();
                if (recipe != null)
                {
                    currentTooltip.add(new TextComponentTranslation("waila.tfc.barrel.recipe", recipe.getResultName()).getFormattedText());
                }
                else
                {
                    currentTooltip.add(new TextComponentTranslation("waila.tfc.barrel.no_recipe").getFormattedText());
                }
            }
            if (fluid != null && fluid.amount > 0)
            {
                currentTooltip.add(new TextComponentTranslation("waila.tfc.barrel.contents", fluid.amount, fluid.getLocalizedName()).getFormattedText());
            }
        }
    }
}
