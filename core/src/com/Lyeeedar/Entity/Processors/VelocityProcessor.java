package com.Lyeeedar.Entity.Processors;

import com.Lyeeedar.Entity.Aspect;
import com.Lyeeedar.Entity.ComponentMapper;
import com.Lyeeedar.Entity.ComponentType;
import com.Lyeeedar.Entity.Entity;
import com.Lyeeedar.Entity.Processor;
import com.Lyeeedar.Entity.Components.Position;
import com.Lyeeedar.Entity.Components.Velocity;
import com.badlogic.gdx.math.Vector3;

public class VelocityProcessor extends Processor
{
	private ComponentMapper<Position> pm;
	private ComponentMapper<Velocity> vm;
	
	private final Vector3 tmp = new Vector3();
	
	public VelocityProcessor()
	{
		super(Aspect.getAspectForAll(Position.class, Velocity.class), 1);
	}

	@Override
	public void process(Entity e, float delta)
	{
		Position p = pm.get(e);
		Velocity v = vm.get(e);
		
		tmp.set(v.velocity).scl(delta);
		
		if (tmp.x != 0 || tmp.y != 0 || tmp.z != 0)
		{
			p.position.add(tmp);
			world.entityChanged(e);
		}
	}

	@Override
	protected void obtainMappers()
	{
		pm = world.getMapper(Position.class);
		vm = world.getMapper(Velocity.class);
	}

}
