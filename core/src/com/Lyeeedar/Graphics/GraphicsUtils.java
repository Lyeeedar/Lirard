package com.Lyeeedar.Graphics;

import com.badlogic.gdx.graphics.Texture;

public class GraphicsUtils
{
	private static final int MAX_TEXTURES = 8;
	private static final Texture[] boundTextures = new Texture[MAX_TEXTURES];
	private static int index = -1;
	public static int bindTexture(Texture tex)
	{
		for (int i = 0; i < MAX_TEXTURES; i++)
		{
			if (boundTextures[i] == tex) return i;
		}
		
		index++;
		if (index == MAX_TEXTURES) index = 0;
		
		tex.bind(index);
		boundTextures[index] = tex;
		
		return index;
	}
	public static void clearTextures()
	{
		for (int i = 0; i < MAX_TEXTURES; i++)
		{
			boundTextures[i] = null;
		}
		index = -1;
	}
}
