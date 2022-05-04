package model;

import java.util.ArrayList;

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
	public ArrayList<T> searchCoincidences(T toSrch);
	public ArrayList<T> searchCoincidences(Node<T> actNd, T toSrch);
	public Node<T> firstCncidence(Node<T> actNd, T toSrch);
	public int getWeight();
	public ArrayList<T> subTreeCoincidences(Node<T> nd, T toSrch);
	
}
