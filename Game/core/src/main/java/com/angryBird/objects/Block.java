package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;

public class Block {
    
    private String blocktype;
    private Texture img;

    private float width;
    private float height;

    boolean horizontal;
    public Block(String blocktype,String img,float width,float height){
        
        this.blocktype = blocktype;
        this.img = new Texture(img);
        this.width = width;
        this.height = height;

        if(width>=height){
            horizontal = true;
        }
        else{
            horizontal = false;
        }
    }

    public boolean isHorizontal(){
        return horizontal;
    }

    public Texture getImg(){
        return img;
    }
}
