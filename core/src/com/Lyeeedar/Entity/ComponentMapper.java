package com.Lyeeedar.Entity;

import com.Lyeeedar.Util.Bag;

/**
 * A component array wrapper.
 * @author Philip Collin
 *
 * @param <C>
 */
public class ComponentMapper<C extends Component> {

	public final Bag<C> components;
	public final ComponentType<C> type;
	
	public ComponentMapper(ComponentType<C> type)
	{
		components = new Bag<C>();
		this.type = type;
	}
	
	public void clear(int index)
	{
		if (index >= components.getCapacity()) return;
		components.set(index, null);
	}
	
	public void set(int index, C c)
	{
		components.ensureCapacity(index);
		components.set(index, c);
	}
	
	public C get(int index)
	{
		if (index >= components.getCapacity()) return null;
		return components.get(index);
	}
	
	public C get(Entity e)
	{
		if (e.id >= components.getCapacity()) return null;
		return components.get(e.id);
	}
}
