package com.tumods.adventurecraft.objects.items.food;

import com.tumods.adventurecraft.objects.items.FoodBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemLemon extends FoodBase {

	public ItemLemon(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
//		player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:effect.confusion"), 8, 1));
	}

}
