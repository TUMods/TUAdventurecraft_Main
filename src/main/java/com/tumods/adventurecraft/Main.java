package com.tumods.adventurecraft;

import com.tumods.adventurecraft.init.RecipeInit;
import com.tumods.adventurecraft.proxy.CommonProxy;
import com.tumods.adventurecraft.tabs.BlockTab;
import com.tumods.adventurecraft.tabs.ItemTab;
import com.tumods.adventurecraft.util.Reference;
import com.tumods.adventurecraft.util.handlers.RegistryHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	@Instance
	public static Main instance;
	
	// Creative Tabs
	public static final ItemTab itemTab = new ItemTab("itemtab");
	public static final BlockTab blocktab = new BlockTab("blocktab");
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		RegistryHandler.otherRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {

	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		System.out.println("\n\n#########################\n\n\t\tPOST INIT\n\n#########################\n\n");
		RecipeInit.unregisterRecipes();
	}
}
