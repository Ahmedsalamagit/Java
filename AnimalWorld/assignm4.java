/*author @ Ahmed Salama
 * version @ 12/6/2018
 * description: create a game where user inputs animals and searches for them
 */
package labs;
import java.util.Scanner;
public class assignm4 {
		static boolean[][] exploredWorld;//global variable declared
		public static String[][] makeWorld(String[][] world, int posX, int posY) {
			for (int i = 0; i<exploredWorld.length; i++) {//For loop to create world
				for (int j = 0; j<exploredWorld[i].length; j++) {
					//If the current indexes equal the position args, show an X
					if (posX == i && posY == j) {
						world[i][j] = "x";//Set users location
					} 
					else if (exploredWorld[i][j]) {
						world[i][j] = "O";
					//Set where the player has moved
					} 
					else {
						world[i][j] = "*";
					}//All other set a star
				}
			}
			return world;
		}
		public static void printMainMenu() {
			System.out.println("Select an option: "
			+ "\n1. Insert an animal into the world"
			+ "\n2. Remove an animal from the world"
			+ "\n3. Explore the world");
		}
		public static void printMoveMenu() {
		System.out.println("Select an option: "
				+ "\nW. Move Forward"
				+ "\nA. Move Left"
				+ "\nS. Move Backward"
				+ "\nD. Move Right"
				+ "\nI. Display Observed Animals"
				+ "\nE. Exit	");
		}
		public static void insertAnimalToWorld(String[][] animalLocations) {
		Scanner sc=new Scanner(System.in);
		while(true) {	
//Ask user for x and y coordinates
			System.out.println("Enter the x coordinate of the position of the animal: ");
			int x=sc.nextInt();
			System.out.println("Enter the y coordinate of the position of the animal: ");
			int y=sc.nextInt();
			if (isEmptyBlock(animalLocations,x,y) == true) {		
				//Get the animal's name from the user
				System.out.println("Enter the name of the animal: ");
				String name = sc.next();
				animalLocations[x][y] = name;
				System.out.println(name+" has been added!");
							//Break 
							break;
			}
			else {		
				System.out.println("An animal is already there, or you have invalid coordinates!");
				System.out.println(" ");
			}
		}
		}
		public static void removeAnimalFromWorld(String[][] animalLocations) {
			Scanner sc=new Scanner(System.in);
			while(true) {//loop for invalid coordinates or position is empty
				//ask user for x and y coordinates
			System.out.println("Enter the x coordinate of the position of the animal: ");
			int x=sc.nextInt();
			System.out.println("Enter the y coordinate of the position of the animal: ");
			int y=sc.nextInt();
			if (animalLocations[x][y]=="") {
				System.out.println("Invalid coordinates or the postion is empty");
			} 
				else {
					//Reset the location to an empty string
					animalLocations[x][y] = "";
					System.out.println("Animal removed.");
					//Break the loop 
					break;
				}
			}
		}
		public static boolean isEmptyBlock(String[][] world, int x, int y) {
			if (x < 0 || y < 0 || x >= world.length || y >= world[0].length) {
				return false;//Return false if coordinates are out of range
			}
			//If string is empty
			else if (world[x][y] == null) {
				return false;
			}
			return true;
		}
		public static String[] updateObservedAnimals(String[] observedAnimals, String animal) {
			for (int i = 0; i<observedAnimals.length; i++) {
				// Add the animal if array is empty
				if (observedAnimals[i].equals("")) {
					observedAnimals[i] = animal;
					break;//Break the loop
				}
			}
			return observedAnimals;

		}
		public static void printWorld(String[][] world) {
			for (int i = 0; i < world.length; i++) {//For loop to print the world
				for (String s : world[i]) {
					System.out.print(s + " ");
				}
				System.out.println();
			}
		}
		public static void explore(String[][] world, String[][] animalLocations, String[] observedAnimals) {
			Scanner input = new Scanner(System.in);
			char choice = 0;
			int x = 0;
			int y = 0;
			//Loop until the user wishes to exit
			while (choice != 'e') {
				//Print the world and the move menu
				System.out.println("");
				printWorld(world);
				printMoveMenu();
				choice = input.next().toLowerCase().charAt(0);//for lowercase letter inputted
				if (choice == 'w') {		//Move up
					--x;
					//Make sure we are still in the world
					if (x < 0) {
						x = 0;
						System.out.println("You left the world!");
					}
					//Move the user
					world = move(world,x,y,observedAnimals,animalLocations,null);
				} 
				else if (choice == 'a') {		//Move left
					--y;
					//Making sure we are still in the world
					if (y < 0) {
						y = 0;
						System.out.println("You left the world");
					}
					//Moving  the user
					world = move(world,x,y,observedAnimals,animalLocations,null);
				} 
				else if (choice == 's') {		//Move down
					++x;
					//Making sure we are in the world
					if (x >= world.length) {
						x = 0;
						System.out.println("You left the world");
					}

					//Move the user
					world = move(world,x,y,observedAnimals,animalLocations, null);
				} 
				else if (choice == 'd') {		//Move right
					++y;
					//Making sure we are in the world
					if (y >= world[0].length) {
						y = 0;
						System.out.println("You left the world");
					}
					//Moving the user
					world = move(world,x,y,observedAnimals,animalLocations,null);
				//Display observed animals
				} 
				else if (choice == 'i') {
					System.out.println("Your observed animals are:");
					printobservedAnimals(observedAnimals);
				} 
				else if (choice == 'e') {
					System.out.println("Goodbye");
					System.exit(0);
				//For invalid input
				} 
				else {
					System.out.println("Unknown input");
				}
			}
		}
		public static String[][] move(String[][] world, int x, int y, String[] observedAnimals,String[][] animalLocations,String move) {
			if (x < 0 || y < 0) {
				return makeWorld(world,x,y);
			} 
			else if (x > world.length || y > world.length) {
				return makeWorld(world,x,y);
			}
			//Set the coordinates to explored
			exploredWorld[x][y] = true;
			//Check to see if an animal exists at the current array
			if (animalLocations[x][y].isEmpty()) {	
				System.out.println("You did not encounter any animals");
			} 
			else {	
				observedAnimals = updateObservedAnimals(observedAnimals,animalLocations[x][y]);
				System.out.println("You found an animal");
				printobservedAnimals(observedAnimals);
			}

			//Display the world
			return makeWorld(world,x,y);
		} static void printobservedAnimals(String[] observedAnimals) {
			for (int i = 0; i < observedAnimals.length; i++) {//For loop to print observed animals
				if (observedAnimals[i] != null)
					System.out.print(observedAnimals[i] + " ");
			}
			System.out.println();
		}
		public static boolean isExplored(int x, int y) {
			return exploredWorld[x][y];
		}
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	System.out.println(
			"Welcome to the jungle creator!\nIn this game, you will be inserting animals in certain places in the world\nthat you create! You will also be allowed to remove animals from certain locations before you start exploring!\nOnce you start exploring you will navigate around the world to observe the animals you have inserted.\nThe game will keep track of all the animals you have observed!");
	System.out.println("Enter the dimensions of your jungle !");
	//Ask user to enter the dimensions of the world
	System.out.print("Enter the length: ");
	int l = sc.nextInt();
	System.out.print("Enter the width: ");
	int w = sc.nextInt();
	String[][] world = new String[l][w];
	//set world within the dimensions
	for (int i = 0; i<world.length; i++) {
		//initialize world
		for (int j = 0; j<world[i].length; j++) {
			world[i][j] = "";
		}
	}
	String[][] animalWorld = new String[l][w];	//Set animal locations within dimensions
	for (int i = 0; i<animalWorld.length; i++) {
		//initialixe animal locations
		for (int j = 0; j<animalWorld[i].length; j++) {
			animalWorld[i][j] = "";
		}
	}
	String[] observedAnimals = new String[l*w];//Set observed animals 
	for (int i = 0; i<observedAnimals.length; i++) {
		observedAnimals[i] = "";
	}
	exploredWorld = new boolean[l][w];//initialize explored world
	//Set the 0,0 coordinate to false
	exploredWorld[0][0] = true;
	world = makeWorld(world,0,0);
	printWorld(world);
	while(choice!=4) {//for loop for users choice of the menu
		printMainMenu();
		choice=sc.nextInt();
			if(choice==1) {
				insertAnimalToWorld(animalWorld);
			}
			else if(choice==2) {
				removeAnimalFromWorld(animalWorld);
			}
			else if(choice==3) {
				explore(world, animalWorld, observedAnimals);
			}
			else {
				System.out.println("please try again.");
			}	
		}
	System.out.println("Goodbye!");
}
}
