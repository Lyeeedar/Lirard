package com.Lyeeedar.Util;

public class BitMask
{
	private int mask = 0;
	
	public BitMask()
	{
		
	}
	
	public void set(int index)
	{
		mask |= 1<<index;
	}
	
	public boolean check(int index)
	{
		int flag = 1<<index;
		return (mask & flag) == flag;
	}
	
	public boolean intersect(BitMask mask)
	{
		return (this.mask & mask.mask) > 0;
	}
	
	public boolean compare(BitMask mask)
	{
		return (this.mask & mask.mask) == mask.mask;
	}
	
	public boolean isEmpty()
	{
		return mask == 0;
	}
	
	public void clear(int index)
	{
		int flag = 1<<index;
		mask &= ~flag;
	}
	
	public void clear()
	{
		mask = 0;
	}
}
