package com.angryBird.objects;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.badlogic.gdx.math.Vector2;




public class BuildingM {
    
    private String buildingName;
    private ArrayList<Integer> emptyPositions; //stores tierwise positions
    private HashMap<Integer,ArrayList<Block>> blocksUsed;         //storing blocks tierwise
    // private ArrayList<Integer> arrangement;
    private ArrayList<Integer> blockStrength;
    private int baseSpan;

    public BuildingM(String name,ArrayList<Integer> positions,ArrayList<Integer> blockstrength){
        
        buildingName = name;
        emptyPositions = positions;
        blockStrength = blockstrength;
        createBlocks();
    }


    //creating required blocks for positions given as input
    public void createBlocks(){


        for (int i = 0; i < emptyPositions.size(); i++) {
            
            if(i == 0){
                baseSpan = emptyPositions.get(0);
                for (int j = 0; j < emptyPositions.get(0); j++) {
                    blocksUsed.putIfAbsent(-1, new ArrayList<Block>());
                    blocksUsed.get(-1).add((new Block("block.png", 1.5f,0.25f,blockStrength.get(i),true)));                    
                }
            }

            //vertical blocks
            blocksUsed.putIfAbsent(2*i, new ArrayList<Block>());
            for (int j = 0; j < emptyPositions.get(i)+1; j++) {
                blocksUsed.get(2*i).add(new Block("block.png", 1.5f, 0.25f, blockStrength.get(i), false));
            }

            //horizontal blocks
            blocksUsed.putIfAbsent(2*i+1, new ArrayList<Block>());
            
            if(i == emptyPositions.size() - 1 || emptyPositions.get(i) >= emptyPositions.get(i+1)){
                for (int j = 0; j < emptyPositions.get(i); j++) {
                    blocksUsed.get(2*i+1).add(new Block("block.png", 1.5f, 0.25f, blockStrength.get(i), false));
                }
            }
            else{
                for (int j = 0; j < emptyPositions.get(i+1); j++) {
                    blocksUsed.get(2*i+1).add(new Block("block.png", 1.5f, 0.25f, blockStrength.get(i), false));
                }
            }

        }
    }   

    public HashMap<Integer, ArrayList<Block>> getBlocksUsed() {
        return blocksUsed;
    }


}
