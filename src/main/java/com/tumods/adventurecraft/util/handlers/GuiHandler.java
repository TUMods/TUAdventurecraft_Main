package com.tumods.adventurecraft.util.handlers;

import com.tumods.adventurecraft.objects.blocks.machines.bloomery.ContainerBloomery;
import com.tumods.adventurecraft.objects.blocks.machines.bloomery.GuiBloomery;
import com.tumods.adventurecraft.objects.blocks.machines.bloomery.TileEntityBloomery;
import com.tumods.adventurecraft.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_BLOOMERY) return new ContainerBloomery(player.inventory, (TileEntityBloomery)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_BLOOMERY) return new GuiBloomery(player.inventory, (TileEntityBloomery)world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}
}
