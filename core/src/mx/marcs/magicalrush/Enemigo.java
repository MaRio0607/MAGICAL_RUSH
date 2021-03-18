package mx.marcs.magicalrush;

import com.badlogic.gdx.graphics.Texture;

public class Enemigo extends Objeto {
    public Enemigo(Texture texture, float x, float y){
        super(texture, x, y);// el constructor de la super clase
    }
    //mover
    public void  moverHorizontal (float dx){
        sprite.setX(sprite.getX()+dx);
    }
    //Dibujar



    //objeto

}
