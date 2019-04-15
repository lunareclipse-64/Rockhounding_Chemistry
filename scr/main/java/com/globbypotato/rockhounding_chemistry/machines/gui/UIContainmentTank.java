package com.globbypotato.rockhounding_chemistry.machines.gui;

import java.util.List;

import com.globbypotato.rockhounding_chemistry.handlers.Reference;
import com.globbypotato.rockhounding_chemistry.machines.container.COContainmentTank;
import com.globbypotato.rockhounding_chemistry.machines.tile.TEContainmentTank;
import com.globbypotato.rockhounding_chemistry.utils.ModUtils;
import com.globbypotato.rockhounding_core.machines.gui.GuiUtils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class UIContainmentTank extends GuiBase {

	public static ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guicontainmenttank.png");

    private final TEContainmentTank tile;

    public UIContainmentTank(InventoryPlayer playerInv, TEContainmentTank tile){
    	super(new COContainmentTank(playerInv,tile), ModUtils.HEIGHT);
        GuiBase.TEXTURE = TEXTURE_REF;
        this.tile = tile;
		this.containerName = "container." + TEContainmentTank.getName();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

		//void
	    if(GuiUtils.hoveringArea(109, 33, 18, 18, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawLabel(this.void_label, mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
	    }

		//filter
	    if(GuiUtils.hoveringArea(49, 81, 18, 18, mouseX, mouseY, x, y)){
			String filterstring = TextFormatting.BLUE + "Fluid Filter: " + TextFormatting.WHITE + "use a filled ampoule to set";
			if(!this.tile.hasFilter()){
				List<String> tooltip = GuiUtils.drawLabel(filterstring, mouseX, mouseY);
				drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
			}else{
				filterstring = TextFormatting.GRAY + "Filter: " + TextFormatting.WHITE + this.tile.getFilter().getLocalizedName();
				List<String> tooltip = GuiUtils.drawLabel(filterstring, mouseX, mouseY);
				drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
			}
		}

		//input fluid
	    if(GuiUtils.hoveringArea(72, 34, 32, 64, mouseX, mouseY, x, y)){
			List<String> tooltip = GuiUtils.drawFluidTankInfo(this.tile.inputTank, mouseX, mouseY);
			drawHoveringText(tooltip, mouseX, mouseY, this.fontRenderer);
		}

    }

	@Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

		//input fluid
		if(this.tile.getTankFluid() != null){
			GuiUtils.renderFluidBar(this.tile.getTankFluid(), this.tile.getTankAmount(), this.tile.getTankCapacity(), i + 72, j + 34, 32, 64);
		}

		//filter
		if(this.tile.hasFilter()){
			GuiUtils.renderFluidBar(this.tile.getFilter(), 1000, 1000, i + 50, j + 82, 16, 16);
		}

    }
}