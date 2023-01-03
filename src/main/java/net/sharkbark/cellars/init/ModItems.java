package net.sharkbark.cellars.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.registries.IForgeRegistry;
import net.sharkbark.cellars.items.ItemIceShard;
import net.sharkbark.cellars.util.Reference;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<Item> ITEMS = new ArrayList<Item>();

    @ObjectHolder(Reference.MOD_ID + ":ice_shard")
    public static final Item ICE_SHARD = null;
    @ObjectHolder(Reference.MOD_ID + ":packed_ice_shard")
    public static final Item PACKED_ICE_SHARD = null;
    @ObjectHolder(Reference.MOD_ID + ":sea_ice_shard")
    public static final Item SEA_ICE_SHARD = null;

    public static void registerItems(IForgeRegistry<Item> registry) {
        registry.registerAll(
            new ItemIceShard("ice_shard"),
            new ItemIceShard("packed_ice_shard"),
            new ItemIceShard("sea_ice_shard")
        );
    }
}
