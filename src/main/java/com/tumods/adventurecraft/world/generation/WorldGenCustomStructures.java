package com.tumods.adventurecraft.world.generation;

import java.util.ArrayList;
import java.util.Random;

import com.tumods.adventurecraft.world.generation.generators.structures.WorldGenStructure;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import scala.actors.threadpool.Arrays;

public class WorldGenCustomStructures implements IWorldGenerator {

//	public static final WorldGenStructure SHACK = new WorldGenStructure("shack");
	ArrayList<WorldGenStructure> overworldBuildings = new ArrayList<WorldGenStructure>();
	ArrayList<WorldGenStructure> overworldTrees = new ArrayList<WorldGenStructure>();
	
	public WorldGenCustomStructures() {
		// Buildings
//		overworldBuildings.add(new WorldGenStructure("shack"));
		
		// Trees
		overworldTrees.add(new WorldGenStructure("chestnut1"));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case -1:
			
			break;
		case 0:
			for (WorldGenStructure structure : overworldBuildings)
				generateStructure(structure, world, random, chunkX, chunkZ, 25, Blocks.GRASS);
			
			for (WorldGenStructure structure : overworldTrees)
				generateStructure(structure, world, random, chunkX, chunkZ, 9, Blocks.GRASS);
			break;
		case 1:
			
			break;
		}	
	}
	
	private void generateStructure(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chance,
			Block topBlock, Class<?>...biomes) {
		ArrayList<Class<?>> biomeList = new ArrayList<Class<?>>(Arrays.asList(biomes));
		
		int x = (chunkX * 16) + random.nextInt(15);
		int z = (chunkZ * 16) + random.nextInt(15);
		int y = calculateGenerationHeight(world, x, z, topBlock) + 1;
		BlockPos pos = new BlockPos(x, y, z);
		
		Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
		
		if (world.getWorldType() != WorldType.FLAT)
			if (biomeList.contains(biome) || biomes.length == 0)
				if (random.nextInt(chance) == 0)
					generator.generate(world, random, pos);
	}
	
	private static int calculateGenerationHeight(World world, int x, int z, Block topBlock) {
		int y = world.getHeight();
		boolean foundGround = false;
		
		while (!foundGround && y-- >= 0) {
			Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
			foundGround = block == topBlock;
		}
		
		return y;
	}
}
