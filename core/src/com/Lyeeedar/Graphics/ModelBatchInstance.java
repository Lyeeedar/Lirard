package com.Lyeeedar.Graphics;

import com.Lyeeedar.Graphics.InstanceRenderer.InstanceBin;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;

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
	
	@Override
	public int compareTo(GraphicsObject object)
	{
		if (!(object instanceof ModelBatchInstance))
		{
			return 1;
		}
		ModelBatchInstance obj = (ModelBatchInstance) object;
		
		return obj.data.hashCode() - data.hashCode();
	}

	public static class ModelBatchData
	{
		public final Mesh mesh;
		public final int primitive_type;
		public final Texture[] textures;
		public final boolean useTriplanarSampling;
		public final float triplanarScaling;
		public final boolean canCull;
		
		public InstanceBin bin = null;
		
		public ModelBatchData(Mesh mesh, int primitive_type, Texture[] textures, boolean canCull, boolean useTriplanarSampling, float triplanarScaling)
		{
			this.mesh = mesh;
			this.primitive_type = primitive_type;
			this.textures = textures;
			this.canCull = canCull;
			this.useTriplanarSampling = useTriplanarSampling;
			this.triplanarScaling = triplanarScaling;
		}
	}
}
