package com.tumods.adventurecraft.objects.blocks.plants;

import java.util.Random;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.BlockInit;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;
import com.tumods.adventurecraft.world.generation.generators.WorldGenTreeCherry;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

public class BlockSaplingCherry extends BlockBush implements IGrowable, IHasModel {
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
	protected static final AxisAlignedBB SAPLING_AABB = 
			new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
	
	public BlockSaplingCherry(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setSoundType(SoundType.PLANT);
		setDefaultState(this.blockState.getBaseState()
				.withProperty(STAGE, Integer.valueOf(0)));
		
//		setCreativeTab(Main.blocktab);
		setTickRandomly(true);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        if (((Integer)state.getValue(STAGE)).intValue() == 0)
        {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        }
        else
        {
            this.generateTree(worldIn, pos, state, rand);
        }
    }
	
	public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
		System.out.println("SaplingCherry generate Tree saplingGrowTree");
		WorldGenerator gen = new WorldGenTrees(false);//(rand.nextInt(10) == 0 ? new WorldGenBigTree(false) : new WorldGenTrees(false));
		int i = 0, j = 0; // used for big trees
		boolean flag = false; // used for big trees
		
		gen = new WorldGenTreeCherry();
		
		IBlockState iBlockstate = Blocks.AIR.getDefaultState();
		if (flag) {
			// is big tree
			worldIn.setBlockState(pos.add(i, 0, j), iBlockstate, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j), iBlockstate, 4);
			worldIn.setBlockState(pos.add(i, 0, j + 1), iBlockstate, 4);
			worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iBlockstate, 4);
		}
		else
		{
			System.out.println("Change sapling to air block");
			worldIn.setBlockState(pos, iBlockstate, 4);
		}
			
		
		if (!gen.generate(worldIn, rand, pos.add(i, 0, j))) {
			if (flag) {
				// is big tree
				worldIn.setBlockState(pos.add(i, 0, j), iBlockstate, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j), iBlockstate, 4);
				worldIn.setBlockState(pos.add(i, 0, j + 1), iBlockstate, 4);
				worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iBlockstate, 4);
			}
			else
				worldIn.setBlockState(pos, iBlockstate, 4);
		}
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		return (double)worldIn.rand.nextFloat() < 0.45D;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		return true;
	}
	
	@Override
	protected boolean canSustainBush(IBlockState state) {
		return super.canSustainBush(state);
	}
	
	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState()
        		.withProperty(STAGE, Integer.valueOf((meta & 8) >> 3));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((Integer)state.getValue(STAGE)).intValue() << 3;
        return i;
    }
	
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {STAGE});
    }

}
