package com.tumods.adventurecraft.objects.items;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;

import net.minecraft.item.ItemFood;

public class FoodBase extends ItemFood implements IHasModel {

	public FoodBase(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(Main.itemTab);
		
		ItemInit.ITEMS.add(this);
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
