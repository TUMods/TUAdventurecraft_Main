package com.tumods.adventurecraft.world.generation.generators;

import java.util.Random;

import com.tumods.adventurecraft.init.BlockInit;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;

public class WorldGenTreeCherry extends WorldGenAbstractTree {

	private static final IBlockState LOG = BlockInit.LOG_CHERRY.getDefaultState();
	private static final IBlockState LEAF = BlockInit.LEAVES_CHERRY.getDefaultState();
	
	private final int minHeight;
	private final int heightVariation;
	
	public WorldGenTreeCherry() {
		super(false);
		this.minHeight = 4;
		this.heightVariation = 3;
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int height = this.minHeight + rand.nextInt(this.heightVariation);
		boolean flag = true;
		
		int x = position.getX(), y = position.getY(), z = position.getZ();
		
		for (int yPos = y; yPos <= y + 1 + height; yPos++) {
			int b0 = 2;
			if (yPos == y) b0 = 1;
			if (yPos >= y + 1 + height - 2) b0 = 2;
			
			BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
			
			for (int xPos = x - b0; xPos <= x + b0 && flag; xPos++) {
				for (int zPos = z - b0; zPos <= z + b0 && flag; zPos++) {
					if (yPos >= 0 && yPos < worldIn.getHeight()) {
						if (!this.isReplaceable(worldIn, new BlockPos(xPos, yPos, zPos)))
							flag = false;
					}
					else
						flag = false;
				}
			}
		}
		
		if (flag)
			return false;
		else {
			BlockPos down = position.down();
			IBlockState state = worldIn.getBlockState(down);
			boolean isSoil = state.getBlock().canSustainPlant(state, worldIn, down, EnumFacing.UP, (IPlantable)Blocks.SAPLING);
			
			if (isSoil && y < worldIn.getHeight() - height - 1) {
				state.getBlock().onPlantGrow(state, worldIn, down, position);
				
				for (int yPos = y - 3 + height; yPos <= y + height; yPos++) {
					int b1 = yPos - (y + height);
					int b2 = 1 - b1 / 2;
					
					for (int xPos = x - b2; xPos <= x + b2; xPos++) {
						int b3 = xPos - x;
						for (int zPos = z - b2; zPos <= z + b2; zPos++) {
							int b4 = zPos - z;
							if (Math.abs(b3) != b2 || Math.abs(b4) != b2 || rand.nextInt(2) != 00 && b1 != 0) {
								BlockPos treePos = new BlockPos(xPos, yPos, zPos);
								IBlockState treeState = worldIn.getBlockState(treePos);
								
								if (treeState.getBlock().isAir(treeState, worldIn, treePos) 
										|| treeState.getBlock().isAir(treeState, worldIn, treePos)) {
									this.setBlockAndNotifyAdequately(worldIn, treePos, LEAF);
//									this.setBlockAndNotifyAdequately(worldIn, treePos.add(0, -0.25 * height, 0), LEAF);
								}
							}
						}
					}
				}
				
				for (int logHeight = 0; logHeight < height; logHeight++) {
					BlockPos up = position.up(logHeight);
					IBlockState logState = worldIn.getBlockState(up);
					Block logBlock = logState.getBlock();
					
					if (logBlock.isAir(logState, worldIn, up) || logBlock.isLeaves(logState, worldIn, up))
						this.setBlockAndNotifyAdequately(worldIn, position.up(logHeight), LOG);
				}
				
				return true;
			}
		}
		
		return true;
	}
	
	@Override
	protected boolean canGrowInto(Block blockType) {
		Material material = blockType.getDefaultState().getMaterial();
		return material == Material.AIR || material == Material.LEAVES || material == Material.GROUND 
				|| blockType == Blocks.GRASS || blockType == Blocks.DIRT || blockType == Blocks.LOG || blockType == Blocks.LOG2
				|| blockType == Blocks.SAPLING || blockType == Blocks.VINE || blockType == Blocks.FARMLAND;
	}

}
