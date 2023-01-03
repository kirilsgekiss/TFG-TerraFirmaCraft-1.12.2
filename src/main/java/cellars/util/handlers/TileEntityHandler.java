package cellars.util.handlers;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import cellars.blocks.tileentity.TECellarShelf;
import cellars.blocks.tileentity.TEFreezeDryer;
import cellars.blocks.tileentity.TEIceBunker;
import cellars.blocks.tileentity.TEInfectedAir;
import cellars.util.Reference;

public class TileEntityHandler {
    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(TECellarShelf.class,new ResourceLocation(Reference.MOD_ID+":cellar_shelf"));
        GameRegistry.registerTileEntity(TEIceBunker.class,new ResourceLocation(Reference.MOD_ID+":ice_shelf"));
        GameRegistry.registerTileEntity(TEFreezeDryer.class,new ResourceLocation(Reference.MOD_ID+":freeze_dryer"));
        GameRegistry.registerTileEntity(TEInfectedAir.class,new ResourceLocation(Reference.MOD_ID+":infected_air"));
    }
}
