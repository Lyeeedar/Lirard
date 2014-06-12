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
	
	public final void clear(final int index)
	{
		if (index >= components.getCapacity()) return;
		components.set(index, null);
	}
	
	public final void set(final int index, final C c)
	{
		components.ensureCapacity(index);
		components.set(index, c);
	}
	
	public final C get(final int index)
	{
		if (index >= components.getCapacity()) return null;
		return components.get(index);
	}
	
	public final C get(final Entity e)
	{
		if (e.id >= components.getCapacity()) return null;
		return components.get(e.id);
	}
}
