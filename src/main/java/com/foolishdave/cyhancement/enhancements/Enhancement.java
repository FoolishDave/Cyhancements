package com.foolishdave.cyhancement.enhancements;

public class Enhancement {

	private String id;
	private int healthMod = 0;
	private int jumpMod = 0;
	private int digSpeedMod = 0;
	private int digQualityMod = 0;
	private int moveBoost = 0;
		
	public Enhancement(String id)
	{
		this.id = id;
		
		//Enhancements.instance.addEnhancement(id, this);
	}
	
	public String getId()
	{
		return id;
	}
	
	public int getHealthMod()
	{
		return healthMod;
	}
	
	public void setHealthMod(int hMod)
	{
		healthMod = hMod;
	}
	
	public int getJumpMod()
	{
		return jumpMod;
	}
	
	public int getMoveBoost() {
		return moveBoost;
	}
	
	public void setJumpMod(int jMod)
	{
		jumpMod = jMod;
	}
	
	public int getDigSpeedMod()
	{
		return digSpeedMod;
	}
	
	public void setDigSpeedMod(int dSM)
	{
		digSpeedMod = dSM;
	}
	
	public int getDigQualityMod()
	{
		return digQualityMod;
	}
	
	public void setDigQualityMod(int mod)
	{
		digQualityMod = mod;
	}
	
	public void effect()
	{
		
	}

	
}
