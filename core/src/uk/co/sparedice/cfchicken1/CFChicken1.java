package uk.co.sparedice.cfchicken1;

import uk.co.sparedice.cfchicken_playscreen.PlayScreen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CFChicken1 extends Game {
	
	PlayScreen playScreen;
	
	@Override
	public void create () {
		
		AssetLoader.load();
		
		playScreen = new PlayScreen();
		setScreen(playScreen);
		Gdx.input.setInputProcessor(playScreen);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}
}
