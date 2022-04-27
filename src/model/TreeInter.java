package model;

public interface TreeInter<T> {
	
	public void add(Node<T> toAdd);
	public Node<T> add(Node<T> actNd, Node<T> toAdd);
	public void delete(T obj);
	public Node<T> delete(Node<T> actNd, Node<T> toDel);
	public Node<T> search(T obj);
	public Node<T> search(Node<T> actNd, T obj);
	public int height(Node<T> nd);
	public Node<T> rebalance(Node<T> nd);
	public Node<T> leftRotate(Node<T> nd);
	public Node<T> rightRotate(Node<T> nd);
	
}
