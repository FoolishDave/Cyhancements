package com.foolishdave.cyhancement.core.load;

import com.foolishdave.cyhancement.item.ItemHealthStick;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CyhancementItems {

	public static ItemHealthStick itemHealthStick;
	
	public static void preInit(FMLPreInitializationEvent event)
	{
		itemHealthStick = new ItemHealthStick();
		
		
		
		GameRegistry.registerItem(itemHealthStick, "healthStick");
	}
	
}
