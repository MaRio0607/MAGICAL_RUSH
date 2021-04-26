package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import mx.marcs.magicalrush.Juego;
import mx.marcs.magicalrush.Pantalla;
import mx.marcs.magicalrush.menu.PantallaMenu;

public class PantallaJuegoMain extends Pantalla {
    private Juego juego;

    //fondo infinito
    private Texture textureFondo;
    private float xFondo=0;

    //personaje
    private Texture textureRUI;
    private Personaje personaje;

    //enemigo
    //private Goomba goomba;
    private Texture textureSlime;
    private Array<Slime> SlimeArray; //guarda los aliens
    private Texture texturaFondo;
    private float timerSlime;
    private final float TIEMPO_CREAR_SLIME=2;

    // fondo
    private Stage escenaJuego;

    //Disparo del personaje
    private Array<Bola>arrBolas;
    private Texture texturaBola;

    public PantallaJuegoMain(Juego juego) {
        this.juego=juego;
    }

    @Override
    public void show() {
        crearFondo();
        crearPersonaje();
        crearBolas();
        crearSlime();
        //poner input procesor
        Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearFondo() {
        textureFondo=new Texture("Juego/fondoMain.png");

    }

    private void crearBolas() {
        arrBolas=new Array<>();
        texturaBola=new Texture("Juego/bolaFuego.png");
    }


    private void crearPersonaje() {
        textureRUI = new Texture("Juego/RUI-Sheet.png");
        personaje = new Personaje(textureRUI,100,0.1f*ALTO);

    }
    private void crearSlime() {
        textureSlime = new Texture("Juego/Slime-Sheet.png");
        //goomba=new Goomba(textureGoomba,ANCHO-62,64);
        SlimeArray=new Array<>(3);
    }


    @Override
    public void render(float delta) {
        actualizar(delta);
        borrarPantalla(0,0,0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        //se dibuja el fondo
        batch.draw(textureFondo,xFondo,0);
        batch.draw(textureFondo,xFondo+textureFondo.getWidth(),0);
        personaje.render(batch);

        //Dibujar Slime
        for (Slime enemigo: SlimeArray) {
            enemigo.render(batch);
        }
        //Dibujar bolas
        for (Bola bolaFuego:arrBolas){
            bolaFuego.render(batch);
        }

        batch.end();

    }

    private void actualizar(float delta) {
        actualizarFondo();
        actualizarSlime(delta);
        actualizarBolas(delta);
        probarColisiones();
    }

    private void actualizarBolas(float delta) {
        for (int i=arrBolas.size-1;i>=0;i--){
            Bola bola = arrBolas.get(i);
            bola.mover(delta);
            //Prueba si la bola debe desaparecer
            if(bola.getX()>ANCHO){
                //borrar el objeto
                arrBolas.removeIndex(i);
            }
        }
    }

    private void depurarSlimes() {
        for(int i= SlimeArray.size-1; i>=0; i--){
            Slime slime = SlimeArray.get(i);
            if(slime.getEstado() == EstadoSlime.EXPLOTA){
                SlimeArray.removeIndex(i);
            }
        }
    }

    private void actualizarSlime(float delta) {
    //crear
        timerSlime +=delta;
        if (timerSlime>=TIEMPO_CREAR_SLIME){
            timerSlime=0;
            //crear enemigo
            float xGoomba= MathUtils.random(ANCHO,ANCHO*1.5f);
            Slime slime=new Slime(textureSlime,xGoomba,54);
            SlimeArray.add(slime);
        }

        //mover enemigos
        for (Slime goomba:SlimeArray){
            goomba.moverIzquierda(delta);//fisica
        }
        depurarSlimes();
    }

    private void probarColisiones() {
        //NO podemos usar el iterador
        for(int i= SlimeArray.size-1; i>=0; i--){
            Slime slime = SlimeArray.get(i);
            if(personaje.sprite.getBoundingRectangle().overlaps(slime.sprite.getBoundingRectangle())){
                //Le pegó
                slime.setEstado(EstadoSlime.EXPLOTA);
                break;
            }
        }
    }

    private void actualizarFondo() {
        xFondo-=2;
        if (xFondo <= -textureFondo.getWidth()){
            xFondo= 0;
        }

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private class ProcesadorEntrada implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Vector3 v =new Vector3(screenX, screenY,0);
            camara.unproject(v);
            if (v.x<=ANCHO/2){
                //Dispara
                Bola bolaFuego = new Bola(texturaBola,personaje.getSprite().getX(),personaje.getSprite().getY());
                arrBolas.add(bolaFuego);
            } else {
                personaje.saltar();
            }
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
