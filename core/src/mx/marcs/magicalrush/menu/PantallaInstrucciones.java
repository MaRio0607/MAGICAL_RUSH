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
import mx.marcs.magicalrush.menu.PantallaMenu;

public class PantallaInstrucciones extends Pantalla {

    private Juego juego;
    private Sprite sprite;
    private Texture texturaFondo;
    private Stage escenaInstrucciones;

    public PantallaInstrucciones(Juego juego) {
        this.juego=juego;
    }

    @Override
    public void show() {
        crearMenu();

    }

    private void crearMenu() {
        texturaFondo=new Texture("Instruciones/InsBack.jpg");
        sprite=new Sprite(new Texture("Instruciones/yumme.jpg"));
        sprite.setPosition(ANCHO/2-200,ALTO/2);

        escenaInstrucciones=new Stage(vista);

        //crear el boton
        Button btnVolver = crearBoton("Instruciones/volver.png", "Instruciones/volver(2).png");
        btnVolver.setPosition(ANCHO/2,ALTO/2-200, Align.center);
        // Agrega el botón a la escena
        escenaInstrucciones.addActor(btnVolver);
        btnVolver.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                juego.setScreen(new PantallaMenu(juego));
            }
        });


        //Atiende los eventos de entrada
        Gdx.input.setInputProcessor(escenaInstrucciones);

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
        borrarPantalla(0,0,0);
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        sprite.draw(batch);

        batch.end();

        //Escena despues del fondo
        escenaInstrucciones.draw();
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
