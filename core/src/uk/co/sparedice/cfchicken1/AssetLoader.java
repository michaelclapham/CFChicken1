package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
	
	public static Texture badlogicLogo;
	
	public static Texture chicken;
	
	// Called at the start of program execution
	public static void load()
	{
		badlogicLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
		chicken = new Texture(Gdx.files.internal("images/CF_Chicken1-02.png"));
	}
	
	// Any texture that is created should be disposed of here
	public static void dispose()
	{
		badlogicLogo.dispose();
		chicken.dispose();
	}
	
}
