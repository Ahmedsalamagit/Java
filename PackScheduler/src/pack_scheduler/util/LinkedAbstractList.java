/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a LinkedList object that stores references to other objects
 * Contains a series of references to other objects, creating a chain of references that form a list
 * @param <E> allows a LinkedList object to be of any data type
 * @author ahmed
 * @author joel
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
    /**the number of items that are currently in the list*/
    private int size;
    /**the number of items that can be held in a list*/
    private int capacity;
    /**holds the initial values of a LinkedList*/
    private ListNode front;
    /** Points to the last node in the list */
    private ListNode back;

    /**
     * Initializes all values of a LinkedAbstractList object
     * front and back are initialized to null
     * size is set to 0
     * capacity is given in the parameter
     * @param capacity the initial capacity of a list
     * @throws IllegalArgumentException if the capacity is less than 0 or more than the size
     */
    public LinkedAbstractList(int capacity) {
        this.size = 0;
        front = null;
        back = null;
        if (capacity < 0 || capacity < size)
            throw new IllegalArgumentException("Capacity must be greater than 0 the size");
        setCapacity(capacity);
    }
    /**
     * Retrieves an item at an index in the list
     * @param index the position in the list to look for an item
     * @throws IndexOutOfBoundsException if the index is the size or less than 0
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to size.");
        }
        ListNode temp = front;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Changes the value of an item in the list at an index
     * if the index is invalid, throws an IOOBE
     * if the item to be added is null throws a NPE
     * if the item to be added is already in the list throws an IAE
     * @param index position in the list to change the value
     * @param obj the value to be added to the list
     * @throws IndexOutOfBoundsException if the index is the size or less than 0
     * @throws NullPointerException if the item to be added to the list is null
     * @throws IllegalArgumentException if the item to be added to the list is already on the list
     */
    @Override
    public E set(int index, E obj) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to size.");
        }
        if (obj == null) {
            throw new NullPointerException("Object is null.");

        }
        // check for duplicates
        ListNode temp = front;
        for (int i = 0; i < size; i++) {
            if (temp.data.equals(obj)) {
                throw new IllegalArgumentException("Object has a duplicate in list.");
            }
            temp = temp.next;
        }

        ListNode temp2 = front;
        for (int i = 0; i < index; i++) {
            temp2 = temp2.next;
        }
        E saveVal = temp2.data;
        temp2.data = obj;
        return saveVal;
    }

    /**
     * Adds a value to the list at a specified position
     * @param index position in the list to add the value
     * @param obj the value to be added to the list
     * @throws IndexOutOfBoundsException if the index is the size or less than 0
     * @throws NullPointerException if the item to be added to the list is null
     * @throws IllegalArgumentException if the item to be added to the list is already on the list
     * @throws IllegalArgumentException if the size is equal to the capacity
     */
    @Override
    public void add(int index, E obj) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to size.");
        }
        if (obj == null) {
            throw new NullPointerException("Object is null.");
        }
        if (size == capacity) {
            throw new IllegalArgumentException("Size at capacity");
        }

        // check for duplicates
        ListNode temp = front;
        for (int i = 0; i < size - 1; i++) {
            if (temp.data.equals(obj)) {
                throw new IllegalArgumentException("Object has a duplicate in list.");
            }
            temp = temp.next;
        }
        
        // makes back reference the last node in the list
        back = temp;

        if (index == 0) { 
            front = new ListNode(obj, front);
            back = front;
        } else if (index == size - 1) {
        	ListNode current = front; 
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new ListNode(obj, current.next); 
        	back = current.next;
        } else {
            ListNode current = front; 
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new ListNode(obj, current.next); 
        }
        size++;
    }

    /**
     * removes a value to the list at a specified position
     * @param index position in the list to add the value
     * @throws IndexOutOfBoundsException if the index is the size or less than 0
     * @return the value of the element that was removed from the list
     */
    @Override
    public E remove(int index) {
        @SuppressWarnings("unchecked")
        E tester = (E) "test";
        @SuppressWarnings("unused")
        ListNode test = new ListNode(tester);
        ListNode current = null;
        ListNode removed = null;

        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index is less than 0 or greater than or equal to size.");
        }

        if (index == 0) { 
            removed = front;
            front = front.next;
        } else if (index == size - 1) {
        	current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removed = current.next;
            current.next = current.next.next;
            back = current.next;
        } else {
            current = front;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removed = current.next;
            current.next = current.next.next;
        }
        size--;
        return removed.data;
    }

    /**
     * Changes the capacity of a list
     * the capacity of a list cannot be exceeded
     * @param cap the capacity of a list
     */
    public void setCapacity(int cap) {
    	if(cap < 0 || cap < size) {
    		throw new IllegalArgumentException();
    	} else {
        this.capacity = cap;
    	}
    }

    /**
     * Gives the number of items currently in the list
     * @return the number of items that are currently in the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * A ListNode refers to one part of a linked list
     * Used to help navigate the chained refereneces in a LinkedList
     * @author ahmed
     * @author joel
     */
    @SuppressWarnings("hiding")
    private class ListNode {
        /**the value stores in an individual node in a linked list*/
        private E data;
        /**Stores the reference to the next item in a linked list*/
        private ListNode next;

        /**
         * Allows for addition of ListNodes to a LinkedList
         * Adds new objects in order they are created
         * @param data the value of a ListNode to be added to the linked list
         */
        @SuppressWarnings("unused")
        public ListNode(E data) {
            this.data = data;
        }

        /**
         * Allows for addition of ListNodes to a LinkedList
         * Adds new objects in reverse order
         * @param data value of the data stored in a ListNode to be added.
         * @param node what will reference the new object in the list
         */
        public ListNode(E data, ListNode node) {
            this.data = data;
            this.next = node;
        }
    }

}
