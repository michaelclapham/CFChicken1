package uk.co.sparedice.cfchicken1;

import uk.co.sparedice.cfchicken_playscreen.PlayScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen implements Screen, InputProcessor {
	
	LauncherInfo info;
	float time = 0;
	private Game game;
	private boolean letsGo = false;
	
	public MainMenuScreen(LauncherInfo info, Game g){
		this.info = info;
		this.game = g;
	}

	@Override
	public void render(float delta) {
		SpriteBatch batch = new SpriteBatch();
		batch.begin();
			batch.draw(AssetLoader.cfc_splash_screen, 0, 0, info.displayWidth, info.displayHeight);
		batch.end();
		
		if(time > 20 && time < 6 || letsGo){
			PlayScreen playScreen = new PlayScreen(info.displayWidth,info.displayHeight,info.launcherType);
			game.setScreen(playScreen);
			time = 5;
			letsGo = false;
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		letsGo = true;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		letsGo = true;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
