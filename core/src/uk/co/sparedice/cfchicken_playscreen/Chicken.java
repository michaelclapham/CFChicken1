package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chicken {

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	
	public static final float FALL_ACCEL = 1450;
	public static final float RUN_SPEED = 200;
	
	// The location of the chicken is the centre of its base
	private int xLoc;
	private int yLoc;
	
	private float speedX;
	private float speedY;
	
	public Chicken(int x, int y)
	{
		xLoc = x;
		yLoc = y;
		
		speedX = RUN_SPEED;
	}
	
	public void render(SpriteBatch batch)
	{
		batch.begin();
			// Draw the chicken where xLoc and yLoc refer to the centre of the chicken image's base
			batch.draw(AssetLoader.chicken, xLoc - (WIDTH / 2), yLoc, WIDTH, HEIGHT);
		batch.end();
	}
	
	public void update(float delta)
	{
		speedY -= FALL_ACCEL * delta;
		
		xLoc += speedX * delta;
		yLoc += speedY * delta;
	}
	
	public void jump()
	{
		speedY = 600;
	}
	
	public int getX() {
		return xLoc;
	}

	public void setX(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getY() {
		return yLoc;
	}

	public void setY(int yLoc) {
		this.yLoc = yLoc;
	}
	
}
