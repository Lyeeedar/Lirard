package com.Lyeeedar.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class Controls {
	
	final boolean isAndroid;
	int scroll_amount;
	public InputProcessor ip = new InputProcessor(){

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			scroll_amount = amount;
			return true;
		}};
	
	public Controls (boolean isAndroid)
	{
		this.isAndroid = isAndroid;
	}
	
	public boolean getActivate()
	{
		if (isAndroid)
		{
			return Gdx.input.justTouched();
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.E);
		}
	}
	
	public float getDeltaX()
	{
		return Gdx.input.getDeltaX();
	}
	
	public float getDeltaY()
	{
		if (isAndroid)
		{
			return 0;
		}
		else
		{
			return Gdx.input.getDeltaY();
		}
	}
	
	public float scrolled()
	{
		int retval = scroll_amount;
		scroll_amount = 0;
		return retval;
	}
	
	public boolean switchL()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.NUM_1);
		}
	}
	
	public boolean switchR()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.NUM_2);
		}
	}
	
	public boolean leftClick()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
		}
	}
	
	public boolean rightClick()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
		}
	}
	
	public boolean sprint()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.SHIFT_LEFT);
		}
	}
	
	public boolean left()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
		}
	}
	
	public boolean right()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
		}
	}
	
	public boolean up()
	{
		return Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W);
	}
	
	public boolean down()
	{
		return Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S);
	}
	
	public boolean esc()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return  Gdx.input.isKeyPressed(Keys.ESCAPE);
		}
	}
	
	public boolean jump()
	{
		if (isAndroid)
		{
			return false;
		}
		else
		{
			return  Gdx.input.isKeyPressed(Keys.SPACE);
		}
	}

}
