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
    
    private float WORLD_WIDTH = 500;
    private float WORLD_HEIGHT = 300;
	
	// Takes the world and GUI the renderer is to render
	public PlayRenderer(PlayWorld world, PlayGUI gui)
	{
		this.world = world;
		this.gui = gui;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, cam);
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}
	
	public void render()
	{
		if (world.isCamFollowingChicken())
		{
			cam.position.set(world.getChicken().getX(), (WORLD_HEIGHT / 2) + (world.getChicken().getY() - (WORLD_HEIGHT / 2)) / 5, 0);
			cam.update();
		}
		
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		
			shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1f);
			shapeRenderer.rect(cam.position.x - (viewport.getWorldWidth() / 2), 0, WORLD_WIDTH, WORLD_HEIGHT);
		
			shapeRenderer.setColor(0.3f, 0.3f, 0.3f, 1f);
			float left = cam.position.x - (viewport.getWorldWidth() / 2) - ((cam.position.x - (viewport.getWorldWidth() / 2)) % 150);
			for (int i = 0; i < 5; i++)
			{
				shapeRenderer.rect(left + (i * 150), 0, 75, WORLD_HEIGHT);
			}
			
		shapeRenderer.end();
		
		batch.begin();
			left = cam.position.x - (viewport.getWorldWidth() / 2) - ((cam.position.x - (viewport.getWorldWidth() / 2)) % WORLD_WIDTH);
			batch.draw(AssetLoader.floor, left, -WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(AssetLoader.floor, left + WORLD_WIDTH, -WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(AssetLoader.background, left, 0, WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(AssetLoader.background, left + WORLD_WIDTH, 0, WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(AssetLoader.ceiling, left, WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
			batch.draw(AssetLoader.ceiling, left + WORLD_WIDTH, WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
		batch.end();
		
		world.render(batch, shapeRenderer);
		
		batch.begin();
			if (cam.position.x >= world.getWorldLength() - 200)
			{
				batch.draw(AssetLoader.blackGradient, world.getWorldLength(), WORLD_HEIGHT, WORLD_WIDTH / 2, WORLD_HEIGHT);
				batch.draw(AssetLoader.blackGradient, world.getWorldLength(), 0, WORLD_WIDTH / 2, WORLD_HEIGHT);
				batch.draw(AssetLoader.blackGradient, world.getWorldLength(), -WORLD_HEIGHT, WORLD_WIDTH / 2, WORLD_HEIGHT);
			}
		batch.end();
		
		gui.render(batch, cam.position.x - (WORLD_WIDTH / 2), cam.position.y - (WORLD_HEIGHT / 2));
	}
	
	public void resize()
	{
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		batch.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void linkWorld(PlayWorld world) {
		this.world = world;
		
	}
	
}
