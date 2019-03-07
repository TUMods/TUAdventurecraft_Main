package com.tumods.adventurecraft.objects.blocks.plants;

import java.util.Random;

import com.tumods.adventurecraft.init.BlockInit;
import com.tumods.adventurecraft.init.ItemInit;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCropSaplingCherry extends BlockCrops implements IGrowable {
	public static final PropertyInteger CHERRY_SAPLING_AGE = PropertyInteger.create("age", 0, 3);
	
	private static final AxisAlignedBB[] CHERRY_SAPLING_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.125D, 0.75D), // Stage 0
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.25D, 0.75D),  // Stage 1
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.375D, 0.75D), // Stage 2
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.5D, 0.75D)    // Stage 3
//			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.625D, 0.75D), // Stage 4
//			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.625D, 0.75D)  // Stage 5
//			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), // Stage 7
//			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)    // Stage 8
			};
	
	public BlockCropSaplingCherry(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		
		if (!worldIn.isRemote && this.isMaxAge(state))
			worldIn.setBlockState(pos, BlockInit.SAPLING_CHERRY.getDefaultState());
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(getAgeProperty()) == 3)
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), 
				new ItemStack(BlockInit.SAPLING_CHERRY, 1)));
		else
			worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), 
					new ItemStack(ItemInit.SEED_CHERRY, 1)));
	}
	
	@Override
	protected PropertyInteger getAgeProperty() {
		return CHERRY_SAPLING_AGE;
	}
	
	@Override
	public int getMaxAge() {
		return 3;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {CHERRY_SAPLING_AGE});
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return CHERRY_SAPLING_AABB[((Integer)state.getValue(getAgeProperty())).intValue()];
	}
}
