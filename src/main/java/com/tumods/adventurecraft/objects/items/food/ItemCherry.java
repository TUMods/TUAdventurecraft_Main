package com.tumods.adventurecraft.objects.items.food;

import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.objects.items.FoodBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemCherry extends FoodBase {

	public ItemCherry(String name, int amount, float saturation, boolean isWolfFood) {
		super(name, amount, saturation, isWolfFood);
	}
	
	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		super.onFoodEaten(stack, worldIn, player);
		if (MathHelper.getInt(worldIn.rand, 0, 3) == 0) {
			player.addItemStackToInventory(new ItemStack(ItemInit.SEED_CHERRY));
		}
	}

}
