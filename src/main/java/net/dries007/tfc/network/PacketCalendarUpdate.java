/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.network;

import io.netty.buffer.ByteBuf;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.util.calendar.TFCCalendar;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketCalendarUpdate implements IMessage {
    private TFCCalendar instance;

    @SuppressWarnings("unused")
    @Deprecated
    public PacketCalendarUpdate() {
    }

    public PacketCalendarUpdate(TFCCalendar instance) {
        this.instance = instance;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        instance = new TFCCalendar();
        instance.read(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        instance.write(buf);
    }

    public static class Handler implements IMessageHandler<PacketCalendarUpdate, IMessage> {
        @Override
        public IMessage onMessage(PacketCalendarUpdate message, MessageContext ctx) {
            TerraFirmaCraft.getProxy().getThreadListener(ctx).addScheduledTask(() -> TFCCalendar.INSTANCE.resetTo(message.instance));
            return null;
        }
    }
}
