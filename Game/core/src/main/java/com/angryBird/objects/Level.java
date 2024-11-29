package com.angryBird.objects;

import java.util.ArrayList;
import java.util.Objects;
// import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Level {



    private Texture backImg;
    private Catapult catapult;
    private BuildingMIII building;
    private ArrayList<Pig> pigEnemies;
    private ArrayList<Bird> birdHeroes;
    public int birdsdone;
    // HashMap<Pig,Sprite> pigEnemy;


    public Level(String imgName,ArrayList<Bird> birdsUsed,ArrayList<Pig> pigsUsed,BuildingMIII buildingUsed){

        backImg = new Texture(imgName);
        catapult = new Catapult("catapult", "catapult.png");
        building = buildingUsed;
        pigEnemies = pigsUsed;
        birdHeroes = birdsUsed;
    }

    public void removebird(int i) {
        for (int j = 0; j < i; j++) {
            birdHeroes.removeFirst();
        }
    }

    public ArrayList<Pig> getPigEnemies(){
        return pigEnemies;
    }

    public Sprite getBackSprite(){
        return new Sprite(backImg);
    }

    public Texture getBackground(){
        return backImg;
    }

    public Sprite getCatapultSprite(){
        return new Sprite(catapult.getImg());
    }

    public BuildingMIII getBuilding(){
        return building;
    }

    public ArrayList<Bird> getBirdHeroes() {
        return birdHeroes;
    }

}
