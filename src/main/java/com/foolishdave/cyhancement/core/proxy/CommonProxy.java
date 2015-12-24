package com.foolishdave.cyhancement.core.proxy;

import java.util.HashMap;
import java.util.Map;

import com.foolishdave.cyhancement.core.load.CyhancementItems;
import com.foolishdave.cyhancement.enhancements.Enhancements;
import com.foolishdave.cyhancement.handler.PlayerHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	
	private static final Map<String, NBTTagCompound> CyhancedEntityData = new HashMap<String, NBTTagCompound>();
	
	
	public void preInit(FMLPreInitializationEvent event)
	{
		CyhancementItems.preInit(event);
	}
	
	public void init(FMLInitializationEvent event)
	{
		
		Enhancements.loadEnhancements();
		
		
		MinecraftForge.EVENT_BUS.register(new PlayerHandler());
		MinecraftForge.TERRAIN_GEN_BUS.register(new PlayerHandler());
		MinecraftForge.ORE_GEN_BUS.register(new PlayerHandler());
		FMLCommonHandler.instance().bus().register(new PlayerHandler());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void serverAboutStart(FMLServerAboutToStartEvent event)
	{
		
	}
	
	public void serverStart(FMLServerStartingEvent event)
	{
		
	}
	
	public void serverStopping(FMLServerStoppingEvent event)
	{
		
	}
	
	public static void storeEntityData(String name, NBTTagCompound compound)
	{
		CyhancedEntityData.put(name, compound);
	}
	
	public static NBTTagCompound getEntityData(String name)
	{
		return CyhancedEntityData.remove(name);
	}
}
