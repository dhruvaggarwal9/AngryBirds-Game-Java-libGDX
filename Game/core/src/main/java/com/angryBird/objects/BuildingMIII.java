package com.angryBird.objects;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.badlogic.gdx.math.Vector2;

public class BuildingMIII {
    
    private String buildingName;
    private ArrayList<Vector2> blockPositions;
    private ArrayList<Integer> blockStrengths;
    private ArrayList<Vector2> pigPositions;

    private ArrayList<Block> blocks;

    public BuildingMIII(String buildingName, ArrayList<Vector2> blockPositions,ArrayList<Integer> blockStrengths,ArrayList<Vector2> pigPositions){
        
        this.buildingName = buildingName;
        this.blockPositions = blockPositions;
        this.blockStrengths = blockStrengths;
        this.pigPositions = pigPositions;
        blocks = new ArrayList<Block>();
        createBlocks();
    }    



    public void createBlocks(){

        int i = 0;
        for (Vector2 vector2 : blockPositions) {
            
            
            String img;
            if(blockStrengths.get(i) == 1){
                img = "blockM1.png";
            }
            else if(blockStrengths.get(i) == 2){
                img = "blockM2.png";
            }
            else if(blockStrengths.get(i) == 3){
                img = "blockM3.png";
            }
            else{
                img = "blockM3.png";
            }

            blocks.add(new Block(img, 0.8f, 0.8f, blockStrengths.get(i), false));
            i++;
        }

    }

    public ArrayList<Vector2> getBlockPositions() {
        return blockPositions;
    }

    public ArrayList<Vector2> getPigPositions() {
        return pigPositions;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }
}
