/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import net.dries007.tfc.api.capability.damage.DamageType;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Rock.Type;
import net.dries007.tfc.objects.blocks.TFCBlocks;
import net.dries007.tfc.objects.blocks.plants.TFCBlockPlant;
import net.dries007.tfc.objects.blocks.rock.TFCBlockRockDecorative;
import net.dries007.tfc.types.DefaultPlants;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

/**
 * This is not the best example of good coding practice, but I do think it works rather well.
 * The reason for the delayed registration it because now the helper's functions can be called in the constructor of
 * the blocks/items (BEFORE they are actually in registries). At this point you cannot yet make an itemstack.
 * Storing based on RegistryName is also not possible, as they don't have one yet.
 */
public class OreDictionaryHelper {
    private static final Multimap<Thing, String> MAP = HashMultimap.create();
    private static final Converter<String, String> UPPER_UNDERSCORE_TO_LOWER_CAMEL = CaseFormat.UPPER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);
    private static final Joiner JOINER_UNDERSCORE = Joiner.on('_').skipNulls();
    private static boolean done = false;

    public static String toString(Object... parts) {
        return UPPER_UNDERSCORE_TO_LOWER_CAMEL.convert(JOINER_UNDERSCORE.join(parts));
    }

    public static String toString(Iterable<Object> parts) {
        return UPPER_UNDERSCORE_TO_LOWER_CAMEL.convert(JOINER_UNDERSCORE.join(parts));
    }

    public static String toString(Object[] prefix, Object... parts) {
        return toString(ImmutableList.builder().add(prefix).add(parts).build());
    }

    public static void register(Block thing, Object... parts) {
        register(new Thing(thing), parts);
    }

    public static void register(Item thing, Object... parts) {
        register(new Thing(thing), parts);
    }

    public static void registerMeta(Item thing, int meta, Object... parts) {
        register(new Thing(thing, meta), parts);
    }

    public static void registerRockType(Block thing, Type type, Object... prefixParts) {
        registerRockType(new Thing(thing), type, prefixParts);
    }

    public static void registerDamageType(Item thing, DamageType type) {
        register(thing, "damage", "type", type.name().toLowerCase());
    }

    public static void init() {
        done = true;
        MAP.forEach((t, s) -> OreDictionary.registerOre(s, t.toItemStack()));
        MAP.clear(); // No need to keep this stuff around

        //OreDictionary.registerOre("stickBunch", new ItemStack(ItemsTFC.STICK_BUNCH));
        //OreDictionary.registerOre("stickBundle", new ItemStack(ItemsTFC.STICK_BUNCH));
        OreDictionary.registerOre("thatch", new ItemStack(Blocks.HAY_BLOCK));
        OreDictionary.registerOre("bale", new ItemStack(Blocks.HAY_BLOCK));
        OreDictionary.registerOre("baleHay", new ItemStack(Blocks.HAY_BLOCK));
        OreDictionary.registerOre("baleCotton", new ItemStack(TFCBlocks.COTTON_BALE));
        OreDictionary.registerOre("baleCottonYarn", new ItemStack(TFCBlocks.COTTON_YARN_BALE));
        OreDictionary.registerOre("baleHemp", new ItemStack(TFCBlocks.HEMP_BALE));
        OreDictionary.registerOre("baleHempFiber", new ItemStack(TFCBlocks.HEMP_FIBER_BALE));
        OreDictionary.registerOre("baleJute", new ItemStack(TFCBlocks.JUTE_BALE));
        OreDictionary.registerOre("baleJuteFiber", new ItemStack(TFCBlocks.JUTE_FIBER_BALE));
        OreDictionary.registerOre("baleLinen", new ItemStack(TFCBlocks.LINEN_BALE));
        OreDictionary.registerOre("baleLinenFiber", new ItemStack(TFCBlocks.LINEN_FIBER_BALE));
        OreDictionary.registerOre("balePapyrusFiber", new ItemStack(TFCBlocks.PAPYRUS_FIBER_BALE));
        OreDictionary.registerOre("baleSilkString", new ItemStack(TFCBlocks.SILK_STRING_BALE));
        OreDictionary.registerOre("baleSisalFiber", new ItemStack(TFCBlocks.SISAL_FIBER_BALE));
        OreDictionary.registerOre("baleYucca", new ItemStack(TFCBlocks.YUCCA_BALE));
        OreDictionary.registerOre("baleYuccaFiber", new ItemStack(TFCBlocks.YUCCA_FIBER_BALE));
        OreDictionary.registerOre("glue", new ItemStack(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RESIN))));
        OreDictionary.registerOre("slimeball", new ItemStack(TFCBlockPlant.get(TFCRegistries.PLANTS.getValue(DefaultPlants.RESIN))));

        // Flint
        OreDictionary.registerOre("flint", new ItemStack(Items.FLINT));
        OreDictionary.registerOre("itemFlint", new ItemStack(Items.FLINT));

        // Corals
