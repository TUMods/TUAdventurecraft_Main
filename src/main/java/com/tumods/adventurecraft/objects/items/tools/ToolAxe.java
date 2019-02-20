package com.tumods.adventurecraft.objects.items.tools;

import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;
import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;
import com.tumods.adventurecraft.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ToolAxe extends ItemTool implements IHasModel {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);
	
	public ToolAxe(String name, ToolMaterial material) {
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.itemTab);
		
		ItemInit.ITEMS.add(this);
	}
	
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
        return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getDestroySpeed(stack, state) : this.efficiency;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			int maxHealth = this.getMaxDamage();
			int currentHealth = maxHealth - (int)(this.getDurabilityForDisplay(stack) * maxHealth);
			double healthPercent = (currentHealth / (double)maxHealth);
			
			String colourFormat = (healthPercent > 0.5) ? Reference.DARK_GREEN : ((healthPercent > 0.25) ? Reference.YELLOW : Reference.DARK_RED);
			
			tooltip.add(" Durability: " + colourFormat + String.valueOf(currentHealth) + Reference.GRAY + " / " + String.valueOf(maxHealth));
		} else {
			tooltip.add("Hold \u00A73Shift\u00A77 for more information");
		}
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
