package com.foolishdave.cyhancement.block;

import java.util.Random;

import com.foolishdave.cyhancement.tileentity.TileEntitySelfOperating;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		return true;
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		dropItems(world,x,y,z);
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	private void dropItems(World world, int x, int y, int z)
	{
		Random ran = new Random();
		
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory))
			return;
		
		IInventory inventory = (IInventory) tileEntity;
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);
			if (item != null && item.stackSize > 0)
			{
				float ranX = ran.nextFloat() * 0.8f + 0.1f;
				float ranY = ran.nextFloat() * 0.8f + 0.1f;
				float ranZ = ran.nextFloat() * 0.8f + 0.1f;
				
				EntityItem entityItem = new EntityItem(world,
						x + ranX, y + ranY, z + ranZ,
						item.copy());
				
				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}
				
				float factor = 0.05f;
				entityItem.motionX = ran.nextGaussian() * factor;
				entityItem.motionY = ran.nextGaussian() * factor + 0.2f;
				entityItem.motionZ = ran.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntitySelfOperating();
	}
	
}
 