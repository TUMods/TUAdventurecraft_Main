package com.tumods.adventurecraft.objects.blocks.machines.bloomery;

import com.tumods.adventurecraft.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiBloomery extends GuiContainer {
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/container/bloomery.png");
	private final InventoryPlayer player;
	private final TileEntityBloomery tileEntity;
	
	public GuiBloomery(InventoryPlayer player, TileEntityBloomery tileEntity) {
		super(new ContainerBloomery(player, tileEntity));
		this.player = player;
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = tileEntity.getDisplayName().getFormattedText();
		fontRenderer.drawString(tileName, (xSize - fontRenderer.getStringWidth(tileName)) - 10, 8, 4210752);
		fontRenderer.drawString(player.getDisplayName().getUnformattedText(), 122, ySize - 96 + 2, 4210752);
		
		renderHoveredToolTip(mouseY, mouseX);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (TileEntityBloomery.isBurning(tileEntity)) {
			int k = this.getBurnLeftScaled(13);
			drawTexturedModalRect(guiLeft + 57, guiTop + 44 + 12 - k, 176, 12 - k, 14, k + 1);
			
			int l = this.getCookProgressScaled(24);
			drawTexturedModalRect(guiLeft + 88, guiTop + 26, 176, 14, l + 1, 16);
		}
	}
	
	private int getBurnLeftScaled(int pixels) {
		int currentBurnTime = tileEntity.getField(1);
		if (currentBurnTime == 0) currentBurnTime = 200;
		return tileEntity.getField(0) * pixels / currentBurnTime;
	}
	
	private int getCookProgressScaled(int pixels) {
		int cookTime = tileEntity.getField(2);
		int totalCookTime = tileEntity.getField(3);
		return (totalCookTime != 0 && cookTime != 0) ? cookTime * pixels / totalCookTime : 0;
	}
}
