package net.dries007.tfc.network;

import io.netty.buffer.ByteBuf;
import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.events.ProspectEvent;
import net.dries007.tfc.compat.gregtech.items.tools.behaviors.PropickBehavior;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketProspectResult implements IMessage {
    private BlockPos pos;
    private PropickBehavior.ProspectResult.Type type;
    private ItemStack vein;

    @SuppressWarnings("unused")
    @Deprecated
    public PacketProspectResult() {
    }

    public PacketProspectResult(BlockPos pos, PropickBehavior.ProspectResult.Type type, ItemStack vein) {
        this.pos = pos;
        this.type = type;
        this.vein = vein;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        pos = BlockPos.fromLong(buf.readLong());
        type = PropickBehavior.ProspectResult.Type.valueOf(buf.readByte());

        if (type != PropickBehavior.ProspectResult.Type.NOTHING) {
            vein = ByteBufUtils.readItemStack(buf);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(pos.toLong());
        buf.writeByte(type.ordinal());

        if (type != PropickBehavior.ProspectResult.Type.NOTHING) {
            ByteBufUtils.writeItemStack(buf, vein);
        }
    }

    public static final class Handler implements IMessageHandler<PacketProspectResult, IMessage> {
        @Override
        public IMessage onMessage(PacketProspectResult message, MessageContext ctx) {
            TerraFirmaCraft.getProxy().getThreadListener(ctx).addScheduledTask(() -> {
                EntityPlayer player = TerraFirmaCraft.getProxy().getPlayer(ctx);
                if (player != null) {
                    ITextComponent text = new TextComponentTranslation(message.type.translation);
                    if (message.type != PropickBehavior.ProspectResult.Type.NOTHING) {
                        text.appendText(" ").appendSibling(new TextComponentTranslation(message.vein.getTranslationKey() + ".name"));
                    }
                    player.sendStatusMessage(text, TFCConfig.Client.TOOLTIP.propickOutputToActionBar);
                }

                ProspectEvent event = new ProspectEvent.Client(player, message.pos, message.type, message.vein);
                MinecraftForge.EVENT_BUS.post(event);
            });
            return null;
        }
    }
}
