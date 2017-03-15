package maze;

import java.util.NoSuchElementException;

public class Stack<node> {
    private Node<node> first;     // top of stack
    private int n;                // size of the stack
	
	private static class Node<node> 
	{
        private node item;
        private Node<node> next;
    }
	
	 public Stack() 
	 {
		 first = null;
	     n = 0;
	 }
	 
	 public boolean isEmpty() 
	 {
		 return first == null;
	 }
	 
	 public int size() 
	 {
		 return n;
	 }
	 
	 public void push(node item) {
	        Node<node> oldfirst = first;
	        first = new Node<node>();
	        first.item = item;
	        first.next = oldfirst;
	        n++;
	    }
	 
	 public node pop() {
	        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
	        node item = first.item;        // save item to return
	        first = first.next;            // delete first node
	        n--;
	        return item;                   // return the saved item
	    }
	 
	 public node peek() {
	        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
	        return first.item;
	    }
	 


}
