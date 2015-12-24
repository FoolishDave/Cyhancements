package com.foolishdave.cyhancement.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.foolishdave.cyhancement.enhancements.Enhancement;
import com.foolishdave.cyhancement.enhancements.Enhancements;
import com.foolishdave.cyhancement.handler.PlayerHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerHelper {

	public static EntityPlayer getSPPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public static void addEnhancement(EntityPlayer player, Enhancement enhance)
	{
		if (player instanceof EntityPlayerMP)
		{
			CyhancedPlayer cyProps = CyhancedPlayer.get(player);
		
			if (cyProps.addEnhancement(enhance))
			{
				PlayerHandler.applyInitialEffects(player);
			}
			
		}
		//PlayerHandler.removeInitialEffects(player);
		
		//List<Enhancement> currentEnhancements = getPlayerEnhancementList(player);
		//currentEnhancements.add(enhance);
		
		//List<String> enhancementStrings = new ArrayList<String>();
		///for (Enhancement e : currentEnhancements)
		//{
		//	System.out.println("Added enhancement with id: " + e.getId() + " to player.");
		//	enhancementStrings.add(e.getId());
		//}
		
		//savePlayerEnhancementList(enhancementStrings,player);
		
	}
	
	public static void savePlayerEnhancementList(List<String> list)
	{
		savePlayerEnhancementList(list,getSPPlayer());
	}
	
	
	public static void savePlayerEnhancementList(List<String> list, EntityPlayer player)
	{
		NBTTagCompound pData = player.getEntityData();
	
		NBTTagList enhancementList = new NBTTagList();
		
		int i = 0;
		for (String s : list)
		{
			NBTTagCompound tag = new NBTTagCompound();
			tag.setString("Enhancement " + i, s);
			enhancementList.appendTag(tag);
			i++;
		}
		
		pData.setTag("Enhancements", enhancementList);
	}
	
	public static List<Enhancement> getPlayerEnhancementList()
	{
		return getPlayerEnhancementList(getSPPlayer());
	}
	
	public static List<Enhancement> getPlayerEnhancementList(EntityPlayer player)
	{
		NBTTagCompound data = player.getEntityData();
		NBTBase tagHolder = data.getTag("Enhancements");
		NBTTagList enhancementList = (NBTTagList) tagHolder;
		
		List<Enhancement> list = new ArrayList<Enhancement>();
		
		if (enhancementList != null)
		{
			for (int i = 0; i < enhancementList.tagCount(); i++)
			{
				String enhancementString = enhancementList.getStringTagAt(i);
				int tagStartPoint = enhancementString.indexOf("\"") + 1;
				int tagEndPoint = enhancementString.lastIndexOf("\"");
			
				Enhancement eToAdd = Enhancements.instance.getEnhancementFromID(enhancementString.substring(tagStartPoint, tagEndPoint));
			
				list.add(eToAdd);
			}
		}
		return list;
	}
	
}
