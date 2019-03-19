package com.tumods.adventurecraft.util.handlers;

import com.tumods.adventurecraft.Main;
import com.tumods.adventurecraft.init.BlockInit;
import com.tumods.adventurecraft.init.ItemInit;
import com.tumods.adventurecraft.init.RecipeInit;
import com.tumods.adventurecraft.util.IHasModel;
import com.tumods.adventurecraft.world.generation.WorldGenCustomOres;
import com.tumods.adventurecraft.world.generation.WorldGenCustomStructures;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : ItemInit.ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
		
		for (Block block : BlockInit.BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries() {
		System.out.println("\n\n#########################\n\n\t\tPRE INIT\n\n#########################\n\n");
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
//		GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
		GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);
	}
	
	public static void initRegistries() {
		System.out.println("\n\n#########################\n\n\t\tINIT\n\n#########################\n\n");
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegistries() {
		System.out.println("\n\n#########################\n\n\t\tPOST INIT\n\n#########################\n\n");
		RecipeInit.unregisterRecipes();
	}
}
