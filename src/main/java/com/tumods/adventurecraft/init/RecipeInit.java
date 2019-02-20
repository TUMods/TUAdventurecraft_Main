package com.tumods.adventurecraft.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class RecipeInit {
	private static final List<ResourceLocation> toRemove = new ArrayList<ResourceLocation>();
	
	/**
	 * Post init function to remove certain recipes and their functionality
	 */
	public static void unregisterRecipes() {
		// Iterate through each recipe that is registered
		for (IRecipe r : ForgeRegistries.RECIPES) {
			if (r.getRecipeOutput().getItem() == Items.WOODEN_PICKAXE) toRemove.add(r.getRegistryName());
			if (r.getRecipeOutput().getItem() == Items.WOODEN_AXE) toRemove.add(r.getRegistryName());
			if (r.getRecipeOutput().getItem() == Items.WOODEN_HOE) toRemove.add(r.getRegistryName());
			if (r.getRecipeOutput().getItem() == Items.WOODEN_SHOVEL) toRemove.add(r.getRegistryName());
			if (r.getRecipeOutput().getItem() == Items.WOODEN_SWORD) toRemove.add(r.getRegistryName());
		}
		
		// Iterate through the list of recipes to remove and unregister them
		for (ResourceLocation resLoc : toRemove) {
			System.out.println("[TUAC] Remove recipe: " + resLoc);
			((IForgeRegistryModifiable)ForgeRegistries.RECIPES).remove(resLoc);
			
			
		}
	}
}
