/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcards;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ahmed
 */
public class Flashcards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
           gui flashcard = null;
        try {
            flashcard = new gui();
        } catch (IOException ex) {
            Logger.getLogger(Flashcards.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        flashcard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        flashcard.setSize(450,150);
        flashcard.setVisible(true);
    }
    
}
