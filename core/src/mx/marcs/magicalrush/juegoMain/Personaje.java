package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;

import javax.xml.soap.Text;

import mx.marcs.magicalrush.Objeto;

public class Personaje extends Objeto {

    public static  final float VELOCIDAD_Y=-4F; //Velocidad de caída
    public static final float VELOCIDAD_X=2; //Velocidad horizontal
    private Sprite sprite; //Sprite cuando no se mueve


    private Animation animacion;    //Caminando
    private float timerAnimacion; //tiempo para calcular el frame


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
    private EstadoRUI estadoSalto;


    private Animation<TextureRegion> animacionCorrer;
    private float timerAnimation; //sabre el frame que corresponde mostrar

    //Salto
    private final float yBase=54;    //Suelo, piso
    private float tAire;             //Tiempo que lleva en al aire
    private float tVuelo;            //Tiempo de vuelo total
    private final float v0y=200;     //Componete en y de la velocidad
    private final  float g= 150;    //Pixeles sobre segundo al cuadrado
    private  EstadoRUI estadoRUI; //SAltando,caminado, bajando, izquierda, derecha

    public Personaje(Texture textura){
        //lee la textura como región
        TextureRegion texturaCompleta= new TextureRegion(textura);
        TextureRegion[][] texturaPersonaje=texturaCompleta.split(16,32);
        animacion=new Animation(0.25f,texturaPersonaje[0][3],
                texturaPersonaje[0][2], texturaPersonaje[0][1]);
        animacion.setPlayMode(Animation.PlayMode.LOOP);
        timerAnimacion=0;
        sprite=new Sprite(texturaPersonaje[0][0]);
        estadoMoviento=EstadoRUI.QUIETO;
        estadoSalto=EstadoRUI.SALTANDO;

    }
    /*
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

     */
    //Reescribir el metodo render para mostrar la animacion
    public void render(SpriteBatch batch){
        // Dibuja el personaje dependiendo del estadoMovimiento
        switch (estadoMoviento) {
            case MOV_DERECHA:
            case MOV_IZQUIERDA:
                // Incrementa el timer para calcular el frame que se dibuja
                timerAnimacion += Gdx.graphics.getDeltaTime();
                // Obtiene el frame que se debe mostrar (de acuerdo al timer)
                TextureRegion region = (TextureRegion)animacion.getKeyFrame(timerAnimacion);
                // Dirección correcta
                if (estadoRUI==estadoMoviento.MOV_IZQUIERDA) {
                    if (!region.isFlipX()) {
                        region.flip(true,false);
                    }
                } else {
                    if (region.isFlipX()) {
                        region.flip(true,false);
                    }
                }
                // Dibuja el frame en las coordenadas del sprite
                batch.draw(region, sprite.getX(), sprite.getY());
                break;
            case INICIANDO:
            case QUIETO:
                sprite.draw(batch); // Dibuja el sprite
                break;
        }



    }
    //
    // Calcula el movimient vertical
    private void actualizar() {
        // Ejecutar movimiento horizontal
        float nuevaX = sprite.getX();
        switch (estadoMoviento) {
            case MOV_DERECHA:
                // Prueba que no salga del mundo
                nuevaX += VELOCIDAD_X;
                if (nuevaX<=PantallaJuegoMain.ANCHO_MAPA-sprite.getWidth()) {
                    sprite.setX(nuevaX);
                }
                break;
            case MOV_IZQUIERDA:
                // Prueba que no salga del mundo
                nuevaX -= VELOCIDAD_X;
                if (nuevaX>=0) {
                    sprite.setX(nuevaX);
                }
                break;
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
