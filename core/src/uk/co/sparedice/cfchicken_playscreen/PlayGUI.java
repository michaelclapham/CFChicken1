package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayGUI {

	BitmapFont font;
	PlayWorld world;
	
	public PlayGUI(PlayWorld world)
	{
		this.world = world;
		font = new BitmapFont();
	}
	
	public void update(float delta)
	{
		
	}
	
	public void render(SpriteBatch batch, float screenLocationX, float screenLocationY)
	{
		batch.begin();
			font.draw(batch, "SCORE: " + world.getScore(), screenLocationX + 50, screenLocationY + 250);
		batch.end();
	}
	
	public void linkWorld(PlayWorld world)
	{
		this.world = world;
	}
	
}
