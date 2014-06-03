package com.Lyeeedar.Graphics;

import java.util.PriorityQueue;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

public class ModelBatchInstance extends GraphicsObject 
{
	public final ModelBatchData data;
	
	public ModelBatchInstance(ModelBatchData data)
	{
		this.data = data;
	}
	
	public Mesh getMesh()
	{
		return data.mesh;
	}

	public static class ModelBatchData
	{
		public final Mesh mesh;
		public final int primitive_type;
		public final Texture[] textures;
		public final boolean useTriplanarSampling;
		public final float triplanarScaling;
		public final boolean canCull;
				
		public ModelBatchData(Mesh mesh, int primitive_type, Texture[] textures, boolean canCull, boolean useTriplanarSampling, float triplanarScaling)
		{
			this.mesh = mesh;
			this.primitive_type = primitive_type;
			this.textures = textures;
			this.canCull = canCull;
			this.useTriplanarSampling = useTriplanarSampling;
			this.triplanarScaling = triplanarScaling;
		}
		
		public final PriorityQueue<BatchedInstance> instances = new PriorityQueue<BatchedInstance>();
		public boolean added = false;
		private final Vector3 tmpVec = new Vector3();
		
		public void add(Matrix4 transform, Camera cam)
		{
			if (cam == null) return;
			
			Vector3 pos = tmpVec.set(0, 0, 0).mul(transform);
			float d = cam.position.dst2(pos);
			if (d > cam.far*cam.far) return;
			
			instances.add(pool.obtain().set(transform, d));			
		}
		
		public void clear()
		{
			while (!instances.isEmpty())
			{
				pool.free(instances.poll());
			}
		}
		
		public Pool<BatchedInstance> pool = new Pool<BatchedInstance>(){
			@Override
			protected BatchedInstance newObject() {
				return new BatchedInstance();
			}
		};
		
		public static class BatchedInstance implements Comparable<BatchedInstance>
		{
			private float dist;
			public final Matrix4 transform = new Matrix4();
			
			public BatchedInstance set(Matrix4 transform, float dist)
			{
				this.transform.set(transform);
				return this;
			}

			@Override
			public int compareTo(BatchedInstance bi) {
				if (equals(bi)) return 0;
				return Float.compare(bi.dist, dist);
			}	
		}
	}
}
