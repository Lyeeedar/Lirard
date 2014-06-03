package com.Lyeeedar.Graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import com.Lyeeedar.Graphics.GraphicsSorter.GraphicsInstance;
import com.Lyeeedar.Graphics.ModelBatchInstance.ModelBatchData;
import com.Lyeeedar.Graphics.ModelBatchInstance.ModelBatchData.BatchedInstance;
import com.Lyeeedar.Graphics.Lights.LightManager;
import com.Lyeeedar.Lirard.GLOBALS;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.UniformBufferObject;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Pool;

public class InstanceRenderer
{
	private static final int BLOCK_SIZE = 16;
	
	private final int MAX_INSTANCES;
		
	private ShaderProgram samplingShader;
	private ShaderProgram noSamplingShader;
	
	private ShaderProgram shader;
	
	private UniformBufferObject ubo;
		
	private ModelBatchData bound = null;
	
	public InstanceRenderer()
	{
		IntBuffer ib = BufferUtils.newIntBuffer(16);
		Gdx.gl.glGetIntegerv(GL30.GL_MAX_UNIFORM_BLOCK_SIZE, ib);
		int limitBytes = ib.get(0);
		int supportedMaxFloats = (limitBytes / 4);
		int supportedBlocks = supportedMaxFloats / Math.max(4, BLOCK_SIZE) ;
		MAX_INSTANCES = supportedBlocks ;
		
		ubo = new UniformBufferObject(4 * BLOCK_SIZE * MAX_INSTANCES, 1);
		
		loadShaders();
	}
	
	public void render(GraphicsSorter sorter, LightManager lights, Camera cam)
	{
		for (ModelBatchData data : sorter.data)
		{
			render(data, lights, cam);
		}
		end(lights, cam);
		sorter.clear();
	}
	
	public void render(ModelBatchData data, LightManager lights, Camera cam)
	{
		beginData(data, lights, cam);
		
		int i = 0;
		FloatBuffer floatbuffer = ubo.getDataBuffer().asFloatBuffer();
		while (!data.instances.isEmpty())
		{			
			BatchedInstance bi = data.instances.poll();
			floatbuffer.put(bi.transform.val);
			
			i++;
			if (i == MAX_INSTANCES)
			{
				ubo.bind();
				data.mesh.renderInstanced(shader, data.primitive_type, i);
				i = 0;
				floatbuffer = ubo.getDataBuffer().asFloatBuffer();
			}
			
			data.pool.free(bi);
		}
		
		if (i > 0)
		{
			ubo.bind();
			data.mesh.renderInstanced(shader, data.primitive_type, i);
		}
	}
	
	private void beginData(ModelBatchData data, LightManager lights, Camera cam)
	{
		if (data == bound) return;
		
		bound = data;
		
		ShaderProgram desired = data.useTriplanarSampling ? samplingShader : noSamplingShader;
		beginShader(desired, lights, cam);
		
		shader.setUniformi("u_texNum", data.textures.length);
		if (data.useTriplanarSampling) shader.setUniformf("u_triplanarScaling", data.triplanarScaling);
		
		for (int i = 0; i < data.textures.length; i++)
		{
			shader.setUniformi("u_texture"+i, i);
			data.textures[i].bind(i);
		}	
	}
	
	private void beginShader(ShaderProgram shader, LightManager lights, Camera cam)
	{
		if (this.shader == shader) return;
		
		if (this.shader != null) this.shader.end();
		
		this.shader = shader;
		shader.begin();
		
		shader.setUniformMatrix("u_pv", cam.combined);
		shader.setUniformf("fog_col", lights.ambientColour);
		shader.setUniformf("fog_min", GLOBALS.FOG_MIN);
		shader.setUniformf("fog_max", GLOBALS.FOG_MAX);
		shader.setUniformf("u_viewPos", cam.position);
		Gdx.gl.glEnable(GL30.GL_BLEND);
	}
	
	public void end(LightManager lights, Camera cam)
	{	
		if (shader != null) shader.end();
		shader = null;
		
		bound = null;
	}
	
	private void loadShaders()
	{		
		String vert = "";
		String frag = "";
		
		vert = "#define MAX_INSTANCES " + MAX_INSTANCES + "\n" + Gdx.files.internal("data/shaders/deferred/modelbatcher.vertex.glsl").readString();
		frag = Gdx.files.internal("data/shaders/deferred/modelbatcher.fragment.glsl").readString();
				
		noSamplingShader = new ShaderProgram(vert, frag);
		if (!noSamplingShader.isCompiled()) System.err.println(noSamplingShader.getLog());
		
		samplingShader = new ShaderProgram("#define USE_TRIPLANAR_SAMPLING\n"+vert, "#define USE_TRIPLANAR_SAMPLING\n"+frag);
		if (!samplingShader.isCompiled()) System.err.println(samplingShader.getLog());
		
		samplingShader.registerUniformBlock("InstanceBlock", 1);
		noSamplingShader.registerUniformBlock("InstanceBlock", 1);
	}

}
