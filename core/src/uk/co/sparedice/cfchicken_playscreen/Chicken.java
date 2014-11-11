package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Chicken {

	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	
	public static final float FALL_ACCEL = 1450; // the acceleration due to gravity
	public static final float JUMP_SPEED = 600; // The reduction on gravity when the jump button is held
	public static final float DIVE_SPEED = 600; // The addition on gravity when the dive button is held
	public static final float RUN_SPEED = 150;
	
	public static final int STARTING_HEALTH = 1;
	public static final float DAMAGE_COOLDOWN = 2; // the time before the chicken can take more damage
	
	public static final float KICK_TIME = 0.5f; // The length of time that a kick takes
	
	// The location of the chicken is the centre of its base
	private float xLoc;
	private float yLoc;
	
	private int health;
	private boolean invuln; // whether the chicken is invulnerable (probably from just taking damage)
	private float invulnTime; // Time that the chicken has been invulnerable for
	
	private float speedX;
	private float speedY;
	
	private boolean falling;
	private boolean diving;
	private boolean jumping;
	private boolean kicking;
	
	private float kickTimer;
	
	private boolean alive;
        
    private float animCounter = 0;
    private float animStep = 0.10f;
    private int animFrame = 0;
	
	public Chicken(int x, int y)
	{
		xLoc = x;
		yLoc = y;
		
		falling = true;
		diving = false;
		alive = true;
		
		speedX = RUN_SPEED;
	}
	
	public void render(SpriteBatch batch)
	{
		batch.begin();
			// Draw the chicken where xLoc and yLoc refer to the centre of the chicken image's base
			if (alive)
			{
				if (kicking)
					batch.draw(AssetLoader.chicken_kicking, xLoc - (WIDTH / 2), yLoc, WIDTH, HEIGHT);
				else
					batch.draw(AssetLoader.chicken_anim_running1[animFrame], xLoc - (WIDTH / 2), yLoc, WIDTH, HEIGHT);
			}
			else
				batch.draw(AssetLoader.chicken, xLoc - (WIDTH / 2), yLoc, WIDTH, HEIGHT);
		batch.end();
		
		
	}
	
	public void update(float delta)
	{
		if (invuln)
		{
			invulnTime += delta;
			if (invulnTime > DAMAGE_COOLDOWN)
			{
				invuln = false;
				invulnTime = 0;
			}
			System.out.println("Invulnerable");
		}
		
		// check if the chicken is kicking
		if (kicking)
		{
			kickTimer += delta;
			if (kickTimer >= KICK_TIME)
			{
				kicking = false;
				kickTimer = 0;
			}
		}
		
		if (diving)
			speedY -= (FALL_ACCEL + DIVE_SPEED) * delta;
		else if (jumping)
			speedY -= (FALL_ACCEL - JUMP_SPEED) * delta;
		else
			speedY -= FALL_ACCEL * delta;

		
		falling = true;
		
		xLoc += speedX * delta;
		yLoc += speedY * delta;
                

        animCounter += delta;
        //System.out.println("Anim:" + animCounter);
        if(animCounter > animStep){
            animCounter = 0;
            progressAnim();
        }

                animCounter += delta;
                if(animCounter > animStep){
                    animCounter = 0;
                    progressAnim();
                }

	}
	
	public void jump()
	{
		if (!falling)
			speedY = 600;
	}
	
	public void kick()
	{
		kicking = true;
		if (!falling) // add a small jump to the kick if the chicken is on the floor
			speedY = 400;
	}
	
	public void damage(int amount)
	{
		health -= amount;
		if (health <= 0)
			die();
		else
			invuln = true; // so more damage isn't taken instantly from the same source
	}
	
	public void die()
	{
		speedX = -200;
		alive = false;
	}
	
	public void setFalling(boolean falling)
	{
		this.falling = falling;
	}
	
	public boolean isFalling()
	{
		return falling;
	}
	
	public void setDiving(boolean diving)
	{
		this.diving = diving;
	}
	
	public boolean isDiving()
	{
		return diving;
	}
	
	public void setJumping(boolean jumping)
	{
		this.jumping = jumping;
	}
	
	public boolean isJumping()
	{
		return jumping;
	}
	
	public boolean isAlive()
	{
		return alive;
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

	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return health;
	}
	
    private void progressAnim() {
        animFrame += 1;
        if(animFrame > 9){
            animFrame = 0;
        }
    }
    
    public Rectangle getWorldCollisionRectangle(){
        return new Rectangle(xLoc - (WIDTH / 2), yLoc, WIDTH, HEIGHT);
    }

    public boolean isKicking() {
        return kicking;
    }
	
}
