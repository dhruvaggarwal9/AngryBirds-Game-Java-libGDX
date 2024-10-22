package com.angryBird.objects;

public class Profile {
    
    String userName;
    String userID;
    int totalStarsachieved;
    int currentLevel;
    
    
    public Profile(String userName,String userID,int totalStars,int currLevel){
        this.userName = userName;
        this.userID = userID;
        this.totalStarsachieved = totalStars;
        this.currentLevel = currLevel;
    }
}
