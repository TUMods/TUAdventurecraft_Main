package com.tumods.adventurecraft.objects.blocks.machines.bloomery;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutableTriple;

import com.google.common.collect.Maps;
import com.tumods.adventurecraft.init.ItemInit;

import net.minecraft.item.ItemStack;

public class BloomeryRecipes {
	// Make a singleton instance of the object
	private static final BloomeryRecipes INSTANCE = new BloomeryRecipes();
	// HashMap<output, Triple<input, input, input>>
	private final HashMap<ItemStack, ImmutableTriple<ItemStack, ItemStack, ItemStack>> 
		alloyList = Maps.<ItemStack, ImmutableTriple<ItemStack, ItemStack, ItemStack>>newHashMap();
	private final Map<ItemStack, Float> 
		experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static BloomeryRecipes getInstance() {
		return INSTANCE;
	}
	
	private BloomeryRecipes() {
		addBloomeryRecipe(new ItemStack(ItemInit.ORESHARD_OVERWORLD_COPPER), new ItemStack(ItemInit.ORESHARD_OVERWORLD_COPPER),
				new ItemStack(ItemInit.ORESHARD_OVERWORLD_COPPER), new ItemStack(ItemInit.BLOOM_COPPER), 2.0f);
	}
	
	public void addBloomeryRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack result, float experience) {
		if (getAlloyingResult(input1, input2, input3) != ItemStack.EMPTY) return;
		alloyList.put(result, new ImmutableTriple<ItemStack, ItemStack, ItemStack>(input1, input2, input3));
		experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getAlloyingResult(ItemStack input1, ItemStack input2, ItemStack input3) {
		// HashMap<output, Triple<input, input, input>>
		for (Entry<ItemStack, ImmutableTriple<ItemStack, ItemStack, ItemStack>> entry : alloyList.entrySet()) {
			if (compareItemStacks(input1, entry.getValue().getLeft()) && compareItemStacks(input2, entry.getValue().getMiddle())
					&& compareItemStacks(input3, entry.getValue().getRight())) {
				return entry.getKey();
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2) {
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public HashMap<ItemStack, ImmutableTriple<ItemStack, ItemStack, ItemStack>> getAlloyingList() {
		return alloyList;
	}
	
	public float getAlloyingExperience(ItemStack stack) {
		for (Entry<ItemStack, Float> entry : experienceList.entrySet()) {
			if (this.compareItemStacks(stack, entry.getKey())) {
				return entry.getValue();
			}
		}
		return 0.0f;
	}

}
