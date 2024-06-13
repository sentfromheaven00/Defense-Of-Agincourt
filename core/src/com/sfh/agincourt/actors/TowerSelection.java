package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sfh.agincourt.actors.TowerSprites;

public class TowerSelection extends Actor {
    private final Sprite tsSprite = new Sprite(new Texture(Gdx.files.internal("towers/tower_selection.png")));

    public TowerSelection() {
        tsSprite.setBounds(0, 0, 106, 162);
        setBounds(0, 0, 106, 162);

        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Tower tower = new Tower(getX(), getY());
                getStage().addActor(tower);
                remove();
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Vector2 stageCoordinates = getStage().screenToStageCoordinates(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        setPosition(stageCoordinates.x - getWidth() / 2, stageCoordinates.y - getHeight() / 2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        tsSprite.setPosition(getX(), getY());
        tsSprite.draw(batch);
    }
}