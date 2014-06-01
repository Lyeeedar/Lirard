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
import com.badlogic.gdx.math.Vector3;

public class RenderProcessor extends Processor
{
	private ComponentMapper<Position> pm;
	private ComponentMapper<LODModel> lm;
	private ComponentMapper<Rotation> rm;
	private ComponentMapper<Scale> sm;
	
	private final Vector3 dpos = new Vector3(0, 0, 0);
	private final Vector3 drot = GLOBALS.DEFAULT_ROTATION;
	private final Vector3 dscl = new Vector3(1, 1, 1);
	
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
		LODModel l = lm.get(e);
		
		Vector3 pos = p != null ? p.position : dpos; 
		Vector3 rot = r != null ? r.rotation : drot;
		Vector3 scl = s != null ? s.scale : dscl;
		
		l.model.getTransform().setToTranslationAndScaling(pos, scl).rotate(GLOBALS.DEFAULT_ROTATION, rot);
		
		sorter.add(l.model);
	}

}
