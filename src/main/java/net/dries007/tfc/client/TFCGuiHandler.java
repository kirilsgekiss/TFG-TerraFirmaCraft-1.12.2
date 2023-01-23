/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.recipes.knapping.KnappingType;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.api.util.IRockObject;
import net.dries007.tfc.client.gui.*;
import net.dries007.tfc.objects.blocks.wood.TFCBlockChest;
import net.dries007.tfc.objects.container.*;
import net.dries007.tfc.objects.items.ItemQuiver;
import net.dries007.tfc.objects.items.ceramics.fired.molds.ItemClayMold;
import net.dries007.tfc.objects.items.ceramics.fired.ItemSmallVessel;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.objects.te.*;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.OreDictionaryHelper;
import net.dries007.tfc.objects.items.ItemBag;
import net.dries007.tfc.objects.items.ItemSack;
import net.dries007.tfc.objects.items.rock.ItemMud;
import net.dries007.tfc.objects.te.TECondenser;
import net.dries007.tfc.objects.te.TECrate;
import net.dries007.tfc.objects.te.TEUrn;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class TFCGuiHandler implements IGuiHandler
{
    public static final ResourceLocation SMALL_INVENTORY_BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/small_inventory.png");
    public static final ResourceLocation CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button.png");
    public static final ResourceLocation FIRE_CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_fire.png");
    public static final ResourceLocation LEATHER_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/leather_button.png");
    public static final ResourceLocation QUIVER_BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/quiver_inventory.png");
    public static final ResourceLocation CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_disabled.png");
    public static final ResourceLocation FIRE_CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/clay_button_fire_disabled.png");
    public static final ResourceLocation SACK_INVENTORY_BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/sack_inventory.png");
    public static final ResourceLocation BAG_INVENTORY_BACKGROUND = new ResourceLocation(MOD_ID, "textures/gui/bag_inventory.png");
    public static final ResourceLocation PINEAPPLE_LEATHER_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/pineapple_leather_button.png");
    public static final ResourceLocation BURLAP_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/burlap.png");
    public static final ResourceLocation WOOL_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/wool.png");
    public static final ResourceLocation SILK_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/silk.png");
    public static final ResourceLocation SISAL_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/sisal.png");
    public static final ResourceLocation COTTON_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/cotton.png");
    public static final ResourceLocation LINEN_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/linen.png");
    public static final ResourceLocation HEMP_CLOTH_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/hemp.png");
    public static final ResourceLocation YUCCA_CANVAS_TEXTURE = new ResourceLocation(MOD_ID, "textures/blocks/devices/loom/product/yucca.png");
    public static final ResourceLocation MUD_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/mud_button.png");
    public static final ResourceLocation MUD_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/mud_button_disabled.png");
    public static final ResourceLocation EARTHENWARE_CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/earthenware_clay_button.png");
    public static final ResourceLocation EARTHENWARE_CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/earthenware_clay_button_disabled.png");
    public static final ResourceLocation KAOLINITE_CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/kaolinite_clay_button.png");
    public static final ResourceLocation KAOLINITE_CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/kaolinite_clay_button_disabled.png");
    public static final ResourceLocation STONEWARE_CLAY_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/stoneware_clay_button.png");
    public static final ResourceLocation STONEWARE_CLAY_DISABLED_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/stoneware_clay_button_disabled.png");
    public static final ResourceLocation FLINT_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/knapping/flint_button.png");

    // use this instead of player.openGui() -> avoids magic numbers
    public static void openGui(World world, BlockPos pos, EntityPlayer player, Type type)
    {
        player.openGui(TerraFirmaCraft.getInstance(), type.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    }

    // Only use this for things that don't need a BlockPos to identify TE's!!!
    public static void openGui(World world, EntityPlayer player, Type type)
    {
        player.openGui(TerraFirmaCraft.getInstance(), type.ordinal(), world, 0, 0, 0);
    }

    @Override
    @Nullable
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        ItemStack stack = player.getHeldItemMainhand();
        Type type = Type.valueOf(ID);
        switch (type)
        {
            case NEST_BOX:
                TENestBox teNestBox = Helpers.getTE(world, pos, TENestBox.class);
                return teNestBox == null ? null : new ContainerNestBox(player.inventory, teNestBox);
            case LOG_PILE:
                TELogPile teLogPile = Helpers.getTE(world, pos, TELogPile.class);
                return teLogPile == null ? null : new ContainerLogPile(player.inventory, teLogPile);
            case SMALL_VESSEL:
                return new ContainerSmallVessel(player.inventory, stack.getItem() instanceof ItemSmallVessel ? stack : player.getHeldItemOffhand());
            case SMALL_VESSEL_LIQUID:
                return new ContainerLiquidTransfer(player.inventory, stack.getItem() instanceof ItemSmallVessel ? stack : player.getHeldItemOffhand());
            case MOLD:
                return new ContainerLiquidTransfer(player.inventory, stack.getItem() instanceof ItemClayMold ? stack : player.getHeldItemOffhand());
            case FIRE_PIT:
                //noinspection ConstantConditions
                return new ContainerFirePit(player.inventory, Helpers.getTE(world, pos, TEFirePit.class));
            case BARREL:
                return new ContainerBarrel(player.inventory, Helpers.getTE(world, pos, TEBarrel.class));
            case CHARCOAL_FORGE:
                //noinspection ConstantConditions
                return new ContainerCharcoalForge(player.inventory, Helpers.getTE(world, pos, TECharcoalForge.class));
            case ANVIL:
                //noinspection ConstantConditions
                return new ContainerAnvil(player.inventory, Helpers.getTE(world, pos, TEAnvilTFC.class));
            case ANVIL_PLAN:
                return new ContainerAnvilPlan(player.inventory, Helpers.getTE(world, pos, TEAnvilTFC.class));
            case KNAPPING_STONE:
                return new ContainerKnapping(KnappingType.STONE, player.inventory, stack.getItem() instanceof ItemRock ? stack : player.getHeldItemOffhand());
            case KNAPPING_CLAY:
                return new ContainerKnapping(KnappingType.CLAY, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clay") ? stack : player.getHeldItemOffhand());
            case KNAPPING_LEATHER:
                return new ContainerKnapping(KnappingType.LEATHER, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "leather") ? stack : player.getHeldItemOffhand());
            case KNAPPING_FIRE_CLAY:
                return new ContainerKnapping(KnappingType.FIRE_CLAY, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "fireClay") ? stack : player.getHeldItemOffhand());
            case CRUCIBLE:
                return new ContainerCrucible(player.inventory, Helpers.getTE(world, pos, TECrucible.class));
            case LARGE_VESSEL:
                return new ContainerLargeVessel(player.inventory, Helpers.getTE(world, pos, TELargeVessel.class));
            case POWDERKEG:
                return new ContainerPowderKeg(player.inventory, Helpers.getTE(world, pos, TEPowderKeg.class));
            case CALENDAR:
            case SKILLS:
            case NUTRITION:
                return new ContainerSimple(player.inventory);
            case BLAST_FURNACE:
                return new ContainerBlastFurnace(player.inventory, Helpers.getTE(world, pos, TEBlastFurnace.class));
            case CRAFTING:
                return new ContainerInventoryCrafting(player.inventory, player.world);
            case QUIVER:
                return new ContainerQuiver(player.inventory, stack.getItem() instanceof ItemQuiver ? stack : player.getHeldItemOffhand());
            case CHEST:
                if (world.getBlockState(pos).getBlock() instanceof TFCBlockChest)
                {
                    ILockableContainer chestContainer = ((TFCBlockChest) world.getBlockState(pos).getBlock()).getLockableContainer(world, pos);
                    if (chestContainer == null) // This is null if the chest is blocked
                    {
                        return null;
                    }
                    return new ContainerChest(player.inventory, chestContainer, player);
                }
                return null;
            case SALAD:
                return new ContainerSalad(player.inventory);
            case SACK:
                return new ContainerSack(player.inventory, stack.getItem() instanceof ItemSack ? stack : player.getHeldItemOffhand());
            case BAG:
                return new ContainerBag(player.inventory, stack.getItem() instanceof ItemBag ? stack : player.getHeldItemOffhand());
            case PINEAPPLE_LEATHER:
                return new ContainerKnapping(KnappingType.PINEAPPLE_LEATHER, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "leatherPineapple") ? stack : player.getHeldItemOffhand());
            case BURLAP_CLOTH:
                return new ContainerKnapping(KnappingType.BURLAP_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothBurlap") ? stack : player.getHeldItemOffhand());
            case WOOL_CLOTH:
                return new ContainerKnapping(KnappingType.WOOL_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothWool") ? stack : player.getHeldItemOffhand());
            case SILK_CLOTH:
                return new ContainerKnapping(KnappingType.SILK_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothSilk") ? stack : player.getHeldItemOffhand());
            case SISAL_CLOTH:
                return new ContainerKnapping(KnappingType.SISAL_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothSisal") ? stack : player.getHeldItemOffhand());
            case COTTON_CLOTH:
                return new ContainerKnapping(KnappingType.COTTON_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothCotton") ? stack : player.getHeldItemOffhand());
            case LINEN_CLOTH:
                return new ContainerKnapping(KnappingType.LINEN_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothLinen") ? stack : player.getHeldItemOffhand());
            case HEMP_CLOTH:
                return new ContainerKnapping(KnappingType.HEMP_CLOTH, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clothHemp") ? stack : player.getHeldItemOffhand());
            case YUCCA_CANVAS:
                return new ContainerKnapping(KnappingType.YUCCA_CANVAS, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "canvasYucca") ? stack : player.getHeldItemOffhand());
            case MUD:
                return new ContainerKnapping(KnappingType.MUD, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "mud") ? stack : player.getHeldItemOffhand());
            case EARTHENWARE_CLAY:
                return new ContainerKnapping(KnappingType.EARTHENWARE_CLAY, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clayEarthenware") ? stack : player.getHeldItemOffhand());
            case KAOLINITE_CLAY:
                return new ContainerKnapping(KnappingType.KAOLINITE_CLAY, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clayKaolinite") ? stack : player.getHeldItemOffhand());
            case STONEWARE_CLAY:
                return new ContainerKnapping(KnappingType.STONEWARE_CLAY, player.inventory, OreDictionaryHelper.doesStackMatchOre(stack, "clayStoneware") ? stack : player.getHeldItemOffhand());
            case FLINT:
                return new ContainerKnapping(KnappingType.FLINT, player.inventory,  OreDictionaryHelper.doesStackMatchOre(stack, "flint") ? stack : player.getHeldItemOffhand());
            case URN:
                return new ContainerUrn(player.inventory, Helpers.getTE(world, pos, TEUrn.class));
            case CRATE:
                return new ContainerCrate(player.inventory, Helpers.getTE(world, pos, TECrate.class));
            case CONDENSER:
                return new ContainerCondenser(player.inventory, Helpers.getTE(world, pos, TECondenser.class));
            default:
                return null;
        }
    }

    @Override
    @Nullable
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        Container container = getServerGuiElement(ID, player, world, x, y, z);
        Type type = Type.valueOf(ID);
        BlockPos pos = new BlockPos(x, y, z);
        switch (type)
        {
            case NEST_BOX:
            case SMALL_VESSEL:
            case LOG_PILE:
                return new GuiContainerTFC(container, player.inventory, SMALL_INVENTORY_BACKGROUND);
            case SMALL_VESSEL_LIQUID:
                return new GuiLiquidTransfer(container, player, player.getHeldItemMainhand().getItem() instanceof ItemSmallVessel);
            case MOLD:
                return new GuiLiquidTransfer(container, player, player.getHeldItemMainhand().getItem() instanceof ItemClayMold);
            case FIRE_PIT:
                return new GuiFirePit(container, player.inventory, Helpers.getTE(world, pos, TEFirePit.class));
            case BARREL:
                return new GuiBarrel(container, player.inventory, Helpers.getTE(world, pos, TEBarrel.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            case CHARCOAL_FORGE:
                return new GuiCharcoalForge(container, player.inventory, Helpers.getTE(world, pos, TECharcoalForge.class));
            case ANVIL:
                return new GuiAnvilTFC(container, player.inventory, Helpers.getTE(world, pos, TEAnvilTFC.class));
            case ANVIL_PLAN:
                return new GuiAnvilPlan(container, player.inventory, Helpers.getTE(world, pos, TEAnvilTFC.class));
            case KNAPPING_STONE:
                ItemStack stack = player.getHeldItemMainhand();
                Rock rock = stack.getItem() instanceof IRockObject ? ((IRockObject) stack.getItem()).getRock(stack) :
                    ((IRockObject) player.getHeldItemOffhand().getItem()).getRock(player.getHeldItemOffhand());
                //noinspection ConstantConditions
                return new GuiKnapping(container, player, KnappingType.STONE, rock.getTexture());
            case KNAPPING_CLAY:
                return new GuiKnapping(container, player, KnappingType.CLAY, CLAY_TEXTURE);
            case KNAPPING_LEATHER:
                return new GuiKnapping(container, player, KnappingType.LEATHER, LEATHER_TEXTURE);
            case KNAPPING_FIRE_CLAY:
                return new GuiKnapping(container, player, KnappingType.FIRE_CLAY, FIRE_CLAY_TEXTURE);
            case CRUCIBLE:
                return new GuiCrucible(container, player.inventory, Helpers.getTE(world, pos, TECrucible.class));
            case LARGE_VESSEL:
                return new GuiLargeVessel(container, player.inventory, Helpers.getTE(world, pos, TELargeVessel.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            case POWDERKEG:
                return new GuiPowderkeg(container, player.inventory, Helpers.getTE(world, pos, TEPowderKeg.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            case CALENDAR:
                return new GuiCalendar(container, player.inventory);
            case NUTRITION:
                return new GuiNutrition(container, player.inventory);
            case SKILLS:
                return new GuiSkills(container, player.inventory);
            case BLAST_FURNACE:
                return new GuiBlastFurnace(container, player.inventory, Helpers.getTE(world, pos, TEBlastFurnace.class));
            case CRAFTING:
                return new GuiInventoryCrafting(container);
            case QUIVER:
                return new GuiContainerTFC(container, player.inventory, QUIVER_BACKGROUND);
            case CHEST:
                if (container instanceof ContainerChest)
                {
                    return new GuiChestTFC((ContainerChest) container, player.inventory);
                }
                return null;
            case SALAD:
                return new GuiSalad(container, player.inventory);
            case SACK:
                return new GuiSack(container, player.inventory, SACK_INVENTORY_BACKGROUND);
            case BAG:
                return new GuiBag(container, player.inventory, BAG_INVENTORY_BACKGROUND);
            case PINEAPPLE_LEATHER:
                return new GuiKnapping(container, player, KnappingType.PINEAPPLE_LEATHER, PINEAPPLE_LEATHER_TEXTURE);
            case BURLAP_CLOTH:
                return new GuiKnapping(container, player, KnappingType.BURLAP_CLOTH, BURLAP_CLOTH_TEXTURE);
            case WOOL_CLOTH:
                return new GuiKnapping(container, player, KnappingType.WOOL_CLOTH, WOOL_CLOTH_TEXTURE);
            case SILK_CLOTH:
                return new GuiKnapping(container, player, KnappingType.SILK_CLOTH, SILK_CLOTH_TEXTURE);
            case SISAL_CLOTH:
                return new GuiKnapping(container, player, KnappingType.SISAL_CLOTH, SISAL_CLOTH_TEXTURE);
            case COTTON_CLOTH:
                return new GuiKnapping(container, player, KnappingType.COTTON_CLOTH, COTTON_CLOTH_TEXTURE);
            case LINEN_CLOTH:
                return new GuiKnapping(container, player, KnappingType.LINEN_CLOTH, LINEN_CLOTH_TEXTURE);
            case HEMP_CLOTH:
                return new GuiKnapping(container, player, KnappingType.HEMP_CLOTH, HEMP_CLOTH_TEXTURE);
            case YUCCA_CANVAS:
                return new GuiKnapping(container, player, KnappingType.YUCCA_CANVAS, YUCCA_CANVAS_TEXTURE);
            case MUD:
                ItemStack stackMud = player.getHeldItemMainhand();
                stackMud = OreDictionaryHelper.doesStackMatchOre(stackMud, "mud") ? stackMud : player.getHeldItemOffhand();
                ItemMud mud = (ItemMud)(stackMud.getItem());
                return new GuiKnapping(container, player, KnappingType.MUD, mud.getForegroundTexture(), mud.getBackgroundTexture());
            case EARTHENWARE_CLAY:
                return new GuiKnapping(container, player, KnappingType.EARTHENWARE_CLAY, EARTHENWARE_CLAY_TEXTURE);
            case KAOLINITE_CLAY:
                return new GuiKnapping(container, player, KnappingType.KAOLINITE_CLAY, KAOLINITE_CLAY_TEXTURE);
            case STONEWARE_CLAY:
                return new GuiKnapping(container, player, KnappingType.STONEWARE_CLAY, STONEWARE_CLAY_TEXTURE);
            case FLINT:
                return new GuiKnapping(container, player, KnappingType.FLINT, FLINT_TEXTURE);
            case URN:
                return new GuiUrn(container, player.inventory, Helpers.getTE(world, pos, TEUrn.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            case CRATE:
                return new GuiCrate(container, player.inventory, Helpers.getTE(world, pos, TECrate.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            case CONDENSER:
                return new GuiCondenser(container, player.inventory, Helpers.getTE(world, pos, TECondenser.class), world.getBlockState(new BlockPos(x, y, z)).getBlock().getTranslationKey());
            default:
                return null;
        }
    }

    public enum Type
    {
        NEST_BOX,
        LOG_PILE,
        SMALL_VESSEL,
        SMALL_VESSEL_LIQUID,
        MOLD,
        FIRE_PIT,
        BARREL,
        KNAPPING_STONE,
        KNAPPING_CLAY,
        KNAPPING_FIRE_CLAY,
        KNAPPING_LEATHER,
        CHARCOAL_FORGE,
        ANVIL,
        ANVIL_PLAN,
        CRUCIBLE,
        BLAST_FURNACE,
        LARGE_VESSEL,
        POWDERKEG,
        CALENDAR,
        NUTRITION,
        SKILLS,
        CHEST,
        SALAD,
        INVENTORY, // This is special, it is used by GuiButtonPlayerInventoryTab to signal to open the vanilla inventory
        CRAFTING, // In-inventory 3x3 crafting grid
        QUIVER,
        SACK,
        BAG,
        PINEAPPLE_LEATHER,
        BURLAP_CLOTH,
        WOOL_CLOTH,
        SILK_CLOTH,
        SISAL_CLOTH,
        COTTON_CLOTH,
        LINEN_CLOTH,
        HEMP_CLOTH,
        YUCCA_CANVAS,
        MUD,
        EARTHENWARE_CLAY,
        KAOLINITE_CLAY,
        STONEWARE_CLAY,
        FLINT,
        URN,
        CRATE,
        CONDENSER,
        NULL; // This is special, it is a non-null null.

        private static final Type[] values = values();

        @Nonnull
        public static Type valueOf(int id)
        {
            return id < 0 || id >= values.length ? NULL : values[id];
        }
    }
}
