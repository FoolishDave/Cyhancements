package com.foolishdave.cyhancement.helper;

import java.util.HashSet;
import java.util.Set;

import com.foolishdave.cyhancement.core.proxy.CommonProxy;
import com.foolishdave.cyhancement.enhancements.Enhancement;
import com.foolishdave.cyhancement.enhancements.Enhancements;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CyhancedPlayer implements IExtendedEntityProperties{

	public static String IEXT_PROP_NAME = "CyhancedPlayer";
	
	private final EntityPlayer player;
	
	private int healthBoost;
	private int mineSpeedBoost;
	private int mineQualityBoost;
	private int moveBoost;
	private Set<Enhancement> enhancementSet;
	
	public CyhancedPlayer(EntityPlayer player)
	{
		this.player = player;
		healthBoost = 0;
		mineSpeedBoost = 0;
		mineQualityBoost = 0;
		moveBoost = 0;
		enhancementSet = new HashSet<Enhancement>();
	}
	
	public int getHealthBoost()
	{
		return healthBoost;
	}
	
	public int getMineSpeedBoost()
	{
		return mineSpeedBoost;
	}
	
	public int getMineQualityBoost()
	{
		return mineQualityBoost;
	}
	
	public int getMoveBoost()
	{
		return moveBoost;
	}
	
	public Set<Enhancement> getAppliedEnhancements()
	{
		return enhancementSet;
	}
	
	public boolean addEnhancement(Enhancement en)
	{
		boolean success = false;
		if (!enhancementSet.contains(en))
		{
			enhancementSet.add(en);
			
			this.healthBoost += en.getHealthMod();
			this.mineQualityBoost += en.getDigQualityMod();
			this.mineSpeedBoost += en.getDigSpeedMod();
			this.moveBoost += en.getMoveBoost();
			success = true;
		} else
		{
			System.out.println("Player tried to double down on enhancement.");
		}
		return success;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(CyhancedPlayer.IEXT_PROP_NAME, new CyhancedPlayer(player));
	}
	
	public static final CyhancedPlayer get(EntityPlayer player)
	{
		return (CyhancedPlayer) player.getExtendedProperties(IEXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setInteger("healthBoost", healthBoost);
		properties.setInteger("mineSpeedBoost", mineSpeedBoost);
		properties.setInteger("mineQualityBoost", mineQualityBoost);
		properties.setInteger("moveBoost", moveBoost);
		properties.setInteger("numEnhancements", enhancementSet.size());
		
		NBTTagCompound enhancementCompound = new NBTTagCompound();
		int i = 0;
		for (Enhancement e : enhancementSet)
		{
			enhancementCompound.setString(String.valueOf(i), e.getId());
			i++;
		}
		
		properties.setTag("enhancementList", enhancementCompound); 
		
		
		compound.setTag(IEXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(IEXT_PROP_NAME);
		
		this.healthBoost = properties.getInteger("healthBoost");
		this.mineSpeedBoost = properties.getInteger("mineSpeedBoost");
		this.mineQualityBoost = properties.getInteger("mineQualityBoost");
		this.moveBoost = properties.getInteger("moveBoost");
		NBTTagCompound enhancementCompound = (NBTTagCompound) properties.getTag("enhancementList");
	
		for (int i = 0; i < properties.getInteger("numEnhancements");i++)
		{
			String id = enhancementCompound.getString(String.valueOf(i));
			System.out.println("Adding effect with id " + id);
			Enhancement e = Enhancements.instance.getEnhancementFromID(id);
			this.enhancementSet.add(e);
		}
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}

	private static String getSaveKey(EntityPlayer player)
	{
		return player.getDisplayName() + ":" + IEXT_PROP_NAME;
	}
	
	public static void saveProxyData(EntityPlayer player)
	{
		CyhancedPlayer cyData = CyhancedPlayer.get(player);
		NBTTagCompound savedData = new NBTTagCompound();
		
		cyData.saveNBTData(savedData);
		CommonProxy.storeEntityData(getSaveKey(player), savedData);
	}
	
	public static void loadProxyData(EntityPlayer player)
	{
		CyhancedPlayer cyData = CyhancedPlayer.get(player);
		NBTTagCompound savedData = CommonProxy.getEntityData(getSaveKey(player));
		
		if(savedData != null)
		{
			cyData.loadNBTData(savedData);
		}
		//cyData.syncProperties();
	}
}
