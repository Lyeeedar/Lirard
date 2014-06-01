package com.Lyeeedar.Entity;

/**
 * An abstract class used to process entity components. Contains an aspect to speed up identification of desired entities.
 * @author Philip Collin
 *
 */
public abstract class Processor 
{
	public final Aspect aspect;
	protected EntityWorld world;
	
	public Processor(Aspect aspect)
	{
		this.aspect = aspect;
	}
	
	public void addToWorld(EntityWorld world)
	{
		this.world = world;
		world.processors.add(this);
		obtainMappers();
	}
	
	protected abstract void obtainMappers();
	public abstract void process(Entity e, float delta);
}
