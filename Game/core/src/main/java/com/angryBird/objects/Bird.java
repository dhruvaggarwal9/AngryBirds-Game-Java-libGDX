package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;

public class Bird extends Character{
    

    
    public Bird(String name,String imgName){
        
        super(name,imgName);

    }

    public String getName(){
        return myName;
    }

    public Texture getImg(){
        return myImg;
    }
    public int getStrength(){
        return 0;
    }
    
    public void setSound(){

    }
}
