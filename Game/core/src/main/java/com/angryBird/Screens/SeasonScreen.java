package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SeasonScreen implements Screen{
    
    Button backButton;
    Season season;
    Main game;

    Sprite backButtonSprite;
    Sprite backgroundSprite;
    SpriteBatch spriteBatch;



    ArrayList<Vector2> levelIconPositons = new ArrayList<Vector2>();
    ArrayList<Sprite> levelIcons = new ArrayList<Sprite>();

    Vector2 touch;
    
    
    public SeasonScreen(Main game,Season season){

        backButton = new Button("back.png");
        this.game = game;
        this.season = season;

        backButtonSprite =  backButton.getButtonSprite(); 
        backgroundSprite = new Sprite(season.getBackground());
        spriteBatch = new SpriteBatch();

        touch = new Vector2();

        levelIconPositons.add(new Vector2(192, 108));
        levelIconPositons.add(new Vector2(500, 280));
        levelIconPositons.add(new Vector2(800, 395));
        levelIconPositons.add(new Vector2(1100, 315));
        levelIconPositons.add(new Vector2(1300, 450));
        levelIconPositons.add(new Vector2(1500, 650));
        levelIconPositons.add(new Vector2(1800, 900));


        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));
        levelIcons.add(new Sprite(new Texture("back.png")));

        setPosition();

    }



    public void setPosition() {
        // Resize your screen here. The parameters represent the new window size.
        
        for(int i = 0; i<levelIconPositons.size(); i++){

            levelIcons.get(i).setBounds(levelIconPositons.get(i).x, levelIconPositons.get(i).y, backButtonSprite.getWidth()-10,backButtonSprite.getHeight()-10);
            
        }

        backButtonSprite.setPosition(0,Gdx.graphics.getHeight() - backButtonSprite.getHeight());
        
        
    }


    public void handleInput(){

        if(Gdx.input.justTouched()){

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            
            if(backButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(new SeasonsListScreen(game));
            }
            else{
                int i = 0;
                for (Sprite sprite : levelIcons) {
                    if(i<season.getLevels().size()){
                        if(sprite.getBoundingRectangle().contains(touch)){
                            game.setScreen(new LevelScreen(game,season.getLevels().get(i)));
                        }
                    }
                    i++;

                }
            }
            
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundSprite.draw(spriteBatch);
        
        
        for (Sprite sprite : levelIcons) {
            
            sprite.draw(spriteBatch);
        }

        backButtonSprite.draw(spriteBatch);
        
        spriteBatch.end();
   
    }

    
    @Override
    public void show() {
        // Prepare your screen here.
    }


    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }


}
