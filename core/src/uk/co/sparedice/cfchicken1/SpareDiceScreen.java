package uk.co.sparedice.cfchicken1;

import uk.co.sparedice.cfchicken_playscreen.PlayScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpareDiceScreen implements Screen {
	
	LauncherInfo info;
	float time = 0;
	private Game game;
	
	public SpareDiceScreen(LauncherInfo info, Game g){
		this.info = info;
		this.game = g;
	}

	@Override
	public void render(float delta) {
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
			batch.draw(AssetLoader.sparedice_splash, 0, 0, info.displayWidth, info.displayHeight);
		batch.end();
		
		if(time > 2 && time < 6){
			PlayScreen playScreen = new PlayScreen(info.displayWidth,info.displayHeight,info.launcherType);
			game.setScreen(playScreen);
			time = 5;
			Gdx.input.setInputProcessor(playScreen);
		} else {
			time += delta;
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
