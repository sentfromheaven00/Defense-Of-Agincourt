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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfh.agincourt.Agincourt;
import com.sfh.agincourt.actors.Zombie;

public class PlayScreen extends ScreenAdapter {

    public static final int MAP_HEIGHT = 720;
    public static final int MAP_WIDTH = 1280;
    private Agincourt game;
    private BitmapFont font;
    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private ShapeRenderer shapeRenderer;
    private Rectangle rectangle;
    public PlayScreen(Agincourt agincourt) {
        game = agincourt;
        camera = game.camera;
        viewport = game.viewport;
        font = game.font;
        stage = new Stage(viewport, game.batch);
        stage.addActor(new Background());
        stage.addActor(new Map1());
        stage.addActor(new Zombie());
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

    class Map1 extends Actor {
        Sprite mapSprite;
        int MAP_HEIGHT;
        int MAP_WIDTH;

        public Map1() {
            mapSprite = new Sprite(new Texture(Gdx.files.internal("monkey_meadow.png")));
            MAP_HEIGHT = game.VIEWPORT_HEIGHT;
            MAP_WIDTH = (MAP_HEIGHT * mapSprite.getTexture().getWidth() / mapSprite.getTexture().getHeight());
            this.setBounds(0, game.VIEWPORT_HEIGHT - MAP_HEIGHT, MAP_WIDTH, MAP_HEIGHT);
            mapSprite.setBounds(getX(), getY(), getWidth(), getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            mapSprite.draw(batch);
        }
    }

    // Make a class for an actor that will fill the entire viewport using the texture "missing.jpeg" and set its bounds to the viewport's bounds.
    class Background extends Actor {
        Sprite backgroundSprite;

        public Background() {
            backgroundSprite = new Sprite(new Texture(Gdx.files.internal("missing.jpeg")));
            this.setBounds(0, 0, game.VIEWPORT_WIDTH, game.VIEWPORT_HEIGHT);
            backgroundSprite.setBounds(getX(), getY(), getWidth(), getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            backgroundSprite.draw(batch);
        }
    }
}
