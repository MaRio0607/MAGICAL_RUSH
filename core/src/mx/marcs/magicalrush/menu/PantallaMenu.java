package mx.marcs.magicalrush.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import mx.marcs.magicalrush.Juego;
import mx.marcs.magicalrush.Pantalla;
import mx.marcs.magicalrush.juegoMain.PantallaJuegoMain;

public class PantallaMenu extends Pantalla {
    private Juego juego;
    // fondo
    private Texture texturaFondo;
    private Sprite sprite;
    //escena
    private Stage escenaMenu;
    public PantallaMenu(Juego juego) {
        this.juego=juego;

    }

    @Override
    public void show() {
        crearMenu();
    }

    private void crearMenu() {
        //Fondo
        texturaFondo=new Texture("menu/menu_room.png");
        //se crea la escena del menu
        escenaMenu=new Stage(vista);
        // Crear titulo
        sprite=new Sprite(new Texture("menu/magical_rush.png"));
        sprite.setPosition(ANCHO/2-320,ALTO/2);
        //crear el boton
        Button btnJugar = crearBoton("menu/jugar.png", "menu/jugar(2).png");
        btnJugar.setPosition(ANCHO/2,ALTO/2-100, Align.center);
        // Agrega el botón a la escena
        escenaMenu.addActor(btnJugar);
        btnJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                juego.setScreen(new PantallaJuegoMain(juego));
            }
        });

        //crear el boton
        Button btnInts = crearBoton("menu/instrucciones.png", "menu/instrucciones(2).png");
        btnInts.setPosition(ANCHO/2,ALTO/2-200, Align.center);
        // Agrega el botón a la escena
        escenaMenu.addActor(btnInts);
        btnInts.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                juego.setScreen(new PantallaInstrucciones(juego));
            }
        });

        Button btnAcerca = crearBoton("menu/acerca-de.png", "menu/acerca-de(2).png");
        btnAcerca.setPosition(ANCHO/2,ALTO/2-300, Align.center);
        // Agrega el botón a la escena
        escenaMenu.addActor(btnAcerca);
        btnAcerca.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                juego.setScreen(new PantallaAcerca(juego));
            }
        });



        //Atiende los eventos de entrada
        Gdx.input.setInputProcessor(escenaMenu);
    }

    private Button crearBoton(String image_path, String inverse_image_path) {
        Texture texturaBoton = new Texture(image_path);
        TextureRegionDrawable trdButton = new TextureRegionDrawable(texturaBoton);
        Texture texturaInverso = new Texture(inverse_image_path);
        TextureRegionDrawable trdBtnInverso = new TextureRegionDrawable(texturaInverso);

        return new Button(trdButton,trdBtnInverso);
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0,1,0);
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        sprite.draw(batch);

        batch.end();

        //Escena despues del fondo
        escenaMenu.draw();
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
