package mx.marcs.magicalrush;

import com.badlogic.gdx.Game;

import mx.marcs.magicalrush.menu.PantallaMenu;

public class Juego  extends Game {
	@Override
	public void create () {
		//Muestre la primer pantlla
		setScreen(new PantallaMenu(this)); //Primera pantalla visible
	}
	//Comentario de España :3
	//Rafa estuvo aqui
}
