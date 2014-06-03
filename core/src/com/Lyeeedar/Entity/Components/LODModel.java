package com.Lyeeedar.Entity.Components;

import com.Lyeeedar.Entity.Component;
import com.Lyeeedar.Graphics.GraphicsObject;
import com.Lyeeedar.Graphics.ModelBatchInstance;
import com.Lyeeedar.Util.FileUtils;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;

public class LODModel extends Component
{
	public ModelBatchInstance model;
	
	public LODModel(ModelBatchInstance model)
	{
		this.model = model;
	}
}
