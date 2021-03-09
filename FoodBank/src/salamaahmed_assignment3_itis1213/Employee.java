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
public class Employee  extends User{//extends user
    int earnings;
    String position;
    String name;
   int ID;

    public Employee(int earnings, String position, String name, int ID) {//employee constructor
        this.earnings = earnings;
        this.position = position;
        this.name = name;
        this.ID = ID;
    }
//below you have all the setters adn getters of the fields above
    public int getEarnings() {
        return earnings;
    }

    public void setEarnings(int earnings) {
        this.earnings = earnings;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
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
