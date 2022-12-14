package net.dries007.tfc.api.capability;

import gregtech.api.unification.material.Material;
import net.dries007.tfc.api.capability.heat.IItemHeat;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;

public interface IMaterialHandler extends IFluidHandler, INBTSerializable<NBTTagCompound>, IItemHeat {

    @Nullable
    Material getMaterial();

    int getAmount();
}
