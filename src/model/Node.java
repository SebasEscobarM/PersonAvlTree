package model;

public class Node<T> {
	
	private Node<T> left;
	private Node<T> right;
	private int balance;
	private T item;
	
	public Node(T item){
		this.left=null;
		this.right=null;
		this.balance=0;
		this.item=item;
	}
	
	public Node<T> findSuccessor(){
		if (this.getLeft() == null) {
	        return this;
	    } else {
	        return this.getLeft().findSuccessor();
	    }
	}
	
	public boolean isLeaf() {
		if(left==null && right==null) {
			return true;
		}else {
			return false;
		}
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public T getItem() {
		return item;
	}
	public void setItem(T item) {
		this.item = item;
	}
	
	
}
