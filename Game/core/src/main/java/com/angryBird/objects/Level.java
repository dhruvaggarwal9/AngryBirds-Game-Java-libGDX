package com.angryBird.objects;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;



public class Level {
  
    
    // -String levelName
    // -in/t levelNumber
    // -ArrayList<Pig> pigs
    // -ArrayList<Position> pigPositions
    // -ArrayList<Building> buildings
    // -Catapult catapult
    // -Background background
    // -Season season
    // -Level nextLevel (Pointer to next level)
    // -Time time
    // -Stars stars
    // -SoundEffects snd
    Texture backImg;
    Sprite backSprite;

    HashMap<Pig,Sprite> pigEnemy = new HashMap<Pig,Sprite>();
    HashMap<Bird,Sprite> birdsUnlocked = new HashMap<Bird,Sprite>();
    HashMap<Buildings,Sprite> builds = new HashMap<Buildings,Sprite>();

    HashMap<Button,Sprite> buttonSprite = new HashMap<Button,Sprite>();
    // Button B1;

    public Level(String img,Pig p,Button pause){

        pigEnemy.put(p, new Sprite(p.getImg()));
        backImg = new Texture(img);
        backSprite = new Sprite(backImg);
        // B1 = pause;

        
        buttonSprite.put(pause,pause.getButtonSprite());
        buttonSprite.get(pause).setBounds(0, 0, 100, 100);
    }


    public HashMap<Pig,Sprite> getPigenemy(){
        return pigEnemy;
    }

    public Sprite getBackSprite(){
        return backSprite;
    }
    
    public HashMap<Button,Sprite> getButtons(){
        return buttonSprite;
    }


}
