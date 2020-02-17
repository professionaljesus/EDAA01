package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		try {
			QueueNode<E> neu = new QueueNode<E>(e);
			if(size > 0) {				
				neu.next = last.next;
				last.next = neu;
				last = neu;
				
			}else {
				neu.next = neu;
				last = neu;
			}
			size++;
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(size > 0)
			return last.next.element;
		else 
			return null;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
		if(size > 0) {
			QueueNode<E> temp = last.next;
			last.next = temp.next;
			size = size - 1;
			return temp.element;
		}else
			return null;
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}
	
	/**
	* Appends the specified queue to this queue
	* post: all elements from the specified queue are appended
	* to this queue. The specified queue (q) is empty after the call.
	* @param q the queue to append
	* @throws IllegalArgumentException if this queue and q are identical
	*/
	public void append(FifoQueue<E> q) {
		if(this.equals(q)) 
			throw new IllegalArgumentException();
		else {
			if(size > 0 && q.size() > 0) {
				QueueNode<E> temp = last.next;
				last.next = q.last.next;
				last = q.last;
				last.next = temp;
				size = size + q.size();
				
			}else if(q.size() > 0) {
				last = q.last;
				size = q.size();
			}
			q.size = 0;
			q = null;
		}
		
		
	}
	
	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		private int c;

		private QueueIterator() {
			pos = last;
			c = 0;
		}
		
		public boolean hasNext() {
			if(size > 0) {
				if(pos.next != null && c < size)
					return true;
				else
					return false;
			}else
				return false;
		}
		
		public E next() {
			if(hasNext()) {
				pos = pos.next;
				c++;
				return pos.element;
			}
			else
				throw new NoSuchElementException();
		}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
