package net.dries007.tfc.client.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IHasModel {

    @SideOnly(Side.CLIENT)
    void onModelRegister();

}
