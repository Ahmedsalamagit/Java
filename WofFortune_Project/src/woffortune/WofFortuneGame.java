/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*@author Ahmed salama
 *@version 3/22/19
*
 */
package woffortune;

import java.util.*;

/**
 * WofFortuneGame class
 * Contains all logistics to run the game
 * @author ahmed salama
 */
public class WofFortuneGame {
Scanner input = new Scanner(System.in);
    
    private boolean puzzleSolved = false;
String prize;
    private Wheel wheel;//wheel object
    private Player player;//player object
    private String phrase = "Once upon a time";//originalphrase
    ArrayList<Letter> letters = new ArrayList<Letter> ();//letter arraylist
   //private Letter[] letter_array = new Letter[16];
    ArrayList<String> phrases = new ArrayList<String>();//string list
    ArrayList<Player> players = new ArrayList<Player>();//player arraylist

    /**
     * Constructor
     * @param wheel Wheel 
     * @throws InterruptedException 
     */
    public WofFortuneGame(Wheel wheel) throws InterruptedException {
        // get the wheel
        this.wheel = wheel;
        
        // do all the initialization for the game
        setUpGame();
        

    }
    
    /**
     * Plays the game
     * @throws InterruptedException 
     */
    public void playGame() throws InterruptedException {
        // while the puzzle isn't solved, keep going
        while (!puzzleSolved){
            int guess =0;
            for (Player spin : players) {//for each loop for each player 
                players.get(guess);//get index of guess
                guess++;//increment
                playTurn(spin);
                if(puzzleSolved)
                    break;//if solved break
            }
        }
    }
    
    /**
     * Sets up all necessary information to run the game
     */
    private void setUpGame() {
        phrases();
         Random rand = new Random();
        // create a single player 
         try{
         System.out.println("How many players are playing? ");
         int playerNum = input.nextInt();
          System.out.println("Enter the names of the players: ");
            for (int i = -1; i < playerNum; i++) {
                String playerName = input.nextLine();
               players.add(new Player(playerName));
            }
             players.remove(0);
               } catch (InputMismatchException e) {
            System.out.println("Incorrect input " + e); 
}
        // print out the rules
        System.out.println("RULES!");
        System.out.println("Each player gets to spin the wheel, to get a number value");
        System.out.println("Each player then gets to guess a letter. If that letter is in the phrase, ");
        System.out.println(" the player will get the amount from the wheel for each occurence of the letter");
        System.out.println("If you have found a letter, you will also get a chance to guess at the phrase");
        System.out.println("Each player only has three guesses, once you have used up your three guesses, ");
        System.out.println("you can still guess letters, but no longer solve the puzzle.");
        try{
            System.out.println("Would you like to enter a phrase");
        
        String choice = input.nextLine();
         switch (choice){
           case"yes": System.out.println("Enter a phrase");//if user wishes to enter a phrase
            phrase = input.nextLine();
               break;
            case"Yes": System.out.println("Enter a phrase");
            phrase = input.nextLine();
               break;
            case "no": 
                phrase = phrases.get(rand.nextInt(phrases.size()));//get random phrase freom arraylist
                break;
            case "No": 
                phrase = phrases.get(rand.nextInt(phrases.size()));
                break;
                        
        }
        
        // for each character in the phrase, create a letter and add to letters array
        for (int i = 0; i < phrase.length(); i++) {
            //letter_array[i] = new Letter(phrase.charAt(i));
            letters.add(new Letter(phrase.charAt(i)));
        }
         } catch (Exception y) {//catch excaption
            System.out.println("Incorrect input " + y);
         }
        // setup done
    }
    
