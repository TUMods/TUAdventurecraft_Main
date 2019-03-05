package com.tumods.adventurecraft.objects.blocks.trees;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.BlockInit;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.util.IHasModel;

import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class BlockLogBase extends BlockLog implements IHasModel {
	
	public BlockLogBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.blocktab);
		setHardness(2.0F);
		setSoundType(SoundType.WOOD);
		
		setDefaultState(this.blockState.getBaseState()
				.withProperty(LOG_AXIS, EnumAxis.Y));
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState state = this.getDefaultState();
		switch(meta & 12) {
		case 0:
			state = state.withProperty(LOG_AXIS, EnumAxis.Y);
			break;
		case 4:
			state = state.withProperty(LOG_AXIS, EnumAxis.X);
			break;
		case 8:
			state = state.withProperty(LOG_AXIS, EnumAxis.Z);
			break;
		default:
			state = state.withProperty(LOG_AXIS, EnumAxis.NONE);
			break;
		}
		return state;
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		
		switch((BlockLog.EnumAxis)state.getValue(LOG_AXIS)) {
		case X:
			i |= 4;
			break;
		case Y:
			i |= 8;
			break;
		case Z:
			i |= 12;
			break;
		}
		return i;
	}
	
	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this);
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {LOG_AXIS});
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
}
