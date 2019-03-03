package by.vg.zombie.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import by.vg.zombie.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Zombie";
		config.useGL30 =  true;
		config.width = 1200;
		config.height = 720;
		new LwjglApplication(new Main(), config);
	}
}
