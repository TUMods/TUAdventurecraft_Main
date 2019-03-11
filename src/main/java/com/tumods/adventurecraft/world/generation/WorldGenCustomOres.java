package com.tumods.adventurecraft.world.generation;

import java.util.Random;

import com.tumods.adventurecraft.init.BlockInit;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator {
	private WorldGenerator ore_end_neodymium;
	private WorldGenerator ore_nether_manganese;
	private WorldGenerator ore_overworld_copper;
	private WorldGenerator ore_overworld_manganese;
	private WorldGenerator ore_overworld_silver;
	private WorldGenerator ore_overworld_tin;
	
	public WorldGenCustomOres() {
		ore_end_neodymium = new WorldGenMinable(BlockInit.ORE_END_NEODYMIUM.getDefaultState(),
				3, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_nether_manganese = new WorldGenMinable(BlockInit.ORE_NETHER_MANGANESE.getDefaultState(),
				5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_OVERWORLD_COPPER.getDefaultState(),
				7, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_manganese = new WorldGenMinable(BlockInit.ORE_OVERWORLD_MANGANESE.getDefaultState(),
				5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_silver = new WorldGenMinable(BlockInit.ORE_OVERWORLD_SILVER.getDefaultState(),
				3, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_tin = new WorldGenMinable(BlockInit.ORE_OVERWORLD_TIN.getDefaultState(),
				7, BlockMatcher.forBlock(Blocks.STONE));
	}
	
	private void runGenerator(WorldGenerator gen, World worldIn, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		if (minHeight > maxHeight || minHeight < 0 || maxHeight > worldIn.getHeight()) 
			throw new IllegalArgumentException("Ore generated out of bounds");
		
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chance; i++) {
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
//			System.out.println("Ore at " + x + ", " + y + ", " + z);
			gen.generate(worldIn, rand, new BlockPos(x, y, z));
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
		case -1: // Nether
			runGenerator(ore_nether_manganese, world, random, chunkX, chunkZ, 50, 0, 100);
			break;
		case 0: // Overworld
			runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 50, 0, 100);
			runGenerator(ore_overworld_manganese, world, random, chunkX, chunkZ, 50, 0, 100);
			runGenerator(ore_overworld_silver, world, random, chunkX, chunkZ, 50, 0, 100);
			runGenerator(ore_overworld_tin, world, random, chunkX, chunkZ, 50, 0, 100);
			break;
		case 1: // End
			runGenerator(ore_end_neodymium, world, random, chunkX, chunkZ, 50, 0, 100);
			break;
		}
	}
}
