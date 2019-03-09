package com.tumods.adventurecraft.world.generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import com.tumods.adventurecraft.world.generation.generators.WorldGenTreeCherry;
import com.tumods.adventurecraft.world.generation.generators.WorldGenTreeChestnut;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeBeach;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomTrees implements IWorldGenerator {
	
	private final WorldGenerator TREE_CHERRY = new WorldGenTreeCherry();
	private final WorldGenerator TREE_CHESTNUT = new WorldGenTreeChestnut();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		case 1: // END
			break;
		case 0: // OVERWORLD
			runGenerator(TREE_CHERRY, world, random, chunkX, chunkZ, 3, -1, 0);
			runGenerator(TREE_CHESTNUT, world, random, chunkX, chunkZ, 1, -1, 0);
			
			break;
		case -1: // NETHER
			break;
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, double chancesToSpawn, 
			int minHeight, int maxHeight, Class<?>... biomes) {
		if (chancesToSpawn < 1)
			chancesToSpawn = (random.nextDouble() < chancesToSpawn) ? 1 : 0;
		
		ArrayList<Class<?>> classesList = new ArrayList<Class<?>>(Arrays.asList(biomes));
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			BlockPos pos = new BlockPos(chunkX * 16 + 10 + random.nextInt(15), minHeight + random.nextInt(heightDiff), 
					chunkZ * 16 + 10 + random.nextInt(15));
			if (minHeight < 0) pos = world.getHeight(pos);
			Class<?> biome = world.provider.getBiomeForCoords(pos).getClass();
			
			if (classesList.contains(biome) || biomes.length == 0) generator.generate(world, random, pos);
		}
	}
}
