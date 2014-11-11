package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public static Texture badlogicLogo;
	
	public static Texture chicken;
        
        public static Texture[] chicken_anim_running1;
	
	public static Texture floorTexture;
	public static TextureRegion floor;
	public static TextureRegion ceiling;
	public static Texture platform;
	public static Texture blackGradient;
        
        public static Texture sawblade1;
        public static Texture sawblade1_plank;
	
	// Called at the start of program execution
	public static void load()
	{
		badlogicLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
		chicken = new Texture(Gdx.files.internal("images/CF_Chicken1-02.png"));
                
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
                
                sawblade1 = intTex("images/objects/sawblades/blade1.png");
                sawblade1_plank = intTex("images/objects/sawblades/plank1.png");
	}
        
        private static Texture intTex(String dir){
            return new Texture(Gdx.files.internal(dir));
        }
	
	// Any texture that is created should be disposed of here
	public static void dispose()
	{
		badlogicLogo.dispose();
		chicken.dispose();
		floorTexture.dispose();
		platform.dispose();
		blackGradient.dispose();
	}
	
}
