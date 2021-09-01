
/*
*  @author:
*  @date:
*
*  Description:
*
*  Pseudocode:
*
*/
import java.util.Scanner;

public class Assignment_4 {
	static boolean[][] exploredWorld;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		int a=0;
		int b=0;

		System.out.println(
				"Welcome to the jungle creator!\nIn this game, you will be inserting animals in certain places in the world\nthat you create! You will also be allowed to remove animals from certain locations before you start exploring!\nOnce you start exploring you will navigate around the world to observe the animals you have inserted.\nThe game will keep track of all the animals you have observed!");
		System.out.println("Enter the dimensions of your jungle !");
		System.out.print("Enter length: ");
		int length = sc.nextInt();
		System.out.print("Enter width: ");
		int width = sc.nextInt();
		String[][] world = new String[length][width];
		for (int i = 0; i<world.length; i++) {
			for (int j = 0; j<world[i].length; j++) {
				world[i][j] = "";
			}
		}
		String[][] animalWorld = new String[length][width];	//Holds the animal locations
		for (int i = 0; i<animalWorld.length; i++) {
			for (int j = 0; j<animalWorld[i].length; j++) {
				animalWorld[i][j] = "";
			}
		}
		String[] observedAnimals = new String[length*width];
		for (int i = 0; i<observedAnimals.length; i++) {
			observedAnimals[i] = "";
		}
		//Initialize the exploredWorld array
		exploredWorld = new boolean[length][width];

		//Set the 0,0 coordinate to explored since that's where we start
		exploredWorld[0][0] = true;

		//Set the rest of the exploredWorld array to false (unexplored)
		for (int i = 1; i<exploredWorld.length; i++) {
			for (int j = 1; j<exploredWorld[i].length; j++) {
				exploredWorld[i][j] = false;
			}
		}

