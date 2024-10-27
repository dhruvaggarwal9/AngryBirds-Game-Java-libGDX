package com.angryBird.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Season {
    
    private String seasonName;
    private Texture seasonBackground;
    private Texture seasonTheme;
    private Texture blurBackgroundImage;
    private ArrayList<Level> levels;
    //SEASON SCREEN WILL HAVE RECTANGLES CREATED TO SHOW LEVELS
    public Season(String seasonName,String backgroundImg,String themeImg,String blurBackgroundImage,ArrayList<Level> levels){

        this.seasonName = seasonName;
        this.seasonBackground = new Texture(backgroundImg);
        this.seasonTheme = new Texture(themeImg);
        this.blurBackgroundImage = new Texture(blurBackgroundImage);
        this.levels = levels;
    }

    public String getName(){
        return seasonName;
    }

    public Texture getBackground(){ 
        return seasonBackground;
    }

    public Texture getBlurBackground(){
        return blurBackgroundImage;
    }
    public Texture getThemeImg(){
        return seasonTheme;
    }
    public ArrayList<Level> getLevels(){
        return levels;
    }
    
}
