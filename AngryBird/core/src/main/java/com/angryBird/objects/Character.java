package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Character {


    private  String myName;
    protected Sprite mySprite;
    // protected int Health;

    public Character(String name,String imgName){

        myName = name;
        mySprite = new Sprite(new Texture(imgName));
    }




    public String getName(){
        return myName;
    }

    public Sprite getSprite(){
        return mySprite;
    }
    public abstract int getStrength();

    public abstract void setSound();

    // +playSound() : void
    // +getHealth() : double
    // +isDead() : boolean

}
