package com.foolishdave.cyhancement.enhancements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foolishdave.cyhancement.helper.PlayerHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;

public class Enhancements {
	public static Enhancements instance = new Enhancements();
	private static Map<String,Enhancement> enhanceMap = new HashMap<String, Enhancement>();
	
	public static EnhancementHealthUp healthUp;
	public static EnhancementDigUp digUp;
	public static EnhancementQualityUp qualityUp;
	
	public Enhancements()
	{
		
	}
	
	public static void loadEnhancements()
	{
		healthUp = new EnhancementHealthUp("healthUp");
		digUp = new EnhancementDigUp("digUp");
		qualityUp = new EnhancementQualityUp("qualityUp");
		
		enhanceMap.put(healthUp.getId(), healthUp);
		enhanceMap.put(digUp.getId(), digUp);
		enhanceMap.put(qualityUp.getId(), qualityUp);
	}
	
	public Enhancement getEnhancementFromID(String id)
	{
		if (enhanceMap.containsKey(id))
			return enhanceMap.get(id);
		
		System.out.println("Failed to retreive an enhancement with id " + id);
		return null;
	}
	
	public void addEnhancement(String id, Enhancement en)
	{
		enhanceMap.put(id, en);
	}
	
	
}
