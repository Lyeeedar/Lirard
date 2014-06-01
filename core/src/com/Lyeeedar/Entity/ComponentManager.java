package com.Lyeeedar.Entity;

import java.util.BitSet;

import com.Lyeeedar.Util.Bag;

/**
 * This class manages component mappers and provides a simple interface for extracting them.
 * @author Philip Collin
 *
 */
public class ComponentManager
{
	private Bag<ComponentMapper<?>> componentsByType;

	public ComponentManager()
	{
		componentsByType = new Bag<ComponentMapper<?>>();
	}

	public void removeComponentsOfEntity(Entity e)
	{
		BitSet componentBits = e.componentMask;
		for (int i = componentBits.nextSetBit(0); i >= 0; i = componentBits.nextSetBit(i + 1))
		{
			componentsByType.get(i).set(e.id, null);
		}
		componentBits.clear();
	}

	public <c extends Component> void addComponent(Entity e, ComponentType<c> type, c component)
	{
		ComponentMapper<c> components = getComponentsByType(type);

		components.set(e.id, component);

		e.componentMask.set(type.index);
	}

	public void removeComponent(Entity e, ComponentType<?> type)
	{
		if (e.componentMask.get(type.index))
		{
			componentsByType.get(type.index).set(e.id, null);
			e.componentMask.clear(type.index);
		}
	}

	public <c extends Component> ComponentMapper<c> getComponentsByType(ComponentType<c> type)
	{		
		componentsByType.ensureCapacity(type.index);
		
		ComponentMapper<c> components = (ComponentMapper<c>) componentsByType.get(type.index);
		if (components == null)
		{
			components = type.mapperFromType();
			componentsByType.set(type.index, components);
		}
		
		return components;
	}

	public <c extends Component> c getComponent(Entity e, ComponentType<c> type)
	{
		ComponentMapper<c> components = (ComponentMapper<c>) componentsByType.get(type.index);
		if (components != null) { return components.get(e.id); }
		return null;
	}
}
