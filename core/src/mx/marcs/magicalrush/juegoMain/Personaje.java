package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

import mx.marcs.magicalrush.Objeto;

public class Personaje extends Objeto {

    public static  final float VELOCIDAD_Y=-4F; //Velocidad de caída
    public static final float VELOCIDAD_X=2; //Velocidad horizontal
    private Sprite sprite; //Sprite cuando no se mueve

    /*
    private Animation animacion;    //Caminando
    private float timerAnimacion; //tiempo para calcular el frame
     */

    //ANIMACIÓN
    private EstadoRUI CAMINANDO;
    private EstadoRUI SALTANDO;
    private EstadoRUI SUBIENDO;
    private EstadoRUI BAJANDO;
    private EstadoRUI MOV_IZQUIERDA;
    private EstadoRUI MOV_DERECHA;

    //CALCULADOR DE FRAME
    private float TimerAnimation;

    //Estado Personaje
    private EstadoRUI estadoMoviento;


    private Animation<TextureRegion> animacionCorrer;
    private float timerAnimation; //sabre el frame que corresponde mostrar

    //Salto
    private final float yBase=54;    //Suelo, piso
    private float tAire;             //Tiempo que lleva en al aire
    private float tVuelo;            //Tiempo de vuelo total
    private final float v0y=200;     //Componete en y de la velocidad
    private final  float g= 150;    //Pixeles sobre segundo al cuadrado
    private  EstadoRUI estadoRUI; //SAltando,caminado, bajando, izquierda, derecha

    public Personaje(Texture texture,float x, float y){
        // super(texture, x, y);// el constructor de la super clase
        TextureRegion region=new TextureRegion(texture);
        TextureRegion[][]texturas= region.split(104,128);

        //cuadros para caminar
        TextureRegion[] arrFramesCaminar={ texturas[2][3],texturas[2][2], texturas[2][1], texturas[2][0]};
        animacionCorrer= new Animation<TextureRegion>(0.2f,arrFramesCaminar);
        animacionCorrer.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimation=0;

        //IDLE
        sprite=new Sprite(texturas[1][2]);
        sprite.setPosition(x,y);

        //estado inical
        estadoRUI=EstadoRUI.CAMINADO;
    }
    //Reescribir el metodo render para mostrar la animacion
    public void render(SpriteBatch batch){
        float delta= Gdx.graphics.getDeltaTime();
        switch (estadoRUI){
            case CAMINADO:
                timerAnimation+=delta;
                TextureRegion frame=animacionCorrer.getKeyFrame(timerAnimation);
                batch.draw(frame, sprite.getX(),sprite.getY());
                break;
            case SALTANDO:
                actualizar();  //al saltar calcula la nueva poscion
                super.render(batch);
                break;
        }
        //



    }
    //Calcula el movimient vertical
    private void actualizar() {
        float delta=Gdx.graphics.getDeltaTime();
        tAire+=5*delta;
        float y =yBase+v0y*tAire-0.5f*g*tAire*tAire;
        sprite.setY(y);
        //como saber si ya termino
        if(tAire>=tVuelo||y<=yBase){
            estadoRUI=EstadoRUI.CAMINADO;
            sprite.setY(yBase);
        }
    }





    public Sprite getSprite() {
        return sprite;
    }

    //metodo para mover el sprite
    public  void  mover(float dx){
        sprite.setX(sprite.getX()+dx);
    }
    public void saltar() {
        if (estadoRUI != EstadoRUI.SALTANDO){
            tAire=0; //inicia el salto
            tVuelo=2*v0y/g;
            estadoRUI=EstadoRUI.SALTANDO;
        }
    }

}
