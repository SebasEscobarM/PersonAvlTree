package model;

import java.util.Comparator;

public class Tree<T extends Comparable<T>> implements TreeInter<T>{
	
	private Node<T> root;
	private int weight;
	private Comparator<T> cmp;
	
	public Tree(Comparator<T> cmp) {

		this.root = null;
		this.weight = 0;
		this.cmp = cmp;
	}

	@Override
	public void add(Node<T> toAdd) {
		if(root==null) {
			root=toAdd;
		}else {
			add(root,toAdd);		
		}
	}
	
	@Override
	public void add(Node<T> actNd, Node<T> toAdd) {
		//Cuando es 0 se esta enviando a la izquierda como predeterminado
		if(actNd.getItem()==toAdd.getItem()) {
			//Cambiar por un alert
			System.out.println("No se pueden agregar dos items iguales.");
			return;
		}
		
		if((cmp.compare(actNd.getItem(), toAdd.getItem()))>=0 ) {
			if(actNd.getLeft()==null) {
				actNd.setLeft(toAdd);
			}else {
				add(actNd.getLeft(), toAdd);
			}
		}else {
			if(actNd.getRight()==null) {
				actNd.setRight(toAdd);
			}else {
				add(actNd.getRight(), toAdd);
			}
		}
	}
	
	@Override
	public void delete(T obj) {
		if(root!=null) {
			delete(false, null, root, new Node<>(obj));
		}else {
			//Cambiar por un alert
			System.out.println("Debe agregar personas antes de poder eliminar.");
		}
	}
	
	@Override
	public void delete(boolean left,Node<T> fathr, Node<T> actNd, Node<T> toDel) {
		//Cuando el primer compare es 0 deberia buscar hacia la izq si el segundo es != de 0 pues al agregar se envia asi
		if((cmp.compare(actNd.getItem(), toDel.getItem()))>0) {
			delete(true, actNd, actNd.getLeft(), toDel);
		}else if((cmp.compare(actNd.getItem(), toDel.getItem()))<0) {
			delete(false, actNd, actNd.getRight(), toDel);
		}else{
			
			if((actNd.getItem()).compareTo(toDel.getItem())>0) {
				delete(true, actNd, actNd.getLeft(), toDel);
			}else if((actNd.getItem()).compareTo(toDel.getItem())<0) {
				delete(false, actNd, actNd.getRight(), toDel);
			}else {
				if(actNd.getLeft()!=null && actNd.getLeft()!=null) {
					Node<T> suce=actNd.getRight().findSuccessor();
					actNd.setItem(suce.getItem());
					delete(false, actNd, actNd.getRight(), suce);
				}else if(actNd.getLeft()!=null) {
					if(left) {
						fathr.setLeft(actNd.getLeft());
					}else {
						fathr.setRight(actNd.getLeft());
					}
				}else if(actNd.getLeft()!=null) {
					if(left) {
						fathr.setLeft(actNd.getRight());
					}else {
						fathr.setRight(actNd.getRight());
					}
				}else {
					if(left) {
						fathr.setLeft(null);
					}else {
						fathr.setRight(null);
					}
					return;
				}
			}		
		}
	}
	
	@Override
	public Node<T> search(T obj) {
		// TODO Auto-generated method stub
		return null;
	}
	public Node<T> getRoot() {
		return root;
	}
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}	
}