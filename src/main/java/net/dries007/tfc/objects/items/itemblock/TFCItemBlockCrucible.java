/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.objects.items.itemblock;

import net.dries007.tfc.TFCConfig;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.util.Alloy;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
public class TFCItemBlockCrucible extends TFCItemBlock {
    public TFCItemBlockCrucible(Block block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt != null) {
            Alloy alloy = new Alloy(TFCConfig.Devices.CRUCIBLE.tank);
            alloy.deserializeNBT(nbt.getCompoundTag("alloy"));
            String metalName = (new TextComponentTranslation(alloy.getResult().getUnlocalizedName())).getFormattedText();
            tooltip.add(I18n.format(TerraFirmaCraft.MOD_ID + ".tooltip.crucible_alloy", alloy.getAmount(), metalName));
        }
    }
}
