package model;

public interface TreeInter<T> {
	
	public void add(Node<T> toAdd);
	public void add(Node<T> actNd, Node<T> toAdd);
	public void delete(T obj);
	public void delete(boolean left,Node<T> fathr, Node<T> actNd, Node<T> toDel);
	public Node<T> search(T obj);
	
}
