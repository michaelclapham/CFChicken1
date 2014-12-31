package uk.co.sparedice.cfchicken1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Files;
import uk.co.sparedice.cfchicken1.CFChicken1;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 480;
        config.addIcon("icon_32.png", Files.FileType.Internal);
		new LwjglApplication(new CFChicken1(config.width, config.height,"desktop"), config);
	}
}
