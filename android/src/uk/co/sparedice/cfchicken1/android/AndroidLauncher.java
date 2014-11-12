package uk.co.sparedice.cfchicken1.android;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import uk.co.sparedice.cfchicken1.CFChicken1;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point(display.getWidth(), display.getHeight());
		initialize(new CFChicken1(size.x, size.y, "android"), config);
	}
}
