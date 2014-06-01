package com.Lyeeedar.Graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public abstract class GraphicsObject
{
	private static final Vector3 tmp = new Vector3();
	
	public final Matrix4 transform = new Matrix4();
	
	public float distance(Camera cam)
	{
		return tmp.set(0, 0, 0).mul(transform).dst2(cam.position);
	}
	
	public Matrix4 getTransform()
	{
		return transform;
	}
	
	public abstract int compareTo(GraphicsObject object);
}
