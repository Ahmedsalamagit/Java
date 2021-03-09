/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salamaahmed_assignment3_itis1213;

/**
 *
 * @author ahmed
 */
import java.util.*;
public class Donor extends User{//donor extends user
   String name;//name or user
   int ID;//id of user
    ArrayList<Perishables> foodp = new ArrayList<Perishables>();//arraylist of peridsahbles
   ArrayList<NonPerishables> foodnp = new ArrayList<NonPerishables>();//arraylist of nonperishables
    public Donor(String name, int ID){//, Perishables perish, NonPerishables nonperish) {//donor constructorr
        this.name = name;
        this.ID = ID;
        //this.foodp.add(perish);
        //this.foodnp.add(nonperish);
    }
//below you have all the setters adn getters of the fields above
    public ArrayList<Perishables> getFoodp() {
        return foodp;
    }

    public void setFoodp(Perishables perish) {
        this.foodp.add(perish);//add perisheable to arraylist
    }

    public ArrayList<NonPerishables> getFoodnp() {
        return foodnp;
    }

    public void setFoodnp(NonPerishables nonperish) {
        this.foodnp.add(nonperish);//add nonperisheable to arraylist
    }
   @Override
       public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
   
     public void print(ArrayList<NonPerishables> foodnp,ArrayList<Perishables> foodp){//take arraylists as parameters
         System.out.println("NonPerishables: ");//print out arraylist of nonperishables
         for(NonPerishables food:foodnp ){//for loop
             System.out.println("name: " + food.getFoodName()+ " category: "+ food.getFoodType());
         }
         System.out.println("Perishables: ");//print out arraylist of perishables
          for(int i = 0; i<foodp.size(); i++){//for loop
             System.out.println("name: " + foodp.get(i).getFoodName()+ " category: "+ foodp.get(i).getFoodType());
         }
     }
             
}
