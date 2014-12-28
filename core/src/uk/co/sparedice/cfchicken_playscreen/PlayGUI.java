package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayGUI {

	BitmapFont font;
	PlayWorld world;
	private double time = 0;
	
	public PlayGUI(PlayWorld world)
	{
		this.world = world;
		font = new BitmapFont();
	}
	
	public void update(float delta)
	{
		time += delta;
	}
	
	public void render(SpriteBatch batch, float screenLocationX, float screenLocationY)
	{
		batch.begin();
			font.draw(batch, "SCORE: " + world.getScore(), screenLocationX + 50, screenLocationY + 250);
			//batch.draw(AssetLoader.ceiling_lamp_on, screenLocationX, screenLocationY,200,200);
			float sx = screenLocationX;
			float sy = screenLocationY;
			
			if(time < 3 && AssetLoader.launcherType == "android"){
				font.draw(batch, "Tap here to jump", 330 + sx, 260 + sy);
				font.draw(batch, "Tap here to dive", 330 + sx, 60 + sy);
				font.draw(batch, "Tap here to kick", 50 + sx, 150 + sy);
			}
		batch.end();
	}
	
	public void linkWorld(PlayWorld world)
	{
		this.world = world;
	}
	
}
