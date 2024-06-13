package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TowerSprites {
    public static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("towers/towers.atlas"));
    public static Sprite tower1 = atlas.createSprite("tower1");
    public static Sprite tower2 = atlas.createSprite("tower2");
    public static Sprite tower3 = atlas.createSprite("tower3");
    public static Sprite tower4 = atlas.createSprite("tower4");
    public static Sprite tower5 = atlas.createSprite("tower5");
    public static Sprite tower6 = atlas.createSprite("tower6");
}
