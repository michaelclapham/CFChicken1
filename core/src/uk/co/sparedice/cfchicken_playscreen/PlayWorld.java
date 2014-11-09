package uk.co.sparedice.cfchicken_playscreen;

import java.util.ArrayList;
import java.util.Random;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayWorld {

	private Chicken chicken;
	private ArrayList<Platform> platforms;
	
	private int lastPlatform;
	
	public PlayWorld()
	{
		chicken = new Chicken(300, 200);
		platforms = new ArrayList<Platform>();
		
		Random rnd = new Random();
		
		for (int i = 0; i < 50; i++)
		{
			platforms.add(new Platform(i * 200, rnd.nextInt(230) + 20, 200));
		}
	}
	
	public void update(float delta)
	{
		// check if the chicken is diving
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			chicken.setDiving(true);
		else
			chicken.setDiving(false);
		
		// check if the chicken is jumping
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			chicken.setJumping(true);
		else
			chicken.setJumping(false);
		
		System.out.println("isDiving " + chicken.isDiving());
		
		float chickenY = chicken.getY();
		chicken.update(delta);
		float newChickenY = chicken.getY();
		
		for (Platform platform : platforms)
		{
			if (!chicken.isDiving() // if the chicken is not trying to fall through platforms...
				&& chicken.getX() > platform.getX() && chicken.getX() < platform.getX() + platform.getLength() // AND if the chicken is over or under the platform...
				&& chickenY >= platform.getY() && newChickenY < platform.getY()) // AND if the chicken would have just passed fell through the platform 
			{
				chicken.setY(platform.getY());
				chicken.setSpeedY(0);
				chicken.setFalling(false);
			}
		}
		
		if (chicken.getY() < 0)
		{
			chicken.setY(0);
			chicken.setSpeedY(0);
			chicken.setFalling(false);
		}
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		// call render code on everything in world
		
		chicken.render(batch);
		
		for (Platform platform : platforms)
			platform.render(batch, renderer);
	}
	
	public void keyDown(int keycode)
	{
		switch (keycode)
		{
		case Input.Keys.SPACE: chicken.jump();
		case Input.Keys.UP: chicken.jump();
		case Input.Keys.DOWN: chicken.setDiving(true);
		}
	}
	
	public Chicken getChicken()
	{
		return chicken;
	}
	
}
