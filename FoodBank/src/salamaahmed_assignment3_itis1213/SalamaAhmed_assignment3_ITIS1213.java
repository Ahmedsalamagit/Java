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
public class SalamaAhmed_assignment3_ITIS1213 {

    /**
     * Test harness program: My program allows unlimited donors, employees and volunteers to be added. 
     * Each given an id. Each donor can have unlimited perishable or non perishable items to his or her name
     * The program will search for your user when u enter that id for menu choices. The menu choices are: 
     * ONLY DONORS! 1.Donate food  2.Remove food  3.View food donated
     * 4.If you are a volunteer and wish to remove the expired item  5.If you are an employee and wish to see your salary, name and position
     * 
     * @param args the command line arguments
     */
   
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // TODO code application logic here
         int userid=0;//to keep check of how many objects in Arraylist of donor
         int useride=0;//to keep check of how many objects in Arraylist of employee
         int useridv=0;//to keep check of how many objects in Arraylist of vulounteers
         int id=1;//user id 
          ArrayList<Donor> donor =new ArrayList<Donor>();//arraylist of donor
          ArrayList<Employee> employee =new ArrayList<Employee>();//arraylist of employee
          ArrayList<Volunteer> volunteer =new ArrayList<Volunteer>();//arraylist of volunteers
          try {
        System.out.println("How many users would you like to add? ");
        int users = input.nextInt();
        int usercheck=0;//checks how many users have been creates
        while(usercheck<users){//if the number of users they would like has been reached end the loop
        System.out.println("Add a new user");
        System.out.println("1. Donor \t 2.Employee \t 3.Volunteer"); 
        int choice2 = input.nextInt();
         System.out.println("What is your name");
        String name=input.next();
        switch(choice2){
            case 1: 
                donor.add(new Donor(name,id)); //new donor object added to arraylist
                System.out.println(donor.get(userid).getID() +" Will be your id number!!");
                userid++;//increment users created
                break;
            case 2:            
                System.out.println("What is your salary");
                int salary=input.nextInt();
                System.out.println("What is your job title");
                String title = input.next();
                employee.add(new Employee(salary,title,name,id)); //new employee object added to arraylist
                System.out.println(employee.get(useride).getID()+" Will be your id number!!");
                useride++;//increment users created
                break;
            case 3:                
                volunteer.add(new Volunteer(name,id));   //new volunteer object added to arraylist
                System.out.println(volunteer.get(useridv).getID()+" Will be your id number!!");
                useridv++;//increment users created
                break;
        }
        id++;usercheck++;//increment id num and usercheck
        }
        while(true){
            System.out.println("");
            System.out.println("ONLY DONORS! 1.Donate food  2.Remove food  3.View food donated");
             System.out.println("4.If you are a volunteer and wish to remove the expired item \t 5.If you are an employee "); 
             System.out.println("");
           int choice2 = input.nextInt();
        switch(choice2){
            case 1: //if choice = 1
                if(donor.isEmpty()){//if donor is empty break
                    System.out.println("No donor available");
                    break;
                }
                else{
                System.out.println("What is your ID number,  this option is only for donors");
            int idn=input.nextInt();//id number of user
                System.out.println("Would you like to donate perishable food(1) or non perishable food(2)");
                int choice3 = input.nextInt();//users choice of perishable or nonperishable food
                System.out.println("What is the food name and type");
                String fname=input.next();
                String type = input.next();
                if(choice3==1){//if perishable
                    System.out.println("What is the expiration date (yearmonthday)");
                    int expdate=input.nextInt();//ask for expiration date
                for(int i = 0; i<donor.size();i++){
                        if(donor.get(i).getID()==idn){//if the donor id is equal to the id entered
                      donor.get(i).setFoodp(new Perishables(expdate,fname,type));//add perishable food
                        }
                }
                }
                else{
                        for(int i = 0; i<donor.size();i++){
                        if(donor.get(i).getID()==idn){//if the donor id is equal to the id entered
                      donor.get(i).setFoodnp(new NonPerishables(fname,type));//add nonperishable food 
                        }
                        }
                        }
                }
                break;
            case 2://if choice = 2
                if(donor.isEmpty()){//if donor is empty break
                    System.out.println("No donor available");
                    break;
                }
                else{
                System.out.println("What is your ID number");
                int idn2=input.nextInt();//id num of user
                System.out.println("What is the food name that you would like to remove");
                String fnameremove=input.next();//food name of item they wish to remove
                System.out.println(" Is this a perishable food(1) or non perishable food(2)");
                int choice4=input.nextInt();//users choice of perishable or nonperishable food
                if(choice4==1){
                    for(int i = 0; i<donor.size();i++){//if the donor id is equal to the id entered
                        if(donor.get(i).getID()==idn2){
                    for(int j=0; j<donor.get(i).getFoodp().size();j++){//iterate through arraylist of perishables of that donor 
                if(donor.get(i).getFoodp().get(j).getFoodName().equals(fnameremove)){//if the name is equal to name enetered
                    donor.get(i).getFoodp().remove(j);//remove food
                }
                    }}
                    }
                }
                else{
                    for(int i = 0; i<donor.size();i++){//if the donor id is equal to the id entered
                        if(donor.get(i).getID()==idn2){
                    for(int j=0; j<donor.get(i).getFoodnp().size();j++){//iterate through arraylist of nonperishables of that donor 
                if(donor.get(i).getFoodnp().get(j).getFoodName().equals(fnameremove)){//if the name is equal to name entered 
                       donor.get(i).getFoodnp().remove(j); //remove food
                    }
                    }
                        }
                    }
                }
                }
                break;
            case 3://if choice = 3
                if(donor.isEmpty()){//if donor is empty break
                    System.out.println("No donor available");
                    break;
                }
                else{
                System.out.println("What is your ID number,  this option is only for donors");
            int idn3=input.nextInt();
            for(int i = 0; i<donor.size();i++){
                        if(donor.get(i).getID()==idn3){//if the donor id is equal to the id entered
                  donor.get(i).print(donor.get(i).getFoodnp(),donor.get(i).getFoodp());//print out the donors list of food donated
                        }
            }
                }
                break;
        
            case 4: //if choice = 4
                if(volunteer.isEmpty()){//if volunteer is empty break
                    System.out.println("No volunteer available");
                    break;
                }
                else{
                    System.out.println("What is your ID number,  this option is only for volunteers");
            int idn4=input.nextInt();
            for(int i = 0; i<donor.size();i++){
                int todaysdate =20190419;//todays date
                for(int j=0; j<donor.get(i).getFoodp().size();j++)//iterate through all of food items of all donors
                if(donor.get(i).getFoodp().get(j).getExp()<todaysdate){//if todays date has passed 
                    donor.get(i).getFoodp().remove(j);//remove the food item
                }
            }
                    for(int i = 0; i<volunteer.size();i++){
                        if(volunteer.get(i).getID()==idn4){//if the volunteer id is equal to the id entered
                    System.out.println(volunteer.get(i).getName()+" has gotten rid of the expired food");//print out the name of the volunteer that removed the food
                        }
                }
                }
            break;
            case 5://if choice = 5
                 if(employee.isEmpty()){//if employee is empty break
                     System.out.println("No employee available");
                     break;
                }
                else{
                System.out.println("What is your ID number,  this option is only for employees");
            int idn4=input.nextInt();
            for(int i = 0; i<employee.size();i++){
                        if(employee.get(i).getID()==idn4){//if the employee id is equal to the id entered
                            System.out.println(employee.get(i).getName()+", you have earned a total of "+ 
                                    employee.get(i).getEarnings() + " dollars this year, as a " + employee.get(i).getPosition());//print out the salary name and position of employee
                        }
            
        }
                 }
        }
        }
       } catch (InputMismatchException ime) {System.out.println("Sorry, wrong input"); 
       } }
}
        
        
    
    

