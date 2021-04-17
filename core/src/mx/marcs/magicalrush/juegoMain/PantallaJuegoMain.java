package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
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
    // fondo
    private Personaje personaje;
    private Texture textureSlime;
    private Array<Slime> SlimeArray; //guarda los aliens
    private Texture texturaFondo;
    private Stage escenaJuego;
    private float timerSlime;
    private final float TIEMPO_CREAR_SLIME=2;

    public PantallaJuegoMain(Juego juego) {
        this.juego=juego;
    }

    @Override
    public void show() {
        texturaFondo=new Texture("Juego/Back2.png");
        crearSlime();
        crearPersonaje();
        crearMenu();
    }

    private void crearMenu() {
        escenaJuego=new Stage(vista);
        //crear el boton
        Button btnVolver = crearBoton("Instruciones/volver.png", "Instruciones/volver(2).png");
        btnVolver.setPosition(100,600, Align.center);
        // Agrega el botón a la escena
        escenaJuego.addActor(btnVolver);
        btnVolver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                juego.setScreen(new PantallaMenu(juego));
            }
        });

        //Atiende los eventos de entrada
        Gdx.input.setInputProcessor(escenaJuego);
    }

    private Button crearBoton(String image_path, String inverse_image_path) {
        Texture texturaBoton = new Texture(image_path);
        TextureRegionDrawable trdButton = new TextureRegionDrawable(texturaBoton);
        Texture texturaInverso = new Texture(inverse_image_path);
        TextureRegionDrawable trdBtnInverso = new TextureRegionDrawable(texturaInverso);

        return new Button(trdButton,trdBtnInverso);
    }


    private void crearPersonaje() {
        Texture textPersonaje =new Texture("Juego/RUI(SPRITE)).png");
        personaje=new Personaje(textPersonaje,100,0.1f*ALTO);
    }
    private void crearSlime() {
        textureSlime=new Texture("Juego/Slime-Sheet.png");
        //goomba=new Goomba(textureGoomba,ANCHO-62,64);
        SlimeArray=new Array<>(3);
    }


    @Override
    public void render(float delta) {
        actualizar(delta);
        borrarPantalla(0,0,0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(texturaFondo,0,0);
        //Dibujar Slime
        for (Slime enemigo: SlimeArray) {
            enemigo.render(batch);
        }
        personaje.render(batch);
        batch.end();

        //Escena despues del fondo
        escenaJuego.draw();
    }

    private void actualizar(float delta) {
        actualizarFondo();
        actualizarGoombas(delta);
    }

    private void actualizarGoombas(float delta) {
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
    }

    private void actualizarFondo() {

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
}
