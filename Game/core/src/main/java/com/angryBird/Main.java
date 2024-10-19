package com.angryBird;

import com.angryBird.Screens.MainScreen;
import com.badlogic.gdx.Game;

public class Main extends Game {
    @Override
    public void create() {
        setScreen(new MainScreen(this));
    }
}