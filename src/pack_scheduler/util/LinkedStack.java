package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

public class LinkedStack<E> implements Stack<E>{
	private LinkedAbstractList<E> list;
	private int capacity;
	public LinkedStack(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	@Override
	public void push(E element) {
		if(list.size() + 1 > capacity) {
			throw new IllegalArgumentException();
		}
		else
			list.add(element);

	}

	@Override
	public E pop() {
		if(list.isEmpty()) {
			throw new EmptyStackException();
		}
		
		return list.remove(size() - 1);
	}

	@Override
	public boolean isEmpty() {
		if(list.size() == 0) {
			return true;
		} 
			return false;
	}
	

	@Override
	public int size() {
		if(list == null) {
			return 0;
		} else 
			return list.size();
		}

	@Override
	public void setCapacity(int capacity) {
		if ( capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		} else 
			this.capacity = capacity;
		
	
	}

}
