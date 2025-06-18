package com.angryBird.objects;

import java.io.*;
import com.angryBird.Main;

public class SaveLoadUtility {

    // Save the game state to a file
    public static void saveGame(Main game, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(game);
            System.out.println("Game saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save the game.");
        }
    }

    // Load the game state from a file
    public static Main loadGame(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Main loadedGame = (Main) ois.readObject();
            System.out.println("Game loaded successfully from " + filePath);
            return loadedGame;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed to load the game.");
            return null;
        }
    }
}
