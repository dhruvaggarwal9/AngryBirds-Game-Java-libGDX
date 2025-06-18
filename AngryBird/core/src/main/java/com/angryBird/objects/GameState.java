package com.angryBird.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private static final long serialVersionUID = 1L;


    public ArrayList<Bird> unlockedBirds;
    public ArrayList<Pig> unlockedPigs;
    public ArrayList<Building> unlockedBuildings;

    public GameState(int currentLevelIndex, ArrayList<Bird> unlockedBirds, ArrayList<Pig> unlockedPigs, ArrayList<Building> unlockedBuildings) {
        this.unlockedBirds = unlockedBirds;
        this.unlockedPigs = unlockedPigs;
        this.unlockedBuildings = unlockedBuildings;
    }
}
