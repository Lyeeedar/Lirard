package com.Lyeeedar.Lirard;

import com.Lyeeedar.Graphics.Lights.LightManager;
import com.badlogic.gdx.math.Vector3;

public class GLOBALS
{
	public static final Vector3 DEFAULT_UP = new Vector3(0, 1, 0);
	public static final Vector3 DEFAULT_ROTATION = new Vector3(0, 0, 1);
	
	public static final float GRAVITY = -150;
	public static final float FOG_MIN = 1000;
	public static final float FOG_MAX = 7000;
	
	public static LightManager LIGHTS = new LightManager();
	
	public static final int[] RESOLUTION = {1960, 1080};
	public static final int[] SCREEN_SIZE = {RESOLUTION[0], RESOLUTION[1]};
}
