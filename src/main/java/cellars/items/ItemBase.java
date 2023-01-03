package cellars.items;

import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.creativetab.CreativeTabs;
import cellars.Main;
import cellars.init.ModItems;
import cellars.util.IHasModel;

public abstract class ItemBase extends ItemTFC implements IHasModel {

    public ItemBase(String name){
        // setUnlocalizedName(name);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.MATERIALS);

        ModItems.ITEMS.add(this);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

}
