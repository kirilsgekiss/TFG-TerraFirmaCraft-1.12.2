package cellars.blocks.gui;

import net.dries007.tfc.client.gui.GuiContainerTE;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import cellars.blocks.tileentity.TEIceBunker;
import cellars.util.Reference;

import java.util.ArrayList;
import java.util.List;

public class GuiIceBunker extends GuiContainerTE<TEIceBunker> {

    private static final ResourceLocation GUI_BUNKER = new ResourceLocation(Reference.MOD_ID + ":textures/gui/ice_bunker.png");
    private final InventoryPlayer playerInventory;
    private final TEIceBunker te;

    public GuiIceBunker(Container container, InventoryPlayer playerInv, TEIceBunker tile, String translationKey){
        super(container, playerInv, tile, GUI_BUNKER);
        this.playerInventory = playerInv;
        this.te = tile;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String name = I18n.format(this.playerInventory.getDisplayName().getUnformattedText());
        fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 00000000);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize-92, 00000000);

        if(mouseX >= guiLeft + 5 && mouseX <= guiLeft + 15 && mouseY >= guiTop + 5 && mouseY <= guiTop + 15) {
            List<String> infoText = new ArrayList<String>();
            float temperature = te.getTemperature();
            int coolant = te.getCoolant();

            if(temperature <= -1000) {
                switch((int)(temperature * 0.001f)) {
                    case -1: infoText.add("[!] The cellar has a problem with walls or has no doors");
                        break;
                    case -2: infoText.add("[!] A block prevents the cellar from working correctly");
                        break;
                    case -3: infoText.add("[!] The cellar has no or more than one entrance");
                        break;
                }
                infoText.add("Check the structure if the error will appear after 1 minute");
                infoText.add("or break and place the Ice Bunker block again");

            } else {
                if(temperature < 0) {
                    infoText.add("Temperature: below zero");
                } else {
                    infoText.add("Temperature: " + String.format("%.2f", temperature) + "\u2103");
                }
                infoText.add("Coolant: " + coolant + " units");
            }

            this.drawHoveringText(infoText, mouseX-guiLeft, mouseY-guiTop);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f,1.0f,1.0f,1.0f);
        this.mc.getTextureManager().bindTexture(GUI_BUNKER);
        this.drawTexturedModalRect(this.guiLeft,this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
