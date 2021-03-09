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
public class Volunteer extends User {//extends user
    String name;
   int ID;

    public Volunteer(String name, int ID) {//volunteer constructor
        this.name = name;
        this.ID = ID;
    }
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
