package net.dries007.tfc.compat.dynamictrees.dropcreators;

import com.ferreusveritas.dynamictrees.systems.dropcreators.DropCreator;
import com.ferreusveritas.dynamictrees.trees.Species;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLog;
import net.dries007.tfc.objects.blocks.wood.tree.TFCBlockLogDT;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class DTDropCreatorLog extends DropCreator {
    public DTDropCreatorLog(String name) {
        super(new ResourceLocation(MOD_ID, name + "logs"));
    }

    @Override
    public List<ItemStack> getLogsDrop(World world, Species species, BlockPos breakPos, Random random, List<ItemStack> dropList, float volume) {
        Species.LogsAndSticks logsAndSticks = species.getLogsAndSticks(volume);
        int numLogs = logsAndSticks.logs;
        ItemStack logs = species.getFamily().getPrimitiveLogItemStack(1);
        int stackSize = TFCBlockLog.get(((TFCBlockLogDT) species.getFamily().getPrimitiveLog().getBlock()).tree).getStackSize(logs);
        while (numLogs > 0) {
            dropList.add(species.getFamily().getPrimitiveLogItemStack(Math.min(numLogs, stackSize)));
            numLogs -= stackSize;
        }
        int numSticks = logsAndSticks.sticks;
        if (numSticks > 0) {
            dropList.add(species.getFamily().getStick(numSticks));
        }
        return dropList;
    }

}
