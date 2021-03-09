package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

public class ArrayStack<E> implements Stack<E> {
	private ArrayList<E> array;
	private int capacity;
	public ArrayStack(int capacity) {
		array = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	@Override
	public void push(E element) {
		if(array.size() + 1  > capacity) {
		throw new IllegalArgumentException();
		}
		else
			array.add(element);
	}

	@Override
	public E pop() {
		if(array.isEmpty()) {
			throw new EmptyStackException();
		}
		
		return array.remove(size() - 1);
	}

	@Override
	public boolean isEmpty() {
		if(array.size() == 0) {
			return true;
		} 
			return false;
	}

	@Override
	public int size() {
	if(array == null) {
		return 0;
	} else 
		return array.size();
	}

	@Override
	public void setCapacity(int capacity) {
		
		if ( capacity < 0 || capacity < array.size()) {
			throw new IllegalArgumentException();
		} else 
			this.capacity = capacity;
		
	}

}
