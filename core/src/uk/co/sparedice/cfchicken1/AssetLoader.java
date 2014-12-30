package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

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
        
    public static Texture ceiling_lamp_on;
	
	public static String launcherType;
    public static I18NBundle mainBundle;
    
    // RENDERED TEXT
    public static Texture rt_score;
    public static Texture rt_tapToKick;
    public static Texture rt_tapToDive;
    public static Texture rt_tapToJump;
    public static Texture rt_instructs;
    
    public static boolean USING_RT = false;
    
    private static String ld;
    public static Texture cfc_splash_screen_bg;
    
	
	// Called at the start of program execution
	public static void load()
	{
        // Load localization bundle
        FileHandle baseFileHandle = Gdx.files.internal("i18n/MainBundle");
        Locale locale = new Locale("pt", "PT");
        mainBundle = I18NBundle.createBundle(baseFileHandle,locale);
        ld = mainBundle.get("localeDirectory");
        
        // Load pre-rendered text for scripts with massive alphabets
        if(mainBundle.get("usingRenderedText").equalsIgnoreCase("true")){
            USING_RT = true;
            rt_score = intTex("i18n/zh/rt/score.png");
            rt_tapToKick = intTex("i18n/"+ld+"/rt/tap_to_kick.png");
            rt_tapToDive = intTex("i18n/"+ld+"/rt/tap_to_dive.png");
            rt_tapToJump = intTex("i18n/"+ld+"/rt/tap_to_jump.png");
            rt_instructs = intTex("i18n/"+ld+"/rt/instructions1.png");
        }
        
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
		//background = new Texture(Gdx.files.internal("images/Background.png"));
		background = new Texture(Gdx.files.internal("images/dark_rusty_steel.jpg"));
        
		cage_chick = new Texture(Gdx.files.internal("images/CageChick.png"));
		cage_bomb = new Texture(Gdx.files.internal("images/CageBomb.png"));
		cage_broken = new Texture(Gdx.files.internal("images/CageBroken.png"));
                
        sawblade1 = intTex("images/objects/sawblades/blade1.png");
        sawblade1_plank = intTex("images/objects/sawblades/plank1.png");
        sawblade1_platform_base = intTex("images/objects/sawblades/platform_base.png");
        
        sparedice_splash = intTex("images/spare_dice_screen.jpg");
        cfc_splash_screen_bg = intTex("images/CF_Chicken1-31.png");
        cfc_splash_screen = intTexLocalized("images/splash1.png");
        
        
        ceiling_lamp_on = intTex("images/objects/lighting/ceiling_lamp1_on.png");
	}
        
    private static Texture intTex(String dir){
        return new Texture(Gdx.files.internal(dir));
    }
    
    private static Texture intTexLocalized(String dir){
        Texture retTex;
        try {
            retTex = new Texture(Gdx.files.internal("i18n/"+ld+"/"+dir));
        } catch (Exception ex){
            retTex = new Texture(Gdx.files.internal(dir));
        }
        return retTex;
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