    /**
     * One player's turn in the game
     * Spin wheel, pick a letter, choose to solve puzzle if letter found
     * @param player
     * @throws InterruptedException 
     */
    private void playTurn(Player player) throws InterruptedException {
        int money = 0;
        
        Scanner sc = new Scanner(System.in);
    try{
           System.out.println(player.getName() + ", you have $" + player.getWinnings());
        System.out.println("Spin the wheel! <press enter>");
        sc.nextLine();
        System.out.println("<SPINNING>");
        Thread.sleep(200);
        Wheel.WedgeType type = wheel.spin();
        System.out.print("The wheel landed on: ");
        switch (type) {
            case MONEY:
                money = wheel.getAmount();
                System.out.println("$" + money);
                break;
                
            case LOSE_TURN:
                System.out.println("LOSE A TURN");
                System.out.println("So sorry, you lose a turn.");
                return; // doesn't get to guess letter
                
                
            case BANKRUPT:
                System.out.println("BANKRUPT");
                player.bankrupt();
                return; // doesn't get to guess letter
            case PRIZE:
               prize = wheel.getPrize();//get prize for the getter in wheel class
                System.out.println("Prize: " + prize);
                player.incrementPrize(prize);//call increment method
                return;
                
            default:        
        }
        System.out.println("");
        System.out.println("Here is the puzzle:");
        showPuzzle();
        System.out.println();
        System.out.println(player.getName() + ", please guess a letter.");
        //String guess = sc.next();
        
        char letter = sc.next().charAt(0);
        if (!Character.isAlphabetic(letter)) {
            System.out.println("Sorry, but only alphabetic characters are allowed. You lose your turn.");
        } else {
            // search for letter to see if it is in
            int numFound = 0;
            for (Letter l : letters) {
                if ((l.getLetter() == letter) || (l.getLetter() == Character.toUpperCase(letter))) {
                    l.setFound();
                    numFound += 1;
                }
            }
            if (numFound == 0) {
                System.out.println("Sorry, but there are no " + letter + "'s.");
            } else {
                if (numFound == 1) {
                    System.out.println("Congrats! There is 1 letter " + letter + ":");
                } else {
                    System.out.println("Congrats! There are " + numFound + " letter " + letter + "'s:");
                }
                System.out.println();
                showPuzzle();
                System.out.println();
                player.incrementScore(numFound*money);
                System.out.println("You earned $" + (numFound*money) + ", and you now have: $" + player.getWinnings());


                System.out.println("Would you like to try to solve the puzzle? (Y/N)");
                letter = sc.next().charAt(0);
                System.out.println();
                if ((letter == 'Y') || (letter == 'y')) {
                    solvePuzzleAttempt(player);
                }
            }
        
     
        }
       } catch (InterruptedException e) {
            System.out.println("There is an error" + e); 
        }
       }
    
    /**
     * Logic for when user tries to solve the puzzle
     * @param player 
     */
    private void solvePuzzleAttempt(Player player) {
        
        if (player.getNumGuesses() >= 3) {
            System.out.println("Sorry, but you have used up all your guesses.");
            return;
        }
        
        player.incrementNumGuesses();
        System.out.println("What is your solution?");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String guess = sc.next();
        if (guess.compareToIgnoreCase(phrase) == 0) {
            System.out.println("Congratulations! You guessed it!");
            puzzleSolved = true;
            // Round is over. Write message with final stats
            // TODO
            System.out.println(player.getName() + " congrats you won");
            for (int i = 0; i < players.size() ; i++){
                System.out.println(players.get(i).getName() + " earned " + players.get(i).getWinnings());
                 System.out.println("Prizes: " + players.get(i).getPrize());
            }
        } else {
            System.out.println("Sorry, but that is not correct.");
        }
    }
    
    /**
     * Display the puzzle on the console
     */
    private void showPuzzle() {
        System.out.print("\t\t");
        for (Letter l : letters) {
            if (l.isSpace()) {
                System.out.print("   ");
            } else {
                if (l.isFound()) {
                    System.out.print(Character.toUpperCase(l.getLetter()) + " ");
                } else {
                    System.out.print(" _ ");
                }
            }
        }
        System.out.println();
        
    }
    private void phrases() {//phrases available
        phrases.add("Mother of Dragons");
        phrases.add("Winter is coming");
        phrases.add("A lannister always pays his debts");
        phrases.add("Valar Morghulis");
        phrases.add("The night watch");
        phrases.add("Fear cuts deeper than swords");
        phrases.add("Game of thrones");     
        phrases.add("The things I do for love");        
        phrases.add("Winter is here");        
        phrases.add("Three eyed raven");      
        phrases.add("Reek");
        phrases.add("Hold the door");
    }
    /**
     * For a new game reset player's number of guesses to 0
     */
    public void reset() {
        player.reset();
    }
  
}
