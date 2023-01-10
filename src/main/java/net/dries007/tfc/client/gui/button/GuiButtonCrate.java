package net.dries007.tfc.client.gui.button;

import javax.annotation.Nonnull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;

import net.dries007.tfc.client.button.GuiButtonTFC;
import net.dries007.tfc.client.button.IButtonTooltip;

import net.dries007.tfc.objects.te.TECrate;

import static net.dries007.tfc.client.gui.GuiLargeVessel.LARGE_VESSEL_BACKGROUND;

import static tfcflorae.TFCFlorae.TFCFLORAE_MODID;

public class GuiButtonCrate extends GuiButtonTFC implements IButtonTooltip
{
    private final TECrate tile;

    public GuiButtonCrate(TECrate tile, int buttonId, int guiTop, int guiLeft)
    {
        super(buttonId, guiLeft + 123, guiTop + 35, 20, 20, "");
        this.tile = tile;
    }

    @Override
    public String getTooltip()
    {
        return TFCFLORAE_MODID + ".tooltip." + (tile.isSealed() ? "crate_unseal" : "crate_seal");
    }

    @Override
    public boolean hasTooltip()
    {
        return true;
    }

    @Override
    public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY, float partialTicks)
    {
        if (this.visible)
        {
            GlStateManager.color(1, 1, 1, 1);
            mc.getTextureManager().bindTexture(LARGE_VESSEL_BACKGROUND);
            hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            if (tile.isSealed())
            {
                drawModalRectWithCustomSizedTexture(x, y, 236, 0, 20, 20, 256, 256);
            }
            else
            {
                drawModalRectWithCustomSizedTexture(x, y, 236, 20, 20, 20, 256, 256);
            }
            mouseDragged(mc, mouseX, mouseY);
        }
    }
}
