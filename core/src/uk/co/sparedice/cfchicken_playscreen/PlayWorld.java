package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayWorld {

	private Chicken chicken;
	
	public PlayWorld()
	{
		chicken = new Chicken(300, 200);
	}
	
	public void update(float delta)
	{
		chicken.update(delta);
		if (chicken.getY() < 0)
			chicken.setY(0);
	}
	
	public void render(SpriteBatch batch)
	{
		// call render code on everything in world
		
		chicken.render(batch);
	}
	
	public void keyDown(int keycode)
	{
		switch (keycode)
		{
		case Input.Keys.SPACE: chicken.jump();
		}
	}
	
	public Chicken getChicken()
	{
		return chicken;
	}
	
}
