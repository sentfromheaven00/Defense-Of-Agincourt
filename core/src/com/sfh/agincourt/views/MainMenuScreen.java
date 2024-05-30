package com.sfh.agincourt.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sfh.agincourt.Agincourt;
import com.badlogic.gdx.scenes.scene2d.*;

public class MainMenuScreen extends ScreenAdapter {

    class LogoActor extends Actor {
        Sprite logo;

        public LogoActor() {
            logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
            logo.setBounds(200, 200, logo.getWidth() / 2, logo.getHeight() / 2);
            this.setBounds(200, 200, logo.getWidth() / 2, logo.getHeight() / 2);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            logo.draw(batch);
        }

    }


    private Agincourt game;
    private BitmapFont font;
    private OrthographicCamera camera;
    private Stage stage;
    private FitViewport viewport;

    public MainMenuScreen(Agincourt agincourt) {
        game = agincourt;
        font = game.font;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage(viewport, game.batch);
        stage.addActor(new LogoActor());
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }
}
