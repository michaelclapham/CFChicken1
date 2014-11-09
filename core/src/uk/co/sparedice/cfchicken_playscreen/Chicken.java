package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Chicken {

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	
	public static final float FALL_ACCEL = 1450; // the acceleration due to gravity
	public static final float JUMP_SPEED = 600; // The reduction on gravity when the jump button is held
	public static final float DIVE_SPEED = 600; // The addition on gravity when the dive button is held
	public static final float RUN_SPEED = 200;
	
	// The location of the chicken is the centre of its base
	private float xLoc;
	private float yLoc;
	
	private float speedX;
	private float speedY;
	
	private boolean isFalling;
	private boolean isDiving;
	private boolean isJumping;
	
	public Chicken(int x, int y)
	{
		xLoc = x;
		yLoc = y;
		
		isFalling = true;
		isDiving = false;
		
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
		if (isDiving)
			speedY -= (FALL_ACCEL + DIVE_SPEED) * delta;
		else if (isJumping)
			speedY -= (FALL_ACCEL - JUMP_SPEED) * delta;
		else
			speedY -= FALL_ACCEL * delta;

		
		isFalling = true;
		
		xLoc += speedX * delta;
		yLoc += speedY * delta;
	}
	
	public void jump()
	{
		if (!isFalling)
			speedY = 600;
	}
	
	public void setFalling(boolean falling)
	{
		isFalling = falling;
	}
	
	public boolean isFalling()
	{
		return isFalling;
	}
	
	public void setDiving(boolean diving)
	{
		isDiving = diving;
	}
	
	public boolean isDiving()
	{
		return isDiving;
	}
	
	public void setJumping(boolean jumping)
	{
		isJumping = jumping;
	}
	
	public boolean isJumping()
	{
		return isJumping;
	}
	
	public float getX() {
		return xLoc;
	}

	public void setX(float xLoc) {
		this.xLoc = xLoc;
	}

	public float getY() {
		return yLoc;
	}

	public void setY(float yLoc) {
		this.yLoc = yLoc;
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

	public float getSpeedY() {
		return speedY;
	}

	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}
	
	
}
