package assignments;
import java.util.*;
public class assignment4 {
static boolean[][] spot;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to the jungle world creator!\n" + 
				"In this game, you will be inserting animals in certain places in the world that you create! You will also be allowed to remove animals from certain locations before you start exploring!\n" + 
				"Once you start exploring, you will navigate around the world to observe the animals that you have inserted.\n" + 
				"The game will keep track of all the animals you have observed!\n" + 
				"");
		System.out.println("enter the length of the world");
		int l = input.nextInt();
		System.out.println("width:" );
		int w = input.nextInt();
		int lw = w * l;
		String [][] world = new String [l] [w];
		String [][] anim = new String [l] [w];
		spot = new boolean [l][w];
		spot[l][w] = true;
		String [] oanim =  new String [lw];
		world = makeWorld(world,0,0);
		for(int x = 0; x<world.length;x++) {
			for(int y = 0;y<world.length;y++) {
				System.out.print(world[x][y]);
			}
		}
		
		System.out.println(insertAnimalToWorld(anim,l,w));
		
	}
	public static String[][] makeWorld(String[][] world, int posX, int posY) {
		for(int x=0;x<world.length;x++) {
			for(int y =0; y<world.length;y++) {
				world [x][y] = "*";
				if(spot[x][y]==true) {
					world[x][y]="O";
				}}
		}
		world[posX][posY] = "X";
		return world;
	}

	public static void printMainMenu() {
System.out.println("	1. Insert an animal into the world\n" + 
		"	2. Remove an animal from the world\n" + 
		"	3. Explore the world");
	}
	public static void printMoveMenu() {
	System.out.println("W. Move Forward\n "+
	"A. Move Left\n" +
	"S. Move Backward\n" +
	"D. Move Right\n" +
	"I. Display observed animals\n" +
	"E. Exit");

	//Note: Whether the user enters I or i it should be the same thing!
	}
	public static String[][] insertAnimalToWorld(String[][] animalLocations,int l, int w) {
		Scanner input = new Scanner(System.in);
		int z = 0;
		do {
		System.out.println("enter the x and y coordinates: ");
		int x = input.nextInt();
		int y = input.nextInt();
		System.out.println("insert name of animal");
		String name = input.nextLine();
		if (animalLocations[x][y]==null && x<=l && y<=w) {
		animalLocations[x][y]=name;
		z=1;
		}
		else {
			System.out.println("try again");
			z=0;
		}}while(z==0);
		return animalLocations;
	}
	public static String[][] removeAnimalFromWorld(String[][] animalLocations,int l,int w){
	Scanner input = new Scanner(System.in);
	int z = 0;
	do {
	System.out.println("enter the x and y coordinates: ");
	int x = input.nextInt();
	int y = input.nextInt();
	if(x<=l && y<=w && animalLocations[x][y]!="") {
		animalLocations[x][y]="";
		z=(int) Math.random()*2+1;
	}
	else {
		System.out.println("try again");
		z=0;
	}
	}while(z==0);
	return animalLocations;
}
	

}


