package com.foolishdave.cyhancement.item;

import com.foolishdave.cyhancement.enhancements.Enhancements;
import com.foolishdave.cyhancement.helper.PlayerHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHealthStick extends CyhancementItem {
	public ItemHealthStick()
	{
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		
		PlayerHelper.addEnhancement(player, Enhancements.instance.healthUp);
		
		return stack;
	}
}
