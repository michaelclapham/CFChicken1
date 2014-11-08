package uk.co.sparedice.cfchicken1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
	
	public static Texture badlogicLogo;
	
	public static void load()
	{
		badlogicLogo = new Texture(Gdx.files.internal("badlogic.jpg"));
	}
	
	public static void dispose()
	{
		badlogicLogo.dispose();
	}
	
}
