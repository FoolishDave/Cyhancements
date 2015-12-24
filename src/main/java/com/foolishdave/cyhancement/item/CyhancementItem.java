package com.foolishdave.cyhancement.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CyhancementItem extends Item {
	public CyhancementItem()
	{
		super();
		//setCreativeTab(CyhancementCreativeTab.INSTANCE);
	}
	
	@Override
	public Item setUnlocalizedName(String par1Str)
	{
		GameRegistry.registerItem(this, par1Str);
		return super.setUnlocalizedName(par1Str);
	}
}
