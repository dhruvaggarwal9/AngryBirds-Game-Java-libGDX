package com.angryBird.objects;

import java.io.Serializable;

public class Pig extends Character implements Serializable {
    private static final long serialVersionUID = 1L; // Serialization version control
    private int health;
    private transient String imgName; // Transient since Character's imgName isn't serialized

    public Pig(String name, String imgName, int h, int w, int health) {
        super(name, imgName);
        this.imgName = imgName; // Store the image name for reloading
        this.health = health;
    }

    public void useAbility() {
        // Ability logic
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth(int x) {
        health -= x;
    }

    public int getStrength() {
        return 0; // Placeholder
    }

    public void setSound() {
        // Sound logic
    }

}
