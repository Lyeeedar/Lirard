package com.Lyeeedar.Graphics.Lights;

import com.Lyeeedar.Collision.Octtree;
import com.Lyeeedar.Collision.Octtree.OcttreeShape;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class LightManager 
{	
	Octtree<Light> lightGraph = new Octtree<Light>(null, new Vector3(-10000, -10000, -10000), new Vector3(10000, 10000, 10000));
	public final Vector3 ambientColour = new Vector3(1, 1, 1);
	
	public DirectionalLight dl = new DirectionalLight();
	
	Array<Light> lightArray = new Array<Light>(false, 16);
	
	public LightManager()
	{
	}
		
	public void addLight(Light light)
	{
		light.createEntry(lightGraph);
		lightGraph.add(light.entry);
	}
	
	public void collectAll(Array<Light> output, OcttreeShape shape, int bitmask)
	{
		lightGraph.collectAll(output, shape, bitmask);
	}
}
