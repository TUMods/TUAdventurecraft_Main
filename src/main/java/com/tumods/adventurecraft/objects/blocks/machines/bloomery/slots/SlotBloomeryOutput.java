package com.tumods.adventurecraft.objects.blocks.machines.bloomery.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBloomeryOutput extends Slot {
	
	private final EntityPlayer player;
	private int removeCount;

	public SlotBloomeryOutput(EntityPlayer player, IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.player = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false; // Can't put anything into output
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		if (getHasStack()) removeCount += Math.min(amount, getStack().getCount());
		return super.decrStackSize(amount);
	}

}
