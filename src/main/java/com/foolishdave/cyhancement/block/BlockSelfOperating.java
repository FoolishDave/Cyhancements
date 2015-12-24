package com.foolishdave.cyhancement.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSelfOperating extends Block {

	protected BlockSelfOperating() {
		super(Material.wood);
		setBlockName("blockSelfOperatingTable");
		setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float a, float b, float c)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		return true;
	}

}
 