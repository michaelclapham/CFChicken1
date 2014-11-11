package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.Color;
import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Cage implements GenObj {

	public static final int SIZE = 50;
	
	private float x;
	private float y;
	private boolean isBomb;
	private boolean isBroken;
    
    private float birdX;
    private float birdY;
    private float birdAngle = 0;
    private float birdSpeed = 6;
	
	public Cage(float x, float y, boolean isBomb)
	{
		this.x = x;
		this.y = y;
        birdX = x;
        birdY = y;
		this.isBomb = isBomb;
		isBroken = false;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		batch.begin();
			if (isBroken)
				batch.draw(AssetLoader.cage_broken, x, y, SIZE, SIZE);
			else if (isBomb)
				batch.draw(AssetLoader.cage_bomb, x, y, SIZE, SIZE);
			else
				batch.draw(AssetLoader.cage_chick, x, y, SIZE, SIZE);
		batch.end();
        
        renderer.begin(ShapeRenderer.ShapeType.Filled);
            if(isBroken && !isBomb){
                renderer.setColor(Color.YELLOW);
                renderer.circle(birdX, birdY, 20);
            }
        renderer.end();
	}
	
	public void breakCage()
	{
		isBroken = true;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isBomb() {
		return isBomb;
	}

	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	
	public boolean isBroken()
	{
		return isBroken;
	}

    @Override
    public void update(float delta) {
        if(isBroken && !isBomb){
            birdX += birdSpeed * Math.sin(Math.toRadians(birdAngle));
            birdY += birdSpeed * Math.cos(Math.toRadians(birdAngle));
        }
    }

    @Override
    public Rectangle getWorldCollisionRectangle() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    @Override
    public void onChickenCollide(Chicken c) {
        if(c.isKicking() && !isBroken){
            if(isBomb){
                c.die();
            } else {
                // @TODO: RELEASE THE BIRDS!!!!
                birdX = x;
                birdY = y;
                birdAngle = (float) (Math.random()*360);
            }
            isBroken = true;
            System.out.println("CACAW!!!! I'M FREE!!!");
        }
    }

	
}
