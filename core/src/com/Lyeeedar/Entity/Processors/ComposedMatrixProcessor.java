package com.Lyeeedar.Entity.Processors;

import com.Lyeeedar.Entity.Aspect;
import com.Lyeeedar.Entity.ComponentMapper;
import com.Lyeeedar.Entity.Entity;
import com.Lyeeedar.Entity.Processor;
import com.Lyeeedar.Entity.Components.ComposedMatrix;
import com.Lyeeedar.Entity.Components.LODModel;
import com.Lyeeedar.Entity.Components.Position;
import com.Lyeeedar.Entity.Components.Rotation;
import com.Lyeeedar.Entity.Components.Scale;
import com.badlogic.gdx.math.Vector3;

public class ComposedMatrixProcessor extends Processor
{
	private ComponentMapper<ComposedMatrix> cmm;
	private ComponentMapper<Position> pm;
	private ComponentMapper<Rotation> rm;
	private ComponentMapper<Scale> sm;
	private ComponentMapper<LODModel> lmm;

	public ComposedMatrixProcessor()
	{
		super(Aspect.getAspectForAll(ComposedMatrix.class), 0);
	}

	@Override
	protected void obtainMappers()
	{
		cmm = world.getMapper(ComposedMatrix.class);
		pm = world.getMapper(Position.class);
		rm = world.getMapper(Rotation.class);
		sm = world.getMapper(Scale.class);
		lmm = world.getMapper(LODModel.class);
	}

	@Override
	public void process(Entity e, float delta)
	{
		final Position p = pm.get(e.id);
		final Rotation r = rm.get(e.id);
		final Scale s = sm.get(e.id);
		
		final ComposedMatrix cm = cmm.get(e.id);
		final LODModel lm = lmm.get(e.id);
		
		Vector3 pos = p != null ? p.position : null;
		Vector3 rot = r != null ? r.rotation : null;
		Vector3 scl = s != null ? s.scale : null;
		
		cm.compose(pos, rot, scl);
		
		if (lm != null)
		{
			lm.model.transform.set(cm.composedMatrix);
		}
	}

}