		//Build the initial world
		world = makeWorld(world,0,0);
		printWorld(world);
		while(choice!=4) {
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
					System.out.println("Invalid Output! Please try again.");
				}	
			}
		System.out.println("Good-Bye!");
		/*
		 * TODO: Declare and initialize arrays and generate the world starting from 0,0
		 * Note: Don't forget to initialize exploredWorld Note: Make sure to loop
		 * through the String array animalLocations and initialize all it's elements to
		 * "" otherwise it initializes everything to null causing a Nullpointer
		 * exception
		 */
		/*
		 * While choice is not exit: Print menu Take choice Process the user choice
		 * Note: Be sure to include input validation for choice
		 *
		 */

	}

	/*
	 *
	 */
	public static String[][] makeWorld(String[][] world, int posX, int posY) {
		for (int i = 0; i<exploredWorld.length; i++) {
			for (int j = 0; j<exploredWorld[i].length; j++) {
				//If the current indexes equal the position args, show an X
				if (posX == i && posY == j) {
					world[i][j] = "x";
				//If the current coordinates have been explored, display an O
				} 
				else if (exploredWorld[i][j]) {
					world[i][j] = "O";
				//Otherwise, just show a placeholder
				} 
				else {
					world[i][j] = "*";
				}
			}
		}
		return world;
	}

	/*
	 * TODO: Complete printMainMenu Prints: 1. Insert an animal into the world 2.
	 * Remove an animal from the world 3. Explore the world Enter your choice:
	 *
	 */
	public static void printMainMenu() {
		System.out.println("Select an option: "
		+ "\n1. Insert an animal into the world"
		+ "\n2. Remove an animal from the world"
		+ "\n3. Explore the world");
	}

	/*
	 * TODO: Complete printMoveMenu Prints: W. Move Forward A. Move Left S. Move
	 * Backward D. Move Right I. Display observed animals E. Exit Enter your choice:
	 *
	 */
	public static void printMoveMenu() {
	System.out.println("Select an option: "
			+ "\nW. Move Forward"
			+ "\nA. Move Left"
			+ "\nS. Move Backward"
			+ "\nD. Move Right"
			+ "\nI. Display Observed Animals"
			+ "\nE. Exit	");
	}

	/*
	 * TODO: Complete insertAnimal to World
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Prompts the user to enter the x and y coordinate as well as their desired
	 * animal name and inserts it to the world in the location [x,y]as long as: -
	 * The x and y coordinates are in the worlds boundaries - The area [x,y] is
	 * not occupied by another animal If the conditions are not met prompt the user
	 * to enter the coordinates as well as the animal name again until the
	 * conditions above are met.
	 */
	public static void insertAnimalToWorld(String[][] animalLocations) {
	Scanner sc=new Scanner(System.in);
	while(true) {	

		System.out.println("Enter the x coordinate of the position of the animal: ");
		int x=sc.nextInt();
		System.out.println("Enter the y coordinate of the position of the animal: ");
		int y=sc.nextInt();
		if (isEmptyBlock(animalLocations,x,y) == true) {		//We do, so add the animal
			//Get the animal's name from the user
			System.out.println("Enter the name of the animal: ");
			String name = sc.next();
			animalLocations[x][y] = name;
			System.out.println(name+" has been added!");
						//Exit the array
						break;
			
			//Add the animal to the animalLocations array
			
		
		}
		else {		//We don't, so inform the user
			System.out.println("An animal is already there, or you have invalid coordinates!");
			System.out.println(" ");
		}
	}
	}
		

	

	/*
	 * TODO: Complete removeAnimalFromWorld Prompts the user to enter the x and y
	 * coordinate and removes the animal from the world in the location [x,y] as
	 * long as: - The x and y coordinates are in the worlds boundaries - The
	 * area [x,y] is not empty (contains an animal) If the conditions are not met
	 * prompt the user to enter the coordinates until the conditions above are met.
	 * Hint: To remove the animal you can just set the String at [x,y] to.
	 *
	 */
	public static void removeAnimalFromWorld(String[][] animalLocations) {
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println("Enter the name of the animal: ");
		String name=sc.next();
		System.out.println("Enter the x coordinate of the position of the animal: ");
		int x=sc.nextInt();
		System.out.println("Enter the y coordinate of the position of the animal: ");
		int y=sc.nextInt();
		if (x < 0 || y < 0 || x >= animalLocations.length || y >= animalLocations[0].length) {		//We don't-> tell the user
			System.out.println("Invalid coordinates.");
		} 
		else {	//We do-> remove the animal
			//Check to see if the coordinates are empty-> we can't from from an empty location
			if (isEmptyBlock(animalLocations, x, y) == true) {
				System.out.println("Cannot remove an animal from an empty location.");
			} 
			else {
				//Simply reset the location to an empty string
				animalLocations[x][y] = "";
				
				//Inform the user
				System.out.println("Animal removed.");

				//Leave the loop
				break;
			}
		}
	}
	}
	

	/*
	 * TODO: Complete isEmptyBlock
	 *
	 * Returns false if the array has an animal at [x,y] and true if [x,y] is empty.
	 *
	 */
	public static boolean isEmptyBlock(String[][] world, int x, int y) {
	
		//If either x or y is less than 0, then it's invalid
		if (x < 0 || y < 0) {
			return false;
		//If either x or y is greater than the arrays' length, it's invalid
		} 
		else if (x >= world.length || y >= world[0].length) {
			return false;
		}
		//Loop through the world
		else if (world[x][y] == null) {
			return false;
		}
		//Return true by default
		return true;
	}

	/*
	 * TODO: Complete updateObservedAnimals adds animal into the observedAnimals
	 * array into the first occurrence of an empty String and returns the
	 * observedAnimals array.
	 */
	public static String[] updateObservedAnimals(String[] observedAnimals, String animal) {
		for (int i = 0; i<observedAnimals.length; i++) {
			//If the current element is empty, add the animal
			if (observedAnimals[i].equals("")) {
				observedAnimals[i] = animal;
				break;
			}
		}
		return observedAnimals;

	}

	/*
	 * Prints the 2D array world Note: Nothing to do here, it's done for you!
	 */
	public static void printWorld(String[][] world) {
		for (int i = 0; i < world.length; i++) {
			for (String s : world[i]) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}

	/*
	 * TODO: Complete explore Starts the exploration! This method prints out the
	 * world and then the move menu and asks the user to choose from the move menu
	 * until the user chooses to exit. In this method, make sure to declare the x
	 * and y variables that keep track of the  position in the world. When
	 * the user chooses to move forward for example, change the x and y values
	 * accordingly (refer to activity 8.0) and make sure they are within the bounds, if they are, call the move method and print the world, otherwise
	 * reset the position to 0,0 and tell the user that they went off bounds. If the
	 * user chooses to print observed animals , call the print observed animals
	 * method If the user chooses to exit, exit the program
	 */
	public static void explore(String[][] world, String[][] animalLocations, String[] observedAnimals) {
		Scanner input = new Scanner(System.in);

		//Holds the user's choice
		char choice = 0;

		//Holds the current coordinates
		int x = 0;
		int y = 0;

		//Loop until the user wants to exit
		while (choice != 'e') {
			//Print the world and the move menu
			System.out.println("");
			printWorld(world);
			printMoveMenu();

			//Get user input
			choice = input.next().toLowerCase().charAt(0);

			//Parse the input and do the required actions
			if (choice == 'w') {		//Move up
				//Decrement the current row
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
				//Decrement the current column
				--y;

				//Make sure we are still in the world
				if (y < 0) {
					y = 0;
					System.out.println("You left the world!");
				}

				//Move the user
				world = move(world,x,y,observedAnimals,animalLocations,null);
			} 
			else if (choice == 's') {		//Move down
				//Increment the current row
				++x;

				//Make sure we are still within the world
				if (x >= world.length) {
					x = 0;
					System.out.println("You left the world!");
				}

				//Move the user
				world = move(world,x,y,observedAnimals,animalLocations, null);
			} 
			else if (choice == 'd') {		//Move right
				//Increment the current column
				++y;

				//Make sure we are still within the world
				if (y >= world[0].length) {
					y = 0;
					System.out.println("You left the world!");
				}

				//Move the user
				world = move(world,x,y,observedAnimals,animalLocations,null);

			//Display the list of observed animals
			} 
			else if (choice == 'i') {
				System.out.println("Your observed animals are:");
				printobservedAnimals(observedAnimals);

			//Exit the program
			} 
			else if (choice == 'e') {
				System.out.println("Goodbye!!");
				System.exit(0);

			//We have invalid input
			} 
			else {
				System.out.println("Unknown input.");
			}
		}
	}

	/*
	 * TODO: Complete move This method calls the makeWorld method to create a new
	 * world with the new user position and sets the 2D boolean array at [x,y] to be
	 * true, setting this area as explored. If [x,y] has an animal display the
	 * animal name and add it to the observed animals list and print the observed
	 * animals list, otherwise display you did not encounter anything :( . This
	 * method returns the 2D array world in the end.
	 *
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static String[][] move(String[][] world, int x, int y, String[] observedAnimals,String[][] animalLocations,String move) {
	
		if (x < 0 || y < 0) {
			return makeWorld(world,x,y);
		} 
		else if (x > world.length || y > world.length) {
			return makeWorld(world,x,y);
		}

		//Set the current coordinates to explored
		exploredWorld[x][y] = true;

		//Check to see if an animal exists at the current location
		if (animalLocations[x][y].isEmpty()) {		//We did not-> Inform the user
			System.out.println("You did not encounter anything!");
		} 
		else {		//We did-> Tell the user
			observedAnimals = updateObservedAnimals(observedAnimals,animalLocations[x][y]);
			System.out.println("You found an animal!!");
			printobservedAnimals(observedAnimals);
		}

		//Build the world and return it
		return makeWorld(world,x,y);
	}

	/*
	 * Prints the observedAnimals list by printing each animal on one line separated
	 * by a space. Note: nothing to do here it's done for you.
	 */
	public static void printobservedAnimals(String[] observedAnimals) {
		for (int i = 0; i < observedAnimals.length; i++) {
			if (observedAnimals[i] != null)
				System.out.print(observedAnimals[i] + " ");
		}
		System.out.println();
	}

	/*
	 * Returns true if [x,y] is explored and false if [x,y] is not explored. Note:
	 * Nothing to do here it's done for you.
	 */
	public static boolean isExplored(int x, int y) {
		return exploredWorld[x][y];
	}
}
