package net.dries007.tfc.objects.items.ceramics;

import javax.annotation.Nonnull;

import net.dries007.tfc.client.TFCGuiHandler;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;

import net.dries007.tfc.util.OreDictionaryHelper;

public class ItemClayStoneware extends ItemTFC implements IItemSize
{
    private final Size size;
    private final Weight weight;

    public ItemClayStoneware(Size size, Weight weight, Object... oreNameParts)
    {
        this(size, weight);

        for (Object obj : oreNameParts)
        {
            if (obj instanceof Object[])
                OreDictionaryHelper.register(this, (Object[]) obj);
            else
                OreDictionaryHelper.register(this, obj);
        }
    }

    public ItemClayStoneware(Size size, Weight weight)
    {
        this.size = size;
        this.weight = weight;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack stack)
    {
        return size;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack stack)
    {
        return weight;
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote && !player.isSneaking() && stack.getCount() > 4)
        {
            TFCGuiHandler.openGui(world, player.getPosition(), player, TFCGuiHandler.Type.STONEWARE_CLAY);
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    public void onRightClick(PlayerInteractEvent.RightClickItem event)
    {
        EnumHand hand = event.getHand();
        if(OreDictionaryHelper.doesStackMatchOre(event.getItemStack(), "clayStoneware") && hand == EnumHand.MAIN_HAND)
        {
            EntityPlayer player = event.getEntityPlayer();
            World world = event.getWorld();
            ItemStack stack = player.getHeldItem(hand);
            if (!world.isRemote && !player.isSneaking() && stack.getCount() > 4)
            {
                TFCGuiHandler.openGui(world, player.getPosition(), player, TFCGuiHandler.Type.STONEWARE_CLAY);
            }
        }
    }
}
