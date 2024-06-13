package com.sfh.agincourt.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.sfh.agincourt.actors.Tower;
import com.sfh.agincourt.actors.TowerSelection;
import com.sfh.agincourt.views.PlayScreen;

public class UiOverlay implements InputProcessor {

    private Skin skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
    public Stage stage;
    private Window window = new Window("Buy Menu", skin);

    public void createWindow() {
        stage = PlayScreen.stage;
        window.setMovable(false);
        window.setResizable(false);

        TextButton towerButton = new TextButton("Tower", skin);
        towerButton.align(Align.top);

        // When the tower button is clicked, a TowerSelection actor is added to the stage, and will follow the mouse cursor
        towerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                TowerSelection towerSelection = new TowerSelection();
                stage.addActor(towerSelection);
            }
        });

        window.add(towerButton);
        window.pack();
        window.setBounds(Gdx.graphics.getWidth(), 0, 300, Gdx.graphics.getHeight());
        window.setVisible(false);
        stage.addActor(window);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.X) { // Replace X with the key you want to use
            if (Tower.selectedTower != null) {
                Tower.selectedTower.level = Tower.selectedTower.level < 6 ? Tower.selectedTower.level + 1 : 1;
            }
            return true;
        }


        Array<Actor> actors = stage.getActors();
        for (Actor actor : actors) {
            if (actor instanceof TowerSelection) {
                actor.remove(); // This will remove the TowerSelection actor from the stage
                break;
            }
        }

        if (keycode == Input.Keys.E) {
            window.setVisible(!window.isVisible());
            Gdx.app.log("UiOverlay", "E key pressed");
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
