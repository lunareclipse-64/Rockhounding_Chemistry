package com.globbypotato.rockhounding_chemistry.machines.gui;

import com.globbypotato.rockhounding_chemistry.handlers.Reference;
import com.globbypotato.rockhounding_chemistry.machines.container.ContainerMineralSizer;
import com.globbypotato.rockhounding_chemistry.machines.tileentity.TileEntityMineralSizer;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiMineralSizer extends GuiBase {

	public static ResourceLocation TEXTURE_REF = new ResourceLocation(Reference.MODID + ":textures/gui/guimineralsizer.png");

    private final InventoryPlayer playerInventory;
    private final TileEntityMineralSizer mineralSizer;
	public static final int WIDTH = 176;
	public static final int HEIGHT = 191;

    public GuiMineralSizer(InventoryPlayer playerInv, TileEntityMineralSizer tile){
        super(tile, new ContainerMineralSizer(playerInv,tile));
       this.TEXTURE = TEXTURE_REF;
        this.mineralSizer = tile;
        this.playerInventory = playerInv;
		this.xSize = WIDTH;
		this.ySize = HEIGHT;
		this.containerName = "container.mineralSizer";
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
       super.drawScreen(mouseX, mouseY, f);
	   int x = (this.width - this.xSize) / 2;
	   int y = (this.height - this.ySize) / 2;

	   //fuel
	   if(mouseX >= 11+x && mouseX <= 20+x && mouseY >= 40+y && mouseY <= 89+y){
		   drawPowerInfo("ticks", this.mineralSizer.getPower(), this.mineralSizer.getPowerMax(), mouseX, mouseY);
	   }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
    	super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);

        //power bar
        if (this.mineralSizer.getPower() > 0){
            int k = this.getBarScaled(50, this.mineralSizer.getPower(), this.mineralSizer.getPowerMax());
            this.drawTexturedModalRect(i + 11, j + 40 + (50 - k), 176, 51, 10, k);
        }
        //smelt bar
        if (this.mineralSizer.cookTime > 0){
            int k = this.getBarScaled(31, this.mineralSizer.cookTime, this.mineralSizer.getMaxCookTime());
            this.drawTexturedModalRect(i + 65, j + 15, 176, 0, k, 31);
        }
        //inductor
        if(this.mineralSizer.hasPermanentInduction()){
            this.drawTexturedModalRect(i + 7, j + 19, 176, 103, 18, 18);
        }
    }

}