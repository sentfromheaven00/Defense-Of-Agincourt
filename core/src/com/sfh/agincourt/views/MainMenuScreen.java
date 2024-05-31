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
        final int LOGO_HEIGHT = (int) (game.VIEWPORT_HEIGHT * .4);
        final int LOGO_WIDTH = LOGO_HEIGHT * 2;

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
        Sprite button;
        final int BUTTON_HEIGHT = (int) (game.VIEWPORT_HEIGHT * .2);
        final int BUTTON_WIDTH = BUTTON_HEIGHT * 2;

        public PlayButton() {
            button = new Sprite(new Texture(Gdx.files.internal("play_button.png")));
            this.setBounds((game.VIEWPORT_WIDTH / 2) - (BUTTON_WIDTH / 2), (int) (camera.viewportHeight * 0.38) - (int) (BUTTON_HEIGHT * .618), BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setBounds(getX(), getY(), getWidth(), getHeight());
            this.setTouchable(Touchable.enabled);

            this.addListener(new InputListener() {
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
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

    private Agincourt game;
    private BitmapFont font;
    private Stage stage;
    private OrthographicCamera camera;
    private FitViewport viewport;

    public MainMenuScreen(Agincourt agincourt) {
        game = agincourt;
        camera = game.camera;
        viewport = game.viewport;
        font = game.font;
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
}
