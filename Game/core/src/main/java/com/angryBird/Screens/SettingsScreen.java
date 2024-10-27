package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen implements Screen {
    

        
    private Button backButton;
    private Button musicLevel;
    private Button soundLevel;

    private Main game;
    private MainScreen mainScreen;
    private Sprite musicSprite;
    private Sprite soundSprite;
    private Sprite backButtonSprite;
    private Sprite backgroundSprite;

    private SpriteBatch spriteBatch;
    private Vector2 touch;
    
    
    public SettingsScreen(Main game,MainScreen mainScreen){

        backButton = new Button("back4.png");
        musicLevel = new Button("music5.png");
        soundLevel = new Button("sound5.png");
        this.game = game;
        this.mainScreen = mainScreen;


        backButtonSprite =  backButton.getButtonSprite(); 
        backgroundSprite = new Sprite(new Texture("pauseBackground.png"));
        musicSprite = musicLevel.getButtonSprite();
        soundSprite = soundLevel.getButtonSprite();
        spriteBatch = new SpriteBatch();

        touch = new Vector2();


        setPosition();

    }



    public void setPosition() {
        // Resize your screen here. The parameters represent the new window size.
        backButtonSprite.setSize(80, 80);
        backButtonSprite.setPosition(0,Gdx.graphics.getHeight() - backButtonSprite.getHeight());
        
        musicSprite.setBounds(4*192,6*108,5*(50) + 25, 2*50);
        soundSprite.setBounds(4*192,6*108-musicSprite.getHeight()-10,5*(50)+25, 2*(50));
        backgroundSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    public void handleInput(){

        if(Gdx.input.justTouched()){

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
            
            if(backButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(mainScreen);
            }
            
            
        }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();

        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        
        
        backgroundSprite.draw(spriteBatch);
        musicSprite.draw(spriteBatch);
        soundSprite.draw(spriteBatch);
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
        musicSprite.getTexture().dispose();
        soundSprite.getTexture().dispose();
        backButtonSprite.getTexture().dispose();
        backgroundSprite.getTexture().dispose();
    
        spriteBatch.dispose();
    }



}
