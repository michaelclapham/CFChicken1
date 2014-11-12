package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Platform {

	public static final int HEIGHT = 20;
	
	// The x and y refers to the top of the platform
	private float x;
	private float y;
	private float length;
	
	public Platform(int x, int y, int length)
	{
		this.x = x;
		this.y = y;
		this.length = length;
	}
	
	public void render(SpriteBatch batch, ShapeRenderer renderer)
	{
		batch.begin();
			batch.draw(new TextureRegion(AssetLoader.platform, (int) length, 40), x, y - HEIGHT, length, HEIGHT); 
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

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}
	
	
	
}
