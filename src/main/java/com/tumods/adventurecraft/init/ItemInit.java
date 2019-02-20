package com.tumods.adventurecraft.init;

import java.util.ArrayList;
import java.util.List;

import com.tumods.adventurecraft.objects.items.ItemBase;
import com.tumods.adventurecraft.objects.items.tools.ToolAxe;
import com.tumods.adventurecraft.objects.items.tools.ToolKnife;
import com.tumods.adventurecraft.objects.items.tools.ToolPickaxe;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// INGOTS
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	
	
	// TOOL MATERIALS
	public static final ToolMaterial TOOL_FLINT = EnumHelper.addToolMaterial("tool_flint", 1, 20, 1.5F, 1.0F, 5);
	
	
	// TOOLS
	public static final Item HATCHET_FLINT = new ToolAxe("hatchet_flint", TOOL_FLINT);
	public static final Item PICKAXE_FLINT = new ToolPickaxe("pickaxe_flint", TOOL_FLINT);
	public static final Item KNIFE_FLINT = new ToolKnife("knife_flint", TOOL_FLINT);
}
