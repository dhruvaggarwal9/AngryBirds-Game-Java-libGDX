package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
    
    // String displayMessage;
    // int x;
    // int y;

    Texture image;
    Sprite buttonSprite;

    public Button(String imgName){
        // displayMessage = message;
        image = new Texture(imgName);
        buttonSprite = new Sprite(image); 
    }

    public Sprite getButtonSprite(){  
        return buttonSprite;
    }

    // buttonSprite.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY())    //to check if input cursor's X and Y lie in Rectangle around button   

}

