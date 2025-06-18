package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.io.Serializable;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient Texture img; // Mark as transient
    private int health;
    private float width, height;
    private transient Sprite blockSprite; // Mark as transient
    boolean horizontal;

    public Block(String imgPath, float height, float width, int health, boolean horizontal) {
        this.img = new Texture(imgPath);
        this.health = health;
        this.width = width;
        this.height = height;
        this.horizontal = horizontal;
        initializeSprite();
    }

    private void initializeSprite() {
        blockSprite = new Sprite(this.img);
        if (horizontal) blockSprite.rotate90(true);
        blockSprite.setSize(width, height);
    }

    public void reduceHealth(int x) {
        health -= x;
    }

    public int getHealth() {
        return health;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public Texture getImg() {
        return img;
    }

    public Sprite getBlockSprite() {
        return blockSprite;
    }
}
