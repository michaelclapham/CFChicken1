package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture badlogicLogo;
	
	public static Texture chicken;
	public static Texture chicken_kicking;
        
        public static Texture[] chicken_anim_running1;
	
	public static Texture floorTexture;
	public static TextureRegion floor;
	public static TextureRegion ceiling;
	public static Texture platform;
	public static Texture blackGradient;
	
	public static Texture cage_chick;
	public static Texture cage_bomb;
	public static Texture cage_broken;
	
	// Called at the start of program execution
	public static void load()
	{
		badlogicLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
		chicken = new Texture(Gdx.files.internal("images/CF_Chicken1-02.png"));
		chicken_kicking = new Texture(Gdx.files.internal("images/CF_Chicken1-02-Kicking.png"));
                
                // Chicken runing animation 1
                chicken_anim_running1 = new Texture[10];
                for(int i = 0; i < 5; i++)
                    chicken_anim_running1[i] = new Texture(Gdx.files.internal("images/anims/running_1/" + i + ".png"));
                for(int i = 0; i < 5; i++)
                    chicken_anim_running1[i+5] = new Texture(Gdx.files.internal("images/anims/running_1/" + (4-i) + ".png"));
		
		floorTexture = new Texture(Gdx.files.internal("images/Floor.png"));
		floor = new TextureRegion(floorTexture);
		ceiling = new TextureRegion(floorTexture);
		ceiling.flip(false, true);
		platform = new Texture(Gdx.files.internal("images/Platform.png"));
		blackGradient = new Texture(Gdx.files.internal("images/BlackGradient.png"));
		
		cage_chick = new Texture(Gdx.files.internal("images/CageChick.png"));
		cage_bomb = new Texture(Gdx.files.internal("images/CageBomb.png"));
		cage_broken = new Texture(Gdx.files.internal("images/CageBroken.png"));
	}
	
	// Any texture that is created should be disposed of here
	public static void dispose()
	{
		badlogicLogo.dispose();
		chicken.dispose();
		chicken_kicking.dispose();
		floorTexture.dispose();
		platform.dispose();
		blackGradient.dispose();
		cage_chick.dispose();
		cage_bomb.dispose();
		cage_broken.dispose();
	}
	
}
