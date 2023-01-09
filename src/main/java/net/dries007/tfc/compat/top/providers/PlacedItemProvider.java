package net.dries007.tfc.compat.top.providers;

import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ProbeMode;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.objects.items.rock.ItemRock;
import net.dries007.tfc.objects.te.TEPlacedItemFlat;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class PlacedItemProvider implements IProbeInfoProvider {

    @Override
    public String getID() {
        return TerraFirmaCraft.MOD_ID + ":placed_item";
    }

    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) {
        TEPlacedItemFlat te = Helpers.getTE(world, data.getPos(), TEPlacedItemFlat.class);
        if (te != null)
        {
            ItemStack stack = te.getStack();
            if (stack.getItem() instanceof ItemRock)
            {
                ItemRock pebble = (ItemRock) stack.getItem();
                Rock rock = pebble.getRock(stack);
                if (rock.isFluxStone())
                {
                    probeInfo.text(new TextComponentTranslation("waila.tfc.flux_stone").getFormattedText());
                }
            }
        }
    }
}
