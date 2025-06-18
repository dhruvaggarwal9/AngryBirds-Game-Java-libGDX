package com.angryBird.objects;

import java.io.Serializable;

public class Bird extends Character implements Serializable {
    private static final long serialVersionUID = 1L; // For version control of serialized data
    int strength;

    public Bird(String name, String imgName, int strength) {
        super(name, imgName);
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setSound() {
        // Logic for setting sound
    }
}
