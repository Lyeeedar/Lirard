package com.Lyeeedar.Entity;

import com.badlogic.gdx.utils.Array;

/**
 * This class represents the entire system of Entities and Components.
 * @author Philip Collin
 *
 */
public class EntityWorld 
{
	ComponentManager cm;
	EntityManager em;
	Array<Processor> processors;
	
	public EntityWorld()
	{
		cm = new ComponentManager();
		em = new EntityManager();
		processors = new Array<Processor>(false, 8);
	}
	
	public void linkEntityComponent(Entity e, Component c)
	{
		cm.addComponent(e, ComponentType.getTypeFor(c.getClass()), c);
	}
	
	public Entity createEntity()
	{
		return em.createEntityInstance(this);
	}
	
	public <c extends Component> ComponentMapper<c> getMapper(ComponentType<c> type)
	{
		return cm.getComponentsByType(type);
	}
	
	public <c extends Component> ComponentMapper<c> getMapper(Class<c> clss)
	{
		ComponentType<c> type = ComponentType.getTypeFor(clss);
		return cm.getComponentsByType(type);
	}
	
	public void process(float delta)
	{
		int esize = em.entities.size;
		int psize = processors.size;
		for (int i = 0; i < esize; i++)
		{
			Entity e = em.entities.get(i);
			if (e == null) continue;
			
			for (int j = 0; j < psize; j++)
			{
				Processor p = processors.get(j);
				if (p.aspect.check(e.componentMask))
				{
					p.process(e, delta);
				}
			}
		}
	}
}
