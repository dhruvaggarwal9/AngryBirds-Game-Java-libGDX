package com.angryBird.Screens;

import java.util.ArrayList;

import com.angryBird.Main;
import com.angryBird.objects.Button;
import com.angryBird.objects.Season;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
// import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SeasonsListScreen implements Screen{
    
    ArrayList<Season> seasonsAvailable;
    ArrayList<Sprite> seasonThemes;
    Button backButton;
    Sprite backButtonSprite;
    Vector2 touch;
    SpriteBatch spriteBatch;
    Main game;




    public SeasonsListScreen(Main game){

        
        this.game = game;
        seasonsAvailable = game.getSeasons();
        seasonThemes = new ArrayList<Sprite>();
        spriteBatch = new SpriteBatch();
        touch = new Vector2();
        backButton = new Button("back.png");
        backButtonSprite = backButton.getButtonSprite();
        
        
        for (Season season : seasonsAvailable) {
            
            seasonThemes.add(new Sprite(season.getThemeImg()));
        }

        setPosition(Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight());
    }


    
    public void setPosition(float width, float height) {
        // Resize your screen here. The parameters represent the new window size.
        
        for(int i = 0; i< seasonThemes.size(); i++){
            seasonThemes.get(i).setBounds(i*width,0, width, height);
        }

        backButtonSprite.setPosition(0, height - backButtonSprite.getHeight());
    }

    public void handleInput(){

        if (Gdx.input.justTouched()) {

            touch.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());

            if(backButtonSprite.getBoundingRectangle().contains(touch)){
                game.setScreen(new MainScreen(game));
            }

            else{
               for(int i = 0; i<seasonThemes.size(); i++){
                    if(seasonThemes.get(i).getBoundingRectangle().contains(touch)){
                        game.setScreen(new SeasonScreen(game, seasonsAvailable.get(i)));
                    }
               }
            }
          
        }

    }


    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        handleInput();

        spriteBatch.begin();

        for (Sprite sprite : seasonThemes) {
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
        spriteBatch.dispose();
      
    }

}
