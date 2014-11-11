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

	public static int WORLD_CEILING = 300;
	
	private ICFCWorldContainer container;
	
	private Chicken chicken;
	private ArrayList<Platform> platforms;
	private ArrayList<Cage> cages;
	
	private int worldLength;
	
	private boolean levelWon; // if the level has been won or not
	private boolean camFollowChicken;
	
	private int score;
	
	public PlayWorld(ICFCWorldContainer container, int length)
	{
		this.container = container;
		initWorld(length);
	}
	
	public PlayWorld(ICFCWorldContainer container, int length, int score)
	{
		this.container = container;
		this.score = score;
		initWorld(length);
	}
	
	public void initWorld(int length)
	{
		worldLength = length;
		levelWon = false;
		camFollowChicken = true;
		
		score = 0;
		
		chicken = new Chicken(300, 200);
		platforms = new ArrayList<Platform>();
		cages = new ArrayList<Cage>();
		
		Random rnd = new Random();
		
		int lastPlatformEnd = 0;
		while (lastPlatformEnd < worldLength)
		{
			int thisLength = rnd.nextInt(200) + 100;
			if (lastPlatformEnd + thisLength > worldLength) // ensure the platforms do not go past the edge of the world
				thisLength = worldLength - lastPlatformEnd;
			int thisPlatformHeight = rnd.nextInt(230) + 20;
			platforms.add(new Platform(lastPlatformEnd, thisPlatformHeight, thisLength));
			
			if (rnd.nextInt(5) == 1) // place a cage on this platform
			{
				boolean isBomb = (rnd.nextInt(1) == 1);
				cages.add(new Cage(rnd.nextInt(thisLength - Cage.SIZE) + lastPlatformEnd, thisPlatformHeight, isBomb));
			}
			
			lastPlatformEnd += thisLength;	
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
		
		//System.out.println("isDiving " + chicken.isDiving());
		
		float chickenY = chicken.getY();
		chicken.update(delta);
		float newChickenY = chicken.getY();
		
		if (chicken.isAlive()) // only collide or win if the chicken is alive
		{
			/* --------- Collisions ----------- */
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
			
			// Collision with the floor
			if (chicken.getY() < 0)
			{
				chicken.setY(0);
				chicken.setSpeedY(0);
				chicken.setFalling(false);
			}
			
			// Collision with the ceiling
			if (chicken.getY() > 300 - chicken.HEIGHT)
			{
				chicken.setY(300 - chicken.HEIGHT);
				chicken.setSpeedY(0);
			}
			
			/* --------- Other ---------- */
			if (chicken.getX() > worldLength) // the chicken has passed the finish line...
			{
				levelWon = true;
				camFollowChicken = false;
			}
		}
		else // the chicken is dead
		{
			camFollowChicken = false;
		}
		
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		// call render code on everything in world		
		
		for (Platform platform : platforms)
			platform.render(batch, renderer);
		for (Cage cage : cages)
			cage.render(batch);
		
		chicken.render(batch);
	}
	
	public void keyDown(int keycode)
	{
		if (levelWon)
		{
			System.out.println("Sending nextLevel message");
			container.nextLevel(score); // start the next level, passing out the score
		}
		else
		{
			switch (keycode)
			{
			case Input.Keys.SPACE: if (chicken.isAlive()) chicken.jump(); break;
			case Input.Keys.UP: if (chicken.isAlive()) chicken.jump(); break;
			case Input.Keys.DOWN: if (chicken.isAlive()) chicken.setDiving(true); break;
			case Input.Keys.A: if (chicken.isAlive()) chicken.damage(1); break; // temporary for testing
			}
		}
	}
	
	public Chicken getChicken()
	{
		return chicken;
	}
	
	public boolean isCamFollowingChicken()
	{
		return camFollowChicken;
	}
	
	public int getWorldLength()
	{
		return worldLength;
	}
	
}
