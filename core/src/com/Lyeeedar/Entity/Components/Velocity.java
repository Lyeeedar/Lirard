package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.badlogic.gdx.math.Vector3;

public class Velocity extends Component
{
	public final Vector3 velocity;
	
	public Velocity()
	{
		this(0, 0, 0);
	}
	
	public Velocity(float x, float y, float z)
	{
		velocity = new Vector3(x, y, z);
	}
}
