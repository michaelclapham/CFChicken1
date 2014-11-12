package uk.co.sparedice.cfchicken_playscreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

public class PlayScreen implements Screen, InputProcessor, ICFCWorldContainer {

	private PlayWorld world;
	private PlayGUI gui;
	private PlayRenderer renderer;
    private int worldLength = 6000;
    private final int displayWidth;
    private final int displayHeight;
    private final String launcherType;
	
	public PlayScreen(int displayWidth, int displayHeight, String launcherType)
	{
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;
        this.launcherType = launcherType;
		world = new PlayWorld((ICFCWorldContainer) this, worldLength, 0, displayWidth, displayHeight, launcherType); // pass in the length of the level
		gui = new PlayGUI(world);
		renderer = new PlayRenderer(world, gui);
	}

	/*---------------------- World Container Methods ---------------------*/
	
	public void nextLevel(int score)
	{
		world = new PlayWorld((ICFCWorldContainer) this, worldLength, 0, displayWidth, displayHeight, launcherType);
		renderer.linkWorld(world);
		gui.linkWorld(world);
	}
	
	/*---------------------- Screen Methods -----------------------*/
	
	@Override
	public void render(float delta) {
		
		// Update everything
		world.update(delta);
		gui.update(delta);
		
		
		// Render everything
		renderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		
		renderer.resize();
		
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
	
	/*------------------- Input Processor Methods -------------------*/
	
	@Override
	public boolean keyDown(int keycode) {

		world.keyDown(keycode);
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		return world.touchUp(screenX,screenY,pointer,button);
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
