package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import mx.marcs.magicalrush.Objeto;

public class Personaje extends Objeto {
    public Personaje(Texture texture,float x, float y){
        super(texture, x, y);// el constructor de la super clase
    }
    public Sprite getSprite() {
        return sprite;
    }
    //metodo para mover el sprite
    public  void  mover(float dx){
        sprite.setX(sprite.getX()+dx);
    }
}
