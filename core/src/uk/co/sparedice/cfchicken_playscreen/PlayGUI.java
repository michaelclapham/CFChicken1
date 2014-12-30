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
            if(AssetLoader.USING_RT){
                batch.draw(AssetLoader.rt_score, screenLocationX + 50, screenLocationY + 220);
                font.draw(batch, ":"+world.getScore(),
                        screenLocationX + 50 + AssetLoader.rt_score.getWidth(), 
                        screenLocationY + 250);
            } else {
                font.draw(batch, AssetLoader.mainBundle.format("score", world.getScore()),
                    screenLocationX + 50, screenLocationY + 250);
            }
			//batch.draw(AssetLoader.ceiling_lamp_on, screenLocationX, screenLocationY,200,200);
			float sx = screenLocationX;
			float sy = screenLocationY;
			
			if(time < 4 && AssetLoader.launcherType.equals("android")){
                if(AssetLoader.USING_RT){
                    batch.draw(AssetLoader.rt_tapToJump, 320 + sx, 250 + sy);
                    batch.draw(AssetLoader.rt_tapToDive, 320 + sx, 50 + sy);
                    batch.draw(AssetLoader.rt_tapToKick, 40 + sx, 140 + sy);
                } else {
                    font.draw(batch, AssetLoader.mainBundle.get("tap_to_jump"), 330 + sx, 260 + sy);
                    font.draw(batch,  AssetLoader.mainBundle.get("tap_to_dive"), 330 + sx, 60 + sy);
                    font.draw(batch,  AssetLoader.mainBundle.get("tap_to_kick"), 50 + sx, 150 + sy);
                }
				
			}
            if(time < 6){
                if(AssetLoader.USING_RT){
                    batch.draw(AssetLoader.rt_instructs, 8 + sx, sy + 8);
                } else {
                    font.draw(batch, AssetLoader.mainBundle.get("instruct_1"), 70 + sx, 70+ sy);
                    font.draw(batch, AssetLoader.mainBundle.get("instruct_2"), 70 + sx, 55 + sy);
                    font.draw(batch, AssetLoader.mainBundle.get("instruct_3"), 70 + sx, 40 + sy);
                }
                
            }
		batch.end();
	}
	
	public void linkWorld(PlayWorld world)
	{
		this.world = world;
	}
	
}
