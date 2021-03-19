package mx.marcs.magicalrush.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import mx.marcs.magicalrush.Juego;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//definir el tamaño incial
		config.width=1280/2;
		config.height=720/2;
		new LwjglApplication(new Juego(), config);
	}
}
