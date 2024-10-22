package com.angryBird.objects;

import java.util.ArrayList;
// import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Level {
    


    Texture backImg;
    Catapult catapult;
    Building building;
    ArrayList<Pig> pigEnemies;
    
    // HashMap<Pig,Sprite> pigEnemy;

    
    public Level(String imgName,ArrayList<Pig> pigsUsed,Building buildingUsed){
    
        backImg = new Texture(imgName);
        catapult = new Catapult("catapult", "catapult.png");
        building = buildingUsed;
        pigEnemies = pigsUsed;
        
    }

    public ArrayList<Pig> getPigEnemies(){
        return pigEnemies;
    }

    public Sprite getBackSprite(){
        return new Sprite(backImg);
    }
    
 

    public Sprite getCatapultSprite(){
        return new Sprite(catapult.getImg());
    }

    public Building getBuilding(){
        return building;
    }
    

}
