/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcards;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author ahmed
 */
    

public class gui extends JFrame implements ActionListener{
    
    
    ArrayList<String> frontside = new ArrayList<>();
    ArrayList<String> backside = new ArrayList<>();
    
    private JLabel title;
    private JLabel term=new JLabel("");;private JButton ok;
    private JTextField user;
   
    Timer mytimer,mytimer2;
    int amount = 0, size = 0;
    int realIndex;
    String userinput1, userinput2;
    
    GridBagConstraints layoutConst =new GridBagConstraints();
    
    gui() throws IOException{
        super();
        
        Scanner input = new Scanner(System.in);
            try{  
                System.out.println("How many flashcards would you like to have? ");
                amount = input.nextInt();
               for(int i = 0 ; i < amount ; i++) { 
                   size++;
               System.out.println("Flashcards number: " + size);
               System.out.println("What is the term? ");  
               userinput1 = input.next();
               frontside.add(userinput1);
               System.out.println("What is the definition? ");   
               userinput2 = input.next();
               backside.add(userinput2);
        }
        }catch(Exception e){
            System.out.println("Problem prompted");
        }
        
        setLayout(new GridBagLayout());
        random();
        print();
        
         mytimer = new Timer(10*1000, new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                ok.doClick();
                mytimer.stop();
            }
        });
          mytimer2 = new Timer(3*1000, new ActionListener() 
         {
            @Override
            public void actionPerformed(ActionEvent e) {
              random();
             user.setText("");
         user.setEditable(true);
          ok.setEnabled(true);
          ok.setText("OK");
         mytimer2.stop();
         mytimer.start();
            }
        });
mytimer.start();
    }
    public void random() {
        Random rand = new Random();
        int index = ((int) new Random().nextInt(amount) + 1)-1;
        int ran = (int) new Random().nextInt(2) + 1;
        
        for(int i = 0; i<frontside.size(); i++){
            if(ran==1){
                term.setText(frontside.get(index) );
            realIndex=0;
            }
            else{
               term.setText(backside.get(index)); 
               realIndex=1;
                    }
        }
    }
  @Override
    public void actionPerformed(ActionEvent e) {
       ok.setEnabled(false);
       user.setEditable(false);
    
       switch(realIndex){
           case 0:
           if (backside.get(frontside.indexOf(term.getText())).toUpperCase().equals(user.getText().toUpperCase())){
               this.title.setText("Correct");
               mytimer2.start();
           }
           else{
               title.setText("Incorrect");
               mytimer2.start();
              user.setText(backside.get(frontside.indexOf(term.getText())));
           }
               break;
           case 1:
           if (frontside.get(backside.indexOf(term.getText())).toUpperCase().equals(user.getText().toUpperCase())){
               this.title.setText("Correct");
               mytimer2.start();
           }
           else{
                this.title.setText("Incorrect");
               mytimer2.start();
              user.setText(frontside.get(backside.indexOf(term.getText())));
           }
               break;
       }
      
    }

      public void print(){
         try {
         
         user = new JTextField(10);
         ok = new JButton("OK");
          ok.setEnabled(true);
         title = new JLabel("");
         
        ok.addActionListener(this);
        layoutConst.gridx = 2;
        layoutConst.gridy = 2;
        add(ok,layoutConst);
        
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        title.setFont(new Font ("Comic Sans MS",Font.BOLD,18));
        title.setText("Flashcards");
        add(title,layoutConst);
        
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        add(user, layoutConst);
        
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(term, layoutConst);
        
        
         } catch(Exception ae){
             System.out.print("Exception thrown");
         }
         }

  



}
    
   





