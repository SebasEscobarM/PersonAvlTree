package model;

public interface TreeInter<T> {
	
	public void add(Node<T> toAdd);
	public Node<T> add(Node<T> actNd, Node<T> toAdd);
	public void delete(T obj);
	public void delete(boolean left,Node<T> fathr, Node<T> actNd, Node<T> toDel);
	public Node<T> search(T obj);
	public Node<T> search(Node<T> actNd, T obj);
	public int height(Node<T> nd);
	Node<T> rebalance(Node<T> nd);
	Node<T> leftRotate(Node<T> nd);
	Node<T> rightRotate(Node<T> nd);
	
}
