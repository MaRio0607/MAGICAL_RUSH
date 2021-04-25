package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import mx.marcs.magicalrush.Objeto;

public class Slime extends Objeto {
    private Animation<TextureRegion> animation;
    private float timerAnimacion=0;
    private EstadoSlime estado;

    //fisica
    private float velocidadX=-300;    //pixeles/segndo
    public Slime(Texture texture, float x, float y){
        TextureRegion region= new TextureRegion(texture);
        TextureRegion [][] texturas= region.split(128,116);

        //Animacion
        TextureRegion[] arrFrames={texturas[0][0], texturas[0][1]};
        animation= new Animation<TextureRegion>(0.3f,arrFrames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion=0;

        sprite=new Sprite(texturas [0][0]);
        sprite.setPosition(x,y);
        estado = EstadoSlime.VIVO;
    }
    @Override
    public void render(SpriteBatch batch){
        timerAnimacion+= Gdx.graphics.getDeltaTime();
        TextureRegion frame= animation.getKeyFrame(timerAnimacion);
        batch.draw(frame,sprite.getX(),sprite.getY());
    }

    public EstadoSlime getEstado() {
        return estado;
    }

    public void setEstado(EstadoSlime nuevoEstado){
        this.estado = nuevoEstado;
    }

    //mover enemigos
    public void moverIzquierda(float delta) {
        float dx =velocidadX*delta;
        sprite.setX(sprite.getX()+dx);

    }

}
