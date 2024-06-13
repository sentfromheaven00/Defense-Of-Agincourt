package com.sfh.agincourt.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class FireballSprites {
    public static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("fireball/fireball.atlas"));
    public static Sprite fireball1 = atlas.createSprite("fireball1");
    public static Sprite fireball2 = atlas.createSprite("fireball2");
    public static Sprite fireball3 = atlas.createSprite("fireball3");
    public static Sprite fireball4 = atlas.createSprite("fireball4");
}
