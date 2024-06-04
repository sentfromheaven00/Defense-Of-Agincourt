package com.sfh.agincourt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sfh.agincourt.views.MainMenuScreen;
import com.sfh.agincourt.views.PlayScreen;

public class Agincourt extends Game {

    public final static int MENU = 0;
    public final static int PLAY = 1;
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    public OrthographicCamera camera;
    public Viewport viewport;
    public int VIEWPORT_HEIGHT;
    public int VIEWPORT_WIDTH;
    public Music music;
    private MainMenuScreen mainMenuScreen;
    private PlayScreen playScreen;

    public void changeScreen(int screen) {
        switch (screen) {
            case MENU:
                if (mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
                this.setScreen(mainMenuScreen);
                break;
            case PLAY:
                if (playScreen == null) playScreen = new PlayScreen(this);
                this.setScreen(playScreen);
                break;
        }
    }

    @Override
    public void create() {
        VIEWPORT_HEIGHT = Gdx.graphics.getHeight();
        VIEWPORT_WIDTH = VIEWPORT_HEIGHT * 16 / 9;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        music = Gdx.audio.newMusic(Gdx.files.internal("trick.wav"));
        music.setLooping(true);
        music.setVolume(0.1f);
//        music.play();
        changeScreen(MENU);
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        music.dispose();
    }

}
