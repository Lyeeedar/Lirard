package com.Lyeeedar.Entity;

import com.Lyeeedar.Util.Bag;

/**
 * A component array wrapper.
 * @author Philip Collin
 *
 * @param <C>
 */
public class ComponentMapper<C extends Component> {

	Bag<C> components;
	
	public ComponentMapper()
	{
		components = new Bag<C>();
	}
	
	public void clear(int index)
	{
		components.ensureCapacity(index);
		components.set(index, null);
	}
	
	public void set(int index, C c)
	{
		components.ensureCapacity(index);
		components.set(index, c);
	}
	
	public C get(int index)
	{
		components.ensureCapacity(index);
		return components.get(index);
	}
	
	public C get(Entity e)
	{
		components.ensureCapacity(e.id);
		return components.get(e.id);
	}
}
