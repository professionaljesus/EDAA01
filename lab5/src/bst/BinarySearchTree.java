package bst;

import java.io.IOException;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    int c;
    
    
    public static void main(String[] args) throws IOException {
    	BSTVisualizer vis = new BSTVisualizer("Window", 600, 600);
		BinarySearchTree<Integer> a = new BinarySearchTree<Integer>();
		a.add(0);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		
		a.printTree();
		vis.drawTree(a);
		
		System.in.read();
		System.out.println(a.size);
		a.rebuild();
		vis.drawTree(a);
		
	}
    
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		c = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		
		if(root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		else
			return arec(x,root);
	}
	
	private boolean arec(E x, BinaryNode<E> e){
		if(e.element.compareTo(x) < 0) {
			if(e.right == null) {
				e.right = new BinaryNode<E>(x);
				size++;
				return true;
			}else
				return arec(x, e.right);
		}else if(e.element.compareTo(x) > 0) {
			if(e.left == null) {
				e.left = new BinaryNode<E>(x);
				size++;
				return true;
			}else
				return arec(x, e.left);
		}else
			return false;
	}
	
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return hrec(0, root);
	}
	
	private int hrec(int h, BinaryNode<E> e) {
		if(e == null)
			return h;
		else
			return Math.max(hrec(h + 1, e.right), hrec(h + 1, e.left));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		prec(root);
	}
	
	private void prec(BinaryNode<E> e) {
		if(e == null)
			return;
		prec(e.left);
		System.out.println(e.element);
		prec(e.right);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		int i = toArray(root, a, 0);
		BinaryNode<E> newroot = buildTree(a, 0, i - 1);
		
		root = newroot;
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	
	private void inorder(BinaryNode<E> e, E[] a, int i){
		if(e == null)
			return;
		inorder(e.left, a, i + 1);
		a[i] = e.element;
		inorder(e.right, a, i + 1);
		c++;
	}
	
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		c = 0;
		inorder(n, a, index);
		System.out.println(c);
		return index + c;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int mid = (first + last)/2;
		BinaryNode<E> r = new BinaryNode<E>(a[mid]);
		rec(r, a, first, last);
		
		return r;
	}
	
	private void rec(BinaryNode<E> e, E[] a, int first, int last) {
		System.out.println(e.element + "   " +first + "    " + last);
		
		if(last - first > 2) {
			int mid = (first + last)/2;

			e.left = new BinaryNode<E>(a[(mid - 1 + first)/2]);
			e.right = new BinaryNode<E>(a[(mid + 1 + last)/2]);
			
			rec(e.left, a, first, mid - 1);
			rec(e.right, a, mid + 1, last);
		}else if(last - first == 2) {
			e.left = new BinaryNode<E>(a[first]);
			e.right = new BinaryNode<E>(a[last]);

		}else if(last - first == 1){
			e.right = new BinaryNode<E>(a[last]);
		}
	}
	
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}	
	}
	
}
