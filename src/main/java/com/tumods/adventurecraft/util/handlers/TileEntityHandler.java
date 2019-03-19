package com.tumods.adventurecraft.util.handlers;

import com.tumods.adventurecraft.objects.blocks.machines.bloomery.TileEntityBloomery;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBloomery.class, "bloomery");
	}
}
