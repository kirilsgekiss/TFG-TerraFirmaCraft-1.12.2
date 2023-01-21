//package net.dries007.tfc.types;
//
//import net.dries007.tfc.api.recipes.LoomRecipe;
//import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
//import net.dries007.tfc.api.recipes.barrel.BarrelRecipeFluidMixing;
//import net.dries007.tfc.api.recipes.barrel.BarrelRecipeTemperature;
//import net.dries007.tfc.api.recipes.heat.HeatRecipe;
//import net.dries007.tfc.api.recipes.knapping.KnappingRecipe;
//import net.dries007.tfc.api.recipes.quern.QuernRecipe;
//import net.dries007.tfc.api.registries.TFCRegistries;
//import net.dries007.tfc.api.types.IFruitTree;
//import net.dries007.tfc.api.types.Rock;
//import net.dries007.tfc.api.types.Tree;
//import net.dries007.tfc.objects.blocks.plants.BlockPlantTFC;
//import net.dries007.tfc.objects.fluids.FluidsTFC;
//import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
//import net.dries007.tfc.objects.inventory.ingredient.IngredientFluidItem;
//import net.dries007.tfc.objects.items.ItemAnimalHide;
//import net.dries007.tfc.objects.items.ItemsTFC;
//import net.dries007.tfc.objects.items.food.ItemFoodTFC;
//import net.dries007.tfc.util.agriculture.Food;
//import net.dries007.tfc.util.agriculture.FruitTree;
//import net.dries007.tfc.util.calendar.ICalendar;
//import net.minecraft.init.Blocks;
//import net.minecraft.init.Items;
//import net.minecraft.item.EnumDyeColor;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.crafting.IRecipe;
//import net.minecraft.util.ResourceLocation;
//import net.minecraftforge.event.RegistryEvent;
//import net.minecraftforge.fluids.FluidStack;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.registries.IForgeRegistry;
//import net.minecraftforge.registries.IForgeRegistryModifiable;
//import net.dries007.tfc.ConfigTFCF;
//import tfcflorae.TFCFlorae;
//import tfcflorae.BlocksTFCF;
//import tfcflorae.ItemsTFC;
//import net.dries007.tfc.objects.recipes.StickBundleRecipe;
//import net.dries007.tfc.util.agriculture.SeasonalTrees;
//
//import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;
//import static net.dries007.tfc.objects.fluids.FluidsTFC.FRESH_WATER;
//
//@SuppressWarnings("unused")
//@Mod.EventBusSubscriber(modid = MOD_ID)
//public final class RecipesTFCF
//{
//    @SuppressWarnings("rawtypes")
//    @SubscribeEvent
//    public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            for (SeasonalTrees fruitTreeTFCF : SeasonalTrees.values())
//            {
//                String nameTFCF = fruitTreeTFCF.getName().toLowerCase();
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "wood/fruit_tree/door/" + nameTFCF,
//                        "wood/fruit_tree/fence/" + nameTFCF,
//                        "wood/fruit_tree/fence_gate/" + nameTFCF,
//                        "wood/fruit_tree/trapdoor/" + nameTFCF
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//
//            for (IFruitTree fruitTree : FruitTree.values())
//            {
//                String nameTFC = fruitTree.getName().toLowerCase();
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "wood/fruit_tree/door/" + nameTFC,
//                        "wood/fruit_tree/fence/" + nameTFC,
//                        "wood/fruit_tree/fence_gate/" + nameTFC,
//                        "wood/fruit_tree/trapdoor/" + nameTFC
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//        }
//
//        if (!TFCFlorae.FirmaLifeAdded)
//        {
//            for (SeasonalTrees fruitTreeTFCF : SeasonalTrees.values())
//            {
//                String nameTFCF = fruitTreeTFCF.getName().toLowerCase();
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "wood/fruit_tree/firmalife/door/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/fence/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/fence_gate/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/trapdoor/" + nameTFCF
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//
//            for(Tree wood : TFCRegistries.TREES.getValuesCollection())
//            {
//                String nameTFCF = wood.getRegistryName().getPath().toLowerCase();
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "wood/fruit_tree/firmalife/door/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/fence/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/fence_gate/" + nameTFCF,
//                        "wood/fruit_tree/firmalife/trapdoor/" + nameTFCF
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//
//            for (IFruitTree fruitTree : FruitTree.values())
//            {
//                String nameTFC = fruitTree.getName().toLowerCase();
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "wood/fruit_tree/firmalife/door/" + nameTFC,
//                        "wood/fruit_tree/firmalife/fence/" + nameTFC,
//                        "wood/fruit_tree/firmalife/fence_gate/" + nameTFC,
//                        "wood/fruit_tree/firmalife/trapdoor/" + nameTFC
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "food/flatbread_dough/amaranth", "food/flatbread_dough/buckwheat", "food/flatbread_dough/fonio", "food/flatbread_dough/millet", "food/flatbread_dough/quinoa", "food/flatbread_dough/spelt", "food/flatbread_dough/wild_rice",
//                    "food/dough_yeast/amaranth", "food/dough_yeast/buckwheat", "food/dough_yeast/fonio", "food/dough_yeast/millet", "food/dough_yeast/quinoa", "food/dough_yeast/spelt", "food/dough_yeast/wild_rice",
//                    "food/sandwich_slice/amaranth", "food/sandwich_slice/buckwheat", "food/sandwich_slice/fonio", "food/sandwich_slice/millet", "food/sandwich_slice/quinoa", "food/sandwich_slice/spelt", "food/sandwich_slice/wild_rice",
//                    "metal/unmold/mallet_head", "food/pinecone", "yeast"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }
//
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllCoarse)
//        {
//            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//            {
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "stone/tfcflorae/coarse_dirt/" + rock
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//            if (!(ConfigTFC.FloraeGeneral.WORLD.enableAllCoarse && ConfigTFC.FloraeGeneral.WORLD.enableAllSpecialSoil))
//            {
//                for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//                {
//                    IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                    String[] regNames = {
//                            "stone/tfcflorae/coarse_humus/" + rock,
//                            "stone/tfcflorae/coarse_loam/" + rock,
//                            "stone/tfcflorae/coarse_loamy_sand/" + rock,
//                            "stone/tfcflorae/coarse_sandy_loam/" + rock,
//                            "stone/tfcflorae/coarse_silt/" + rock,
//                            "stone/tfcflorae/coarse_silt_loam/" + rock
//                    };
//                    for (String name : regNames)
//                    {
//                        IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                        if (recipe != null)
//                        {
//                            registry.remove(recipe.getRegistryName());
//                            TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                        }
//                    }
//                }
//            }
//        }
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllEarthenwareClay)
//        {
//            for (Metal.ItemType type : Metal.ItemType.values())
//            {
//                if (type.hasMold(null))
//                {
//                    IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                    String[] regNames = {
//                            "metal/unmold/earthenware/" + type,
//                            "ceramics/unfired_clay_recycle_earthenware/" + type
//                    };
//                    for (String name : regNames)
//                    {
//                        IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                        if (recipe != null)
//                        {
//                            registry.remove(recipe.getRegistryName());
//                            TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                        }
//                    }
//                }
//            }
//            for (EnumDyeColor dyeColor : EnumDyeColor.values())
//            {
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "ceramics/glazed_vessel_earthenware/" + dyeColor,
//                        "ceramics/unfired_clay_recycle_earthenware/vessel_glazed/" + dyeColor
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "ceramics/unfired_clay_recycle_earthenware/bowl",
//                    "ceramics/unfired_clay_recycle_earthenware/flowerpot",
//                    "ceramics/unfired_clay_recycle_earthenware/jug",
//                    "ceramics/unfired_clay_recycle_earthenware/large_vessel",
//                    "ceramics/unfired_clay_recycle_earthenware/pot",
//                    "ceramics/unfired_clay_recycle_earthenware/spindle",
//                    "ceramics/unfired_clay_recycle_earthenware/vessel",
//                    "earthenware_block",
//                    "earthenware_bricks",
//                    "earthenware_clay",
//                    "unfired_spindle_head_earthenware"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllEarthenwareClay || !TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/earthenware/mallet_head",
//                    "ceramics/unfired_clay_recycle_earthenware/mallet",
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllEarthenwareClay)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/earthenware/nail",
//                    "metal/unmold/earthenware/ring",
//                    "metal/unmold/earthenware/halberd_blade",
//                    "metal/unmold/earthenware/metal_block",
//                    "ceramics/unfired_clay_recycle_earthenware/nail",
//                    "ceramics/unfired_clay_recycle_earthenware/ring",
//                    "ceramics/unfired_clay_recycle_earthenware/halberd_blade",
//                    "ceramics/unfired_clay_recycle_earthenware/metal_block"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllKaoliniteClay)
//        {
//            for (Metal.ItemType type : Metal.ItemType.values())
//            {
//                if (type.hasMold(null))
//                {
//                    IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                    String[] regNames = {
//                            "metal/unmold/kaolinite/" + type,
//                            "ceramics/unfired_clay_recycle_kaolinite/" + type
//                    };
//                    for (String name : regNames)
//                    {
//                        IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                        if (recipe != null)
//                        {
//                            registry.remove(recipe.getRegistryName());
//                            TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                        }
//                    }
//                }
//            }
//            for (EnumDyeColor dyeColor : EnumDyeColor.values())
//            {
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "ceramics/glazed_vessel_kaolinite/" + dyeColor,
//                        "ceramics/unfired_clay_recycle_kaolinite/vessel_glazed/" + dyeColor
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "ceramics/unfired_clay_recycle_kaolinite/bowl",
//                    "ceramics/unfired_clay_recycle_kaolinite/flowerpot",
//                    "ceramics/unfired_clay_recycle_kaolinite/jug",
//                    "ceramics/unfired_clay_recycle_kaolinite/large_vessel",
//                    "ceramics/unfired_clay_recycle_kaolinite/pot",
//                    "ceramics/unfired_clay_recycle_kaolinite/spindle",
//                    "ceramics/unfired_clay_recycle_kaolinite/vessel",
//                    "kaolinite_block",
//                    "kaolinite_bricks",
//                    "kaolinite_clay",
//                    "unfired_spindle_head_kaolinite"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllKaoliniteClay || !TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/kaolinite/mallet_head",
//                    "ceramics/unfired_clay_recycle_kaolinite/mallet",
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllKaoliniteClay)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/kaolinite/nail",
//                    "metal/unmold/kaolinite/ring",
//                    "metal/unmold/kaolinite/halberd_blade",
//                    "metal/unmold/kaolinite/metal_block",
//                    "ceramics/unfired_clay_recycle_kaolinite/nail",
//                    "ceramics/unfired_clay_recycle_kaolinite/ring",
//                    "ceramics/unfired_clay_recycle_kaolinite/halberd_blade",
//                    "ceramics/unfired_clay_recycle_kaolinite/metal_block"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllStonewareClay)
//        {
//            for (Metal.ItemType type : Metal.ItemType.values())
//            {
//                if (type.hasMold(null))
//                {
//                    IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                    String[] regNames = {
//                            "metal/unmold/stoneware/" + type,
//                            "ceramics/unfired_clay_recycle_stoneware/" + type
//                    };
//                    for (String name : regNames)
//                    {
//                        IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                        if (recipe != null)
//                        {
//                            registry.remove(recipe.getRegistryName());
//                            TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                        }
//                    }
//                }
//            }
//            for (EnumDyeColor dyeColor : EnumDyeColor.values())
//            {
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "ceramics/glazed_vessel_stoneware/" + dyeColor,
//                        "ceramics/unfired_clay_recycle_stoneware/vessel_glazed/" + dyeColor
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "ceramics/unfired_clay_recycle_stoneware/bowl",
//                    "ceramics/unfired_clay_recycle_stoneware/flowerpot",
//                    "ceramics/unfired_clay_recycle_stoneware/jug",
//                    "ceramics/unfired_clay_recycle_stoneware/large_vessel",
//                    "ceramics/unfired_clay_recycle_stoneware/pot",
//                    "ceramics/unfired_clay_recycle_stoneware/spindle",
//                    "ceramics/unfired_clay_recycle_stoneware/vessel",
//                    "stoneware_block",
//                    "stoneware_bricks",
//                    "stoneware_clay",
//                    "unfired_spindle_head_stoneware"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllStonewareClay || !TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/stoneware/mallet_head",
//                    "ceramics/unfired_clay_recycle_stoneware/mallet",
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//        /*
//        if (!ConfigTFC.FloraeGeneral.WORLD.enableAllStonewareClay)
//        {
//            IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//            String[] regNames = {
//                    "metal/unmold/stoneware/nail",
//                    "metal/unmold/stoneware/ring",
//                    "metal/unmold/stoneware/halberd_blade",
//                    "metal/unmold/stoneware/metal_block",
//                    "ceramics/unfired_clay_recycle_stoneware/nail",
//                    "ceramics/unfired_clay_recycle_stoneware/ring",
//                    "ceramics/unfired_clay_recycle_stoneware/halberd_blade",
//                    "ceramics/unfired_clay_recycle_stoneware/metal_block"
//            };
//            for (String name : regNames)
//            {
//                IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                if (recipe != null)
//                {
//                    registry.remove(recipe.getRegistryName());
//                    TFCFlorae.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                }
//            }
//        }*/
//    }
//
//
//    @SubscribeEvent
//    public static void onRegisterKnappingRecipeEvent(RegistryEvent.Register<KnappingRecipe> event)
//    {
//        event.getRegistry().registerAll(
//
//                /*
//                // Flint Tool Heads
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_AXE_HEAD, 1), " X   ", "XXXX ", "XXXXX", "XXXX ", " X   ").setRegistryName(TFCFlorae.MODID, "flint_axe_head"),
//
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_HAMMER_HEAD, 1), "     ", "XXXXX", "XXXXX", "  X  ", "     ").setRegistryName(TFCFlorae.MODID, "flint_hammer_head"),
//
//                // new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_HOE_HEAD, 1), "XXXXX", "   XX").setRegistryName(TFCFlorae.MODID, "flint_hoe_head_1"),
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_HOE_HEAD, 2), "XXXXX", "XX   ", "     ", "XXXXX", "XX   ").setRegistryName(TFCFlorae.MODID, "flint_hoe_head_2"),
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_HOE_HEAD, 2), "XXXXX", "XX   ", "     ", "XXXXX", "   XX").setRegistryName(TFCFlorae.MODID, "flint_hoe_head_3"),
//
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_JAVELIN_HEAD, 1), "XXX  ", "XXXX ", "XXXXX", " XXX ", "  X  ").setRegistryName(TFCFlorae.MODID, "flint_javelin_head"),
//
//                // new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_KNIFE_HEAD, 1), "X ", "XX", "XX", "XX", "XX").setRegistryName(TFCFlorae.MODID, "flint_knife_head_1"),
//                // new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_KNIFE_HEAD, 1), " X", "XX", "XX", "XX", "XX").setRegistryName(TFCFlorae.MODID, "flint_knife_head_2"),
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_KNIFE_HEAD, 2), "X  X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName(TFCFlorae.MODID, "flint_knife_head_3"),
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_KNIFE_HEAD, 2), "X   X", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName(TFCFlorae.MODID, "flint_knife_head_4"),
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_KNIFE_HEAD, 2), " X X ", "XX XX", "XX XX", "XX XX", "XX XX").setRegistryName(TFCFlorae.MODID, "flint_knife_head_5"),
//
//                new KnappingRecipeSimple(KnappingTypes.FLINT, true, new ItemStack(ItemsTFC.FLINT_SHOVEL_HEAD, 1), " XXX ", " XXX ", " XXX ", " XXX ", "  X  ").setRegistryName(TFCFlorae.MODID, "flint_shovel_head"),
//
//                new KnappingRecipeSimple(KnappingType.CLAY, true, new ItemStack(ItemsTFC.UNFIRED_URN), "XX XX", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "clay_urn"),
//                new KnappingRecipeSimple(KnappingTypes.EARTHENWARE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_URN), "XX XX", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "earthenware_urn"),
//                new KnappingRecipeSimple(KnappingTypes.KAOLINITE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_URN), "XX XX", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "kaolinite_urn"),
//                new KnappingRecipeSimple(KnappingTypes.STONEWARE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_URN), "XX XX", "X   X", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "stoneware_urn"),
//
//                // Containers
//                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(ItemsTFC.LEATHER_BAG_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("leather_bag_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingType.LEATHER, true, new ItemStack(ItemsTFC.LEATHER_BAG_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("leather_bag_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("burlap_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("burlap_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("wool_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("wool_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("silk_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("silk_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("sisal_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("sisal_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("cotton_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("cotton_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("linen_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("linen_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("hemp_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("hemp_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_SACK_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("yucca_sack_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_SACK_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("yucca_sack_pieces_vertical"),
//                new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_BAG_PIECE, 2), " XXX ", " XXX ", "     ", " XXX ", " XXX ").setRegistryName("pineapple_leather_bag_pieces_horizontal"),
//                new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_BAG_PIECE, 2), "     ", "XX XX", "XX XX", "XX XX", "     ").setRegistryName("pineapple_leather_bag_pieces_vertical"),
//
//                // Pineapple Leather
//                new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(Items.SADDLE), "  X  ", "XXXXX", "XXXXX", "XXXXX", "  X  ").setRegistryName("pineapple_leather_saddle"),
//                new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.QUIVER), " XXXX", "X XXX", "X XXX", "X XXX", " XXXX").setRegistryName("pineapple_leather_quiver")
//                */
//                // Armor Knapping
//            /*new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("pineapple_leather_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("pineapple_leather_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("pineapple_leather_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.PINEAPPLE_LEATHER, true, new ItemStack(ItemsTFC.PINEAPPLE_LEATHER_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("pineapple_leather_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("burlap_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("burlap_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("burlap_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.BURLAP_CLOTH, true, new ItemStack(ItemsTFC.BURLAP_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("burlap_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("wool_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("wool_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("wool_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.WOOL_CLOTH, true, new ItemStack(ItemsTFC.WOOL_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("wool_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("silk_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("silk_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("silk_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.SILK_CLOTH, true, new ItemStack(ItemsTFC.SILK_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("silk_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("sisal_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("sisal_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("sisal_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.SISAL_CLOTH, true, new ItemStack(ItemsTFC.SISAL_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("sisal_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("cotton_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("cotton_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("cotton_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.COTTON_CLOTH, true, new ItemStack(ItemsTFC.COTTON_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("cotton_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("linen_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("linen_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("linen_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.LINEN_CLOTH, true, new ItemStack(ItemsTFC.LINEN_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("linen_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_CLOTH_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("hemp_cloth_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_CLOTH_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("hemp_cloth_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_CLOTH_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("hemp_cloth_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.HEMP_CLOTH, true, new ItemStack(ItemsTFC.HEMP_CLOTH_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("hemp_cloth_boots"),
//
//            new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_CANVAS_HELMET), "XXXXX", "X   X", "X   X", "     ", "     ").setRegistryName("yucca_canvas_helmet"),
//            new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_CANVAS_CHESTPLATE), "X   X", "XXXXX", "XXXXX", "XXXXX", "XXXXX").setRegistryName("yucca_canvas_chestplate"),
//            new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_CANVAS_LEGGINGS), "XXXXX", "XXXXX", "XX XX", "XX XX", "XX XX").setRegistryName("yucca_canvas_leggings"),
//            new KnappingRecipeSimple(KnappingTypes.YUCCA_CANVAS, true, new ItemStack(ItemsTFC.YUCCA_CANVAS_BOOTS), "XX   ", "XX   ", "XX   ", "XXXX ", "XXXXX").setRegistryName("yucca_canvas_boots")*/
//        );
//    }
//
//    @SuppressWarnings("rawtypes")
//    @SubscribeEvent
//    public static void onRegisterHeatRecipeEvent(RegistryEvent.Register<HeatRecipe> event)
//    {
//        /*
//        IForgeRegistry<HeatRecipe> r = event.getRegistry();
//
//        // Remove recipes
//        IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.HEAT;
//        String[] regNames = {"grilled_mushroom"};
//        for (String name : regNames)
//        {
//            HeatRecipe recipe = TFCRegistries.HEAT.getValue(new ResourceLocation("tfc", name));
//            if (recipe != null)
//            {
//                modRegistry.remove(recipe.getRegistryName());
//                TFCFlorae.logger.info("Removed heating recipe tfc:{}", name);
//            }
//        }
//
//        // Mud Pottery
//        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//        {
//            ItemFiredMudBrick firedMudBrick = ItemFiredMudBrick.get(ItemUnfiredMudBrick.get(rock));
//
//            HeatRecipe recipe = new HeatRecipeSimple(IIngredient.of(ItemUnfiredMudBrick.get(rock)), new ItemStack(firedMudBrick), 600f, Metal.Tier.TIER_I);
//            event.getRegistry().register(recipe.setRegistryName(rock.getRegistryName().getPath().toLowerCase() + "_unfired_mud_brick"));
//
//            // Fired Pottery - doesn't burn up
//            recipe = new HeatRecipeSimple(IIngredient.of(firedMudBrick), new ItemStack(firedMudBrick), 1599f, Metal.Tier.TIER_I);
//            event.getRegistry().register(recipe.setRegistryName(rock.getRegistryName().getPath().toLowerCase() + "_fired_mud_brick"));
//        }
//
//        // Clay Pottery Items with metadata
//        for (EnumDyeColor dye : EnumDyeColor.values())
//        {
//            r.register(new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.UNFIRED_EARTHENWARE_VESSEL_GLAZED, 1, dye.getMetadata())), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_VESSEL_GLAZED, 1, dye.getMetadata()), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_vessel_glazed_" + dye.getName()));
//            r.register(new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.UNFIRED_KAOLINITE_VESSEL_GLAZED, 1, dye.getMetadata())), new ItemStack(ItemsTFC.FIRED_KAOLINITE_VESSEL_GLAZED, 1, dye.getMetadata()), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_vessel_glazed_" + dye.getName()));
//            r.register(new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.UNFIRED_STONEWARE_VESSEL_GLAZED, 1, dye.getMetadata())), new ItemStack(ItemsTFC.FIRED_STONEWARE_VESSEL_GLAZED, 1, dye.getMetadata()), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_vessel_glazed_" + dye.getName()));
//        }
//
//        // Clay Molds
//        for (Metal.ItemType type : Metal.ItemType.values())
//        {
//            ItemUnfiredEarthenwareMold unfiredMoldEarthenware = ItemUnfiredEarthenwareMold.get(type);
//            ItemEarthenwareMold firedMoldEarthenware = ItemEarthenwareMold.get(type);
//            if (unfiredMoldEarthenware != null && firedMoldEarthenware != null)
//            {
//                r.register(new HeatRecipeSimple(IIngredient.of(unfiredMoldEarthenware), new ItemStack(firedMoldEarthenware), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_mold_" + type.name().toLowerCase()));
//            }
//
//            ItemUnfiredKaoliniteMold unfiredMoldKaolinite = ItemUnfiredKaoliniteMold.get(type);
//            ItemKaoliniteMold firedMoldKaolinite = ItemKaoliniteMold.get(type);
//            if (unfiredMoldKaolinite != null && firedMoldKaolinite != null)
//            {
//                r.register(new HeatRecipeSimple(IIngredient.of(unfiredMoldKaolinite), new ItemStack(firedMoldKaolinite), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_mold_" + type.name().toLowerCase()));
//            }
//
//            ItemUnfiredStonewareMold unfiredMoldStoneware = ItemUnfiredStonewareMold.get(type);
//            ItemStonewareMold firedMoldStoneware = ItemStonewareMold.get(type);
//            if (unfiredMoldStoneware != null && firedMoldStoneware != null)
//            {
//                r.register(new HeatRecipeSimple(IIngredient.of(unfiredMoldStoneware), new ItemStack(firedMoldStoneware), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_mold_" + type.name().toLowerCase()));
//            }
//        }
//
//        // Bread
//        if (!ConfigFL.General.COMPAT.removeTFC)
//        {
//            r.registerAll(
//
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.AMARANTH_DOUGH), new ItemStack(ItemsTFC.AMARANTH_BREAD), 200, 480).setRegistryName("amaranth_bread"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.BUCKWHEAT_DOUGH), new ItemStack(ItemsTFC.BUCKWHEAT_BREAD), 200, 480).setRegistryName("buckwheat_bread"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.FONIO_DOUGH), new ItemStack(ItemsTFC.FONIO_BREAD), 200, 480).setRegistryName("fonio_bread"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.MILLET_DOUGH), new ItemStack(ItemsTFC.MILLET_BREAD), 200, 480).setRegistryName("millet_bread"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.QUINOA_DOUGH), new ItemStack(ItemsTFC.QUINOA_BREAD), 200, 480).setRegistryName("quinoa_bread"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.SPELT_DOUGH), new ItemStack(ItemsTFC.SPELT_BREAD), 200, 480).setRegistryName("spelt_bread")
//            );
//        }*/
//
//        // Bucket Stuff
//        /*ItemStack woodenBucket = new ItemStack(ItemsTFC.WOODEN_BUCKET);
//        IFluidHandler woodenBucketSaltWater = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
//        woodenBucketSaltWater.fill(new FluidStack(FluidsTFC.SALT_WATER.get(), Fluid.BUCKET_VOLUME), true);
//        IFluidHandler woodenBucketSweetSap = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
//        woodenBucketSweetSap.fill(new FluidStack(FluidsTFC.SWEET_SAP.get(), Fluid.BUCKET_VOLUME), true);
//        IFluidHandler woodenBucketSweetSyrup = woodenBucket.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
//        woodenBucketSweetSyrup.fill(new FluidStack(FluidsTFC.SWEET_SYRUP.get(), Fluid.BUCKET_VOLUME), true);
//
//        r.registerAll(
//
//            new HeatRecipeSimple(IIngredient.of((Item) woodenBucketSaltWater), new ItemStack(ItemsTFC.WOODEN_BUCKET_SALT, 1), 480, 500).setRegistryName("bucket_salt"),
//            new HeatRecipeSimple(IIngredient.of((Item) woodenBucketSweetSap), new ItemStack((Item) woodenBucketSweetSyrup, 1), 480, 500).setRegistryName("bucket_syrup"),
//            new HeatRecipeSimple(IIngredient.of((Item) woodenBucketSweetSyrup), new ItemStack(ItemsTFC.WOODEN_BUCKET_SUGAR, 1), 480, 500).setRegistryName("bucket_sugar")
//        );*/
//
//        // Standard / Simple recipes
//        /*
//        r.registerAll(
//
//                // Earthenware Pottery
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_BRICK), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_brick"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_VESSEL), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_vessel"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_JUG), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_POT), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_BOWL), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_bowl"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_EARTHENWARE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_earthenware_large_vessel"),
//
//                // Fired Earthenware Pottery - doesn't burn up
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_BRICK), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_brick"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_vessel"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_VESSEL_GLAZED), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_vessel_glazed_all"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_JUG), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_POT), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_EARTHENWARE_BOWL), new ItemStack(ItemsTFC.FIRED_EARTHENWARE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_bowl"),
//                new HeatRecipeSimple(IIngredient.of(BlocksTFCF.FIRED_EARTHENWARE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_EARTHENWARE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_earthenware_large_vessel"),
//
//                // Kaolinite Pottery
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_BRICK), new ItemStack(ItemsTFC.FIRED_KAOLINITE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_brick"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_VESSEL), new ItemStack(ItemsTFC.FIRED_KAOLINITE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_vessel"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_JUG), new ItemStack(ItemsTFC.FIRED_KAOLINITE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_POT), new ItemStack(ItemsTFC.FIRED_KAOLINITE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_BOWL), new ItemStack(ItemsTFC.FIRED_KAOLINITE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_bowl"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_KAOLINITE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_kaolinite_large_vessel"),
//
//                // Fired Kaolinite Pottery - doesn't burn up
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_BRICK), new ItemStack(ItemsTFC.FIRED_KAOLINITE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_brick"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_vessel"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_VESSEL_GLAZED), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_vessel_glazed_all"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_JUG), new ItemStack(ItemsTFC.FIRED_KAOLINITE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_POT), new ItemStack(ItemsTFC.FIRED_KAOLINITE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_KAOLINITE_BOWL), new ItemStack(ItemsTFC.FIRED_KAOLINITE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_bowl"),
//                new HeatRecipeSimple(IIngredient.of(BlocksTFCF.FIRED_KAOLINITE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_KAOLINITE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_kaolinite_large_vessel"),
//
//                // Stoneware Pottery
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_BRICK), new ItemStack(ItemsTFC.FIRED_STONEWARE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_brick"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_VESSEL), new ItemStack(ItemsTFC.FIRED_STONEWARE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_vessel"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_JUG), new ItemStack(ItemsTFC.FIRED_STONEWARE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_POT), new ItemStack(ItemsTFC.FIRED_STONEWARE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_BOWL), new ItemStack(ItemsTFC.FIRED_STONEWARE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_bowl"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_STONEWARE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_stoneware_large_vessel"),
//
//                // Fired Stoneware Pottery - doesn't burn up
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_STONEWARE_BRICK), new ItemStack(ItemsTFC.FIRED_STONEWARE_BRICK), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_brick"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_STONEWARE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_vessel"),
//                new HeatRecipeVessel(IIngredient.of(ItemsTFC.FIRED_STONEWARE_VESSEL_GLAZED), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_vessel_glazed_all"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_STONEWARE_JUG), new ItemStack(ItemsTFC.FIRED_STONEWARE_JUG), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_jug"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_STONEWARE_POT), new ItemStack(ItemsTFC.FIRED_STONEWARE_POT), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_pot"),
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.FIRED_STONEWARE_BOWL), new ItemStack(ItemsTFC.FIRED_STONEWARE_BOWL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_bowl"),
//                new HeatRecipeSimple(IIngredient.of(BlocksTFCF.FIRED_STONEWARE_LARGE_VESSEL), new ItemStack(BlocksTFCF.FIRED_STONEWARE_LARGE_VESSEL), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_stoneware_large_vessel"),
//
//                new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_URN), new ItemStack(BlocksTFCF.FIRED_URN), 1599f, Metal.Tier.TIER_I).setRegistryName("unfired_urn"),
//                new HeatRecipeSimple(IIngredient.of(BlocksTFCF.FIRED_URN), new ItemStack(BlocksTFCF.FIRED_URN), 1599f, Metal.Tier.TIER_I).setRegistryName("fired_urn"),
//
//                // Bread
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.HASH_MUFFIN), 480).setRegistryName("burned_hash_muffin"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.AMARANTH_BREAD), 480).setRegistryName("burned_barley_bread"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.BUCKWHEAT_BREAD), 480).setRegistryName("burned_cornbread"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.FONIO_BREAD), 480).setRegistryName("burned_oat_bread"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.MILLET_BREAD), 480).setRegistryName("burned_rice_bread"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.QUINOA_BREAD), 480).setRegistryName("burned_rye_bread"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.SPELT_BREAD), 480).setRegistryName("burned_wheat_bread"),
//
//                // Epiphytes
//                new HeatRecipeSimple(IIngredient.of("epiphyteArtistsConk"), new ItemStack(ItemsTFC.ROASTED_ARTISTS_CONK), 200, 480).setRegistryName("roasted_artists_conk"),
//                new HeatRecipeSimple(IIngredient.of("epiphyteSulphurShelf"), new ItemStack(ItemsTFC.ROASTED_SULPHUR_SHELF), 200, 480).setRegistryName("roasted_sulphur_shelf"),
//                new HeatRecipeSimple(IIngredient.of("epiphyteTurkeyTail"), new ItemStack(ItemsTFC.ROASTED_TURKEY_TAIL), 200, 480).setRegistryName("roasted_turkey_tail"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_ARTISTS_CONK), 480).setRegistryName("burned_artists_conk"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_SULPHUR_SHELF), 480).setRegistryName("burned_sulphur_shelf"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_TURKEY_TAIL), 480).setRegistryName("burned_turkey_tail"),
//
//                // Mushrooms
//                new HeatRecipeSimple(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PORCINI))), new ItemStack(ItemsTFC.ROASTED_PORCINI), 200, 480).setRegistryName("roasted_porcini_specific"),
//                new HeatRecipeSimple(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.AMANITA))), new ItemStack(ItemsTFC.ROASTED_AMANITA), 200, 480).setRegistryName("roasted_amanita_specific"),
//                new HeatRecipeSimple(IIngredient.of("mushroomPorcini"), new ItemStack(ItemsTFC.ROASTED_PORCINI), 200, 480).setRegistryName("roasted_porcini"),
//                new HeatRecipeSimple(IIngredient.of("mushroomAmanita"), new ItemStack(ItemsTFC.ROASTED_AMANITA), 200, 480).setRegistryName("roasted_amanita"),
//                new HeatRecipeSimple(IIngredient.of("mushroomBlackPowderpuff"), new ItemStack(ItemsTFC.ROASTED_BLACK_POWDERPUFF), 200, 480).setRegistryName("roasted_black_powderpuff"),
//                new HeatRecipeSimple(IIngredient.of("mushroomChanterelle"), new ItemStack(ItemsTFC.ROASTED_CHANTERELLE), 200, 480).setRegistryName("roasted_chanterelle"),
//                new HeatRecipeSimple(IIngredient.of("mushroomDeathCap"), new ItemStack(ItemsTFC.ROASTED_DEATH_CAP), 200, 480).setRegistryName("roasted_death_cap"),
//                new HeatRecipeSimple(IIngredient.of("mushroomGiantClub"), new ItemStack(ItemsTFC.ROASTED_GIANT_CLUB), 200, 480).setRegistryName("roasted_giant_club"),
//                new HeatRecipeSimple(IIngredient.of("mushroomParasol"), new ItemStack(ItemsTFC.ROASTED_PARASOL_MUSHROOM), 200, 480).setRegistryName("roasted_parasol_mushroom"),
//                new HeatRecipeSimple(IIngredient.of("mushroomStinkhorn"), new ItemStack(ItemsTFC.ROASTED_STINKHORN), 200, 480).setRegistryName("roasted_stinkhorn"),
//                new HeatRecipeSimple(IIngredient.of("mushroomWeepingMilkCap"), new ItemStack(ItemsTFC.ROASTED_WEEPING_MILK_CAP), 200, 480).setRegistryName("roasted_weeping_milk_cap"),
//                new HeatRecipeSimple(IIngredient.of("mushroomWoodBlewit"), new ItemStack(ItemsTFC.ROASTED_WOOD_BLEWIT), 200, 480).setRegistryName("roasted_wood_blewit"),
//                new HeatRecipeSimple(IIngredient.of("mushroomWoollyGomphus"), new ItemStack(ItemsTFC.ROASTED_WOOLLY_GOMPHUS), 200, 480).setRegistryName("roasted_woolly_gomphus"),
//
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_PORCINI), 480).setRegistryName("burned_porcini"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_AMANITA), 480).setRegistryName("burned_amanita"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_BLACK_POWDERPUFF), 480).setRegistryName("burned_black_powderpuff"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_CHANTERELLE), 480).setRegistryName("burned_chanterelle"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_DEATH_CAP), 480).setRegistryName("burned_death_cap"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_GIANT_CLUB), 480).setRegistryName("burned_giant_club"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_PARASOL_MUSHROOM), 480).setRegistryName("burned_parasol_mushroom"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_STINKHORN), 480).setRegistryName("burned_stinkhorn"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_WEEPING_MILK_CAP), 480).setRegistryName("burned_weeping_milk_cap"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_WOOD_BLEWIT), 480).setRegistryName("burned_wood_blewit"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_WOOLLY_GOMPHUS), 480).setRegistryName("burned_woolly_gomphus"),
//
//                // Food
//                new HeatRecipeSimple(IIngredient.of("rawEel"), new ItemStack(ItemsTFC.COOKED_EEL), 200, 480).setRegistryName("cooked_eel"),
//                new HeatRecipeSimple(IIngredient.of("rawCrab"), new ItemStack(ItemsTFC.COOKED_CRAB), 200, 480).setRegistryName("cooked_crab"),
//                new HeatRecipeSimple(IIngredient.of("rawClam"), new ItemStack(ItemsTFC.COOKED_CLAM), 200, 480).setRegistryName("cooked_clam"),
//                new HeatRecipeSimple(IIngredient.of("rawScallop"), new ItemStack(ItemsTFC.COOKED_SCALLOP), 200, 480).setRegistryName("cooked_scallop"),
//                new HeatRecipeSimple(IIngredient.of("rawStarfish"), new ItemStack(ItemsTFC.COOKED_STARFISH), 200, 480).setRegistryName("cooked_starfish"),
//                new HeatRecipeSimple(IIngredient.of("rawSnail"), new ItemStack(ItemsTFC.COOKED_SNAIL), 200, 480).setRegistryName("cooked_snail"),
//                new HeatRecipeSimple(IIngredient.of("rawWorm"), new ItemStack(ItemsTFC.COOKED_WORM), 200, 480).setRegistryName("cooked_worm"),
//
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_EEL), 480).setRegistryName("burned_eel"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_CRAB), 480).setRegistryName("burned_crab"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_CLAM), 480).setRegistryName("burned_clam"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_SCALLOP), 480).setRegistryName("burned_scallop"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_STARFISH), 480).setRegistryName("burned_starfish"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_SNAIL), 480).setRegistryName("burned_snail"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.COOKED_WORM), 480).setRegistryName("burned_worm"),
//
//                // Nut Roasting
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.BEECHNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BEECHNUT_NUT), 200, 480).setRegistryName("roasted_beechnut"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.BLACK_WALNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BLACK_WALNUT_NUT), 200, 480).setRegistryName("roasted_black_walnut"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.BUTTERNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BUTTERNUT_NUT), 200, 480).setRegistryName("roasted_butternut"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.GINKGO_NUT_NUT)), new ItemStack(ItemsTFC.ROASTED_GINKGO_NUT_NUT), 200, 480).setRegistryName("roasted_ginkgo_nut"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.HAZELNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_HAZELNUT_NUT), 200, 480).setRegistryName("roasted_hazelnut"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.WALNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_WALNUT_NUT), 200, 480).setRegistryName("roasted_walnut"),
//
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_BEECHNUT_NUT), 480).setRegistryName("burned_beechnut"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_BLACK_WALNUT_NUT), 480).setRegistryName("burned_black_walnut"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_BUTTERNUT_NUT), 480).setRegistryName("burned_butternut"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_GINKGO_NUT_NUT), 480).setRegistryName("burned_ginkgo_nut"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_HAZELNUT_NUT), 480).setRegistryName("burned_hazelnut"),
//                HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_WALNUT_NUT), 480).setRegistryName("burned_walnut"),
//
//                // Kaolinite Clay
//                //new HeatRecipeSimple(IIngredient.of("clayKaolinite"), new ItemStack(ItemPowder.get(Powder.KAOLINITE)), 200).setRegistryName("kaolinite_clay"),
//                //new HeatRecipeSimple(IIngredient.of(ItemPowder.get(Powder.KAOLINITE)), new ItemStack(ItemPowder.get(Powder.KAOLINITE)), 1599f, 1).setRegistryName("burnt_kaolinite_clay"),
//
//                // Torches
//                new HeatRecipeSimple(IIngredient.of("twig"), new ItemStack(Blocks.TORCH, 6), 50).setRegistryName("torch_twig"),
//                new HeatRecipeSimple(IIngredient.of("driftwood"), new ItemStack(Blocks.TORCH, 12), 60).setRegistryName("torch_driftwood"),
//
//                // Ash
//                new HeatRecipeSimple(IIngredient.of("straw"), new ItemStack(ItemsTFC.WOOD_ASH, 1), 350, 750).setRegistryName("straw_ash"),
//                new HeatRecipeSimple(IIngredient.of("twig"), new ItemStack(ItemsTFC.WOOD_ASH, 2), 350, 750).setRegistryName("twig_ash"),
//                new HeatRecipeSimple(IIngredient.of("torch"), new ItemStack(ItemsTFC.WOOD_ASH, 2), 350, 750).setRegistryName("torch_ash_1"),
//                new HeatRecipeSimple(IIngredient.of(Blocks.TORCH), new ItemStack(ItemsTFC.WOOD_ASH, 2), 350, 750).setRegistryName("torch_ash_2"),
//
//                // Charred Bones
//                new HeatRecipeSimple(IIngredient.of("bone"), new ItemStack(ItemsTFC.CHARRED_BONES), 425, 850).setRegistryName("charred_bones_heat"),
//                new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.CHARRED_BONES)), new ItemStack(ItemsTFC.CHARRED_BONES), 1599f, 1).setRegistryName("burnt_charred_bones")
//        );*/
//        /*
//
//        if (!TFCFlorae.FirmaLifeAdded)
//        {
//            r.registerAll(
//
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.ACORN_NUT)), new ItemStack(ItemsTFC.ROASTED_ACORN_NUT), 200, 480).setRegistryName("roasted_acorn"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.CHESTNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_CHESTNUT_NUT), 200, 480).setRegistryName("roasted_chestnut"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.HICKORY_NUT_NUT)), new ItemStack(ItemsTFC.ROASTED_HICKORY_NUT_NUT), 200, 480).setRegistryName("roasted_hickory_nut"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.PECAN_NUT)), new ItemStack(ItemsTFC.ROASTED_PECAN_NUT), 200, 480).setRegistryName("roasted_pecan"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.PINE_NUT)), new ItemStack(ItemsTFC.ROASTED_PINE_NUT), 200, 480).setRegistryName("roasted_pine_nut"),
//
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_ACORN_NUT), 480).setRegistryName("burned_acorn"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_CHESTNUT_NUT), 480).setRegistryName("burned_chestnut"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_HICKORY_NUT_NUT), 480).setRegistryName("burned_hickory_nut"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_PECAN_NUT), 480).setRegistryName("burned_pecan"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_PINE_NUT), 480).setRegistryName("burned_pine_nut"),
//
//                    // Food Roasting/Drying
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.BLACK_TEA)), new ItemStack(ItemsTFC.DRIED_BLACK_TEA), 200, 480).setRegistryName("dried_black_tea"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.GREEN_TEA)), new ItemStack(ItemsTFC.DRIED_GREEN_TEA), 200, 480).setRegistryName("dried_green_tea"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.WHITE_TEA)), new ItemStack(ItemsTFC.DRIED_WHITE_TEA), 200, 480).setRegistryName("dried_white_tea"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.CANNABIS_BUD)), new ItemStack(ItemsTFC.DRIED_CANNABIS_BUD), 200, 480).setRegistryName("dried_cannabis_bud"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.CANNABIS_LEAF)), new ItemStack(ItemsTFC.DRIED_CANNABIS_LEAF), 200, 480).setRegistryName("dried_cannabis_leaf"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.COCA_LEAF)), new ItemStack(ItemsTFC.DRIED_COCA_LEAF), 200, 480).setRegistryName("dried_coca_leaf"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.OPIUM_POPPY_BULB)), new ItemStack(ItemsTFC.DRIED_OPIUM_POPPY_BULB), 200, 480).setRegistryName("dried_opium_poppy_bulb"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.PEYOTE)), new ItemStack(ItemsTFC.DRIED_PEYOTE), 200, 480).setRegistryName("dried_peyote"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.TOBACCO_LEAF)), new ItemStack(ItemsTFC.DRIED_TOBACCO_LEAF), 200, 480).setRegistryName("dried_tobacco_leaf"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.DRIED_COFFEA_CHERRIES)), new ItemStack(ItemsTFC.ROASTED_COFFEE_BEANS), 200, 480).setRegistryName("roasted_coffea_cherries_firepit"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.CHAMOMILE_HEAD)), new ItemStack(ItemsTFC.DRIED_CHAMOMILE_HEAD), 200, 480).setRegistryName("roasted_chamomile_head"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.DANDELION_HEAD)), new ItemStack(ItemsTFC.DRIED_DANDELION_HEAD), 200, 480).setRegistryName("roasted_dandelion_head"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.LABRADOR_TEA_HEAD)), new ItemStack(ItemsTFC.DRIED_LABRADOR_TEA_HEAD), 200, 480).setRegistryName("roasted_labrador_tea_head"),
//                    new HeatRecipeSimple(IIngredient.of(new ItemStack(ItemsTFC.SUNFLOWER_HEAD)), new ItemStack(ItemsTFC.DRIED_SUNFLOWER_HEAD), 200, 480).setRegistryName("roasted_sunflower_head"),
//
//                    // Food Destroy
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_BLACK_TEA), 480).setRegistryName("burned_black_tea"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_GREEN_TEA), 480).setRegistryName("burned_green_tea"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_WHITE_TEA), 480).setRegistryName("burned_white_tea"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_CANNABIS_BUD), 480).setRegistryName("burned_cannabis_bud"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_CANNABIS_LEAF), 480).setRegistryName("burned_cannabis_leaf"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_COCA_LEAF), 480).setRegistryName("burned_coca_leaf"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_OPIUM_POPPY_BULB), 480).setRegistryName("burned_opium_poppy_bulb"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_PEYOTE), 480).setRegistryName("burned_peyote"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_TOBACCO_LEAF), 480).setRegistryName("burned_tobacco_leaf"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.ROASTED_COFFEE_BEANS), 480).setRegistryName("burned_coffea_cherries"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_CHAMOMILE_HEAD), 480).setRegistryName("burned_chamomile_head"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_DANDELION_HEAD), 480).setRegistryName("burned_dandelion_head"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_LABRADOR_TEA_HEAD), 480).setRegistryName("burned_labrador_tea_head"),
//                    HeatRecipe.destroy(IIngredient.of(ItemsTFC.DRIED_SUNFLOWER_HEAD), 480).setRegistryName("burned_sunflower_head")
//            );
//        }*/
//    }
//
//
//
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterCraftingRecipeEvent(RegistryEvent.Register<IRecipe> event)
//    {
//        IForgeRegistry<IRecipe> r = event.getRegistry();
//        //Register all strips
//        List<ItemStack> allHammers = new ArrayList<>();
//        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
//        {
//            if (!metal.isToolMetal())
//                continue;
//            allHammers.add(new ItemStack(ItemMetal.get(metal, Metal.ItemType.HAMMER), 1, OreDictionary.WILDCARD_VALUE));
//        }
//        Ingredient hammer = Ingredient.fromStacks(allHammers.toArray(new ItemStack[0]));
//
//        ResourceLocation groupSurfaceRock = new ResourceLocation(MODID, "surface_rock");
//
//        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//        {
//            // Surface rocks to TFC rocks
//            Ingredient ingredient = Ingredient.fromStacks(new ItemStack(BlockSurfaceRock.get(rock)));
//            ItemStack output = new ItemStack(ItemRock.get(rock), 1);
//            if (!output.isEmpty())
//            {
//                NonNullList<Ingredient> list = NonNullList.create();
//                list.add(hammer);
//                list.add(ingredient);
//                //noinspection ConstantConditions
//                r.register(new ShapelessDamageRecipe(groupSurfaceRock, list, output, 1).setRegistryName(MODID, rock.getRegistryName().getPath().toLowerCase() + "_rock_hammer"));
//            }
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterKnappingRecipeEventFL(RegistryEvent.Register<KnappingRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            event.getRegistry().registerAll(
//                    // Earthenware Clay
//                    new KnappingRecipeSimple(KnappingTypes.EARTHENWARE_CLAY, true, new ItemStack(BlocksFL.OVEN), "XXXXX", "XX XX", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "earthenware_clay_oven"),
//                    new KnappingRecipeSimple(KnappingTypes.EARTHENWARE_CLAY, true, new ItemStack(BlocksFL.OVEN_CHIMNEY), "XX XX", "X   X", "X   X", "X   X", "X   X").setRegistryName(TFCFlorae.MODID, "earthenware_clay_oven_chimney"),
//                    new KnappingRecipeSimple(KnappingTypes.EARTHENWARE_CLAY, true, new ItemStack(BlocksFL.OVEN_WALL), "    X", "   XX", "   XX", "  XXX", "  XXX").setRegistryName(TFCFlorae.MODID, "earthenware_clay_oven_wall"),
//
//                    // Earthenware Mallet Mold
//                    new KnappingRecipeSimple(KnappingTypes.EARTHENWARE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_EARTHENWARE_MALLET_MOLD, 1), "XXXXX", "     ", "   X ", "XXXXX", "XXXXX").setRegistryName(TFCFlorae.MODID, "unfired_earthenware_clay_mallet_mold"),
//
//                    // Kaolinite Clay
//                    new KnappingRecipeSimple(KnappingTypes.KAOLINITE_CLAY, true, new ItemStack(BlocksFL.OVEN), "XXXXX", "XX XX", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "kaolinite_clay_oven"),
//                    new KnappingRecipeSimple(KnappingTypes.KAOLINITE_CLAY, true, new ItemStack(BlocksFL.OVEN_CHIMNEY), "XX XX", "X   X", "X   X", "X   X", "X   X").setRegistryName(TFCFlorae.MODID, "kaolinite_clay_oven_chimney"),
//                    new KnappingRecipeSimple(KnappingTypes.KAOLINITE_CLAY, true, new ItemStack(BlocksFL.OVEN_WALL), "    X", "   XX", "   XX", "  XXX", "  XXX").setRegistryName(TFCFlorae.MODID, "kaolinite_clay_oven_wall"),
//
//                    // Kaolinite Mallet Mold
//                    new KnappingRecipeSimple(KnappingTypes.KAOLINITE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_KAOLINITE_MALLET_MOLD, 1), "XXXXX", "     ", "   X ", "XXXXX", "XXXXX").setRegistryName(TFCFlorae.MODID, "unfired_kaolinite_clay_mallet_mold"),
//
//                    // Stoneware Clay
//                    new KnappingRecipeSimple(KnappingTypes.STONEWARE_CLAY, true, new ItemStack(BlocksFL.OVEN), "XXXXX", "XX XX", "X   X", "X   X", "XXXXX").setRegistryName(TFCFlorae.MODID, "stoneware_clay_oven"),
//                    new KnappingRecipeSimple(KnappingTypes.STONEWARE_CLAY, true, new ItemStack(BlocksFL.OVEN_CHIMNEY), "XX XX", "X   X", "X   X", "X   X", "X   X").setRegistryName(TFCFlorae.MODID, "stoneware_clay_oven_chimney"),
//                    new KnappingRecipeSimple(KnappingTypes.STONEWARE_CLAY, true, new ItemStack(BlocksFL.OVEN_WALL), "    X", "   XX", "   XX", "  XXX", "  XXX").setRegistryName(TFCFlorae.MODID, "stoneware_clay_oven_wall"),
//
//                    // Stoneware Mallet Mold
//                    new KnappingRecipeSimple(KnappingTypes.STONEWARE_CLAY, true, new ItemStack(ItemsTFC.UNFIRED_STONEWARE_MALLET_MOLD, 1), "XXXXX", "     ", "   X ", "XXXXX", "XXXXX").setRegistryName(TFCFlorae.MODID, "unfired_stoneware_clay_mallet_mold")
//            );
//        }
//    }*/
//
//    /*
//    @SuppressWarnings("rawtypes")
//    @SubscribeEvent
//    public static void onRegisterHeatRecipeEventFL(RegistryEvent.Register<HeatRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistry<HeatRecipe> r = event.getRegistry();
//            event.getRegistry().registerAll(
//
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_EARTHENWARE_MALLET_MOLD), new ItemStack(ItemsTFC.EARTHENWARE_MALLET_MOLD), 1599.0F, Metal.Tier.TIER_I).setRegistryName(TFCFlorae.MODID, "earthenware_clay_mallet_mold"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_KAOLINITE_MALLET_MOLD), new ItemStack(ItemsTFC.KAOLINITE_MALLET_MOLD), 1599.0F, Metal.Tier.TIER_I).setRegistryName(TFCFlorae.MODID, "kaolinite_clay_mallet_mold"),
//                    new HeatRecipeSimple(IIngredient.of(ItemsTFC.UNFIRED_STONEWARE_MALLET_MOLD), new ItemStack(ItemsTFC.STONEWARE_MALLET_MOLD), 1599.0F, Metal.Tier.TIER_I).setRegistryName(TFCFlorae.MODID, "stoneware_clay_mallet_mold")
//            );
//
//            //Remove recipes
//            if (ConfigFL.General.COMPAT.removeTFC)
//            {
//                IForgeRegistryModifiable modRegistry = (IForgeRegistryModifiable) TFCRegistries.HEAT;
//                String[] regNames = {"amaranth_bread", "buckwheat_bread", "fonio_bread", "millet_bread", "quinoa_bread", "spelt_bread"};
//                for (String name : regNames)
//                {
//                    HeatRecipe recipe = TFCRegistries.HEAT.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        modRegistry.remove(recipe.getRegistryName());
//                        if (ConfigFL.General.COMPAT.logging)
//                            FirmaLife.logger.info("Removed heating recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRecipeRegisterFL(RegistryEvent.Register<IRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            if (ConfigFL.General.COMPAT.removeTFC)
//            {
//                IForgeRegistryModifiable<IRecipe> registry = (IForgeRegistryModifiable<IRecipe>) event.getRegistry();
//                String[] regNames = {
//                        "food/dough/amaranth", "food/dough/buckwheat", "food/dough/fonio", "food/dough/millet", "food/dough/quinoa", "food/dough/spelt",
//                        "food/sandwich/amaranth", "food/sandwich/buckwheat", "food/sandwich/fonio", "food/sandwich/millet", "food/sandwich/quinoa", "food/sandwich/spelt"
//                };
//                for (String name : regNames)
//                {
//                    IRecipe recipe = registry.getValue(new ResourceLocation("tfcflorae", name));
//                    if (recipe != null)
//                    {
//                        registry.remove(recipe.getRegistryName());
//                        if (ConfigFL.General.COMPAT.logging)
//                            FirmaLife.logger.info("Removed crafting recipe tfcflorae:{}", name);
//                    }
//                }
//            }
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    @SuppressWarnings("ConstantConditions")
//    public static void onRegisterNutTreeEvent(RegistryEvent.Register<NutRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistry<NutRecipe> r = event.getRegistry();
//
//            Tree chestnut = TFCRegistries.TREES.getValue(DefaultTrees.CHESTNUT);
//            Tree oak = TFCRegistries.TREES.getValue(DefaultTrees.OAK);
//            Tree hickory = TFCRegistries.TREES.getValue(DefaultTrees.HICKORY);
//            Tree beech = TFCRegistries.TREES.getValue(TreesTFCF.BEECH);
//            Tree black_walnut = TFCRegistries.TREES.getValue(TreesTFCF.BLACK_WALNUT);
//            Tree butternut = TFCRegistries.TREES.getValue(TreesTFCF.BUTTERNUT);
//            Tree european_oak = TFCRegistries.TREES.getValue(TreesTFCF.EUROPEAN_OAK);
//            Tree ginkgo = TFCRegistries.TREES.getValue(TreesTFCF.GINKGO);
//            Tree hazel = TFCRegistries.TREES.getValue(TreesTFCF.HAZEL);
//            Tree hemlock = TFCRegistries.TREES.getValue(TreesTFCF.HEMLOCK);
//            Tree ironwood = TFCRegistries.TREES.getValue(TreesTFCF.IRONWOOD);
//            Tree kauri = TFCRegistries.TREES.getValue(TreesTFCF.KAURI);
//            Tree larch = TFCRegistries.TREES.getValue(TreesTFCF.LARCH);
//            Tree nordmann_fir = TFCRegistries.TREES.getValue(TreesTFCF.NORDMANN_FIR);
//            Tree norway_spruce = TFCRegistries.TREES.getValue(TreesTFCF.NORWAY_SPRUCE);
//            Tree redwood = TFCRegistries.TREES.getValue(TreesTFCF.REDWOOD);
//            Tree walnut = TFCRegistries.TREES.getValue(TreesTFCF.WALNUT);
//
//            r.registerAll(
//
//                    new NutRecipe(BlockLogTFC.get(european_oak), BlockLeavesTFC.get(european_oak), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("european_oak_nut"),
//                    new NutRecipe(BlockLogTFC.get(european_oak), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_EUROPEAN_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("european_oak_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(european_oak), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_EUROPEAN_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("european_oak_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(european_oak), BlockLeavesTFCF.get(SeasonalTrees.RED_EUROPEAN_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("european_oak_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(oak), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("oak_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(oak), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("oak_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(oak), BlockLeavesTFCF.get(SeasonalTrees.RED_OAK), new ItemStack(ItemsFL.getFood(FoodFL.ACORNS))).setRegistryName("oak_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(chestnut), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_CHESTNUT), new ItemStack(ItemsFL.getFood(FoodFL.CHESTNUTS))).setRegistryName("chestnut_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(chestnut), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_CHESTNUT), new ItemStack(ItemsFL.getFood(FoodFL.CHESTNUTS))).setRegistryName("chestnut_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(chestnut), BlockLeavesTFCF.get(SeasonalTrees.RED_CHESTNUT), new ItemStack(ItemsFL.getFood(FoodFL.CHESTNUTS))).setRegistryName("chestnut_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(hickory), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_HICKORY), new ItemStack(ItemsFL.getFood(FoodFL.PECAN_NUTS))).setRegistryName("hickory_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(hickory), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_HICKORY), new ItemStack(ItemsFL.getFood(FoodFL.PECAN_NUTS))).setRegistryName("hickory_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(hickory), BlockLeavesTFCF.get(SeasonalTrees.RED_HICKORY), new ItemStack(ItemsFL.getFood(FoodFL.PECAN_NUTS))).setRegistryName("hickory_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(beech), BlockLeavesTFC.get(beech), new ItemStack(ItemsTFC.BEECHNUT)).setRegistryName("beech_nut"),
//                    new NutRecipe(BlockLogTFC.get(beech), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_BEECH), new ItemStack(ItemsTFC.BEECHNUT)).setRegistryName("beech_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(beech), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_BEECH), new ItemStack(ItemsTFC.BEECHNUT)).setRegistryName("beech_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(beech), BlockLeavesTFCF.get(SeasonalTrees.RED_BEECH), new ItemStack(ItemsTFC.BEECHNUT)).setRegistryName("beech_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(black_walnut), BlockLeavesTFC.get(black_walnut), new ItemStack(ItemsTFC.BLACK_WALNUT)).setRegistryName("black_walnut_nut"),
//                    new NutRecipe(BlockLogTFC.get(black_walnut), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_BLACK_WALNUT), new ItemStack(ItemsTFC.BLACK_WALNUT)).setRegistryName("black_walnut_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(black_walnut), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_BLACK_WALNUT), new ItemStack(ItemsTFC.BLACK_WALNUT)).setRegistryName("black_walnut_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(black_walnut), BlockLeavesTFCF.get(SeasonalTrees.RED_BLACK_WALNUT), new ItemStack(ItemsTFC.BLACK_WALNUT)).setRegistryName("black_walnut_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(butternut), BlockLeavesTFC.get(butternut), new ItemStack(ItemsTFC.BUTTERNUT)).setRegistryName("butternut_nut"),
//                    new NutRecipe(BlockLogTFC.get(butternut), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_BUTTERNUT), new ItemStack(ItemsTFC.BUTTERNUT)).setRegistryName("butternut_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(butternut), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_BUTTERNUT), new ItemStack(ItemsTFC.BUTTERNUT)).setRegistryName("butternut_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(butternut), BlockLeavesTFCF.get(SeasonalTrees.RED_BUTTERNUT), new ItemStack(ItemsTFC.BUTTERNUT)).setRegistryName("butternut_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(ginkgo), BlockLeavesTFC.get(ginkgo), new ItemStack(ItemsTFC.GINKGO_NUT)).setRegistryName("ginkgo_nut"),
//                    new NutRecipe(BlockLogTFC.get(ginkgo), BlockLeavesTFCF.get(SeasonalTrees.GINKGO), new ItemStack(ItemsTFC.GINKGO_NUT)).setRegistryName("ginkgo_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(hazel), BlockLeavesTFC.get(hazel), new ItemStack(ItemsTFC.HAZELNUT)).setRegistryName("hazel_nut"),
//                    new NutRecipe(BlockLogTFC.get(hazel), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_HAZEL), new ItemStack(ItemsTFC.HAZELNUT)).setRegistryName("hazel_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(hazel), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_HAZEL), new ItemStack(ItemsTFC.HAZELNUT)).setRegistryName("hazel_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(hazel), BlockLeavesTFCF.get(SeasonalTrees.RED_HAZEL), new ItemStack(ItemsTFC.HAZELNUT)).setRegistryName("hazel_nut_red"),
//                    new NutRecipe(BlockLogTFC.get(hemlock), BlockLeavesTFC.get(hemlock), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("hemlock_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(ironwood), BlockLeavesTFC.get(ironwood), new ItemStack(ItemsTFC.HOPS)).setRegistryName("ironwood_hops"),
//                    new NutRecipe(BlockLogTFC.get(kauri), BlockLeavesTFC.get(kauri), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("kauri_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(larch), BlockLeavesTFCF.get(SeasonalTrees.LARCH), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("larch_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(nordmann_fir), BlockLeavesTFC.get(nordmann_fir), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("nordmann_fir_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(norway_spruce), BlockLeavesTFC.get(norway_spruce), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("norway_spruce_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(redwood), BlockLeavesTFC.get(redwood), new ItemStack(ItemsTFC.PINECONE)).setRegistryName("redwood_pinecone"),
//                    new NutRecipe(BlockLogTFC.get(walnut), BlockLeavesTFC.get(walnut), new ItemStack(ItemsTFC.WALNUT)).setRegistryName("walnut_fruit"),
//                    new NutRecipe(BlockLogTFC.get(walnut), BlockLeavesTFCF.get(SeasonalTrees.YELLOW_WALNUT), new ItemStack(ItemsTFC.WALNUT)).setRegistryName("walnut_nut_yellow"),
//                    new NutRecipe(BlockLogTFC.get(walnut), BlockLeavesTFCF.get(SeasonalTrees.ORANGE_WALNUT), new ItemStack(ItemsTFC.WALNUT)).setRegistryName("walnut_nut_orange"),
//                    new NutRecipe(BlockLogTFC.get(walnut), BlockLeavesTFCF.get(SeasonalTrees.RED_WALNUT), new ItemStack(ItemsTFC.WALNUT)).setRegistryName("walnut_nut_red")
//            );
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterCrackingRecipeEvent(RegistryEvent.Register<CrackingRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistry<CrackingRecipe> r = event.getRegistry();
//            r.registerAll(
//
//                    // Regular Trees
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.ACORN), new ItemStack(ItemsTFC.ACORN_NUT), 0.5f).setRegistryName("acorn_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.BEECHNUT), new ItemStack(ItemsTFC.BEECHNUT_NUT), 0.5f).setRegistryName("beechnut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.BLACK_WALNUT), new ItemStack(ItemsTFC.BLACK_WALNUT_NUT), 0.5f).setRegistryName("black_walnut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.BUTTERNUT), new ItemStack(ItemsTFC.BUTTERNUT_NUT), 0.5f).setRegistryName("butternut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.CHESTNUT), new ItemStack(ItemsTFC.CHESTNUT_NUT), 0.5f).setRegistryName("chestnut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.GINKGO_NUT), new ItemStack(ItemsTFC.GINKGO_NUT_NUT), 0.5f).setRegistryName("ginkgo_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.HAZELNUT), new ItemStack(ItemsTFC.HAZELNUT_NUT), 0.5f).setRegistryName("hazelnut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.HICKORY_NUT), new ItemStack(ItemsTFC.HICKORY_NUT_NUT), 0.5f).setRegistryName("hickory_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.PINECONE), new ItemStack(ItemsTFC.PINE_NUT), 0.5f).setRegistryName("pine_nut_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.PECAN), new ItemStack(ItemsTFC.PECAN_NUT), 0.5f).setRegistryName("pecan_fruit"),
//                    new CrackingRecipe(IIngredient.of(ItemsTFC.WALNUT), new ItemStack(ItemsTFC.WALNUT_NUT), 0.5f).setRegistryName("walnut_fruit")
//            );
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterOvenRecipeEventFL(RegistryEvent.Register<OvenRecipe> event)
//    {
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistry<OvenRecipe> r = event.getRegistry();
//            int hour = ICalendar.TICKS_IN_HOUR;
//
//            // Mud Pottery
//            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//            {
//                ItemFiredMudBrick firedMudBrick = ItemFiredMudBrick.get(ItemUnfiredMudBrick.get(rock));
//
//                OvenRecipe mudBrick = new OvenRecipe(IIngredient.of(ItemUnfiredMudBrick.get(rock)), new ItemStack(firedMudBrick), 4 * hour);
//                event.getRegistry().register(mudBrick.setRegistryName(rock.getRegistryName().getPath().toLowerCase() + "_unfired_mud_brick"));
//            }
//
//            r.registerAll(
//                    new OvenRecipe(IIngredient.of(ItemsTFC.HASH_MUFFIN_DOUGH), new ItemStack(ItemsTFC.HASH_MUFFIN), 4 * hour).setRegistryName(TFCFlorae.MODID, "hash_muffin_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.AMARANTH_DOUGH), new ItemStack(ItemsTFC.AMARANTH_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "amaranth_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.BUCKWHEAT_DOUGH), new ItemStack(ItemsTFC.BUCKWHEAT_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "buckwheat_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.FONIO_DOUGH), new ItemStack(ItemsTFC.FONIO_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "fonio_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.MILLET_DOUGH), new ItemStack(ItemsTFC.MILLET_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "millet_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.QUINOA_DOUGH), new ItemStack(ItemsTFC.QUINOA_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "quinoa_dough_oven"),
//                    new OvenRecipe(IIngredient.of(ItemsTFC.SPELT_DOUGH), new ItemStack(ItemsTFC.SPELT_BREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "spelt_dough_oven"),
//
//                    new OvenRecipe(IIngredient.of("amaranthFlatbreadDough"), new ItemStack(ItemsTFC.AMARANTH_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "amaranth_flatbread_dough_oven"),
//                    new OvenRecipe(IIngredient.of("buckwheatFlatbreadDough"), new ItemStack(ItemsTFC.BUCKWHEAT_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "buckwheat_flatbread_dough_oven"),
//                    new OvenRecipe(IIngredient.of("fonioFlatbreadDough"), new ItemStack(ItemsTFC.FONIO_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "fonio_flatbread_dough_oven"),
//                    new OvenRecipe(IIngredient.of("milletFlatbreadDough"), new ItemStack(ItemsTFC.MILLET_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "millet_flatbread_dough_oven"),
//                    new OvenRecipe(IIngredient.of("quinoaFlatbreadDough"), new ItemStack(ItemsTFC.QUINOA_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "quinoa_flatbread_dough_oven"),
//                    new OvenRecipe(IIngredient.of("speltFlatbreadDough"), new ItemStack(ItemsTFC.SPELT_FLATBREAD), 4 * hour).setRegistryName(TFCFlorae.MODID, "spelt_flatbread_dough_oven"),
//
//                    //new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.ACORN_NUT)), new ItemStack(ItemsTFC.ROASTED_ACORN_NUT), 2 * hour).setRegistryName("acorn_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.BEECHNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BEECHNUT_NUT), 2 * hour).setRegistryName("beechnut_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.BLACK_WALNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BLACK_WALNUT_NUT), 2 * hour).setRegistryName("black_walnut_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.BUTTERNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_BUTTERNUT_NUT), 2 * hour).setRegistryName("butternut_roasted_oven"),
//                    //new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.CHESTNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_CHESTNUT_NUT), 2 * hour).setRegistryName("chestnut_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.GINKGO_NUT_NUT)), new ItemStack(ItemsTFC.ROASTED_GINKGO_NUT_NUT), 2 * hour).setRegistryName("ginkgo_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.HAZELNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_HAZELNUT_NUT), 2 * hour).setRegistryName("hazelnut_roasted_oven"),
//                    //new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.HICKORY_NUT_NUT)), new ItemStack(ItemsTFC.ROASTED_HICKORY_NUT_NUT), 2 * hour).setRegistryName("hickory_roasted_oven"),
//                    //new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.PINE_NUT)), new ItemStack(ItemsTFC.ROASTED_PINE_NUT), 2 * hour).setRegistryName("pine_nut_roasted_oven"),
//                    //new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.PECAN_NUT)), new ItemStack(ItemsTFC.ROASTED_PECAN_NUT), 2 * hour).setRegistryName("pecan_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.WALNUT_NUT)), new ItemStack(ItemsTFC.ROASTED_WALNUT_NUT), 2 * hour).setRegistryName("walnut_roasted_oven"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsFL.getFood(FoodFL.ACORN_FRUIT))), new ItemStack(ItemsTFC.ROASTED_ACORN_NUT), 2 * hour).setRegistryName("acorn_roasted_oven_fl"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsFL.getFood(FoodFL.PINE_NUTS))), new ItemStack(ItemsTFC.ROASTED_PINE_NUT), 2 * hour).setRegistryName("pine_nut_roasted_oven_fl"),
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsFL.getFood(FoodFL.PECAN_NUTS))), new ItemStack(ItemsTFC.ROASTED_PECAN_NUT), 2 * hour).setRegistryName("pecan_roasted_oven_fl"),
//
//                    new OvenRecipe(IIngredient.of(new ItemStack(ItemsTFC.DRIED_COFFEA_CHERRIES)), new ItemStack(ItemsTFC.ROASTED_COFFEE_BEANS), 2 * hour).setRegistryName("coffee_beans_roasted_oven"),
//
//                    new OvenRecipe(IIngredient.of("epiphyteArtistsConk"), new ItemStack(ItemsTFC.ROASTED_ARTISTS_CONK), 2 * hour).setRegistryName("roasted_artists_conk_oven"),
//                    new OvenRecipe(IIngredient.of("epiphyteSulphurShelf"), new ItemStack(ItemsTFC.ROASTED_SULPHUR_SHELF), 2 * hour).setRegistryName("roasted_sulphur_shelf_oven"),
//                    new OvenRecipe(IIngredient.of("epiphyteTurkeyTail"), new ItemStack(ItemsTFC.ROASTED_TURKEY_TAIL), 2 * hour).setRegistryName("roasted_turkey_tail_oven"),
//                    new OvenRecipe(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.PORCINI))), new ItemStack(ItemsTFC.ROASTED_PORCINI), 2 * hour).setRegistryName("roasted_porcini_oven_specific"),
//                    new OvenRecipe(IIngredient.of(BlockPlantTFC.get(TFCRegistries.PLANTS.getValue(DefaultPlants.AMANITA))), new ItemStack(ItemsTFC.ROASTED_AMANITA), 2 * hour).setRegistryName("roasted_amanita_oven_specific"),
//                    new OvenRecipe(IIngredient.of("mushroomPorcini"), new ItemStack(ItemsTFC.ROASTED_PORCINI), 2 * hour).setRegistryName("roasted_porcini_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomAmanita"), new ItemStack(ItemsTFC.ROASTED_AMANITA), 2 * hour).setRegistryName("roasted_amanita_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomBlackPowderpuff"), new ItemStack(ItemsTFC.ROASTED_BLACK_POWDERPUFF), 2 * hour).setRegistryName("roasted_black_powderpuff_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomChanterelle"), new ItemStack(ItemsTFC.ROASTED_CHANTERELLE), 2 * hour).setRegistryName("roasted_chanterelle_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomDeathCap"), new ItemStack(ItemsTFC.ROASTED_DEATH_CAP), 2 * hour).setRegistryName("roasted_death_cap_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomGiantClub"), new ItemStack(ItemsTFC.ROASTED_GIANT_CLUB), 2 * hour).setRegistryName("roasted_giant_club_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomParasol"), new ItemStack(ItemsTFC.ROASTED_PARASOL_MUSHROOM), 2 * hour).setRegistryName("roasted_parasol_mushroom_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomStinkhorn"), new ItemStack(ItemsTFC.ROASTED_STINKHORN), 2 * hour).setRegistryName("roasted_stinkhorn_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomWeepingMilkCap"), new ItemStack(ItemsTFC.ROASTED_WEEPING_MILK_CAP), 2 * hour).setRegistryName("roasted_weeping_milk_cap_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomWoodBlewit"), new ItemStack(ItemsTFC.ROASTED_WOOD_BLEWIT), 2 * hour).setRegistryName("roasted_wood_blewit_oven"),
//                    new OvenRecipe(IIngredient.of("mushroomWoollyGomphus"), new ItemStack(ItemsTFC.ROASTED_WOOLLY_GOMPHUS), 2 * hour).setRegistryName("roasted_woolly_gomphus_oven"),
//
//                    new OvenRecipe(IIngredient.of("rawEel"), new ItemStack(ItemsTFC.COOKED_EEL), 2 * hour).setRegistryName("cooked_eel_oven"),
//                    new OvenRecipe(IIngredient.of("rawCrab"), new ItemStack(ItemsTFC.COOKED_CRAB), 2 * hour).setRegistryName("cooked_crab_oven"),
//                    new OvenRecipe(IIngredient.of("rawClam"), new ItemStack(ItemsTFC.COOKED_CLAM), 2 * hour).setRegistryName("cooked_clam_oven"),
//                    new OvenRecipe(IIngredient.of("rawScallop"), new ItemStack(ItemsTFC.COOKED_SCALLOP), 2 * hour).setRegistryName("cooked_scallop_oven"),
//                    new OvenRecipe(IIngredient.of("rawStarfish"), new ItemStack(ItemsTFC.COOKED_STARFISH), 2 * hour).setRegistryName("cooked_starfish_oven"),
//                    new OvenRecipe(IIngredient.of("rawSnail"), new ItemStack(ItemsTFC.COOKED_SNAIL), 2 * hour).setRegistryName("cooked_snail_oven"),
//                    new OvenRecipe(IIngredient.of("rawWorm"), new ItemStack(ItemsTFC.COOKED_WORM), 2 * hour).setRegistryName("cooked_worm_oven"),
//
//                    new OvenRecipe(IIngredient.of("clayKaolinite"), new ItemStack(ItemPowder.get(Powder.KAOLINITE)), 1 * hour).setRegistryName("kaolinite_clay_oven"),
//                    new OvenRecipe(IIngredient.of("bone"), new ItemStack(ItemsTFC.CHARRED_BONES), 2 * hour).setRegistryName("charred_bones_heat_oven")
//            );
//        }
//    }*/
//
//
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterDryingRecipeEvent(RegistryEvent.Register<tfcflorae.objects.recipes.DryingRecipe> event)
//    {
//        IForgeRegistry<tfcflorae.objects.recipes.DryingRecipe> r = event.getRegistry();
//        int day = ICalendar.TICKS_IN_DAY;
//
//        r.registerAll(
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.SILK_WORM_HATCHERY), new ItemStack(ItemsTFC.SILK_WORM), 24000).setRegistryName("silk_worm_hatchery"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.CELLULOSE_FIBERS), new ItemStack(Items.PAPER), 24000).setRegistryName(TFCFlorae.MODID, "paper_from_cellulose_fibers"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.BLACK_TEA), new ItemStack(ItemsTFC.DRIED_BLACK_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_black_tea"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.GREEN_TEA), new ItemStack(ItemsTFC.DRIED_GREEN_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_green_tea"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.WHITE_TEA), new ItemStack(ItemsTFC.DRIED_WHITE_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_white_tea"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.CANNABIS_BUD), new ItemStack(ItemsTFC.DRIED_CANNABIS_BUD), 24000).setRegistryName(TFCFlorae.MODID, "dried_cannabis_bud"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.CANNABIS_LEAF), new ItemStack(ItemsTFC.DRIED_CANNABIS_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_cannabis_leaf"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.COCA_LEAF), new ItemStack(ItemsTFC.DRIED_COCA_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_coca_leaf"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.OPIUM_POPPY_BULB), new ItemStack(ItemsTFC.DRIED_OPIUM_POPPY_BULB), 24000).setRegistryName(TFCFlorae.MODID, "dried_opium_poppy_bulb"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.PEYOTE), new ItemStack(ItemsTFC.DRIED_PEYOTE), 24000).setRegistryName(TFCFlorae.MODID, "dried_peyote"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.TOBACCO_LEAF), new ItemStack(ItemsTFC.DRIED_TOBACCO_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_tobacco_leaf"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.COFFEA_CHERRIES), new ItemStack(ItemsTFC.DRIED_COFFEA_CHERRIES), 24000).setRegistryName(TFCFlorae.MODID, "dried_coffea_cherries"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.CHAMOMILE_HEAD), new ItemStack(ItemsTFC.DRIED_CHAMOMILE_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_chamomile_head"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.DANDELION_HEAD), new ItemStack(ItemsTFC.DRIED_DANDELION_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_dandelion_head"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.LABRADOR_TEA_HEAD), new ItemStack(ItemsTFC.DRIED_LABRADOR_TEA_HEAD), 24000).setRegistryName("dried_labrador_tea_head"),
//                new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemsTFC.SUNFLOWER_HEAD), new ItemStack(ItemsTFC.DRIED_SUNFLOWER_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_sunflower_head")
//        );
//
//        // Mud Pottery
//        for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//        {
//            ItemFiredMudBrick firedMudBrick = ItemFiredMudBrick.get(ItemUnfiredMudBrick.get(rock));
//
//            tfcflorae.objects.recipes.DryingRecipe mud = new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(ItemUnfiredMudBrick.get(rock)), new ItemStack(firedMudBrick), 6000);
//            event.getRegistry().register(mud.setRegistryName(TFCFlorae.MODID, rock.getRegistryName().getPath().toLowerCase() + "_wet_mud_brick"));
//        }
//
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            for (Fruit fruit : Fruit.values())
//            {
//                r.register(new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(fruit.getFruit()), new ItemStack(ItemsFL.getDriedFruit(fruit)), day / 2).setRegistryName(TFCFlorae.MODID, fruit.name().toLowerCase()));
//            }
//
//            r.registerAll(
//                    new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(new ItemStack(ItemsFL.CINNAMON_BARK)), new ItemStack(ItemsFL.CINNAMON), day).setRegistryName(TFCFlorae.MODID, "cinnamon_bark"),
//                    new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(new ItemStack(ItemsFL.getFood(FoodFL.COCOA_BEANS))), new ItemStack(ItemsFL.getFood(FoodFL.DRIED_COCOA_BEANS)), day / 2).setRegistryName(TFCFlorae.MODID, "cocoa_beans"),
//                    new tfcflorae.objects.recipes.DryingRecipe(IIngredient.of(new ItemStack(ItemsFL.getFood(FoodFL.PINEAPPLE))), new ItemStack(ItemsFL.DRIED_PINEAPPLE), day / 2).setRegistryName(TFCFlorae.MODID, "pineapple")
//            );
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterDryingRecipeEventFL(RegistryEvent.Register<DryingRecipe> event)
//    {
//        int day = ICalendar.TICKS_IN_DAY;
//
//        if (TFCFlorae.FirmaLifeAdded)
//        {
//            IForgeRegistry<DryingRecipe> r = event.getRegistry();
//            r.registerAll(
//                    new DryingRecipe(IIngredient.of(ItemsTFC.SILK_WORM_HATCHERY), new ItemStack(ItemsTFC.SILK_WORM), 24000).setRegistryName(TFCFlorae.MODID, "silk_worm_hatchery_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.CELLULOSE_FIBERS), new ItemStack(Items.PAPER), 24000).setRegistryName(TFCFlorae.MODID, "paper_from_cellulose_fibers_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.BLACK_TEA), new ItemStack(ItemsTFC.DRIED_BLACK_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_black_tea_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.GREEN_TEA), new ItemStack(ItemsTFC.DRIED_GREEN_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_green_tea_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.WHITE_TEA), new ItemStack(ItemsTFC.DRIED_WHITE_TEA), 24000).setRegistryName(TFCFlorae.MODID, "dried_white_tea_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.CANNABIS_BUD), new ItemStack(ItemsTFC.DRIED_CANNABIS_BUD), 24000).setRegistryName(TFCFlorae.MODID, "dried_cannabis_bud_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.CANNABIS_LEAF), new ItemStack(ItemsTFC.DRIED_CANNABIS_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_cannabis_leaf_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.COCA_LEAF), new ItemStack(ItemsTFC.DRIED_COCA_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_coca_leaf_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.OPIUM_POPPY_BULB), new ItemStack(ItemsTFC.DRIED_OPIUM_POPPY_BULB), 24000).setRegistryName(TFCFlorae.MODID, "dried_opium_poppy_bulb_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.PEYOTE), new ItemStack(ItemsTFC.DRIED_PEYOTE), 24000).setRegistryName(TFCFlorae.MODID, "dried_peyote_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.TOBACCO_LEAF), new ItemStack(ItemsTFC.DRIED_TOBACCO_LEAF), 24000).setRegistryName(TFCFlorae.MODID, "dried_tobacco_leaf_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.COFFEA_CHERRIES), new ItemStack(ItemsTFC.DRIED_COFFEA_CHERRIES), 24000).setRegistryName(TFCFlorae.MODID, "dried_coffea_cherries_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.CHAMOMILE_HEAD), new ItemStack(ItemsTFC.DRIED_CHAMOMILE_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_chamomile_head_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.DANDELION_HEAD), new ItemStack(ItemsTFC.DRIED_DANDELION_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_dandelion_head_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.LABRADOR_TEA_HEAD), new ItemStack(ItemsTFC.DRIED_LABRADOR_TEA_HEAD), 24000).setRegistryName("dried_labrador_tea_head_firmalife"),
//                    new DryingRecipe(IIngredient.of(ItemsTFC.SUNFLOWER_HEAD), new ItemStack(ItemsTFC.DRIED_SUNFLOWER_HEAD), 24000).setRegistryName(TFCFlorae.MODID, "dried_sunflower_head_firmalife")
//            );
//
//            // Mud Pottery
//            for (Rock rock : TFCRegistries.ROCKS.getValuesCollection())
//            {
//                ItemFiredMudBrick firedMudBrick = ItemFiredMudBrick.get(ItemUnfiredMudBrick.get(rock));
//
//                DryingRecipe mud = new DryingRecipe(IIngredient.of(ItemUnfiredMudBrick.get(rock)), new ItemStack(firedMudBrick), 6000);
//                event.getRegistry().register(mud.setRegistryName(TFCFlorae.MODID, rock.getRegistryName().getPath().toLowerCase() + "_wet_mud_brick_firmalife"));
//            }
//        }
//    }*/
//
//    /*
//    @SubscribeEvent
//    public static void onRegisterPlanterEvent(RegistryEvent.Register<PlanterRecipe> event)
//    {
//        IForgeRegistry<PlanterRecipe> r = event.getRegistry();
//        r.registerAll(
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.BLACK_EYED_PEAS)), new ItemStack(ItemsTFC.BLACK_EYED_PEAS), 7, true).setRegistryName("black_eyed_peas"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.CAYENNE_PEPPER)), new ItemStack(ItemsTFC.RED_CAYENNE_PEPPER), 7, true).setRegistryName("red_cayenne_pepper"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.GINSENG)), new ItemStack(ItemsTFC.GINSENG), 5, false).setRegistryName("ginseng"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.RUTABAGA)), new ItemStack(ItemsTFC.RUTABAGA), 7, false).setRegistryName("rutabaga"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.TURNIP)), new ItemStack(ItemsTFC.TURNIP), 8, false).setRegistryName("turnip"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.SUGAR_BEET)), new ItemStack(ItemsTFC.SUGAR_BEET), 7, false).setRegistryName("sugar_beet"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.PURPLE_GRAPE)), new ItemStack(ItemsTFC.PURPLE_GRAPE), 8, false).setRegistryName("purple_grape"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.GREEN_GRAPE)), new ItemStack(ItemsTFC.GREEN_GRAPE), 8, false).setRegistryName("green_grape"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.LIQUORICE_ROOT)), new ItemStack(ItemsTFC.LIQUORICE_ROOT), 8, false).setRegistryName("liquorice_root"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.COFFEA)), new ItemStack(ItemsTFC.COFFEA_CHERRIES), 8, false).setRegistryName("coffea_cherries"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.AGAVE)), new ItemStack(ItemsTFC.AGAVE), 6, false).setRegistryName("agave"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.COCA)), new ItemStack(ItemsTFC.COCA_LEAF), 6, true).setRegistryName("coca"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.COTTON)), new ItemStack(ItemsTFC.COTTON_BOLL), 6, false).setRegistryName("cotton"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.FLAX)), new ItemStack(ItemsTFC.FLAX), 6, true).setRegistryName("flax"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.HEMP)), new ItemStack(ItemsTFC.HEMP), 5, true).setRegistryName("hemp"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.HOP)), new ItemStack(ItemsTFC.HOPS), 6, true).setRegistryName("hop"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.INDIGO)), new ItemStack(ItemsTFC.INDIGO), 5, true).setRegistryName("indigo"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.MADDER)), new ItemStack(ItemsTFC.MADDER), 5, false).setRegistryName("madder"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.OPIUM_POPPY)), new ItemStack(ItemsTFC.OPIUM_POPPY_BULB), 6, false).setRegistryName("opium_poppy"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.RAPE)), new ItemStack(ItemsTFC.RAPE), 6, false).setRegistryName("rape"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.WELD)), new ItemStack(ItemsTFC.WELD), 5, true).setRegistryName("weld"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.WOAD)), new ItemStack(ItemsTFC.WOAD), 6, false).setRegistryName("woad"),
//                new PlanterRecipe(IIngredient.of(ItemSeedsTFC.get(CropTFCF.TOBACCO)), new ItemStack(ItemsTFC.TOBACCO_LEAF), 7, true).setRegistryName("tobacco")
//        );
//    }*/
//}
