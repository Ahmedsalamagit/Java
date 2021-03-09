/*@author ahmed salama
 * @version 10/21/2018
 * @description assignment 2: string methods and decision making, with menu provided for user to choose
Enter a new main sentence
Find a String 
Find all incidents of a String
Find and Replace a String 
Replace all the incidents of a String 
Count the number of words 
Count a letter’s occurrences
Count the total number of letters
Delete all the occurrences of a word
Exit
 */
package assignments;
//importing scanners , etc.
import java.util.*;
public class assignment2 {

	public static void main(String[] args) {
//
Scanner input = new Scanner(System.in);
//declaring strings and ints for later use 
String  msent = null;
String  choice;
int y;
int x = 11;
String word,word2;
// do while loop for menu
		do{
		System.out.println("(a)Enter a new main sentence"); 
		System.out.println(	"(b)Find a String ");  
		System.out.println(	"(C)Find all incidents of a String");  
		System.out.println(	"(d)Find and Replace a String ");  
		System.out.println("(e)Replace all the incidents of a String ");  
		System.out.println("(f)Count the number of words ");  
		System.out.println("(g)Count a letter’s occurrences"); 
		System.out.println("(h)Count the total number of letters");  
		System.out.println("(i)Delete all the occurrences of a word");  
		System.out.println("(j)Exit" );
choice =  input.nextLine();
//switch statement for menu option
switch(choice) {
case "a":
	//user inputs main sentence for later use
	System.out.println("enter a new main sentence:");
	msent = input.nextLine();
	
	System.out.println("your new main sentence is " + msent);
	break;
case "b":
	//user inputs a word to find the location of on sentence
	System.out.println("enter a word:");
	word = input.nextLine();
	// finding where the location of the word is
	 y = msent.indexOf(word);
	 // if statement to be able to tell if the word is found or not
	if (y>-1)
		System.out.println(word + " is found at " + y);
	else 
		System.out.println(word + " is not found");
	break;
case "c":
	System.out.println("enter a word:");
	 word = input.nextLine();
		// finding where the first location of the word is
	   y = msent.indexOf(word);
	   //finding last location
	   int r = msent.lastIndexOf(word); 
	   // if statement to be able to tell if the word is found or not
if (y>=0) {
	//for loop to find all of the rest of the locations
	for(; y<=r;y++) {
	y = msent.indexOf(word, y);
	System.out.println(word + " is found at " + y);	
	}
}
else 
	System.out.println(word + " is not found");
	break;
case "d":
	//user inputs a string
	System.out.println("enter the search string: ");
	word = input.nextLine();
	//string is searched for in sentence
	y = msent.indexOf(word);
	if (y>=0) {
		//if word is found the user then inputs another word to replace the first occurence of the word
	System.out.println("enter the word you would like to replace the found string with: ");
	word2 = input.nextLine();
	msent = msent.replaceFirst(word, word2);
	System.out.println(msent);
	}//if word is not found
	else 
		System.out.println("search sring not found");
	break;
case "e":
	//user inputs a string
	System.out.println("enter the search string: ");
	word = input.nextLine();
	//string is searched for in sentence
	y = msent.indexOf(word);
	if (y>=0) {
		//if word is found the user then inputs another word to replace the all occurences of the word
	System.out.println("enter the word you would like to replace the found string with: ");
	word2 = input.nextLine();
	msent = msent.replaceAll(word, word2);
	System.out.println("your new sentence is: " + msent);
	}//if word is not found
	else 
		System.out.println("serch sring not found");
	break;
case "f":
	//finding how many spaces in the sentence there is to find out the words
	y = msent.split(" ").length;
	System.out.println("the number of words are" + y);
	break;
case "g":
	System.out.println("enter a letter: ");
	char l = input.next().charAt(0);
	String o = Character.toString(l);
	y = msent.split(o).length;
	System.out.println("this character is in the main sentence " + (y-1) + " times");
	break;
case "h":
	//trimming nd replacing all spaces with nothing
	String trimmed = msent.replaceAll(" ", "");
	System.out.println(trimmed.trim());
	break;
case "i": 
	//user inputs a word to delete
	System.out.println("enter a word to delete: ");
	String delete = input.nextLine();
	// replacing all words found of the string entered with nothing
	delete = msent.replaceAll(delete, "");
	System.out.println("your new sentence is: " + delete);
	break;
case "j":
	// making the loop statement not run any longer
    x = 10;
    System.out.println("have a good day!");
    break;
}//end switch case
}while(  x>10 );//end loop
	}

}

