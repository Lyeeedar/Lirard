package com.Lyeeedar.Graphics.Particles;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class ParticleEffect {
	
	public String name;
	
	public final Array<Emitter> emitters = new Array<Emitter>(false, 16);
	
	public final Vector3 pos = new Vector3();
	public final Matrix4 transform = new Matrix4();
	
	private boolean playing = false;
	private boolean repeat = false;
	
	public float duration;
	private float time;
	
	public ParticleEffect() {
	}
	
	public void play(boolean repeat)
	{
		this.playing = true;
		this.repeat = repeat;
		duration = 0;
		time = 0;
		for (Emitter e : emitters)
		{
			if (e.emitter.duration > duration) duration = e.emitter.duration;
		}
	}
	
	public boolean isPlaying()
	{
		return playing;
	}
	
	public void setPosition(Vector3 pos) {
		setPosition(pos.x, pos.y, pos.z);
	}
	
	public void setPosition(float x, float y, float z) {
		this.pos.set(x, y, z);

		for (Emitter e : emitters)
		{
			e.emitter.setPosition(x+e.x, y+e.y, z+e.z);
		}
	}
	
	public void deleteEmitter(int index)
	{
		Emitter emitter = emitters.removeIndex(index);
		emitter.emitter.dispose();
	}
	
	public void deleteEmitter(String name)
	{
		Iterator<Emitter> itr = emitters.iterator();
		
		while (itr.hasNext())
		{
			Emitter e = itr.next();
			
			if (e.emitter.name.equals(name))
			{
				itr.remove();
				
				e.emitter.dispose();
			}
		}
	}
	
	public ParticleEmitter getEmitter(int index)
	{
		return emitters.get(index).emitter;
	}
	
	public ParticleEmitter getEmitter(String name)
	{
		for (Emitter e : emitters) if (e.emitter.name.equals(name)) return e.emitter;
		
		return null;
	}
	
	public void getEmitters(List<ParticleEmitter> list)
	{
		for (Emitter e : emitters) list.add(e.emitter);
	}
	
	public ParticleEmitter getFirstEmitter()
	{
		return emitters.get(0).emitter;
	}
	
	public void addEmitter(ParticleEmitter emitter,
			float x, float y, float z)
	{
		Emitter e = new Emitter(emitter, x, y, z);
		emitters.add(e);
	}
	
	public void update(float delta, Camera cam)
	{
		if (!playing) return;
		time += delta;
		if (time > duration)
		{
			if (repeat)
			{
				time = 0;
				for (Emitter e : emitters)
				{
					e.emitter.time = 0;
					e.emitter.emissionVal = 0;
				}
			}
			else
			{
				playing = false;
				return;
			}
		}
		duration = 0;
		for (Emitter e : emitters)
		{
			if (e.emitter.duration > duration) duration = e.emitter.duration;
			e.emitter.time = time;
			if (e.emitter.duration < time) continue;
			e.emitter.update(delta, cam);
		}
	}
	
	public Vector3 getEmitterPosition(int index, Vector3 position)
	{
		Emitter e = emitters.get(index);
		return position.set(e.x, e.y, e.z);
	}
	
	public void setEmitterPosition(int index, Vector3 position)
	{
		Emitter e = emitters.get(index);
		e.x = position.x;
		e.y = position.y;
		e.z = position.z;
		
		setPosition(pos);
	}
	
	public void render()
	{
		for (Emitter e : emitters)
		{
			e.emitter.render();
		}
	}
	
	public void create()
	{
		for (Emitter e : emitters)
		{
			e.emitter.create();
		}
	}
	
	public void dispose()
	{
		for (Emitter e : emitters)
		{
			e.emitter.dispose();
		}
		time = 0;
		duration = 0;
		playing = false;
		repeat = false;
	}
	
	public ParticleEffect copy()
	{
		ParticleEffect effect = new ParticleEffect();
		effect.name = name;
		if (playing) effect.play(repeat);
		
		for (Emitter e : emitters)
		{
			effect.addEmitter(e.emitter.copy(), e.x, e.y, e.z);
		}
		
		return effect;
	}

	private static class Emitter implements Json.Serializable {

		ParticleEmitter emitter;
		float x;
		float y;
		float z;
		
		@SuppressWarnings("unused")
		public Emitter(){}
		
		public Emitter(ParticleEmitter emitter,
				float x, float y, float z)
		{
			this.emitter = emitter;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public void write(Json json) {
			ParticleEmitter.getJson(json);
			json.writeValue("emitter", emitter);
			json.writeValue("x", x);
			json.writeValue("y", y);
			json.writeValue("z", z);
		}

		@Override
		public void read(Json json, JsonValue jsonData) {
			ParticleEmitter.getJson(json);
			
			x = jsonData.getFloat("x");
			y = jsonData.getFloat("y");
			z = jsonData.getFloat("z");
			
			emitter = json.readValue(ParticleEmitter.class, jsonData.get("emitter"));
		}
	}

	public int getActiveParticles() {
		int active = 0;
		
		for (Emitter e : emitters) active += e.emitter.getActiveParticles();
		
		return active;
	}
}