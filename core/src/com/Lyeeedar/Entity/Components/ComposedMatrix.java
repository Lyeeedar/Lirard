package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.Lyeeedar.Lirard.GLOBALS;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class ComposedMatrix extends Component
{
	public final Matrix4 composedMatrix;
	
	public ComposedMatrix()
	{
		composedMatrix = new Matrix4();
	}
	
	public void compose(Vector3 position, Vector3 rotation, Vector3 scale)
	{
		if (position != null)
		{
			composedMatrix.setToTranslation(position);
		}
		else
		{
			composedMatrix.setToTranslation(0, 0, 0);
		}
		
		if (scale != null)
		{
			composedMatrix.scale(scale.x, scale.y, scale.z);
		}
		
		if (rotation != null)
		{
			composedMatrix.rotate(GLOBALS.DEFAULT_ROTATION, rotation);
		}
	}

}
