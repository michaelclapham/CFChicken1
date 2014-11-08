package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayWorld {

	public void update(float delta)
	{
		// update everything
	}
	
	public void render(SpriteBatch batch)
	{
		// call render code on everything in world
		batch.begin();
			batch.draw(AssetLoader.badlogicLogo, 50, 50, 100, 100);
		batch.end();
	}
	
}
