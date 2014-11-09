package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		renderer.begin(ShapeType.Filled);
			renderer.setColor(0.2f, 0.2f, 0.2f, 1f);
			renderer.rect(x, y - HEIGHT, length, HEIGHT);
		renderer.end();
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
