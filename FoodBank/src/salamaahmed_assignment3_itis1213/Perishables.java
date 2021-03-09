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
public class Perishables extends FoodBank{//extends foodbank
    int exp;
    String foodName;
    String foodType;

   

    public Perishables(int exp, String foodName, String foodType) {//constructor of perishables
        this.exp = exp;
        this.foodName = foodName;
        this.foodType = foodType;
    }
//below you have all the setters adn getters of the fields above
    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
       public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}
