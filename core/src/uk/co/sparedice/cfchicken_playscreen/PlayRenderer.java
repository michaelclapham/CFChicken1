package uk.co.sparedice.cfchicken_playscreen;

import uk.co.sparedice.cfchicken1.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PlayRenderer {

	private PlayWorld world;
	private PlayGUI gui;
	
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private OrthographicCamera cam;
	private FitViewport viewport;
	
	// Takes the world and GUI the renderer is to render
	public PlayRenderer(PlayWorld world, PlayGUI gui)
	{
		this.world = world;
		this.gui = gui;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 400, 300);
		viewport = new FitViewport(400, 300, cam);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void render()
	{
		if (world.getChicken().isAlive())
		{
			cam.position.set(world.getChicken().getX(), world.getChicken().getY(), 0);
			cam.update();
		}
		
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		
			shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);
			shapeRenderer.rect(cam.position.x - (viewport.getWorldWidth() / 2), 0, 400, 300);
		
			shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 1f);
			float left = cam.position.x - (viewport.getWorldWidth() / 2) - ((cam.position.x - (viewport.getWorldWidth() / 2)) % 150);
			for (int i = 0; i < 5; i++)
			{
				shapeRenderer.rect(left + (i * 150), 0, 75, 300);
			}
			
		shapeRenderer.end();
		
		batch.begin();
			left = cam.position.x - (viewport.getWorldWidth() / 2) - ((cam.position.x - (viewport.getWorldWidth() / 2)) % 400);
			batch.draw(AssetLoader.floor, left, -300, 400, 300);
			batch.draw(AssetLoader.floor, left + 400, -300, 400, 300);
			batch.draw(AssetLoader.ceiling, left, 300, 400, 300);
			batch.draw(AssetLoader.ceiling, left + 400, 300, 400, 300);
		batch.end();
		
		world.render(batch, shapeRenderer);
		gui.render(batch);
	}
	
	public void resize()
	{
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 400, 300);
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
}
