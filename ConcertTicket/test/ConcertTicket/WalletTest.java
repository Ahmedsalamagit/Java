/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcertTicket;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahmed
 */
public class WalletTest {
    
    public WalletTest() {
    }

    /**
     * Test of add method, of class Wallet.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Wallet wallet = new Wallet();
        ConcertTicket ct1 = new ConcertTicket("Phish",new Date("09/29/2016"),18.5);
        ConcertTicket ct2 = new ConcertTicket("Beyonce",new Date("09/31/2016"),20);
        assertEquals(0,wallet.getSize());
        wallet.add(ct1);
         assertEquals(1,wallet.getSize());
         wallet.add(ct2);
            assertEquals(2,wallet.getSize());
            assertTrue(wallet.tickets[1].compareTo(ct2)==0);
    }

    /**
     * Test of remove method, of class Wallet.
     */
    @Test
    public void testRemove() {
         System.out.println("add");
            System.out.println("add");
        Wallet wallet = new Wallet();
        ConcertTicket ct1 = new ConcertTicket("Phish",new Date("09/29/2016"),18.5);
        ConcertTicket ct2 = new ConcertTicket("Beyonce",new Date("09/31/2016"),20);
        
        wallet.add(ct1);
        
         wallet.add(ct2);
         ConcertTicket ct2Removed = wallet.remove();
         ConcertTicket ct1Removed = wallet.remove();
         assertEquals(0,wallet.getSize());
         assertTrue(ct1.compareTo(ct1Removed)==0);
//
     
    }

    

    /**
     * Test of getSize method, of class Wallet.
     */
    
    
}
