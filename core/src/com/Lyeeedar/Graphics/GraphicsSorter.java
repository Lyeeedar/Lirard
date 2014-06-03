package com.Lyeeedar.Graphics;

import com.Lyeeedar.Graphics.ModelBatchInstance.ModelBatchData;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class GraphicsSorter
{
	public final Camera cam;
	public final Array<ModelBatchData> data = new Array<ModelBatchData>(false, 16);

	public GraphicsSorter(Camera cam)
	{
		this.cam = cam;
	}

	public void add(ModelBatchInstance object)
	{
		object.data.add(object.transform, cam);
		
		if (!object.data.added)
		{
			data.add(object.data);
			object.data.added = true;
		}
	}

	public void clear()
	{
		for (ModelBatchData d : data) d.added = false;
		data.clear();
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
