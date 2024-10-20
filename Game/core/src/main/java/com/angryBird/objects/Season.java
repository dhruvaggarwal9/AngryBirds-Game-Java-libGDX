package com.angryBird.objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Season {
    
    String seasonName;
    Texture seasonBackground;
    Texture seasonTheme;
    ArrayList<Level> levels;
    //SEASON SCREEN WILL HAVE RECTANGLES CREATED TO SHOW LEVELS
    public Season(String seasonName,String backgroundImg,String themeImg,ArrayList<Level> levels){

        this.seasonName = seasonName;
        this.seasonBackground = new Texture(backgroundImg);
        this.seasonTheme = new Texture(themeImg);
        this.levels = levels;
    }

    public String getName(){
        return seasonName;
    }

    public Texture getBackground(){ 
        return seasonBackground;
    }

    public Texture getThemeImg(){
        return seasonTheme;
    }
    public ArrayList<Level> getLevels(){
        return levels;
    }
    
}
