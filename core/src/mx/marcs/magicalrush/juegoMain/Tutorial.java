package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import mx.marcs.magicalrush.Juego;
import mx.marcs.magicalrush.Pantalla;

public class Tutorial extends Pantalla {

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

    public Tutorial(Juego juego) {
        this.juego=juego;
    }

    @Override
    public void show() {
        crearFondo();
        //poner input procesor
        Gdx.input.setInputProcessor(new Tutorial.ProcesadorEntrada());
    }

    private void crearFondo() {
        textureFondo=new Texture("Juego/fondoMain.png");

    }

    @Override
    public void render(float delta) {
        borrarPantalla(0,0,0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        //se dibuja el fondo
        batch.draw(textureFondo,xFondo,0);
        batch.draw(textureFondo,xFondo+textureFondo.getWidth(),0);

        batch.end();

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
                Bola bolaFuego = new Bola(texturaBola,personaje.getSprite().getX()+60,personaje.getSprite().getY()+30);
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
