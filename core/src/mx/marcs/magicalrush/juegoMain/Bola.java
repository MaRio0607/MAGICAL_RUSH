package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.graphics.Texture;

import mx.marcs.magicalrush.Objeto;
/*
Las bolas de fuego que lanza el personaje
 */
public class Bola extends Objeto {
    private float vX=350;

    public Bola(Texture texture, float x, float y){
        super(texture,x,y);
    }

    //Mover a la dercha la bola de fuego
    public void  mover(float delta){
        float dx=vX*delta;
        sprite.setX(sprite.getX()+dx);
    }


    public float getX() {
        return sprite.getX();
    }
}
