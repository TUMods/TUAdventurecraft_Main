package com.tumods.adventurecraft.objects.items.tools;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;
import com.tumods.adventurecraft.util.Reference;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.world.World;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ToolKnife extends ItemSword implements IHasModel {
	public ToolKnife(String name, ToolMaterial material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.itemTab);
		
		ItemInit.ITEMS.add(this);
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
