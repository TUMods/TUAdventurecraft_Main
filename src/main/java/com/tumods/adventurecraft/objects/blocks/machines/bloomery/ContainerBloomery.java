package com.tumods.adventurecraft.objects.blocks.machines.bloomery;

import com.tumods.adventurecraft.objects.blocks.machines.bloomery.slots.SlotBloomeryFuel;
import com.tumods.adventurecraft.objects.blocks.machines.bloomery.slots.SlotBloomeryOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerBloomery extends Container {
	
	private final TileEntityBloomery tileEntity;
	private int cookTime, totalCookTime, burnTime, currentBurnTime;
	
	public ContainerBloomery(InventoryPlayer player, TileEntityBloomery tileEntity) {
		this.tileEntity = tileEntity;
		
		// Bloomery Inventory
		addSlotToContainer(new Slot(this.tileEntity, TileEntityBloomery.INPUT_1, 56, 7));
		addSlotToContainer(new Slot(this.tileEntity, TileEntityBloomery.INPUT_2, 47, 25));
		addSlotToContainer(new Slot(this.tileEntity, TileEntityBloomery.INPUT_3, 65, 25));
		addSlotToContainer(new SlotBloomeryFuel(this.tileEntity, TileEntityBloomery.FUEL, 56, 60));
		addSlotToContainer(new SlotBloomeryOutput(player.player, this.tileEntity, TileEntityBloomery.OUTPUT, 124, 25));
		
		// Player Inventory
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}
		
		// Player Hotbar
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, tileEntity);
	}
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0; i < listeners.size(); i++) {
			IContainerListener listener = listeners.get(i);
			
			if (burnTime != tileEntity.getField(0)) listener.sendWindowProperty(this, 0, tileEntity.getField(0));
			if (cookTime != tileEntity.getField(2)) listener.sendWindowProperty(this, 2, tileEntity.getField(2));
			if (currentBurnTime != tileEntity.getField(1)) listener.sendWindowProperty(this, 1, tileEntity.getField(1));
			if (totalCookTime != tileEntity.getField(3)) listener.sendWindowProperty(this, 3, tileEntity.getField(3));
			
			burnTime = tileEntity.getField(0);
			cookTime = tileEntity.getField(2);
			currentBurnTime = tileEntity.getField(1);
			totalCookTime = tileEntity.getField(3);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		tileEntity.setField(id, data);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tileEntity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if (index == TileEntityBloomery.OUTPUT) {
				if (!mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if (index != TileEntityBloomery.FUEL 
					&& index != TileEntityBloomery.INPUT_1 && index != TileEntityBloomery.INPUT_2 && index != TileEntityBloomery.INPUT_3) {
				Slot slot1 = inventorySlots.get(index + 1);
				Slot slot2 = inventorySlots.get(index + 2);
				
				if (!BloomeryRecipes.getInstance().getAlloyingResult(stack1, slot1.getStack(), slot2.getStack()).isEmpty()) {
					if (!this.mergeItemStack(stack1, 0, 3, false)) return ItemStack.EMPTY;
					else if (TileEntityBloomery.isItemFuel(stack1))
						if (!this.mergeItemStack(stack1, 2, 3, false))return ItemStack.EMPTY;
					else if	(index >= 4 && index < 31)
						if (!this.mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
					else if (index >= 31 && index < 40 && !this.mergeItemStack(stack1, 4, 31, false)) return ItemStack.EMPTY;
				}
			}
			else if (!mergeItemStack(stack1, 31, 40, false)) return ItemStack.EMPTY;
			
			if (stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			
			if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}
	
}
