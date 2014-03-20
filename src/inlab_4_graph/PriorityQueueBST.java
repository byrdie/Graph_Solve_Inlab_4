package inlab_4_graph;

import java.util.*;

/**
 * Represents a priority queue of generically-typed items. 
 * The queue is implemented as a binary search tree. 
 * The BST is implemented using Java's TreeSet.
 * 
 * @author Paymon Saebi & Eric Albee & Daniel Terashima
 */
public class PriorityQueueBST<AnyType> 
{
	private TreeSet<AnyType> BST;

	/**
	 * Constructs an empty priority queue. Orders elements according
	 * to their natural ordering (i.e., AnyType is expected to be Comparable)
	 * AnyType is not forced to be Comparable.
	 */
	public PriorityQueueBST() 
	{
		BST = new TreeSet<AnyType>();  
	}

	/**
	 * Construct an empty priority queue with a specified comparator.
	 * Orders elements according to the input Comparator 
	 * (i.e., AnyType need not be Comparable).
	 */
	public PriorityQueueBST(Comparator<? super AnyType> cmp) 
	{
		BST = new TreeSet<AnyType>(cmp);
	}

	/**
	 * @return the number of items in this priority queue.
	 */
	public int size() 
	{
		return BST.size();
	}
        
        public boolean isEmpty(){
            return BST.isEmpty();
        }

	/**
	 * Makes this priority queue empty.
	 */
	public void clear() 
	{
		BST.clear();
	}

	/**
	 * @return the minimum item in this priority queue.
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType findMin() throws NoSuchElementException 
	{
		return BST.first();
	}

	/**
	 * Removes and returns the minimum item in this priority queue.
	 * 
	 * @throws NoSuchElementException if this priority queue is empty.
	 * 
	 * (Runs in logarithmic time.)
	 */
	public AnyType deleteMin() throws NoSuchElementException 
	{
		if (BST.isEmpty()) throw new NoSuchElementException();	//check to see if BST is empty
		
		return BST.pollFirst();			//remove first object
	}

	/**
	 * Adds an item to this priority queue.
	 * 
	 * (Runs in logarithmic time.) 
	 * 
	 * @param x -- the item to be inserted
	 */
	public void add(AnyType x) 
	{
		BST.add(x);		//add to queue
	}	

	//LEAVE IN for grading purposes
	public Object[] toArray() 
	{    
		return this.BST.toArray();
	}	
}



