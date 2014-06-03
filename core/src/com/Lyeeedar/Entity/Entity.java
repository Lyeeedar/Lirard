package com.Lyeeedar.Entity;

import java.util.BitSet;

import com.Lyeeedar.Util.BitMask;

/**
 * An entity. This simply acts as an index in the component arrays, and also contains a bitmask to easily identify the assigned components.
 * @author Philip Collin
 *
 */
public class Entity {
	public long UUID;
	public int id;
	
	public final BitMask componentMask;
	
	private final EntityWorld world;
	
	public Entity(EntityWorld world)
	{
		this.world = world;
		componentMask = new BitMask();
	}
	
	public void addComponent(Component c)
	{
		world.linkEntityComponent(this, c);
	}
	
	public <c extends Component> c  getComponent(ComponentType<c> type)
	{
		return world.cm.getComponent(this, type);
	}
	
	public <c extends Component> c getComponent(Class<c> clss)
	{
		return (c) getComponent(ComponentType.getTypeFor(clss));
	}
}
