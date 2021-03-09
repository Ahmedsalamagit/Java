package edu.ncsu.csc216.pack_scheduler.util;


import java.util.NoSuchElementException;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class LinkedQueue<E> implements Queue<E> {
	private LinkedAbstractList<E> list;
	private int capacity;
	public LinkedQueue(int capacity) {
		list = new LinkedAbstractList<E>(capacity);
		setCapacity(capacity);
	}
	@Override
	public void enqueue(E element) {
		if(list.size() + 1 > capacity) {
			throw new IllegalArgumentException();
		}
		else
			list.add(element);
		
	}

	@Override
	public E dequeue() {
		if(list.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return list.remove(0);
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

	public boolean contains(Student student) {
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(student)) {
				return true;
			}
		}
		return false;	
			}
	

}
