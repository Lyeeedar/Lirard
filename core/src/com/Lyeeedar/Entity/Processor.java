package com.Lyeeedar.Entity;

/**
 * An abstract class used to process entity components. Contains an aspect to speed up identification of desired entities.
 * @author Philip Collin
 *
 */
public abstract class Processor implements Comparable<Processor>
{
	public final Aspect aspect;
	public EntityWorld world;
	protected final int priority;
	
	public Processor(Aspect aspect, int priority)
	{
		this.aspect = aspect;
		this.priority = priority;
	}
	
	@Override
	public int compareTo(Processor o)
	{
		return priority - o.priority;
	}
	
	protected abstract void obtainMappers();
	public abstract void process(final Entity e, final float delta);
}
