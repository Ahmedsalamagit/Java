/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the implementation of the LinkedAbstractList class
 * Tests various valid and invalid cases to ensure proper outcomes
 * @author Joel McKinney
 */
public class LinkedAbstractListTest {

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#size()}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testSize() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        lal.add(0, "test");
        assertEquals(lal.size(), 1);

        lal.add(1, "test2");
        assertEquals(lal.size(), 2);

        lal.add(2, "test3");
        assertEquals(lal.size(), 3);
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#LinkedAbstractList(int)}.
     */
    @SuppressWarnings("rawtypes")
    @Test
    public void testLinkedAbstractList() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        try {
            lal = new LinkedAbstractList(-1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Capacity must be greater than 0 the size");
            assertEquals(lal.size(), 0);
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#get(int)}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testGet() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        lal.add(0, "test");
        lal.add(1, "test2");
        lal.add(2, "test3");

        assertEquals("test", lal.get(0));
        assertEquals("test2", lal.get(1));
        assertEquals("test3", lal.get(2));

        try {
            lal.get(3);
            fail();
        } catch (IndexOutOfBoundsException e)
        {
            assertEquals(e.getMessage(), "Index is less than 0 or greater than or equal to size.");
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#set(int, java.lang.Object)}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testSet() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        lal.add(0, "test");
        lal.add(1, "test2");
        lal.add(2, "test3");

        assertEquals("test", lal.get(0));
        assertEquals("test2", lal.get(1));
        assertEquals("test3", lal.get(2));

        assertEquals("test", lal.set(0, "test4"));
        assertEquals("test2", lal.set(1, "test5"));
        assertEquals("test3", lal.set(2, "test6"));
        
        try {
            lal.set(2, "test4");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Object has a duplicate in list.", e.getMessage());
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#add(int, java.lang.Object)}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testAdd() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        lal.add(0, "test");
        lal.add(1, "test2");
        lal.add(2, "test3");

        assertEquals("test", lal.get(0));
        assertEquals("test2", lal.get(1));
        assertEquals("test3", lal.get(2));

        lal.add(0, "test4");
        assertEquals("test4", lal.get(0));
        assertEquals("test", lal.get(1));
        assertEquals("test2", lal.get(2));
        assertEquals("test3", lal.get(3));

        try {
            lal.add(-1, "test4");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is less than 0 or greater than or equal to size.", e.getMessage());
            assertEquals("test4", lal.get(0));
            assertEquals("test", lal.get(1));
            assertEquals("test2", lal.get(2));
            assertEquals("test3", lal.get(3));
        }

        try {
            lal.add(70, "test4");
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is less than 0 or greater than or equal to size.", e.getMessage());
            assertEquals("test4", lal.get(0));
            assertEquals("test", lal.get(1));
            assertEquals("test2", lal.get(2));
            assertEquals("test3", lal.get(3));
        }
        
        try {
            lal.add(0, null);
        } catch (NullPointerException e) {
            assertEquals("Object is null.", e.getMessage());
            assertEquals("test4", lal.get(0));
            assertEquals("test", lal.get(1));
            assertEquals("test2", lal.get(2));
            assertEquals("test3", lal.get(3));
        }
        
        try {
            lal.add(0, "test");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Object has a duplicate in list.", e.getMessage());
            assertEquals("test4", lal.get(0));
            assertEquals("test", lal.get(1));
            assertEquals("test2", lal.get(2));
            assertEquals("test3", lal.get(3));
        }
        try {
        lal.setCapacity(-1);
        fail();
        } catch (IllegalArgumentException e) {
        	// do nothing
        }
    }

    /**
     * Test method for {@link edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList#remove(int)}.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testRemoveInt() {
        LinkedAbstractList lal = new LinkedAbstractList(100);
        assertEquals(lal.size(), 0);

        lal.add(0, "test");
        lal.add(1, "test2");
        lal.add(2, "test3");
        lal.add(3, "test4");
        lal.add(4, "test5");

        assertEquals("test", lal.get(0));
        assertEquals("test2", lal.get(1));
        assertEquals("test3", lal.get(2));
        
        try {
            lal.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is less than 0 or greater than or equal to size.", e.getMessage());
        }
        
        try {
            lal.remove(5);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Index is less than 0 or greater than or equal to size.", e.getMessage());
        }
        
        assertEquals(lal.remove(2), "test3");
        assertEquals(lal.size(), 4);
        assertEquals(lal.remove(0), "test");
        assertEquals(lal.size(), 3);
        assertEquals(lal.remove(1), "test4");
        assertEquals(lal.size(), 2);
    }
}
