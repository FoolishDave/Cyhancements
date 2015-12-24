package com.foolishdave.cyhancement.handler;

import java.util.List;

import com.foolishdave.cyhancement.core.proxy.CommonProxy;
import com.foolishdave.cyhancement.enhancements.Enhancement;
import com.foolishdave.cyhancement.enhancements.Enhancements;
import com.foolishdave.cyhancement.helper.CyhancedPlayer;
import com.foolishdave.cyhancement.helper.PlayerHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

public class PlayerHandler {

	@SubscribeEvent
	public void playerDie(LivingDeathEvent e)
	{
		if (!e.entity.worldObj.isRemote && e.entity instanceof EntityPlayer)
		{
			NBTTagCompound cyhancedData = new NBTTagCompound();
			removeInitialEffects((EntityPlayer) e.entityLiving);
			((CyhancedPlayer)(e.entity.getExtendedProperties(CyhancedPlayer.IEXT_PROP_NAME))).saveNBTData(cyhancedData);
			CommonProxy.storeEntityData(((EntityPlayer)e.entity).getDisplayName(), cyhancedData);
		}
	}
	
	@SubscribeEvent
	public void playerRevive(PlayerEvent.PlayerRespawnEvent e)
	{
		if (e.player instanceof EntityPlayerMP)
		{
			CyhancedPlayer cyProp = CyhancedPlayer.get(e.player);
		
			NBTTagCompound cyhancedData = CommonProxy.getEntityData(e.player.getDisplayName());
		
			if (cyhancedData != null)
			{
				e.player.getExtendedProperties(CyhancedPlayer.IEXT_PROP_NAME).loadNBTData(cyhancedData);
			}
		
			System.out.println("Player " + e.player.getDisplayName() + " has health boost " + cyProp.getHealthBoost());
		
			applyInitialEffects(e.player);
			e.player.heal(100000f);
		}
	}
	
	@SubscribeEvent
	public void entityConstructing(EntityConstructing e)
	{
		if (e.entity instanceof EntityPlayer && CyhancedPlayer.get((EntityPlayer) e.entity) == null)
		{
			CyhancedPlayer.register((EntityPlayer) e.entity);
		}
		
	}
	
	@SubscribeEvent
	public void blockBreakEvent(BreakEvent e)
	{
		//System.out.println("Block was broken");
		//PlayerHelper.addEnhancement(e.getPlayer(), Enhancements.instance.healthUp);
		//PlayerHelper.addEnhancement(e.getPlayer(), Enhancements.instance.digUp);
		//PlayerHelper.addEnhancement(e.getPlayer(), Enhancements.instance.qualityUp);
	}
	
	
	@SubscribeEvent
	public void joinWorldEvent(PlayerEvent.PlayerLoggedInEvent e)
	{
		
		if (e.player instanceof EntityPlayer && e.player != null)
		{
			System.out.println("A player joined the world.");

			System.out.println("Applied effects to player.");
			
			removeInitialEffects(e.player);
			applyInitialEffects(e.player);
			
		}
	}
	
	@SubscribeEvent
	public void digEnhancement(BreakSpeed event)
	{
		EntityPlayer playerEntity = null;
		
		if (event.entityLiving instanceof EntityPlayer && event.entityLiving != null)
		{
			playerEntity = (EntityPlayer) event.entityLiving;
			CyhancedPlayer cyProps = CyhancedPlayer.get((EntityPlayer)event.entityLiving);
			//List<Enhancement> enhancementList = PlayerHelper.getPlayerEnhancementList(playerEntity);
			int totalDigSpeedMod = cyProps.getMineSpeedBoost();
			
			playerEntity = (EntityPlayer) event.entityLiving;
			if (playerEntity != null)
			{
				event.newSpeed += event.originalSpeed + totalDigSpeedMod;
			}
			
		}
	}
	
	@SubscribeEvent
	public void checkHarvest(HarvestCheck event)
	{
		//List<Enhancement> enhancementList = PlayerHelper.getPlayerEnhancementList(event.entityPlayer);
		int harvestLevel = event.block.getHarvestLevel(event.block.getIdFromBlock(event.block));
		CyhancedPlayer cyProps = CyhancedPlayer.get(event.entityPlayer);
		int totalDigQualityMod = cyProps.getMineQualityBoost();
		
		if (totalDigQualityMod >= harvestLevel)
			event.success = true;
	}
	
	
	public static void applyInitialEffects(EntityPlayer player)
	{
		System.out.println("Applying basic effects to player " + player.getDisplayName());
		//List<Enhancement> enhancementList = PlayerHelper.getPlayerEnhancementList(player);
		
		CyhancedPlayer cyProps = CyhancedPlayer.get(player);
		
		//System.out.println("Player has list of enhancements of length " + enhancementList.size());
		
		int totalHealthMod = cyProps.getHealthBoost();
		int totalMoveMod = cyProps.getMineQualityBoost();
		int totalDigSpeedMod = cyProps.getMineSpeedBoost();
		int totalDigQualityMod = cyProps.getMineQualityBoost();
		
		player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(player.getMaxHealth()+totalHealthMod);
		player.heal(totalHealthMod);
	}
	
	public static void removeInitialEffects(EntityPlayer player)
	{
		//List<Enhancement> enhancementList = PlayerHelper.getPlayerEnhancementList(player);
		
		CyhancedPlayer cyProps = CyhancedPlayer.get(player);
		
		int totalHealthMod = cyProps.getHealthBoost() * -1;
		int totalMoveMod = cyProps.getMineQualityBoost() * -1;
		int totalDigSpeedMod = cyProps.getMineSpeedBoost() * -1;
		int totalDigQualityMod = cyProps.getMineQualityBoost() * -1;
		
		
		player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(player.getMaxHealth()+totalHealthMod);
	}
}
