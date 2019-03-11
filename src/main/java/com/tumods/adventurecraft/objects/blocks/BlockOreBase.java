package com.tumods.adventurecraft.objects.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockOreBase extends BlockBase {
	
	private final Item customDrop;
	private final int maxQuantityDropped;
	private final int xpDropped;
	
	public BlockOreBase(String name, Material material) {
		super(name, material);
		this.customDrop = null;
		this.maxQuantityDropped = -1;
		this.xpDropped = -1;
	}
	
	public BlockOreBase(String name, Material material, Item customDrop, int harvestLevel, float hardness, int maxQuantityDropped, int xpDropped) {
		super(name, material);
		this.customDrop = customDrop;
		this.maxQuantityDropped = maxQuantityDropped;
		this.xpDropped = xpDropped;
		setHarvestLevel("pickaxe", harvestLevel);
		setHardness(hardness);
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state,
			int fortune) {
		drops.add(new ItemStack(this.customDrop, (maxQuantityDropped == -1) ? 1 : MathHelper.getInt(new Random(), 1, maxQuantityDropped)));
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		return (xpDropped == -1) ? super.getExpDrop(state, world, pos, fortune) : xpDropped;
	}

}
