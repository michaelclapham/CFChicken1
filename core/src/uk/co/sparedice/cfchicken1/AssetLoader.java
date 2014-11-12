package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture badlogicLogo;
	
	public static Texture chicken;
	public static Texture chicken_kicking;
	
	public static Texture chick;
        
        public static Texture[] chicken_anim_running1;
	
	public static Texture floorTexture;
	public static TextureRegion floor;
	public static TextureRegion ceiling;
	public static Texture platform;
	public static Texture blackGradient;
	public static Texture background;
        
        public static Texture sawblade1;
        public static Texture sawblade1_plank;
	
	public static Texture cage_chick;
	public static Texture cage_bomb;
	public static Texture cage_broken;

	public static Texture sawblade1_platform_base;

	public static Texture sparedice_splash;
	public static Texture cfc_splash_screen;
	
	public static String launcherType;
	
	// Called at the start of program execution
	public static void load()
	{
		badlogicLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
		chicken = new Texture(Gdx.files.internal("images/CF_Chicken1-02.png"));
		chicken_kicking = new Texture(Gdx.files.internal("images/CF_Chicken1-02-Kicking.png"));
		
		chick = new Texture(Gdx.files.internal("images/Chick.png"));
                
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
		background = new Texture(Gdx.files.internal("images/Background.png"));
		
		cage_chick = new Texture(Gdx.files.internal("images/CageChick.png"));
		cage_bomb = new Texture(Gdx.files.internal("images/CageBomb.png"));
		cage_broken = new Texture(Gdx.files.internal("images/CageBroken.png"));
                
        sawblade1 = intTex("images/objects/sawblades/blade1.png");
        sawblade1_plank = intTex("images/objects/sawblades/plank1.png");
        sawblade1_platform_base = intTex("images/objects/sawblades/platform_base.png");
        
        sparedice_splash = intTex("images/spare_dice_screen.jpg");
        cfc_splash_screen = intTex("images/cfc_start_screen1.jpg");
	}
        
        private static Texture intTex(String dir){
            return new Texture(Gdx.files.internal(dir));
        }
	
	// Any texture that is created should be disposed of here
	public static void dispose()
	{
		badlogicLogo.dispose();
		chicken.dispose();
		chicken_kicking.dispose();
		chick.dispose();
		floorTexture.dispose();
		platform.dispose();
		blackGradient.dispose();
		background.dispose();
		cage_chick.dispose();
		cage_bomb.dispose();
		cage_broken.dispose();
		sawblade1.dispose();
		sawblade1_plank.dispose();
	}
	
}
