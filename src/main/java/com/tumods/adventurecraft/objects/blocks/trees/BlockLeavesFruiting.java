package com.tumods.adventurecraft.objects.blocks.trees;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockLeavesFruiting extends BlockLeavesBase {

	public static final PropertyBool CAN_FRUIT = PropertyBool.create("can_fruit");
	
	public BlockLeavesFruiting(String name) {
		super(name);
		setDefaultState(this.getDefaultState().withProperty(CAN_FRUIT, true));
	}
	
	public void setFruitByChance(World world, BlockPos pos, boolean condition) {
		world.setBlockState(pos, this.getDefaultState().withProperty(CAN_FRUIT, condition));
		System.out.println("Set canFruit: " + condition);
	}
	
	public boolean canFruit(World world, BlockPos pos) {
		if (!world.isAirBlock(pos))
			return world.getBlockState(pos).getValue(CAN_FRUIT);
		else
			return false;
	}
	
	@Override
	protected BlockStateContainer createBlockState() 
	{
		return new BlockStateContainer(this, new IProperty[] {CAN_FRUIT, CHECK_DECAY, DECAYABLE});
	}

}
