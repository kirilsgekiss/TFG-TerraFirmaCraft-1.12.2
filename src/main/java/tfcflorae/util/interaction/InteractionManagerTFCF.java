package tfcflorae.util.interaction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.dries007.tfc.objects.items.ItemSeedsTFC;
import net.dries007.tfc.util.Helpers;
import net.dries007.tfc.util.interaction.*;

import tfcflorae.client.GuiHandler;
import tfcflorae.objects.blocks.BlocksTFCF;
import tfcflorae.objects.blocks.devices.BlockStickBundle;
import tfcflorae.util.OreDictionaryHelper;

import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

@Mod.EventBusSubscriber(modid = TFCFLORAE_MODID)
public final class InteractionManagerTFCF
{
    private static final Map<Predicate<ItemStack>, IRightClickBlockAction> USE_ACTIONS = new HashMap<>();
    private static final Map<Predicate<ItemStack>, IRightClickItemAction> RIGHT_CLICK_ACTIONS = new HashMap<>();
    private static final ThreadLocal<Boolean> PROCESSING_INTERACTION = ThreadLocal.withInitial(() -> false); // avoids stack overflows where some mods post interaction events during onBlockActivated (see #1321)

    //static
    {
        // Pineapple Leather knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "leatherPineapple"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "knife"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.PINEAPPLE_LEATHER);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Burlap Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothBurlap"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.BURLAP_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Wool Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothWool"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.WOOL_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Silk Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothSilk"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.SILK_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Sisal Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothSisal"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.SISAL_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Cotton Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothCotton"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.COTTON_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Linen Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothLinen"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.LINEN_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Hemp Cloth knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "clothHemp"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.HEMP_CLOTH);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        // Yucca Canvas knapping
        putBoth(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "canvasYucca"), ((worldIn, playerIn, handIn) -> {
            if (Helpers.playerHasItemMatchingOre(playerIn.inventory, "shears"))
            {
                if (!worldIn.isRemote)
                {
                    GuiHandler.openGui(worldIn, playerIn, GuiHandler.Type.YUCCA_CANVAS);
                }
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }));

        USE_ACTIONS.put(stack -> OreDictionaryHelper.doesStackMatchOre(stack, "stickBundle"), (stack, player, worldIn, pos, hand, direction, hitX, hitY, hitZ) -> {
            if (direction == EnumFacing.DOWN)
            {
                IBlockState state = worldIn.getBlockState(pos);
                Block block = state.getBlock();
                if (!block.isReplaceable(worldIn, pos)) pos = pos.down();
                BlockPos posDown = pos.down();
                //ItemStack itemStack = player.getHeldItem(hand);

                if (player.canPlayerEdit(pos, direction, stack) && player.canPlayerEdit(posDown, direction, stack))
                {
                    IBlockState stateDown = worldIn.getBlockState(posDown);
                    boolean canPutBlock = block.isReplaceable(worldIn, pos) || worldIn.isAirBlock(pos);
                    boolean canPutBlockDown = stateDown.getBlock().isReplaceable(worldIn, posDown) || worldIn.isAirBlock(posDown);
                    if (canPutBlock && canPutBlockDown)
                    {
                        if (!worldIn.isRemote)
                        {
                            worldIn.setBlockState(pos, BlocksTFCF.STICK_BUNDLE.getDefaultState().withProperty(BlockStickBundle.PART, BlockStickBundle.EnumBlockPart.UPPER), 10);
                            worldIn.setBlockState(posDown, BlocksTFCF.STICK_BUNDLE.getDefaultState(), 10);
                            SoundType soundtype = BlocksTFCF.STICK_BUNDLE.getSoundType(stateDown, worldIn, pos, player);
                            worldIn.playSound(null, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                            //itemStack.shrink(1);
                            stack.shrink(1);
                            player.setHeldItem(hand, stack);
                        }
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
            return EnumActionResult.FAIL;
        });
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        if (!PROCESSING_INTERACTION.get())
        {
            PROCESSING_INTERACTION.set(true);
            IRightClickBlockAction action = InteractionManagerTFCF.findItemUseAction(event.getItemStack());
            if (event.getItemStack().getItem() instanceof ItemSeedsTFC)
            {
                // Use alternative handling
                EnumActionResult result;
                if (event.getSide() == Side.CLIENT)
                {
                    result = ClientInteractionManagerTFCF.processRightClickBlock(event);
                }
                else
                {
                    result = ServerInteractionManagerTFCF.processRightClickBlock(event);
                }
                event.setCancellationResult(result);
                event.setCanceled(true);
            }
            PROCESSING_INTERACTION.set(false);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event)
    {
        IRightClickItemAction action = InteractionManagerTFCF.findItemRightClickAction(event.getItemStack());
        if (action != null)
        {
            // Use alternative handling
            EnumActionResult result;
            if (event.getSide() == Side.CLIENT)
            {
                result = ClientInteractionManagerTFCF.processRightClickItem(event, action);
            }
            else
            {
                result = ClientInteractionManagerTFCF.processRightClickItem(event, action);
            }
            event.setCancellationResult(result);
            event.setCanceled(true);
        }
    }

    @Nullable
    private static IRightClickBlockAction findItemUseAction(ItemStack stack)
    {
        return USE_ACTIONS.entrySet().stream().filter(e -> e.getKey().test(stack)).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    @Nullable
    private static IRightClickItemAction findItemRightClickAction(ItemStack stack)
    {
        return RIGHT_CLICK_ACTIONS.entrySet().stream().filter(e -> e.getKey().test(stack)).findFirst().map(Map.Entry::getValue).orElse(null);
    }

    private static void putBoth(Predicate<ItemStack> predicate, IRightClickItemAction minorAction)
    {
        USE_ACTIONS.put(predicate, minorAction);
        RIGHT_CLICK_ACTIONS.put(predicate, minorAction);
    }
}
