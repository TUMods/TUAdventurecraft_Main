package com.tumods.adventurecraft.tabs;

import com.tumods.adventurecraft.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemTab extends CreativeTabs {
	public ItemTab(String label) {
		super(label);
//		this.setBackgroundImageName("tutorial.png");
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.INGOT_COPPER);
	}
}