//        TFCBlockCoral.TUBE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralTube", new ItemStack(blockCoral)));
//        TFCBlockCoral.BRAIN_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralBrain", new ItemStack(blockCoral)));
//        TFCBlockCoral.BUBBLE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralBubble", new ItemStack(blockCoral)));
//        TFCBlockCoral.FIRE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFire", new ItemStack(blockCoral)));
//        TFCBlockCoral.HORN_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralHorn", new ItemStack(blockCoral)));
//
//        TFCBlockCoral.TUBE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.BRAIN_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.BUBBLE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.FIRE_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.HORN_CORAL.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//
//        OreDictionary.registerOre("coralTubeDead", new ItemStack(TFCBlocks.TUBE_CORAL_DEAD));
//        OreDictionary.registerOre("coralBrainDead", new ItemStack(TFCBlocks.BRAIN_CORAL_DEAD));
//        OreDictionary.registerOre("coralBubbleDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_DEAD));
//        OreDictionary.registerOre("coralFireDead", new ItemStack(TFCBlocks.FIRE_CORAL_DEAD));
//        OreDictionary.registerOre("coralHornDead", new ItemStack(TFCBlocks.HORN_CORAL_DEAD));
//
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.TUBE_CORAL_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BRAIN_CORAL_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.FIRE_CORAL_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.HORN_CORAL_DEAD));
//
//        TFCBlockCoral.TUBE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFanTube", new ItemStack(blockCoral)));
//        TFCBlockCoral.BRAIN_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFanBrain", new ItemStack(blockCoral)));
//        TFCBlockCoral.BUBBLE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFanBubble", new ItemStack(blockCoral)));
//        TFCBlockCoral.FIRE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFanFire", new ItemStack(blockCoral)));
//        TFCBlockCoral.HORN_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coralFanHorn", new ItemStack(blockCoral)));
//
//        TFCBlockCoral.TUBE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.BRAIN_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.BUBBLE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.FIRE_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//        TFCBlockCoral.HORN_CORAL_FAN.forEach((dyeColor, blockCoral) -> OreDictionary.registerOre("coral", new ItemStack(blockCoral)));
//
//        OreDictionary.registerOre("coralFanTubeDead", new ItemStack(TFCBlocks.TUBE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralFanBrainDead", new ItemStack(TFCBlocks.BRAIN_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralFanBubbleDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralFanFireDead", new ItemStack(TFCBlocks.FIRE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralFanHornDead", new ItemStack(TFCBlocks.HORN_CORAL_FAN_DEAD));
//
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.TUBE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BRAIN_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.FIRE_CORAL_FAN_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.HORN_CORAL_FAN_DEAD));
//
//        TFCBlockCoralBlock.TUBE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralFanTube", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BRAIN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralFanBrain", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BUBBLE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralFanBubble", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.FIRE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralFanFire", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.HORN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralFanHorn", new ItemStack(blockCoralBlock)));
//
//        TFCBlockCoralBlock.TUBE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralBlock", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BRAIN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralBlock", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BUBBLE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralBlock", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.FIRE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralBlock", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.HORN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coralBlock", new ItemStack(blockCoralBlock)));
//
//        TFCBlockCoralBlock.TUBE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coral", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BRAIN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coral", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.BUBBLE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coral", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.FIRE_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coral", new ItemStack(blockCoralBlock)));
//        TFCBlockCoralBlock.HORN_CORAL_BLOCK.forEach((dyeColor, blockCoralBlock) -> OreDictionary.registerOre("coral", new ItemStack(blockCoralBlock)));
//
//        OreDictionary.registerOre("coralBlockTubeDead", new ItemStack(TFCBlocks.TUBE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockBrainDead", new ItemStack(TFCBlocks.BRAIN_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockBubbleDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockFireDead", new ItemStack(TFCBlocks.FIRE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockHornDead", new ItemStack(TFCBlocks.HORN_CORAL_BLOCK_DEAD));
//
//        OreDictionary.registerOre("coralBlockDead", new ItemStack(TFCBlocks.TUBE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockDead", new ItemStack(TFCBlocks.BRAIN_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockDead", new ItemStack(TFCBlocks.FIRE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralBlockDead", new ItemStack(TFCBlocks.HORN_CORAL_BLOCK_DEAD));
//
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.TUBE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BRAIN_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.BUBBLE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.FIRE_CORAL_BLOCK_DEAD));
//        OreDictionary.registerOre("coralDead", new ItemStack(TFCBlocks.HORN_CORAL_BLOCK_DEAD));

        // Vanilla ore dict values
        OreDictionary.registerOre("clay", Items.CLAY_BALL);
        OreDictionary.registerOre("gemCoal", new ItemStack(Items.COAL, 1, 0));
        OreDictionary.registerOre("charcoal", new ItemStack(Items.COAL, 1, 1));
        OreDictionary.registerOre("fireStarter", new ItemStack(Items.FLINT_AND_STEEL, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("fireStarter", new ItemStack(Items.FIRE_CHARGE));
        OreDictionary.registerOre("bowl", Items.BOWL);
        OreDictionary.registerOre("blockClay", Blocks.CLAY);

        //adding oredict to dyeables for dye support. Instead of adding specific recipes color can be changed universally.
        OreDictionary.registerOre("bed", new ItemStack(Items.BED, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("carpet", new ItemStack(Blocks.CARPET, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("powderConcrete", new ItemStack(Blocks.CONCRETE_POWDER, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("terracotta", new ItemStack(Blocks.HARDENED_CLAY, 1, OreDictionary.WILDCARD_VALUE));
        OreDictionary.registerOre("terracotta", new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, OreDictionary.WILDCARD_VALUE));

        TFCBlockRockDecorative.ALABASTER_BRICKS.forEach((dyeColor, blockDecorativeStone) -> OreDictionary.registerOre("alabasterBricks", new ItemStack(blockDecorativeStone)));
        TFCBlockRockDecorative.ALABASTER_BRICKS.forEach((dyeColor, blockDecorativeStone) -> OreDictionary.registerOre("bricksAlabaster", new ItemStack(blockDecorativeStone)));
        TFCBlockRockDecorative.ALABASTER_POLISHED.forEach((dyeColor, blockDecorativeStone) -> OreDictionary.registerOre("alabasterPolished", new ItemStack(blockDecorativeStone)));
        TFCBlockRockDecorative.ALABASTER_RAW.forEach((dyeColor, blockDecorativeStone) -> OreDictionary.registerOre("alabasterRaw", new ItemStack(blockDecorativeStone)));

        OreDictionary.registerOre("alabasterBricks", new ItemStack(TFCBlocks.ALABASTER_BRICKS_PLAIN));
        OreDictionary.registerOre("bricksAlabaster", new ItemStack(TFCBlocks.ALABASTER_BRICKS_PLAIN));
        OreDictionary.registerOre("alabasterRaw", new ItemStack(TFCBlocks.ALABASTER_RAW_PLAIN));
        OreDictionary.registerOre("alabasterPolished", new ItemStack(TFCBlocks.ALABASTER_POLISHED_PLAIN));

        // Register a name without any items
        OreDictionary.getOres("infiniteFire", true);
    }

    /**
     * Checks if an ItemStack has an OreDictionary entry that matches 'name'.
     */
    public static boolean doesStackMatchOre(@Nonnull ItemStack stack, String name) {
        if (!OreDictionary.doesOreNameExist(name)) {
            // TerraFirmaCraft.getLog().warn("doesStackMatchOre called with non-existing name. stack: {} name: {}", stack, name);
            return false;
        }
        if (stack.isEmpty()) return false;
        int needle = OreDictionary.getOreID(name);
        for (int id : OreDictionary.getOreIDs(stack)) {
            if (id == needle) return true;
        }
        return false;
    }

    private static void register(Thing thing, Object... parts) {
//  !      if (done) throw new IllegalStateException("Cannot use the helper to register after postInit has past.");
        MAP.put(thing, toString(parts));
    }

    private static void registerRockType(Thing thing, Type type, Object... prefixParts) {
        switch (type) {
            case MOSSY_RAW:
                MAP.put(thing, toString(prefixParts, "stone"));
                //MAP.put(thing, toString(prefixParts, "stone", rock));
                MAP.put(thing, toString(prefixParts, "stone_mossy"));
                //MAP.put(thing, toString(prefixParts, "stone_mossy", rock));
                break;
            case MUD:
                MAP.put(thing, toString(prefixParts, "block", type));
                //MAP.put(thing, toString(prefixParts, "block", rockTFCF, rock));
                break;
            case MUD_BRICKS:
                MAP.put(thing, toString(prefixParts, type));
                //MAP.put(thing, toString(prefixParts, "mud_bricks", rock));
                break;
            case ROOTED_DIRT:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "rooted_dirt"));
                break;
            case COARSE_DIRT:
            case COARSE_LOAMY_SAND:
            case COARSE_SANDY_LOAM:
            case COARSE_LOAM:
            case COARSE_SILT_LOAM:
            case COARSE_SILT:
            case COARSE_HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "coarse_dirt"));
                //MAP.put(thing, toString(prefixParts, "coarse_dirt", rock));
                break;
            case COARSE_CLAY:
            case COARSE_SANDY_CLAY_LOAM:
            case COARSE_SANDY_CLAY:
            case COARSE_CLAY_LOAM:
            case COARSE_SILTY_CLAY:
            case COARSE_SILTY_CLAY_LOAM:
            case COARSE_CLAY_HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "coarse_dirt"));
                //MAP.put(thing, toString(prefixParts, "coarse_dirt", rock));
                MAP.put(thing, toString(prefixParts, "coarse_clay_dirt"));
                //MAP.put(thing, toString(prefixParts, "coarse_clay_dirt", rock));
                break;
            case COARSE_KAOLINITE_CLAY:
            case COARSE_SANDY_KAOLINITE_CLAY_LOAM:
            case COARSE_SANDY_KAOLINITE_CLAY:
            case COARSE_KAOLINITE_CLAY_LOAM:
            case COARSE_SILTY_KAOLINITE_CLAY:
            case COARSE_SILTY_KAOLINITE_CLAY_LOAM:
            case COARSE_KAOLINITE_CLAY_HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "coarse_dirt"));
                //MAP.put(thing, toString(prefixParts, "coarse_dirt", rock));
                MAP.put(thing, toString(prefixParts, "coarse_kaolinite_clay_dirt"));
                //MAP.put(thing, toString(prefixParts, "coarse_kaolinite_clay_dirt", rock));
                break;
            case LOAMY_SAND_GRASS:
            case SANDY_LOAM_GRASS:
            case LOAM_GRASS:
            case SILT_LOAM_GRASS:
            case SILT_GRASS:
            case HUMUS_GRASS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "grass"));
                //MAP.put(thing, toString(prefixParts, "grass", rock));
                break;
            case PODZOL:
            case LOAMY_SAND_PODZOL:
            case SANDY_LOAM_PODZOL:
            case LOAM_PODZOL:
            case SILT_LOAM_PODZOL:
            case SILT_PODZOL:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "podzol"));
                //MAP.put(thing, toString(prefixParts, "podzol", rock));
                break;
            case LOAMY_SAND_FARMLAND:
            case SANDY_LOAM_FARMLAND:
            case LOAM_FARMLAND:
            case SILT_LOAM_FARMLAND:
            case SILT_FARMLAND:
            case HUMUS_FARMLAND:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "farmland"));
                //MAP.put(thing, toString(prefixParts, "farmland", rock));
                break;
            case LOAMY_SAND:
            case SANDY_LOAM:
            case LOAM:
            case SILT_LOAM:
            case SILT:
            case HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "soil"));
                //MAP.put(thing, toString(prefixParts, "soil", rock));
                MAP.put(thing, toString(prefixParts, "dirt"));
                //MAP.put(thing, toString(prefixParts, "dirt", rock));
                break;
            case SANDY_CLAY_LOAM:
            case SANDY_CLAY:
            case CLAY_LOAM:
            case SILTY_CLAY:
            case SILTY_CLAY_LOAM:
            case CLAY_HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_clay"));
                //MAP.put(thing, toString(prefixParts, "block_clay", rock));
                break;
            case DRY_CLAY_GRASS:
            case DRY_SANDY_CLAY_LOAM_GRASS:
            case DRY_SANDY_CLAY_GRASS:
            case DRY_CLAY_LOAM_GRASS:
            case DRY_SILTY_CLAY_GRASS:
            case DRY_SILTY_CLAY_LOAM_GRASS:
            case DRY_CLAY_HUMUS_GRASS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_clay_dirt"));
                //MAP.put(thing, toString(prefixParts, "block_clay_dirt", rock));
                MAP.put(thing, toString(prefixParts, "dry_grass"));
                //MAP.put(thing, toString(prefixParts, "dry_grass", rock));
                break;
            case SANDY_CLAY_LOAM_GRASS:
            case SANDY_CLAY_GRASS:
            case CLAY_LOAM_GRASS:
            case SILTY_CLAY_GRASS:
            case SILTY_CLAY_LOAM_GRASS:
            case CLAY_HUMUS_GRASS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_clay_grass"));
                //MAP.put(thing, toString(prefixParts, "block_clay_grass", rock));
                MAP.put(thing, toString(prefixParts, "grass"));
                //MAP.put(thing, toString(prefixParts, "grass", rock));
            case CLAY_PODZOL:
            case SANDY_CLAY_LOAM_PODZOL:
            case SANDY_CLAY_PODZOL:
            case CLAY_LOAM_PODZOL:
            case SILTY_CLAY_PODZOL:
            case SILTY_CLAY_LOAM_PODZOL:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_clay_podzol"));
                //MAP.put(thing, toString(prefixParts, "block_clay_podzol", rock));
                MAP.put(thing, toString(prefixParts, "podzol"));
                //MAP.put(thing, toString(prefixParts, "podzol", rock));
                break;
            case KAOLINITE_CLAY:
            case SANDY_KAOLINITE_CLAY_LOAM:
            case SANDY_KAOLINITE_CLAY:
            case KAOLINITE_CLAY_LOAM:
            case SILTY_KAOLINITE_CLAY:
            case SILTY_KAOLINITE_CLAY_LOAM:
            case KAOLINITE_CLAY_HUMUS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_dirt"));
                //MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_dirt", rock));
                break;
            case DRY_KAOLINITE_CLAY_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SANDY_KAOLINITE_CLAY_GRASS:
            case DRY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_GRASS:
            case DRY_SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case DRY_KAOLINITE_CLAY_HUMUS_GRASS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_dry_kaolinite_clay_grass"));
                //MAP.put(thing, toString(prefixParts, "block_dry_kaolinite_clay_grass", rock));
                MAP.put(thing, toString(prefixParts, "dry_grass"));
                //MAP.put(thing, toString(prefixParts, "dry_grass", rock));
                break;
            case KAOLINITE_CLAY_GRASS:
            case SANDY_KAOLINITE_CLAY_LOAM_GRASS:
            case SANDY_KAOLINITE_CLAY_GRASS:
            case KAOLINITE_CLAY_LOAM_GRASS:
            case SILTY_KAOLINITE_CLAY_GRASS:
            case SILTY_KAOLINITE_CLAY_LOAM_GRASS:
            case KAOLINITE_CLAY_HUMUS_GRASS:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_grass"));
                //MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_grass", rock));
                MAP.put(thing, toString(prefixParts, "grass"));
                //MAP.put(thing, toString(prefixParts, "grass", rock));
                break;
            case KAOLINITE_CLAY_PODZOL:
            case SANDY_KAOLINITE_CLAY_LOAM_PODZOL:
            case SANDY_KAOLINITE_CLAY_PODZOL:
            case KAOLINITE_CLAY_LOAM_PODZOL:
            case SILTY_KAOLINITE_CLAY_PODZOL:
            case SILTY_KAOLINITE_CLAY_LOAM_PODZOL:
                MAP.put(thing, toString(prefixParts, type));
                MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_podzol"));
                //MAP.put(thing, toString(prefixParts, "block_kaolinite_clay_podzol", rock));
                MAP.put(thing, toString(prefixParts, "podzol"));
                //MAP.put(thing, toString(prefixParts, "podzol", rock));
                break;
            case RAW:
                MAP.put(thing, toString(prefixParts, "stone"));
                break;
            case SMOOTH:
                MAP.put(thing, toString(prefixParts, "stone", "polished"));
                break;
            case COBBLE:
                MAP.put(thing, toString(prefixParts, "cobblestone"));
                break;
            case BRICKS:
                MAP.put(thing, toString(prefixParts, "stone", "brick"));
                MAP.put(thing, toString(prefixParts, "brick", "stone"));
                break;
            case DRY_GRASS:
                MAP.put(thing, toString(prefixParts, type, "dry"));
                break;
            case CLAY:
                MAP.put(thing, toString(prefixParts, "block", type, "dirt"));
                break;
            case CLAY_GRASS:
                MAP.put(thing, toString(prefixParts, "block", type));
                break;
            case SAND:
            case GRAVEL:
            case DIRT:
            case GRASS:
            default:
                MAP.put(thing, toString(prefixParts, type));
        }
    }

    private static class Thing {
        private final Block block;
        private final Item item;
        private final int meta;

        private Thing(Block thing) {
            block = thing;
            item = null;
            meta = 0;
        }

        private Thing(Item thing) {
            this(thing, -1);
        }

        private Thing(Item thing, int meta) {
            block = null;
            item = thing;
            this.meta = meta;
        }

        private ItemStack toItemStack() {
            if (block != null) {
                return new ItemStack(block, 1, meta);
            } else if (item != null) {
                int meta = this.meta;
                if (meta == -1 && item.isDamageable()) {
                    meta = OreDictionary.WILDCARD_VALUE;
                }
                return new ItemStack(item, 1, meta);
            }
            return ItemStack.EMPTY;
        }
    }
}
