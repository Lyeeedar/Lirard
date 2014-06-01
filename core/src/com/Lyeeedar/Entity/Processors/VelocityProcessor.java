package com.Lyeeedar.Entity.Processors;

import com.Lyeeedar.Entity.Aspect;
import com.Lyeeedar.Entity.ComponentMapper;
import com.Lyeeedar.Entity.ComponentType;
import com.Lyeeedar.Entity.Entity;
import com.Lyeeedar.Entity.Processor;
import com.Lyeeedar.Entity.Components.Position;
import com.Lyeeedar.Entity.Components.Velocity;

public class VelocityProcessor extends Processor
{
	ComponentMapper<Position> pm;
	ComponentMapper<Velocity> vm;
	
	public VelocityProcessor()
	{
		super(Aspect.getAspectForAll(Position.class, Velocity.class));
	}

	@Override
	public void process(Entity e, float delta)
	{
		Position p = pm.get(e);
		Velocity v = vm.get(e);
		
		p.position.add(v.velocity.x*delta, v.velocity.y*delta, v.velocity.z*delta);
	}

	@Override
	protected void obtainMappers()
	{
		pm = world.getMapper(Position.class);
		vm = world.getMapper(Velocity.class);
	}

}
