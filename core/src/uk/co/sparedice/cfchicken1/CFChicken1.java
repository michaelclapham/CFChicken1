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
    private final int displayWidth;
    private final int displayHeight;
    private final String launcherType;

    public CFChicken1(int displayWidth, int displayHeight, String launcherType) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.launcherType = launcherType;
    }
	
	@Override
	public void create () {
		
		AssetLoader.load();
		
		playScreen = new PlayScreen(displayWidth,displayHeight,launcherType);
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
