package com.tumods.adventurecraft.init;

import java.util.ArrayList;
import java.util.List;

import com.tumods.adventurecraft.objects.items.FoodBase;
import com.tumods.adventurecraft.objects.items.ItemBase;
import com.tumods.adventurecraft.objects.items.food.ItemBlueberry;
import com.tumods.adventurecraft.objects.items.food.ItemCherry;
import com.tumods.adventurecraft.objects.items.food.ItemLemon;
import com.tumods.adventurecraft.objects.items.food.ItemRaspberry;
import com.tumods.adventurecraft.objects.items.plants.ItemSeedCherry;
import com.tumods.adventurecraft.objects.items.tools.ToolAxe;
import com.tumods.adventurecraft.objects.items.tools.ToolKnife;
import com.tumods.adventurecraft.objects.items.tools.ToolHoshick;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	// INGOTS
	public static final Item INGOT_COPPER = new ItemBase("ingot_copper");
	public static final Item INGOT_NEODYMIUM = new ItemBase("ingot_neodymium");
	public static final Item INGOT_SILVER = new ItemBase("ingot_silver");
	public static final Item INGOT_TIN = new ItemBase("ingot_tin");
	
	
	// ORESHARDS
	public static final Item ORESHARD_END_NEODYMIUM = new ItemBase("oreshard_end_neodymium");
	public static final Item ORESHARD_NETHER_MANGANESE = new ItemBase("oreshard_nether_manganese");
	public static final Item ORESHARD_OVERWORLD_COPPER = new ItemBase("oreshard_overworld_copper");
	public static final Item ORESHARD_OVERWORLD_MANGANESE = new ItemBase("oreshard_overworld_manganese");
	public static final Item ORESHARD_OVERWORLD_SILVER = new ItemBase("oreshard_overworld_silver");
	public static final Item ORESHARD_OVERWORLD_TIN = new ItemBase("oreshard_overworld_tin");
	
	
	// TOOLS
	public static final Item HATCHET_FLINT = new ToolAxe("hatchet_flint", MaterialsInit.TOOL_FLINT);
	public static final Item PICKAXE_FLINT = new ToolHoshick("hoshick_flint", MaterialsInit.TOOL_FLINT);
	public static final Item KNIFE_FLINT = new ToolKnife("knife_flint", MaterialsInit.TOOL_FLINT);
	
	
	// FOOD
	public static final Item PRODUCE_BLUEBERRY = new ItemBlueberry("produce_blueberry", 1, 3, false);
	public static final Item PRODUCE_RASPBERRY = new ItemRaspberry("produce_raspberry", 1, 3, false);
	public static final Item PRODUCE_CHERRY = new ItemCherry("produce_cherry", 1, 3, false);
	public static final Item PRODUCE_CHESTNUT = new FoodBase("produce_chestnut", 3, 1, false);
	public static final Item PRODUCE_LEMON = new ItemLemon("produce_lemon", 1, 1, false); // Add thirst to player
	public static final Item PRODUCE_LIME = new FoodBase("produce_lime", 1, 1, false);
	public static final Item PRODUCE_RYE = new FoodBase("produce_rye", 2, 1, false);
	
	
	// PLANTS
	public static final Item SEED_CHERRY = new ItemSeedCherry("seed_cherry");
}
