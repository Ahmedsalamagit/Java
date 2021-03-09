/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Maze_DS;

import Exceptions.EmptyCollectionException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ahmed
 * @version 1/23/2019
 */
public class ArrayListStackTest {
    
    /**
     *
     */
    public ArrayListStackTest() {
        //empty because blah blah
    }

    /**
     * Test of push method, of class ArrayListStack.
     */
    @Test
    public void testPush() {
        ArrayListStack<String> instance = new ArrayListStack<String>();
        assertEquals(instance.size(), 0);
        instance.push("Test");
        instance.push("Test2");
        assertEquals(instance.size(), 2);
        try {
            String temp =  instance.pop();
            assertEquals(temp, "Test2");
        } 
        catch (EmptyCollectionException e) {
            System.out.println("Empty collection");
        }
    }

    /**
     * Test of pop method, of class ArrayListStack.
     * @throws java.lang.Exception
     */
    @Test
    public void testPop() throws Exception {
        ArrayListStack<String> instance = new ArrayListStack<String>();
        instance.push("Test");
        instance.push("Test2");
        String temp;
        try {
            assertEquals(instance.size(), 2);
            temp = instance.pop();
            assertEquals(instance.size(), 1);
            assertEquals(temp, "Test2");
            instance.pop();
            try {
                instance.pop();
                fail("Empty collections should fail on pop()");
            } 
            catch (EmptyCollectionException e) {  
                System.out.println("Empty collection");
            }
        } 
        catch (EmptyCollectionException e) {
            fail("Non empty collections should not throw on pop");
        }
       
    }

    /**
     * Test of peek method, of class ArrayListStack.
     * @throws java.lang.Exception
     */
    @Test
    public void testPeek() throws Exception {
        System.out.println("peek");
        ArrayListStack<String> instance = new ArrayListStack<String>();
        instance.push("Test");
        String test = instance.peek();
        assertEquals(instance.size(), 1);
        assertEquals(test, "Test");
        instance.pop();
        try {
            instance.peek();
            fail("Empty collections should fail on peek()");
        } 
        catch (EmptyCollectionException e) { 
            System.out.println("Empty collection");
        }
    }

    /**
     * Test of isEmpty method, of class ArrayListStack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ArrayListStack<String> instance = new ArrayListStack<String>();
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of size method, of class ArrayListStack.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        ArrayListStack<String> instance = new ArrayListStack<String>();
        String add = "Boo";
        assertEquals(instance.size(), 0);
        instance.push(add);
        assertEquals(instance.size(), 1);
    }
    
}
