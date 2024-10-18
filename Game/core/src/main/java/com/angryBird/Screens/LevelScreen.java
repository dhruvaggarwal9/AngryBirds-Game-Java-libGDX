package com.angryBird.Screens;

import com.angryBird.Main;
import com.angryBird.objects.*;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** First screen of the application. Displayed after the application is created. */
public class LevelScreen implements Screen {

    // Texture imgBack = new Texture("scene1.png");
    // Texture imgPig = new Texture("p1.png");

    // Sprite backGrnd = new Sprite(imgBack);
    // Sprite pigSprite = new Sprite(imgPig);

    Level level;
    Main game;

    public LevelScreen(Level lvl,Main game){
        level = lvl;
        this.game = game;
    }
    SpriteBatch spriteBatch = new SpriteBatch();
    @Override
    public void show() {
        // Prepare your screen here.
    }

    public void input(){

        // if(){
        //     game.setScreen(new Secondscreen());
        // }
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.

        // pigSprite.setSize(100, 100);
        // spriteBatch.begin();
        // ScreenUtils.clear(Color.BLACK);

        // backGrnd.draw(spriteBatch);
        // pigSprite.draw(spriteBatch);

        // spriteBatch.end();

        // pigSprite.setSize(100, 100);
        spriteBatch.begin();
        ScreenUtils.clear(Color.BLACK);

        // backGrnd.draw(spriteBatch);
        // pigSprite.draw(spriteBatch);

        level.getBackSprite().draw(spriteBatch);
        for (Sprite pig  :level.getPigenemy().values() ) {
            pig.setCenterX(5);
            pig.draw(spriteBatch);
        }
        
        for (Sprite button  : level.getButtons().values()) {
            button.draw(spriteBatch);
        }

        // for (Bird bird : level.getBirds().values()) {
        //     bird.draw(spriteBatch);
        // }


        spriteBatch.end();

        
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