package com.tumods.adventurecraft.objects.blocks.machines.bloomery.slots;

import com.tumods.adventurecraft.objects.blocks.machines.bloomery.TileEntityBloomery;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBloomeryFuel extends Slot {

	public SlotBloomeryFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityBloomery.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}

}
