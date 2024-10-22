package com.angryBird;

import java.util.ArrayList;

import com.angryBird.Screens.LevelScreen;
import com.angryBird.Screens.LoadingScreen;
import com.angryBird.Screens.MainScreen;
import com.angryBird.Screens.SeasonsListScreen;
import com.angryBird.objects.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class   Main extends Game {

    ArrayList<Season> seasonsAvailable;
    ArrayList<Bird> birdsUnlocked;
    ArrayList<Pig> pigsAvailable;
    ArrayList<Building> buildingsAvailable;
    ArrayList<Level> levelsUnlocked ;
    ArrayList<Vector2> positions;
    public FitViewport viewport = new FitViewport(16, 9);
    @Override
    public void create() {

        // Pig p = new Pig("bigPig","p1.png",100,100);
        // Button pause = new Button("pause.png");
        // Level level = new Level("scene2.png",p,pause);
        // seasonsAvailable = new ArrayList<Season>();
        birdsUnlocked = new ArrayList<Bird>();
        seasonsAvailable = new ArrayList<Season>();
        pigsAvailable = new ArrayList<Pig>();
        buildingsAvailable = new ArrayList<Building>();
        levelsUnlocked = new ArrayList<Level>();
        positions = new ArrayList<Vector2>();

        positions.add(new Vector2(0, 0));
        positions.add(new Vector2(70, 0));
        positions.add(new Vector2(140, 0));
        positions.add(new Vector2(35, 70));
        positions.add(new Vector2(105, 70));
        positions.add(new Vector2(70, 140));
        // positions.add(new Vector2(35, 70));
        Block block = new Block("b1", "block.png", 10, 60);

        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100));

        Building building = new Building("firstBuilding",block,positions);
        buildingsAvailable.add(building);
        birdsUnlocked.add(new Bird("red", "red.png"));
        levelsUnlocked.add(new Level("scene3.png", pigsAvailable, building));
        
        seasonsAvailable.add(new Season("Season1", "season1.png","season1.png", levelsUnlocked));
        seasonsAvailable.add(new Season("Season2", "season2.png","season2.png", null));
        seasonsAvailable.add(new Season("Season3", "season3.png","season3.png", null));
        seasonsAvailable.add(new Season("Season4", "season4.png","season4.png", null));
        seasonsAvailable.add(new Season("Season5", "season5.png","season5.png", null));
        // setScreen(new LevelScreen(level,this));

        setScreen(new LoadingScreen(this));
    }

    public ArrayList<Season> getSeasons(){
        return seasonsAvailable;
    }

    public ArrayList<Bird> getBirdsUnlocked(){
        return birdsUnlocked;
    }

    public ArrayList<Pig> getPigsAvailable(){
        return pigsAvailable;
    }
}
