package com.Lyeeedar.Graphics;

import java.util.PriorityQueue;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class GraphicsSorter
{
	public final Camera cam;
	public final PriorityQueue<GraphicsInstance> instances = new PriorityQueue<GraphicsInstance>();

	public GraphicsSorter(Camera cam)
	{
		this.cam = cam;
	}

	public void add(GraphicsObject object)
	{
		float dist = object.distance(cam);
		instances.add(pool.obtain().set(object, dist));
	}

	public void clear()
	{
		while (!instances.isEmpty())
			pool.free(instances.poll());
	}
	
	public void free(GraphicsInstance instance)
	{
		pool.free(instance);
	}

	private Pool<GraphicsInstance> pool = new Pool<GraphicsInstance>()
	{
		@Override
		protected GraphicsInstance newObject()
		{
			return new GraphicsInstance();
		}
	};

	public static class GraphicsInstance implements Comparable<GraphicsInstance>
	{
		private float dist;
		public GraphicsObject object;

		public GraphicsInstance set(GraphicsObject object, float dist)
		{
			this.object = object;
			this.dist = dist;
			return this;
		}

		@Override
		public int compareTo(GraphicsInstance bi)
		{
			return Float.compare(dist, bi.dist);
		}
	}
}
