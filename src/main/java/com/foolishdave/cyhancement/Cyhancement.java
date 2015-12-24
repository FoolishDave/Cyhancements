package com.foolishdave.cyhancement;

import com.foolishdave.cyhancement.core.proxy.CommonProxy;
import com.foolishdave.cyhancement.library.LibStore;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;

@Mod(modid = LibStore.MOD_ID, name = LibStore.MOD_NAME, version = LibStore.MOD_VERSION)
public class Cyhancement {

	@Instance(LibStore.MOD_ID)
	public static Cyhancement instance;
	
	public static CommonProxy proxy = new CommonProxy();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		//TODO ADD CHECKS FOR COMPATIBLE MODS
		proxy.preInit(event);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverAboutStart(FMLServerAboutToStartEvent event)
	{
		proxy.serverAboutStart(event);
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{
		proxy.serverStart(event);
	}
	
	@EventHandler
	public void serverStopping(FMLServerStoppingEvent event)
	{
		proxy.serverStopping(event);
	}
}
