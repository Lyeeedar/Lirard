package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.Lyeeedar.Lirard.GLOBALS;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Rotation extends Component
{
	public final Vector3 rotation;
	public final Vector3 up;
	
	public Rotation()
	{
		this(GLOBALS.DEFAULT_ROTATION.x, GLOBALS.DEFAULT_ROTATION.y, GLOBALS.DEFAULT_ROTATION.z);
	}
	
	public Rotation(float x, float y, float z)
	{
		this(x, y, z, GLOBALS.DEFAULT_UP.x, GLOBALS.DEFAULT_UP.y, GLOBALS.DEFAULT_UP.z);
	}
	
	public Rotation(float rx, float ry, float rz, float ux, float uy, float uz)
	{
		this.rotation = new Vector3(rx, ry, rz).nor();
		this.up = new Vector3(ux, uy, uz).nor();
	}
}
