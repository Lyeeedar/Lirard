package com.Lyeeedar.Entity.Processors;

import com.Lyeeedar.Entity.Aspect;
import com.Lyeeedar.Entity.ComponentMapper;
import com.Lyeeedar.Entity.Entity;
import com.Lyeeedar.Entity.Processor;
import com.Lyeeedar.Entity.Components.LODModel;
import com.Lyeeedar.Entity.Components.Position;
import com.Lyeeedar.Entity.Components.Rotation;
import com.Lyeeedar.Entity.Components.Scale;
import com.Lyeeedar.Graphics.GraphicsSorter;
import com.Lyeeedar.Lirard.GLOBALS;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class RenderProcessor extends Processor
{
	private ComponentMapper<LODModel> lm;
		
	public final GraphicsSorter sorter;
	public final Camera cam;
	
	public RenderProcessor(Camera cam)
	{
		super(Aspect.getAspectForAll(LODModel.class), 1);
		this.sorter = new GraphicsSorter(cam);
		this.cam = cam;
	}

	@Override
	protected final void obtainMappers()
	{
		lm = world.getMapper(LODModel.class);
	}

	@Override
	public final void process(final Entity e, final float delta)
	{
		final LODModel l = lm.get(e.id);
		
		sorter.add(l.model);
	}
}
