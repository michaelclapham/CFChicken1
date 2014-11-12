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
	private LauncherInfo info;

    public CFChicken1(int displayWidth, int displayHeight, String launcherType) {
        info = new LauncherInfo(displayWidth, displayHeight, launcherType);
    }
	
	@Override
	public void create () {
		
		AssetLoader.load();
		
		AssetLoader.launcherType = info.launcherType;
		
		//playScreen = new PlayScreen(info.displayWidth,info.displayHeight,info.launcherType);
		SpareDiceScreen sdc = new SpareDiceScreen(info,this);
		setScreen(sdc);
		
	}

	@Override
	public void dispose()
	{
		super.dispose();
		AssetLoader.dispose();
	}
	
	public void setScreen(PlayScreen ps){
		setScreen(ps);
	}
}
