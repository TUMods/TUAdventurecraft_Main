package com.tumods.adventurecraft.init;

import java.util.ArrayList;
import java.util.List;

import com.tumods.adventurecraft.objects.blocks.BlockBase;
import com.tumods.adventurecraft.objects.blocks.food.BlockCropBlueberry;
import com.tumods.adventurecraft.objects.blocks.food.BlockCropSaplingCherry;
import com.tumods.adventurecraft.objects.blocks.plants.BlockSaplingCherry;
import com.tumods.adventurecraft.objects.blocks.trees.BlockLeavesCherry;
import com.tumods.adventurecraft.objects.blocks.trees.BlockLogBase;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInit {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	// METAL BLOCKS
	public static final Block BLOCK_COPPER = new BlockBase("block_copper", Material.IRON);
	
	
	// CROPS
	public static final Block CROP_BLUEBERRY = new BlockCropBlueberry("crop_blueberry");
	public static final Block CROP_SAPLING_CHERRY = new BlockCropSaplingCherry("crop_sapling_cherry");
	
	
	// SAPLINGS
	public static final Block SAPLING_CHERRY = new BlockSaplingCherry("sapling_cherry");
	
	
	// TREES
	// Cherry
	public static final Block LOG_CHERRY = new BlockLogBase("log_cherry");
//	public static final Block PLANKS_CHERRY = new BlockBase("planks_cherry", Material.WOOD);
	public static final Block LEAVES_CHERRY = new BlockLeavesCherry("leaves_cherry");
	
}
