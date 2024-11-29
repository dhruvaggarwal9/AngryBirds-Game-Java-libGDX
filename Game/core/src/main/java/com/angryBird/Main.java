package com.angryBird;

import java.util.ArrayList;

import com.angryBird.Screens.GameStatusScreen;
// import com.angryBird.Screens.LevelScreen;
import com.angryBird.Screens.LevelScreenM;
import com.angryBird.Screens.LoadingScreen;
import com.angryBird.Screens.MainScreen;
import com.angryBird.Screens.SeasonScreen;
import com.angryBird.Screens.SeasonsListScreen;
import com.angryBird.objects.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
// /** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class   Main extends Game {



    private ArrayList<Season> seasonsAvailable;
    private ArrayList<Bird> birdsUnlocked;
    private ArrayList<Pig> pigsAvailable;
    private ArrayList<BuildingMIII> buildingsAvailable;
    private ArrayList<Level> levelsUnlocked ;
    private ArrayList<Vector2> positions;
    public FitViewport viewport = new FitViewport(48, 27);




    private ArrayList<Integer> blockStrength;
    private ArrayList<Vector2> blockPosition;
    private ArrayList<Vector2> pigPositions;


    @Override
    public void create() {

        birdsUnlocked = new ArrayList<Bird>();
        seasonsAvailable = new ArrayList<Season>();
        pigsAvailable = new ArrayList<Pig>();
        buildingsAvailable = new ArrayList<BuildingMIII>();
        levelsUnlocked = new ArrayList<Level>();


    

   
        pigsAvailable.add(new Pig("jyadascaredpig", "p6.png", 100, 100,2));
        pigsAvailable.add(new Pig("smallpig", "p1.png", 100, 100,2));
        pigsAvailable.add(new Pig("scaredPig", "p4.png", 100, 100,2));
        pigsAvailable.add(new Pig("Badppig", "p1.png", 100, 100,2));
        pigsAvailable.add(new Pig("sabhyappig", "p5.png", 100, 100,2));
        pigsAvailable.add(new Pig("jyadascaredPig", "p6.png", 100, 100,2));


        birdsUnlocked.add(new Bird("red", "Red.png",1));
        birdsUnlocked.add(new Bird("yellow","Yellow.png",1));
        // birdsUnlocked.add(new Bird("yellow", "Yellow.png"));
        // birdsUnlocked.add(new Bird("red", "Red.png",1));
        birdsUnlocked.add(new Bird("Bomb", "Bomb.png", 3));
        birdsUnlocked.add(new Bird("boomerang", "Boomrang.png", 2));
        




        //LEVEL1

        blockStrength = new ArrayList<Integer>();
        blockPosition = new ArrayList<Vector2>();
        pigPositions = new ArrayList<Vector2>();

        
        // blockPosition.add(new Vector2(0.4f,0.4f));
        // blockPosition.add(new Vector2())
        blockPosition.add(new Vector2(0.5f,0.5f));
        blockPosition.add(new Vector2(3*0.5f,0.5f));
        blockPosition.add(new Vector2(5*0.5f,0.5f));
        blockPosition.add(new Vector2(7*0.5f,0.5f));
        blockPosition.add(new Vector2(9*0.5f,0.5f));
        blockPosition.add(new Vector2(11*0.5f,0.5f));
        blockPosition.add(new Vector2(13*0.5f,0.5f));
        // blockPosition.add(new Vector2(15*0.5f,0.5f));


        blockPosition.add(new Vector2(2*0.5f-0.25f,3*0.5f));
        blockPosition.add(new Vector2(5*0.5f,3*0.5f));
        blockPosition.add(new Vector2(9*0.5f-0.25f,3*0.5f));
        blockPosition.add(new Vector2(12*0.5f,3*0.5f));

        blockPosition.add(new Vector2(3*0.5f+0.1f,5*0.5f));
        blockPosition.add(new Vector2(7*0.5f-0.1f,5*0.5f));
        blockPosition.add(new Vector2(10*0.5f+0.18f,5*0.5f));

        blockPosition.add(new Vector2(5*0.5f-0.05f,7*0.5f));
        blockPosition.add(new Vector2(9*0.5f-0.18f,7*0.5f));

        blockPosition.add(new Vector2(7*0.5f-0.1f,9*0.5f));



        // blockStrength.add(1);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);

        pigPositions.add(new Vector2(3*0.5f+0.25f,3*0.5f));
        pigPositions.add(new Vector2(7*0.5f-0.05f,3*0.5f));
        pigPositions.add(new Vector2(10*0.5f+0.13f,3*0.5f));
        pigPositions.add(new Vector2(5*0.5f,5*0.5f));
        pigPositions.add(new Vector2(9*0.5f-0.25f,5*0.5f));
        pigPositions.add(new Vector2(7*0.5f-0.1f,7*0.5f));
        pigPositions.add(new Vector2(7*0.5f-0.1f,11*0.5f));



        ArrayList<Pig> tempList = new ArrayList<Pig>();
        
        tempList.add(new Pig("jyadascaredpig", "p6.png", 100, 100,2));
        tempList.add(new Pig("smallpig", "p1.png", 100, 100,2));
        tempList.add(new Pig("scaredPig", "p4.png", 100, 100,2));
        tempList.add(new Pig("Badppig", "p1.png", 100, 100,2));
        tempList.add(new Pig("sabhyappig", "p5.png", 100, 100,2));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,2));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,2));        


        ArrayList<Bird> birdstempList = new ArrayList<Bird>();

        birdstempList.add(birdsUnlocked.get(0));
        birdstempList.add(birdsUnlocked.get(1));

        BuildingMIII building1 = new BuildingMIII("TowerResearch", blockPosition, blockStrength, pigPositions);

        levelsUnlocked.add(new Level("level2.jpg",birdstempList, tempList, building1));



        //LEVEL 2
        blockStrength = new ArrayList<Integer>();
        blockPosition = new ArrayList<Vector2>();
        pigPositions = new ArrayList<Vector2>();



        blockPosition.add(new Vector2(0.5f,0.5f));
        blockPosition.add(new Vector2(3*0.5f,0.5f));
        blockPosition.add(new Vector2(5*0.5f,0.5f));
        blockPosition.add(new Vector2(7*0.5f,0.5f));
        blockPosition.add(new Vector2(9*0.5f,0.5f));
        
   

        blockPosition.add(new Vector2(3*0.5f,3*0.5f));
        blockPosition.add(new Vector2(5*0.5f,3*0.5f));
        blockPosition.add(new Vector2(7*0.5f,3*0.5f));

        blockPosition.add(new Vector2(2*0.5f,5*0.5f));
        blockPosition.add(new Vector2(4*0.5f,5*0.5f));
        blockPosition.add(new Vector2(6*0.5f,5*0.5f));
        blockPosition.add(new Vector2(8*0.5f,5*0.5f));

        blockPosition.add(new Vector2(4*0.5f,7*0.5f));
        blockPosition.add(new Vector2(6*0.5f,7*0.5f));

        blockPosition.add(new Vector2(3*0.5f,9*0.5f));
        blockPosition.add(new Vector2(5*0.5f,9*0.5f));
        blockPosition.add(new Vector2(7*0.5f,9*0.5f));

        blockPosition.add(new Vector2(5*0.5f,11*0.5f));

        blockPosition.add(new Vector2(4*0.5f,13*0.5f));
        blockPosition.add(new Vector2(6*0.5f,13*0.5f));

        blockPosition.add(new Vector2(5*0.5f,15*0.5f));



        blockPosition.add(new Vector2(-0.5f,0.5f));
        blockPosition.add(new Vector2(-0.5f,3*0.5f));
        blockPosition.add(new Vector2(-0.5f,5*0.5f));
        blockPosition.add(new Vector2(-0.5f,5*0.5f));
        blockPosition.add(new Vector2(-0.5f,7*0.5f));
        blockPosition.add(new Vector2(-0.5f,9*0.5f));

        blockPosition.add(new Vector2(11*0.5f,0.5f));
        blockPosition.add(new Vector2(11*0.5f,3*0.5f));
        blockPosition.add(new Vector2(11*0.5f,5*0.5f));
        blockPosition.add(new Vector2(11*0.5f,7*0.5f));
        blockPosition.add(new Vector2(11*0.5f,9*0.5f));
        blockPosition.add(new Vector2(11*0.5f,11*0.5f));

        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);


        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);

        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);
        blockStrength.add(1);



        pigPositions.add(new Vector2(0.5f,3*0.5f));
        pigPositions.add(new Vector2(9*0.5f,3*0.5f));
        pigPositions.add(new Vector2(2*0.5f,7*0.5f));
        pigPositions.add(new Vector2(8*0.5f,7*0.5f));
        pigPositions.add(new Vector2(3*0.5f,11*0.5f));
        pigPositions.add(new Vector2(7*0.5f,11*0.5f));
        pigPositions.add(new Vector2(5*0.5f,17*0.5f));


        
        tempList = new ArrayList<Pig>();

        tempList.add(new Pig("jyadascaredpig", "p6.png", 100, 100,2));
        tempList.add(new Pig("smallpig", "p1.png", 100, 100,2));
        tempList.add(new Pig("scaredPig", "p4.png", 100, 100,2));
        tempList.add(new Pig("Badppig", "p1.png", 100, 100,2));
        tempList.add(new Pig("sabhyappig", "p5.png", 100, 100,2));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,2));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,2));
        

        birdstempList = new ArrayList<Bird>();

        birdstempList.add(birdsUnlocked.get(0));
        birdstempList.add(birdsUnlocked.get(1));
        birdstempList.add(birdsUnlocked.get(2));

        BuildingMIII building2 = new BuildingMIII("Taj", blockPosition, blockStrength, pigPositions);

        levelsUnlocked.add(new Level("level3.png",birdstempList, tempList, building2));



        //LEVEL 3
        blockStrength = new ArrayList<Integer>();
        blockPosition = new ArrayList<Vector2>();
        pigPositions = new ArrayList<Vector2>();


        // blockPosition.add(new Vector2(0.4f,0.4f));
        // blockPosition.add(new Vector2())
        blockPosition.add(new Vector2(-4f + -1*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f + 0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +3*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +5*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +7*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +9*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +11*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +13*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +15*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +19*0.5f,0.5f));
        blockPosition.add(new Vector2(-4f +21*0.5f,0.5f));
        // blockPosition.add(new Vector4(15*0.5f,0.5f))4

        blockPosition.add(new Vector2(-4f +0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +3*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +5*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +7*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +9*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +11*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +13*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +15*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,3*0.5f));
        blockPosition.add(new Vector2(-4f +19*0.5f,3*0.5f));


        blockPosition.add(new Vector2(-4f +3*0.5f,5*0.5f));
        blockPosition.add(new Vector2(-4f +5*0.5f,5*0.5f));
        blockPosition.add(new Vector2(-4f +7*0.5f,5*0.5f));

        blockPosition.add(new Vector2(-4f +13*0.5f,5*0.5f));
        blockPosition.add(new Vector2(-4f +15*0.5f,5*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,5*0.5f));



        blockPosition.add(new Vector2(-4f +3*0.5f,7*0.5f));
        blockPosition.add(new Vector2(-4f +5*0.5f,7*0.5f));
        blockPosition.add(new Vector2(-4f +7*0.5f,7*0.5f));
        blockPosition.add(new Vector2(-4f +13*0.5f,7*0.5f));
        blockPosition.add(new Vector2(-4f +15*0.5f,7*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,7*0.5f));

        blockPosition.add(new Vector2(-4f +3*0.5f,9*0.5f));
        blockPosition.add(new Vector2(-4f +5*0.5f,9*0.5f));
        blockPosition.add(new Vector2(-4f +15*0.5f,9*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,9*0.5f));

        blockPosition.add(new Vector2(-4f +3*0.5f,9*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,9*0.5f));

        blockPosition.add(new Vector2(-4f +3*0.5f,11*0.5f));
        blockPosition.add(new Vector2(-4f +17*0.5f,11*0.5f));



        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);

        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);

        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(3);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);
        blockStrength.add(2);


        // pigPositions.add(new Vector2(-40f-0.1f,3*0.4f));
        pigPositions.add(new Vector2(-4f + -1*0.5f,3*0.5f));
        pigPositions.add(new Vector2(-4f + 0.5f,5*0.5f));
        pigPositions.add(new Vector2(-4f +3*0.5f,15*0.5f));
        pigPositions.add(new Vector2(-4f +5*0.5f,11*0.5f));
        pigPositions.add(new Vector2(-4f +7*0.5f,9*0.5f));
        pigPositions.add(new Vector2(-4f +9*0.5f,5*0.5f));
        pigPositions.add(new Vector2(-4f +11*0.5f,5*0.5f));
        pigPositions.add(new Vector2(-4f +13*0.5f,9*0.5f));
        pigPositions.add(new Vector2(-4f +15*0.5f,11*0.5f));
        pigPositions.add(new Vector2(-4f +17*0.5f,15*0.5f));
        pigPositions.add(new Vector2(-4f +19*0.5f,5*0.5f));
        pigPositions.add(new Vector2(-4f +21*0.5f,3*0.5f));




        tempList = new ArrayList<Pig>();
        // tempList.add(pigsAvailable.get(0));
        tempList.add(new Pig("jyadascaredpig", "p6.png", 100, 100,3));
        tempList.add(new Pig("smallpig", "p1.png", 100, 100,2));
        tempList.add(new Pig("scaredPig", "p4.png", 100, 100,3));
        tempList.add(new Pig("Badppig", "p1.png", 100, 100,2));
        tempList.add(new Pig("sabhyappig", "p5.png", 100, 100,3));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,3));

        tempList.add(new Pig("jyadascaredpig", "p6.png", 100, 100,2));
        tempList.add(new Pig("smallpig", "p1.png", 100, 100,2));
        tempList.add(new Pig("scaredPig", "p4.png", 100, 100,2));
        tempList.add(new Pig("Badppig", "p1.png", 100, 100,2));
        tempList.add(new Pig("sabhyappig", "p5.png", 100, 100,3));
        tempList.add(new Pig("jyadascaredPig", "p6.png", 100, 100,3));
        // tempList.add(pigsAvailable.get(1));
        // tempList.add(pigsAvailable.get(2));
        // tempList.add(pigsAvailable.get(3));
        // tempList.add(pigsAvailable.get(4));



        birdstempList = new ArrayList<Bird>();

        birdstempList.add(birdsUnlocked.get(0));
        birdstempList.add(birdsUnlocked.get(1));
        birdstempList.add(birdsUnlocked.get(2));
        birdstempList.add(birdsUnlocked.get(3));

        BuildingMIII building3 = new BuildingMIII("Taj", blockPosition, blockStrength, pigPositions);


        levelsUnlocked.add(new Level("level1o.png",birdstempList, tempList, building3));
        


        

        seasonsAvailable.add(new Season("Season1", "LevelMap.png","Season-1.png","levelMapBlur.png", levelsUnlocked));
        seasonsAvailable.add(new Season("Season2", "LevelMap.png","Season-2.png","levelMapBlur.png", null));
        seasonsAvailable.add(new Season("Season3", "LevelMap.png","Season-3.png", "levelMapBlur.png",null));
        seasonsAvailable.add(new Season("Season4", "LevelMap.png","Season-4.png", "levelMapBlur.png",null));
        seasonsAvailable.add(new Season("Season5", "LevelMap.png","Season-5.png", "levelMapBlur.png",null));
        // setScreen(new LevelScreen(level,this));
        setScreen(new LoadingScreen(this));
        // setScreen(new MainScreen(this));
        // setScreen(new LevelScreenM(this, levelsUnlocked.get(1)));
        // setScreen(new GameStatusScreen(this, new SeasonScreen(this,seasonsAvailable.get(0)), seasonsAvailable.get(0).getLevels().get(2)));
        // setScreen(new SeasonScreen(this, seasonsAvailable.get(0)));
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
