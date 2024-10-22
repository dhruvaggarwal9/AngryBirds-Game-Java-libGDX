package com.angryBird.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Button {
    
    // String displayMessage;
    // int x;
    // int y;
    static float buttonWidth = 50;
    static float buttonHeight = 50;
    Texture image;
    Sprite buttonSprite;

    public Button(String imgName){
        // displayMessage = message;
        image = new Texture(imgName);
        buttonSprite = new Sprite(image); 
        buttonSprite.setSize(buttonWidth, buttonHeight);
    }

    public Sprite getButtonSprite(){  
        return buttonSprite;
    }

    // buttonSprite.getBoundingRectangle().contains(Gdx.input.getX(), Gdx.input.getY())    //to check if input cursor's X and Y lie in Rectangle around button   

}

