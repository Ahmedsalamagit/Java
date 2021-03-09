package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * An array list object can hold an object of anytype
 * There is no limit to how many item that can be added to an array list
 * duplicate items and null items result in an IAE
 * @author Ahmed salama
 * @author Joel McKinney
 * @param <E> Represents every type of object. the ArrayList could be of any object type
 */
public class ArrayList<E> extends AbstractList<E> {
    /** the initial size of an ArrayList object */
    private static final int INT_SIZE = 10;
    /** the initial list */
    private E[] list;
    /** used to store the value of the size/length of an ArrayList */
    private int size;

    /**
     * initializes the array with the initial size of 10 and of type E
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        size = 0;
        list = (E[])(new Object[INT_SIZE]);
    }

    /**
     * Adds a new item to the list at a certain index
     * shifts all items after the specified index to the right
     * @param idx the index at which to add a new element
     * @param element the item to be added to the list
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of items in the list
     * @throws NullPointerException if the received element is null
     */
    @Override
    public void add(int idx, E element)
    {
        if (element == null)
        {
            throw new NullPointerException("Cannot add null element to list");
        }

        if (idx < 0 || idx > size())
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        if (size == list.length - 1)
        {
            growArray();
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(element)) 
            {
                throw new IllegalArgumentException("Cannot add duplicate item to list");
            }
        }

        size++;

        for (int i = list.length - 2; i >= 0; i--)
        {
            if (idx != i)
            {
                list[i + 1] = list[i];
            }
            else if (idx == i)
            {
                list[i + 1] = list[i];
                list[i] = element;
                break;
            }
        }
    }

    /**
     * Increases the size of the array
     * doubles every time the size limit is reached
     */
    @SuppressWarnings("unchecked")
    private void growArray()
    {

        E[] newList = (E[])(new Object[size * 2]);
        for (int i = 0; i < list.length; i++)
            newList[i] = list[i];
        list = newList;
    }

    /**
     * removes an item from the list at a specified index
     * shifts all items after the specified index to the left
     * @param idx the index at which to remove an item
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of items in the list
     */
    @Override
    public E remove(int idx) 
    {
        if (idx < 0 || idx >= size())
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        E removed = null;        
        for (int i = 0; i < list.length - 2; i++)
        {
            if(i == idx)
            {
                removed = list[i];
                list[i] = null;
                list[i] = list[i + 1];
            }
            else if (i > idx)
            {
                list[i] = list[i + 1];
            }
        }

        size--;
        return removed;
    }

    /**
     * changes the value of a specified index to a specified value
     * @return returns the value of the items that is overwritten
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of items in the list
     * @throws NullPointerException if the received element is null
     */
    @Override
    public E set(int idx, E value)
    {
        if (value == null)
        {
            throw new NullPointerException("Cannot add a null value to the list");
        }

        if (idx < 0 || idx >= size())
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        for (int i = 0; i < list.length - 1; i++)
        {
            if (list[i] != null && list[i].equals(value)) {
                throw new IllegalArgumentException("Cannot add duplicate item to list");
            }
        }

        E item = list[idx];
        list[idx] = value;
        return item;
    }

    /**
     * retrieves an item from the array list
     * returns null if the element at an index cannot be found
     * @return the item from the list at a specified index
     * @param idx the index in the list to retrieve an element
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the number of items in the list
     */
    @Override
    public E get(int idx)
    {
        if (idx < 0 || idx >= size())
        {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        for (int i = 0; i < list.length; i++)
        {
            if (i == idx)
            {
                return list[i];
            }
        }
        return null;
    }

    /**
     * Returns the number of elements in the list
     * @return the number of elements in the array list
     */
    public int size()
    {
        return size;
    }
}
