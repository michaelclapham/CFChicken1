package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import uk.co.sparedice.cfchicken1.AssetLoader;

public class Sawblade implements GenObj {
	
	// The x and y refers to the centre of the sawblade
	private float x;
	private float y;
        
    private float SAWBLADE_RADIUS = 25;

    private float angle = 0;
    private float angleStep = 10f;
    
    public static final int TYPE_PLATFORM = 0;
    public static final int TYPE_PLANK = 1;
    
    private int type = TYPE_PLATFORM;
    
	public Sawblade(int x, int y)
	{
		this(x,y,TYPE_PLATFORM);
	}
    
    public Sawblade(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
        this.type = type;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		batch.begin();
            batch.draw(new TextureRegion(AssetLoader.sawblade1), x - SAWBLADE_RADIUS, y - SAWBLADE_RADIUS, SAWBLADE_RADIUS , SAWBLADE_RADIUS, SAWBLADE_RADIUS * 2, SAWBLADE_RADIUS * 2, 1, 1, angle);
            if(type == TYPE_PLANK)
                batch.draw(AssetLoader.sawblade1_plank, x - SAWBLADE_RADIUS , y - SAWBLADE_RADIUS, SAWBLADE_RADIUS * 2f, SAWBLADE_RADIUS * 4.8f);
		batch.end();
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

    @Override
    public void update(float delta) {
        angle+= delta * angleStep * 10;
    }

    @Override
    public Rectangle getWorldCollisionRectangle() {
        return new Rectangle(x - SAWBLADE_RADIUS, y - SAWBLADE_RADIUS, SAWBLADE_RADIUS*2, SAWBLADE_RADIUS*2);
    }

    @Override
    public void onChickenCollide(Chicken c) {
        if(c.isAlive()){
            c.die();
        }
    }
	
	
	
}
