package com.tumods.adventurecraft.objects.blocks.food;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCropRaspberry extends BlockCrops implements IGrowable {
	public static final PropertyInteger RASPBERRY_AGE = PropertyInteger.create("age", 0, 5);
	
	private static final AxisAlignedBB[] RASPBERRY_AABB = new AxisAlignedBB[] {
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.125D, 0.75D), // Stage 0
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.25D, 0.75D),  // Stage 1
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.375D, 0.75D), // Stage 2
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.5D, 0.75D),   // Stage 3
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.625D, 0.75D), // Stage 4
			new AxisAlignedBB(0.25D, 0.0D, 0.25D, 0.6875D, 0.625D, 0.75D)  // Stage 5
//			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D), // Stage 7
//			new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)    // Stage 8
			};
	
	public BlockCropRaspberry(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.swingArm(EnumHand.MAIN_HAND);
		if(!worldIn.isRemote) {
			if (this.isMaxAge(state)) {
				worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), 
						new ItemStack(ItemInit.PRODUCE_RASPBERRY, (new Random().nextInt(3)+1)) ));
				worldIn.setBlockState(pos, this.withAge(3));
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected Item getSeed() {
		return ItemInit.PRODUCE_RASPBERRY;
	}
	
	@Override
	protected Item getCrop() {
		return ItemInit.PRODUCE_RASPBERRY;
	}
	
	@Override
	protected PropertyInteger getAgeProperty() {
		return RASPBERRY_AGE;
	}
	
	@Override
	public int getMaxAge() {
		return 5;
	}
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {RASPBERRY_AGE});
    }
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return RASPBERRY_AABB[((Integer)state.getValue(getAgeProperty())).intValue()];
	}
}
