package com.sfh.agincourt.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfh.agincourt.Agincourt;

public class MainMenuScreen extends ScreenAdapter {

    private final Agincourt game;
    private final Stage stage;
    private final OrthographicCamera camera;

    public MainMenuScreen(Agincourt agincourt) {
        game = agincourt;
        camera = game.camera;
        Viewport viewport = game.viewport;
        stage = new Stage(viewport, game.batch);
        stage.addActor(new LogoActor());
        stage.addActor(new PlayButton());
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
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    class LogoActor extends Actor {
        final int LOGO_HEIGHT = (int) (game.VIEWPORT_HEIGHT * .4);
        final int LOGO_WIDTH = LOGO_HEIGHT * 2;
        final Sprite logo;

        public LogoActor() {
            logo = new Sprite(new Texture(Gdx.files.internal("logo.png")));
            this.setBounds(game.VIEWPORT_WIDTH / 2 - LOGO_WIDTH / 2, game.VIEWPORT_HEIGHT - 80 - LOGO_HEIGHT, LOGO_WIDTH, LOGO_HEIGHT);
            logo.setBounds(getX(), getY(), getWidth(), getHeight());
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            logo.draw(batch);
        }

    }

    class PlayButton extends Actor {
        final int BUTTON_HEIGHT = (int) (game.VIEWPORT_HEIGHT * .2);
        final int BUTTON_WIDTH = BUTTON_HEIGHT * 2;
        final Sprite button;

        public PlayButton() {
            button = new Sprite(new Texture(Gdx.files.internal("play_button.png")));
            this.setBounds((game.VIEWPORT_WIDTH / 2) - (BUTTON_WIDTH / 2), (int) (camera.viewportHeight * 0.38) - (int) (BUTTON_HEIGHT * .618), BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setBounds(getX(), getY(), getWidth(), getHeight());
            this.setTouchable(Touchable.enabled);

            this.addListener(new InputListener() {
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("up");
                    game.changeScreen(Agincourt.PLAY);

                }
            });
        }


        @Override
        public void draw(Batch batch, float parentAlpha) {
            button.draw(batch);
        }
    }
}
