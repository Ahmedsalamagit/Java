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
 * This class defines one wedge of a wheel for the wheel of fortune game
 * @author ahmed salama
 */
public class Wedge {
    
    private Wheel.WedgeType type;
    private int amount = 0;
    String prize;//string for prize
    ArrayList <String> Prizes = new ArrayList<>();//string arraylist for prizes
    /**
     * Constructor
     * @param type Wheel.WedgeType  
     */
    //get prize method
     public String getPrize() {
       
        return prize;
    }
    public Wedge(Wheel.WedgeType type) {
        p();
        this.type = type;
        if (type == Wheel.WedgeType.MONEY) {
            amount = (int)(Math.random()*20 + 1)*25;
        }
        if (type == Wheel.WedgeType.PRIZE) {
              Random r = new Random();//random number generator
            int ran = r.nextInt(8) + 0;
            prize = Prizes.get(ran);//prize set to the prize of the index in the random number
            
        }
        
    }
 
    /**
     * Getter
     * @return Wheel.WedgeType 
     */
    public Wheel.WedgeType getType() {
        return type;
    }

    /**
     * Getter
     * @return int amount (only for wedges of Wheel.WedgeType.MONEY)
     */
    public int getAmount() {
        return amount;
    }
    
   private void p() {//prizes method
        Prizes.add("watch");//add each prize to arraylist
        Prizes.add("concert tickets");
        Prizes.add("miami cruise");
        Prizes.add("tv");     
        Prizes.add("samsung galaxy");        
        Prizes.add("Ipad");        
        Prizes.add("Laptop");      
        Prizes.add("Airpods");
        Prizes.add("Iphone");
    }
    
    
    
}
