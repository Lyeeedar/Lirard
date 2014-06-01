package com.Lyeeedar.Entity;

import com.Lyeeedar.Util.Bag;

/**
 * A class that manages entities.
 * Provides an interface to instantiate an entity and assign it a unique id.
 * @author Philip Collin
 *
 */
public class EntityManager
{
	public final Bag<Entity> entities;

	private int active;
	private long added;
	private long created;
	private long deleted;

	private final IdentifierPool identifierPool;

	public EntityManager()
	{
		entities = new Bag<Entity>();
		identifierPool = new IdentifierPool();
	}

	/**
	 * Create a new entity with the first free id
	 * @return the entity
	 */
	public Entity createEntityInstance(EntityWorld world)
	{
		Entity e = new Entity(world);
		e.id = identifierPool.checkOut();
		
		created++;
		active++;
		added++;
		entities.set(e.id, e);
		
		return e;
	}

	/**
	 * Delete the specified entity and free its id
	 * @param e
	 */
	public void deleted(Entity e)
	{
		entities.set(e.id, null);

		identifierPool.checkIn(e.id);

		active--;
		deleted++;
	}

	/**
	 * Get a entity with this id.
	 * 
	 * @param entityId
	 * @return the entity
	 */
	protected Entity getEntity(int entityId)
	{
		return entities.get(entityId);
	}

	/**
	 * Get how many entities are active in this world.
	 * 
	 * @return how many entities are currently active.
	 */
	public int getActiveEntityCount()
	{
		return active;
	}

	/**
	 * Get how many entities have been created in the world since start.
	 * 
	 * @return how many entities have been created since start.
	 */
	public long getTotalCreated()
	{
		return created;
	}

	/**
	 * Get how many entities have been added to the world since start.
	 * 
	 * @return how many entities have been added.
	 */
	public long getTotalAdded()
	{
		return added;
	}

	/**
	 * Get how many entities have been deleted from the world since start.
	 * 
	 * @return how many entities have been deleted since start.
	 */
	public long getTotalDeleted()
	{
		return deleted;
	}

	/*
	 * Used only internally to generate distinct ids for entities and reuse
	 * them.
	 */
	private class IdentifierPool
	{
		private Bag<Integer> ids;
		private int nextAvailableId;

		public IdentifierPool()
		{
			ids = new Bag<Integer>();
		}

		public int checkOut()
		{
			if (ids.size > 0) { return ids.removeLast(); }
			return nextAvailableId++;
		}

		public void checkIn(int id)
		{
			ids.add(id);
		}
	}

}
