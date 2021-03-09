/*@author ahmed salama
 * @version 11/18/18
 * @description assignment 3
 * pseudocode: i created a menu using a while loop. i used the book titles, ids, and prices.
 * the menu should include a linear search, binary search, bubble sort, selection sort and a quit.
 * i used these methods 
 * display(): display the contents of parallel array in a tabular format. Take in the three different arrays as parameters.
 * linearsearch(): i applied the linear search algorithm to search for the book ID, Returning the index position, or -1 if not found 
 * binarysearch(): i applied the binary search algorithm to search for the book ID, Returning the index position of the found book ID, or -1 if not found.
 * bubblesort(): i applied the bubble sort algorithm to sort the elements of an unsorted array 
 * selectionsort(): i applied the selection sort algorithm to sort the elements of an unsorted array  
 */
package assignments;
import java.util.*;
public class assignment3 {
	public static void main(String[] args) {//start main method
		Scanner input = new Scanner(System.in);//for input
		Random rand = new Random();//for random numbers
		int unsorted[]=new int[10];//used for bubble and selection sort
        int sorted[]=new int[10];   
		String[] bookTitle = {"Starting out with Java", "Java Programming", "Software Structures",
	               "Design and Analysis of Algorithms", "Computer Graphics", "Artificial Intelligence: A Modern Approach",
	               "Probability and Statistics", "Cognitive Science", "Modern Information Retrieval", "Speech and Language Processing"};
	       int[] bookID = {1101, 1211, 1333, 1456, 1567, 1642, 1699, 1755, 1800, 1999};
	       double[] bookPrice = {112.32, 73.25, 54.00, 67.32, 135.00, 173.22, 120.00, 42.25, 32.11, 123.75};
		//book titles, id, and price in arrays
	       int choice,x=0;
		double total=0;
		do {//do while loop for menu
	
		System.out.println("1.                  Linear Search \n" + 
				"2.                  Binary Search \n" + 
				"3.                  Bubble Sort \n" + 
				"4.                  Selection Sort \n" + 
				"5.                  Quit \n" + 
				"");
		choice = input.nextInt();//choice of user 
		switch(choice) {//switch case statement for users choice
		case 1:
			displayDetails(bookTitle,bookID,bookPrice);//display book info
			System.out.println("what is the book id of the book you would like to purchase? ");
			int id = input.nextInt();//input by user for the book id theyd like
			System.out.println("how many would you like to purchase?");
			int num = input.nextInt();//input by user for how many books theyd like
			int s = linearsearch(bookID,id);//intializing s to  method linear search
			if(linearsearch(bookID,id)>=0) {//if book is found
			total = bookPrice[s] * num;//find price total
			System.out.println("the book id is: " + bookID[s]);
			System.out.println("the book title is: " + bookTitle[s]);
			System.out.println("the number of books bought is: " + num);
			System.out.println("your total is: " + total);
			}
			else
				System.out.println("invalid book");
			break;
		case 2:
			displayDetails(bookTitle,bookID,bookPrice);//display book info
			System.out.println("what is the book id of the book you would like to purchase? ");
			int is = input.nextInt();//the book id user choose
			System.out.println("how many would you like to purchase?");
			int num2 = input.nextInt();//num of books theyd like
			int q= binarysearch(bookID,is);//initializing q to method binary search
			if(binarysearch(bookID,is)>=0) {//if book is found
				total = bookPrice[q] * num2;//find price total
				System.out.println("the book id is: " + bookID[q]);
				System.out.println("the book title is: " + bookTitle[q]);
				System.out.println("the number of books bought is: " + num2);
				System.out.println("your total is: " + total);
				}
				else
					System.out.println("invalid book");
			break;
		case 3:       
        System.out.println("array before bubble sort:");
        for(int z=0; z <10; z++){//loop for 10 random numbers 
            unsorted[z]=rand.nextInt(500);
            System.out.print(unsorted[z] + " ");
        }
        sorted = bubblesort(unsorted);//sorting array using a bubble sort
        System.out.println("\narray after bubble sort:");
        for(int z=0; z < sorted.length; z++){//printing out the sorted array
            System.out.print(sorted[z] + " ");
        }
        System.out.println(" ");
			break;
		case 4:
			 System.out.println("array before selection sort:");//loop for 10 random numbers
		        for(int z=0; z <10; z++){
		            unsorted[z]=rand.nextInt(500);
		            System.out.print(unsorted[z] + " ");
		        }
		        sorted = selectionsort(unsorted);//sorting array using selection sort
		        System.out.println("\narray after selection sort:");
		        for(int z=0; z < sorted.length; z++){
		            System.out.print(sorted[z] + " ");//printing out the sorted array
		        }
		        System.out.println(" ");
			break;
		case 5:
			x=1;//end loop
			System.out.println("goodbye!");//goodbye message
			break;
		}}while(x==0);
	}//end of main method
	public static void displayDetails(String bookTitle[],int bookID[],double bookPrice[]) {//start of method
		System.out.println("booktitle \t\t bookid \t\t bookprice");
		for(int y = 0; y<bookID.length;y++) {//for loop to print out book info
			System.out.println(bookTitle[y] + "\t" + bookID[y] + "\t" + bookPrice[y]);
		}	
	}//end of methos
	public static int linearsearch(int bookID[], int id) {//start of method
		for (int i = 0; i < bookID.length; i++) 
        {  
            if (bookID[i] == id) // Return the index of the book
                return i; 
        }  
        return -1; 	// return -1 if the book is not found 
	}
	public static int binarysearch(int bookID[], int is) {
		 int value=0;
	       int first = 0;
	        int last   = bookID.length - 1;
	       int middle = (first + last)/2;	  //setting first, last and middle to the length
	        while( first <= last )//loop 
	        {
	          if ( bookID[middle] < is )
	            first = middle + 1;  
	          else if ( bookID[middle] == is )
	          {
	            value=(middle);
	            break;//break and return value if found
	          }
	          else
	             last = middle - 1;	  
	          middle = (first + last)/2;//cut array in half if value is not found
	       }
	       if ( first > last )//if  
	       value= -1;	     
	       return value;//return value
	}
	public static int[] bubblesort(int array[]) {
		   int n = array.length;//set n to the length of the array
	        int temp = 0;	     
	        for(int i=0; i < n; i++){//for loop
	        for(int j=1; j < (n-i); j++){
	        if(array[j-1] > array[j]){	                              
	        temp = array[j-1];//set temp to the array[j-1]
	        array[j-1] = array[j];//set array[j-1] to array[j]
	        array[j] = temp;//set temp back to array[j]
	        }}}
	        return array;//return array
	}
	public static int[] selectionsort(int array[]) {
		for(int z = 0; z < array.length - 1; z++) {//for loop
			int temp = z;//temp to z
			for(int i = z + 1; i < array.length; i++) {
				if(array[i] < array[temp]) {//if statement for if array[1] id smaller than array[temp]
					temp = i;//set temp to i
				}
				int w = array[temp];//set w to array[temp]
				array[temp] = array[z];
				array[z] = w;
			}}
		return array;//return array
	}
}
