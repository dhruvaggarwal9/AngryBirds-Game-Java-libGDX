package com.angryBird;

import com.angryBird.Screens.LevelScreen;
import com.angryBird.objects.*;
import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {

        Pig p = new Pig("bigPig","p1.png",100,100);
        Button pause = new Button("pause.png");
        Level level = new Level("scene2.png",p,pause);
        setScreen(new LevelScreen(level,this));
    }
}