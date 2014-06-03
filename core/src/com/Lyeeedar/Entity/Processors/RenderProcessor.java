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
	private ComponentMapper<Position> pm;
	private ComponentMapper<LODModel> lm;
	private ComponentMapper<Rotation> rm;
	private ComponentMapper<Scale> sm;
		
	public final GraphicsSorter sorter;
	public final Camera cam;
	
	public RenderProcessor(Camera cam)
	{
		super(Aspect.getAspectForAll(LODModel.class));
		this.sorter = new GraphicsSorter(cam);
		this.cam = cam;
	}

	@Override
	protected void obtainMappers()
	{
		pm = world.getMapper(Position.class);
		lm = world.getMapper(LODModel.class);
		rm = world.getMapper(Rotation.class);
		sm = world.getMapper(Scale.class);
	}

	@Override
	public void process(Entity e, float delta)
	{
		Position p = pm.get(e);
		Rotation r = rm.get(e);
		Scale s = sm.get(e);
		LODModel l = lm.get(e);;
		
		Matrix4 transform = l.model.getTransform();
		if (p != null) transform.set(p.mat);
		if (s != null) transform.mul(s.mat);
		if (r != null) transform.mul(r.mat);
		
		sorter.add(l.model);
	}

}
