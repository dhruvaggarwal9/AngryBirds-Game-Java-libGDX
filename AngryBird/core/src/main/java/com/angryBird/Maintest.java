//package com.angryBird;
//
//import com.angryBird.objects.Bird;
//import com.angryBird.objects.Level;
//import com.angryBird.objects.Pig;
//import com.angryBird.objects.Season;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class Maintest {
//
//    private Main mainGame;
//
//    @Before
//    public void setUp() {
//        mainGame = new Main();
//        mainGame.create(); // Call the create method to initialize game data.
//    }
//
//    @Test
//    public void testSeasonsInitialization() {
//        ArrayList<Season> seasons = mainGame.getSeasons();
//        assertNotNull("Seasons should not be null", seasons);
//        assertEquals("Seasons list size should be 5", 5, seasons.size());
//        assertEquals("First season name should be 'Season1'", "Season1", seasons.get(0).getName());
//    }
//
//    @Test
//    public void testBirdsInitialization() {
//        ArrayList<Bird> birds = mainGame.getBirdsUnlocked();
//        assertNotNull("Birds should not be null", birds);
//        assertEquals("Birds list size should be 4", 4, birds.size());
//        assertEquals("First bird name should be 'red'", "red", birds.get(0).getName());
//    }
//
//    @Test
//    public void testPigsInitialization() {
//        ArrayList<Pig> pigs = mainGame.getPigsAvailable();
//        assertNotNull("Pigs should not be null", pigs);
//        assertEquals("Pigs list size should be 6", 6, pigs.size());
//        assertEquals("First pig name should be 'jyadascaredpig'", "jyadascaredpig", pigs.get(0).getName());
//    }
//
//    @Test
//    public void testLevelsUnlocked() {
//        ArrayList<Level> levels = mainGame.getSeasons().get(0).getLevels();
//        assertNotNull("Levels in Season1 should not be null", levels);
//        assertEquals("There should be 3 levels in Season1", 3, levels.size());
//        assertEquals("First level background should be 'level3.png'", "level3.png", levels.get(0).getBackground());
//    }
//}
