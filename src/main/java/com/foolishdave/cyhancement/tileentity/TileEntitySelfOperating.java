package com.foolishdave.cyhancement.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntitySelfOperating extends TileEntity implements IInventory {

	private ItemStack[] inventory;
	private final String name = "cyhancement.selfoperatingtable";
	
	public TileEntitySelfOperating()
	{
		inventory = new ItemStack[9];
	}
	
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// TODO Auto-generated method stub
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack curStack = getStackInSlot(slot);
		if (curStack != null)
		{
			if (curStack.stackSize <= amount)
			{
				setInventorySlotContents(slot,null);
			} else
			{
				curStack = curStack.splitStack(amount);
				if (curStack.stackSize == 0)
				{
					setInventorySlotContents(slot,null);
				}
			}
		}
		return curStack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack curStack = getStackInSlot(slot);
		if (curStack != null)
		{
			setInventorySlotContents(slot,null);
		}
		return curStack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		ItemStack inStack = inventory[slot];
		inStack.copyItemStack(stack);
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		// TODO Auto-generated method stub
		return worldObj.getTileEntity(xCoord,yCoord,zCoord) == this && player.getDistanceSq(xCoord + 0.5,yCoord + 0.5,zCoord + 0.5)<64;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return true;
	}

}
