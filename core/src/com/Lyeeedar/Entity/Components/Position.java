package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.Lyeeedar.Lirard.GLOBALS;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Position extends Component
{
	public final Vector3 position;
	
	public Position()
	{
		this(0, 0, 0);
	}
	
	public Position(float x, float y, float z)
	{
		position = new Vector3(x, y, z);
	}
}
