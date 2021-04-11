package mx.marcs.magicalrush.juegoMain;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
    private Array<Enemigo> EnemigoArray; //guarda los aliens
    private Texture texturaFondo;
    private Stage escenaJuego;


    public PantallaJuegoMain(Juego juego) {
        this.juego=juego;
    }

    @Override
    public void show() {
        texturaFondo=new Texture("Juego/Back2.png");
        crearEnemigos();
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
    private void crearEnemigos() {
        Texture textureAlien = new Texture("Juego/Slime.png");
        //alien= new Alien(textureAlien,ANCHO/2,ALTO/2);
        //crear 55 aliens

        EnemigoArray=new Array<>(1*1);
        for(int renglon=0;renglon<1; renglon++){//recorre los renglones (0->4)
            for (int columna=0;columna<1;columna++){
                Enemigo alien=new Enemigo(textureAlien,510+columna*60,0.1f*ALTO);
                EnemigoArray.add(alien);
            }
        }
    }


    @Override
    public void render(float delta) {
        borrarPantalla(0,0,0);
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(texturaFondo,0,0);
        for (Enemigo enemigo:EnemigoArray) {
            enemigo.render(batch);
        }
        personaje.render(batch);
        batch.end();

        //Escena despues del fondo
        escenaJuego.draw();
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
