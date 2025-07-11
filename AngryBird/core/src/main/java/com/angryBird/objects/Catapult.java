package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;

public class Catapult {
    
    private String name;
    private Texture img;
    
    Catapult(String name,String img){
        
        this.name = name;
        this.img = new Texture(img); 
    }

    public String getName(){
        return name;
    }

    public Texture getImg(){
        return img;
    }
}
