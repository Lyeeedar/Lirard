package com.Lyeeedar.Entity;

import java.util.HashMap;

/**
 * This class is used to assign indexes to components for array access
 * @author Philip Collin
 *
 * @param <c>
 */
public class ComponentType<c extends Component>
{
	public static int TOTAL = 0;

	public final int index;
	public final Class<c> type;

	private ComponentType(Class<c> type)
	{
		index = TOTAL++;
		this.type = type;
	}

	@Override
	public String toString()
	{
		return "ComponentType[" + type.getSimpleName() + "] (" + index + ")";
	}
	
	public ComponentMapper<c> mapperFromType()
	{
		return new ComponentMapper<c>();
	}

	private static HashMap<Class<? extends Component>, ComponentType<? extends Component>> componentTypes = new HashMap<Class<? extends Component>, ComponentType<? extends Component>>();

	public static <c extends Component> ComponentType<c> getTypeFor(Class clss)
	{
		ComponentType<c> type = (ComponentType<c>) componentTypes.get(clss);

		if (type == null)
		{
			type = new ComponentType<c>(clss);
			componentTypes.put(clss, type);
		}

		return type;
	}

	public static <c extends Component> int getIndexFor(Class<c> clss)
	{
		return getTypeFor(clss).index;
	}
}
