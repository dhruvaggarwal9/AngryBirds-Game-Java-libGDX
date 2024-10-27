package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PauseScreen implements Screen {
    
    // Texture background;
    Button resume;
    Button returnHome;
    Button saveGame;
    Main game;
    LevelScreen levelScreen;

    Sprite backSprite;
    Sprite resumeSprite;
    Sprite returnSprite;
    Sprite saveGameSprite;
    SpriteBatch spriteBatch;
    Vector2 touch;
    public PauseScreen(Main game,LevelScreen level){

        // Button tesButton = new 
        resume = new Button("resume5.png");
        returnHome = new Button("returnHome5.png");
        saveGame = new Button("saveGame5.png");
        this.game = game;
        this.levelScreen = level;

        backSprite = new Sprite(new Texture("pauseBackground.png"));
        resumeSprite = resume.getButtonSprite();
        returnSprite = returnHome.getButtonSprite();
        saveGameSprite = saveGame.getButtonSprite();
        spriteBatch = new SpriteBatch();
        touch = new Vector2();

        setPostions();
    }

    public void setPostions(){


        float width = Gdx.graphics.getWidth()/10;
        float height = Gdx.graphics.getHeight()/10;

        backSprite.setSize(10*width, 10*height);
        resumeSprite.setBounds(4*width,6*height,5*(50) , 2*50);
        returnSprite.setBounds(4*width,6*height-resumeSprite.getHeight()-10,5*(50), 2*(50));
        saveGameSprite.setBounds(4*width, 6*height-2*resumeSprite.getHeight()-20, 5*50, 2*50);
    }


    public void handleInput(){

        if (Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

        
            if(returnSprite.getBoundingRectangle().contains(touch)){
                levelScreen.dispose();
                game.setScreen(new MainScreen(game));
            }
            else if(resumeSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(levelScreen);
            }
          
        }
    }
    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        handleInput();
        spriteBatch.begin();

        backSprite.draw(spriteBatch);
        resumeSprite.draw(spriteBatch);
        returnSprite.draw(spriteBatch);
        saveGameSprite.draw(spriteBatch);
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
