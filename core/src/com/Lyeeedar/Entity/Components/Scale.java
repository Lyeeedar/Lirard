package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.badlogic.gdx.math.Vector3;

public class Scale extends Component
{
	public final Vector3 scale;
	
	public Scale()
	{
		this(1, 1, 1);
	}
	
	public Scale(float x, float y, float z)
	{
		this.scale = new Vector3(x, y, z);
	}
}
