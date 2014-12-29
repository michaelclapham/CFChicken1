package uk.co.sparedice.cfchicken_playscreen;

import java.util.ArrayList;
import java.util.Random;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PlayWorld implements IScorer, ControlActionListener {

	public static int WORLD_CEILING = 300;
	
	private ICFCWorldContainer container;
	
	private Chicken chicken;
	private ArrayList<Platform> platforms;
	
	private int worldLength;
	
	private boolean levelWon; // if the level has been won or not
	private boolean camFollowChicken;
    
    private int displayWidth;
    private int displayHeight;
    private String launcherType;
	
	private double score;
        
        private ArrayList<GenObj> genObjectsList;
        
    private double ceilingLampCounter = 0;
	
	public PlayWorld(ICFCWorldContainer container, int length)
	{
		this.container = container;
		score = 0;
		initWorld(length);
	}
    
    public PlayWorld(ICFCWorldContainer container, int length, int score, 
            int displayWidth, int displayHeight, String launcherType)
	{
		this.container = container;
		this.score = score;
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.launcherType = launcherType;
		initWorld(length);
	}
	
	public void initWorld(int length)
	{
        ACC.addActionListener(this);
		worldLength = length;
		levelWon = false;
		camFollowChicken = true;
		
		chicken = new Chicken(300, 200);
		platforms = new ArrayList<Platform>();
                
        genObjectsList = new ArrayList<GenObj>();
             
        // Add the floor sawblades
        for(int i = 0; i < 4; i++){
            genObjectsList.add(new Sawblade(600 + (i*(worldLength / 4)), Sawblade.SAWBLADE_RADIUS));
        }
		
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
				boolean isBomb = (rnd.nextInt(4) == 0);
				int randLength = thisLength - Cage.SIZE;
				if (randLength < 1)
					randLength = 1;
				genObjectsList.add(new Cage((IScorer) this, rnd.nextInt(randLength) + lastPlatformEnd, thisPlatformHeight, isBomb));
			}
			
            /* Sawblade Spawning */
			if (rnd.nextInt(8) == 1)
			{
				int randLength = thisLength - Sawblade.SAWBLADE_RADIUS;
				if (randLength < 1)
					randLength = 1;
				genObjectsList.add(new Sawblade(rnd.nextInt(randLength) + lastPlatformEnd, thisPlatformHeight + Sawblade.SAWBLADE_RADIUS));
			}
			
			lastPlatformEnd += thisLength;	
		}	
        
        // Add ceiling lamps
        for(int i = 0; i < 16; i++){
            genObjectsList.add(new CeilingLamp(i*500, 20));
        }
        
	}
	
	public void update(float delta)
	{
                // Update generic objects
                for (GenObj go : genObjectsList){
                    go.update(delta);
                    Rectangle r = go.getWorldCollisionRectangle();
                    if(r != null){
                        if(r.overlaps(chicken.getWorldCollisionRectangle())){
                            go.onChickenCollide(chicken);
                        }
                    }
                }
            
		// check if the chicken is diving
		//if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
		//	chicken.setDiving(true);
		//else
		//	chicken.setDiving(false);
		
		// check if the chicken is jumping
		//if (Gdx.input.isKeyPressed(Input.Keys.UP))
		//	chicken.setJumping(true);
		//else
		//	chicken.setJumping(false);
		
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
        
        if(chicken.isAlive() && !levelWon){
            score += 2 * delta;
        }
        
        /*ceilingLampCounter += delta;
        
        if(ceilingLampCounter > 0.1){
            ceilingLampCounter = -2;
            genObjectsList.add(new CeilingLamp(chicken.getX()+400, 100));
        }*/
		
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		// call render code on everything in world		
		
		for (Platform platform : platforms)
			platform.render(batch, renderer);
		
		chicken.render(batch);
                
        for (GenObj go : genObjectsList){
            go.render(batch, renderer);
        }
	}
	
	public void keyDown(int keycode)
	{
		if (levelWon)
		{
			System.out.println("Sending nextLevel message");
			container.nextLevel((int)score); // start the next level, passing out the score
		}
		else if (!chicken.isAlive())
		{
            container.nextLevel(0);
		}
		else
		{
			switch (keycode)
			{
			case Input.Keys.SPACE: ACC.broadcastAction("jump"); break;
			case Input.Keys.UP: ACC.broadcastAction("jump"); break;
            case Input.Keys.W: ACC.broadcastAction("jump");
			case Input.Keys.DOWN: ACC.broadcastAction("dive"); break;
            case Input.Keys.S: ACC.broadcastAction("dive");
			case Input.Keys.A: ACC.broadcastAction("kick"); break;
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

	@Override
	public void increaseScore(int amount) {
		score += amount;
	}
	
	public int getScore()
	{
		return (int) score;
	}

    @Override
    public void onAction(String actionName) {
        if(actionName == "jump"){
            if (chicken.isAlive()) {
                chicken.jump();
                chicken.setJumping(true);
            }
        }
        if(actionName == "jumpEnd"){
            if (chicken.isAlive()) chicken.setJumping(false);
        }
        if(actionName == "dive"){
            if (chicken.isAlive()) chicken.setDiving(true);
        }
        if(actionName == "kick"){
            if (chicken.isAlive()) chicken.kick();
        }
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(screenX < (displayWidth/2)){
            ACC.broadcastAction("kick");
        } else {
            if(screenY < (displayHeight/2)){
                System.out.println("should jump");
                ACC.broadcastAction("jump");
            } else {
                ACC.broadcastAction("dive");
            }
        }
        if(!chicken.isAlive() || levelWon){
            container.nextLevel(0);
        }

        return false;
    }

    boolean touchUp(int screenX,int screenY,int pointer,int button) {
        if(screenX < (displayWidth/2)){
            //
        } else {
            if(screenY < (displayHeight/2)){
                ACC.broadcastAction("jumpEnd");
            } else {
                //
            }
        }
        return false;
    }

    boolean keyUp(int keycode) {
        switch(keycode){
            case Input.Keys.W: ACC.broadcastAction("jumpEnd");
            case Input.Keys.SPACE: ACC.broadcastAction("jumpEnd");
            case Input.Keys.UP: ACC.broadcastAction("jumpEnd");
        }
        return false;
    }
	
}
