package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;

public abstract class Character {
    
    private  String myName;
    protected Texture myImg;
    protected int Health;

    public Character(String name,String imgName){
        
        myName = name;
        myImg = new Texture(imgName);
    }

    public String getName(){
        return myName;
    }

    public Texture getImg(){
        return myImg;
    }
    public abstract int getStrength();
    
    public abstract void setSound();

    // +playSound() : void
    // +getHealth() : double
    // +isDead() : boolean
    
}
