/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*@author Ahmed salama
 *@version 3/22/19
*
 */
package woffortune;
import java.util.*;
/**
 * Class that defines a player for a game with monetary winnings and 
 * a limited number of choices
 * @author clatulip
 */
    public class Player {
    private int winnings = 0; // amount of money won
    private String name; // player's name
    private int numGuesses = 0; // number of times they've tried to solve puzzle
    ArrayList<String> Prizes = new ArrayList<>(); 
    String prize;
  
    /**
     * Constructor
     * @param name String that is the player's name
     */
    
  
      public void Prizes() {
        this.Prizes = Prizes;
    }
       
    public String getPrize() {
        //print out prize
        
        return prize;
    }
 
    public Player(String name) {
        this.name = name;
    }
 
    /**
     * Getter
     * @return int amount of money won so far 
     */
    public int getWinnings() {
        return winnings;
    }

    /**
     * Adds amount to the player's winnings
     * @param amount int money to add
     */
    public void incrementScore(int amount) {
        if (amount < 0) return;
        this.winnings += amount;
    }
      public void incrementPrize(String Prize) {
          prize += Prize;//add prize to prize
         // int i = 0; FAIL
        //Prizes.add(Prize); //add prize to arralist
        //prize += this.Prizes.get(i);//add the prize to 
    // i++;
     prize = prize.replaceAll("null", "");//replace null in prize
              }
    /**
     * Getter 
     * @return String player's name 
     */
    public String getName() {
        return name;
    }

    /**
     * Getter
     * @return int the number of guesses used up 
     */
    public int getNumGuesses() {
        return numGuesses;
    }

    /** 
     * Add 1 to the number of guesses used up
     */
    public void incrementNumGuesses() {
        this.numGuesses++;
    }
    
    /**
     * Resets for a new game (only number of guesses)
     * This does not reset the winnings.
     */
    public void reset() {
        this.numGuesses = 0;
    }
    
    public void bankrupt() {
        this.winnings = 0;
        prize=null;//clear string if bankrupt
    }
    
}
