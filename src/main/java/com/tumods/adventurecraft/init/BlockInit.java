package com.tumods.adventurecraft.init;

import java.util.ArrayList;
import java.util.List;

import com.tumods.adventurecraft.objects.blocks.BlockBase;
import com.tumods.adventurecraft.objects.blocks.BlockOreBase;
import com.tumods.adventurecraft.objects.blocks.food.BlockCropBlueberry;
import com.tumods.adventurecraft.objects.blocks.food.BlockCropRaspberry;
import com.tumods.adventurecraft.objects.blocks.food.BlockCropRye;
import com.tumods.adventurecraft.objects.blocks.plants.BlockCropSaplingCherry;
import com.tumods.adventurecraft.objects.blocks.plants.BlockCropSaplingChestnut;
import com.tumods.adventurecraft.objects.blocks.plants.BlockSaplingCherry;
import com.tumods.adventurecraft.objects.blocks.plants.BlockSaplingChestnut;
import com.tumods.adventurecraft.objects.blocks.trees.BlockLeavesCherry;
import com.tumods.adventurecraft.objects.blocks.trees.BlockLeavesChestnut;
import com.tumods.adventurecraft.objects.blocks.trees.BlockLogBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// METAL BLOCKS
	public static final Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON);
	
	
	// ORES
	public static final Block ORE_END_NEODYMIUM = new BlockOreBase("ore_end_neodymium", Material.ROCK, 
			ItemInit.ORESHARD_END_NEODYMIUM, 3, 4.5f, 1, 55);
	public static final Block ORE_NETHER_MANGANESE = new BlockOreBase("ore_nether_manganese", Material.ROCK, 
			ItemInit.ORESHARD_NETHER_MANGANESE, 2, 3.0f, 1, 30);
	public static final Block ORE_OVERWORLD_COPPER = new BlockOreBase("ore_overworld_copper", Material.ROCK, 
			ItemInit.ORESHARD_OVERWORLD_COPPER, 1, 2.5f, 3, 10);
	public static final Block ORE_OVERWORLD_MANGANESE = new BlockOreBase("ore_overworld_manganese", Material.ROCK, 
			ItemInit.ORESHARD_OVERWORLD_MANGANESE, 2, 3.0f, 2, 30);
	public static final Block ORE_OVERWORLD_SILVER = new BlockOreBase("ore_overworld_silver", Material.ROCK, 
			ItemInit.ORESHARD_OVERWORLD_SILVER, 3, 3.6f, 1, 40);
	public static final Block ORE_OVERWORLD_TIN = new BlockOreBase("ore_overworld_tin", Material.ROCK, 
			ItemInit.ORESHARD_OVERWORLD_TIN, 1, 2.3f, 3, 10);
	
	
	// CROPS
	public static final Block CROP_BLUEBERRY = new BlockCropBlueberry("crop_blueberry");
	public static final Block CROP_RASPBERRY = new BlockCropRaspberry("crop_raspberry");
	public static final Block CROP_RYE = new BlockCropRye("crop_rye");
	public static final Block CROP_SAPLING_CHERRY = new BlockCropSaplingCherry("crop_sapling_cherry");
	public static final Block CROP_SAPLING_CHESTNUT = new BlockCropSaplingChestnut("crop_sapling_chestnut");
	
	
	// SAPLINGS
	public static final Block SAPLING_CHERRY = new BlockSaplingCherry("sapling_cherry");
	public static final Block SAPLING_CHESTNUT = new BlockSaplingChestnut("sapling_chestnut");
	
	
	// TREES
	// Cherry
	public static final Block LOG_CHERRY = new BlockLogBase("log_cherry");
	public static final Block PLANKS_CHERRY = new BlockBase("planks_cherry", Material.WOOD);
	public static final Block LEAVES_CHERRY = new BlockLeavesCherry("leaves_cherry");
	// Chestnut
	public static final Block LOG_CHESTNUT = new BlockLogBase("log_chestnut");
	public static final Block PLANKS_CHESTNUT = new BlockBase("planks_chestnut", Material.WOOD);
	public static final Block LEAVES_CHESTNUT = new BlockLeavesChestnut("leaves_chestnut");
	
}
