package com.sfh.agincourt;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sfh.agincourt.views.MainMenuScreen;
import com.sfh.agincourt.views.PlayScreen;

public class Agincourt extends Game {

	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	public BitmapFont font;

	private MainMenuScreen mainMenuScreen;
	private PlayScreen playScreen;

	public final static int MENU = 0;
	public final static int PLAY = 1;

	public void changeScreen(int screen) {
		switch (screen) {
			case MENU:
				if(mainMenuScreen == null) mainMenuScreen = new MainMenuScreen(this);
				this.setScreen(mainMenuScreen);
				break;
			case PLAY:
				if(playScreen == null) playScreen = new PlayScreen(this);
				this.setScreen(playScreen);
				break;
		}
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		changeScreen(MENU);
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}

}
