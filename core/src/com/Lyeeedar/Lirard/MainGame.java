package com.Lyeeedar.Lirard;

import java.util.Random;

import com.Lyeeedar.Entity.Aspect;
import com.Lyeeedar.Entity.Entity;
import com.Lyeeedar.Entity.EntityWorld;
import com.Lyeeedar.Entity.Processor;
import com.Lyeeedar.Entity.Components.LODModel;
import com.Lyeeedar.Entity.Components.Position;
import com.Lyeeedar.Entity.Components.Velocity;
import com.Lyeeedar.Entity.Processors.RenderProcessor;
import com.Lyeeedar.Entity.Processors.VelocityProcessor;
import com.Lyeeedar.Graphics.DeferredRenderer;
import com.Lyeeedar.Graphics.ModelBatchInstance;
import com.Lyeeedar.Graphics.ModelBatchInstance.ModelBatchData;
import com.Lyeeedar.Util.FileUtils;
import com.Lyeeedar.Util.Shapes;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainGame extends ApplicationAdapter 
{
	protected SpriteBatch spriteBatch;
	protected BitmapFont font;
	
	EntityWorld world = new EntityWorld();
	Entity e;
	
	DeferredRenderer renderer;
	Camera cam;
	
	@Override
	public void create () 
	{
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		
		ModelBatchData[] data = new ModelBatchData[10];
		data[0] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/grass2.png", true, null, null)}, false, false, 1);
		data[1] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone01.png", true, null, null)}, false, false, 1);
		data[2] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone02.png", true, null, null)}, false, false, 1);
		data[3] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone03.png", true, null, null)}, false, false, 1);
		data[4] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone04.png", true, null, null)}, false, false, 1);
		data[5] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone05.png", true, null, null)}, false, false, 1);
		data[6] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone06_d.png", true, null, null)}, false, false, 1);
		data[7] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone07_d.png", true, null, null)}, false, false, 1);
		data[8] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone08_d.png", true, null, null)}, false, false, 1);
		data[9] = new ModelBatchData(Shapes.getBoxMesh(10, 10, 10, true, true), GL30.GL_TRIANGLES, new Texture[]{FileUtils.loadTexture("data/textures/stone10_d.png", true, null, null)}, false, false, 1);
				
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.near = 1;
		cam.far = 7000;
		cam.position.set(80, 80, 500);
		cam.update();
		
		Processor p = new VelocityProcessor();
		p.addToWorld(world);
		
		RenderProcessor rp = new RenderProcessor(cam);
		rp.addToWorld(world);
		
		renderer = new DeferredRenderer(rp);
		
		e = world.createEntity();
		e.addComponent(new Position(10, 10, -20));
		e.addComponent(new Velocity(10, 1, 1));
		e.addComponent(new LODModel(new ModelBatchInstance(data[0])));
		
		Random ran = new Random();
		for (int i = 0; i < 60000; i++)
		{
			Entity t = world.createEntity();
			t.addComponent(new Position(ran.nextFloat()*50-25, ran.nextFloat()*50-25, ran.nextFloat()*50-25));
			t.addComponent(new Velocity(ran.nextFloat()*50-25, ran.nextFloat()*50-25, ran.nextFloat()*50-25));
			t.addComponent(new LODModel(new ModelBatchInstance(data[1])));
		}
	}

	@Override
	public void render () {
		
		world.process(Gdx.graphics.getDeltaTime());
		
		Position p = e.getComponent(Position.class);
		
		cam.lookAt(p.position.x, p.position.y, p.position.z);
		cam.update();
		
		renderer.render();
		
		Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
		Gdx.gl.glDisable(GL20.GL_CULL_FACE);
		Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);
		
		spriteBatch.begin();
        font.draw(spriteBatch, ""+Gdx.app.getGraphics().getFramesPerSecond(), 20, GLOBALS.SCREEN_SIZE[1]-40);
        spriteBatch.end();
	}
	
	@Override
	public void resize(int width, int height) {
		
		GLOBALS.SCREEN_SIZE[0] = width;
		GLOBALS.SCREEN_SIZE[1] = height;
		
		width = GLOBALS.RESOLUTION[0];
		height = GLOBALS.RESOLUTION[1];

        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.near = 2f;
        cam.far = GLOBALS.FOG_MAX ;
        cam.update();       		
	}
}
