package com.sfh.agincourt.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sfh.agincourt.Agincourt;

public class PlayScreen extends ScreenAdapter {

    class MapActor extends Actor {
        Sprite map;
        int MAP_HEIGHT;
        int MAP_WIDTH;

        public MapActor() {
            map = new Sprite(new Texture(Gdx.files.internal("map_1.png")));
            MAP_HEIGHT = (int) (game.VIEWPORT_HEIGHT * .9);
            MAP_WIDTH = (MAP_HEIGHT * map.getTexture().getWidth() / map.getTexture().getHeight());

            this.setBounds(0, game.VIEWPORT_HEIGHT - MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
            map.setBounds(getX(), getY(), getWidth(), getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            map.draw(batch);
        }

    }

    private Agincourt game;
    private BitmapFont font;
    private Stage stage;
    private OrthographicCamera camera;
    private FitViewport viewport;

    private ShapeRenderer shapeRenderer;
    private Rectangle rectangle;

    public PlayScreen(Agincourt agincourt) {
        game = agincourt;
        camera = game.camera;
        viewport = game.viewport;
        font = game.font;
        stage = new Stage(viewport, game.batch);
        stage.addActor(new MapActor());
        game.batch.setProjectionMatrix(camera.combined);


        stage.setDebugAll(true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

}
