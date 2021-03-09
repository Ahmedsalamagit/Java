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
abstract public class User {//abstract class
   String name;
   int ID;
//below you have all the setters adn getters of the fields above
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
   
}
