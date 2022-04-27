package model;

import java.util.Comparator;

public class Tree<T extends Comparable<T>> implements TreeInter<T>{
	
	private Node<T> root;
	private Comparator<T> cmp;
	
	public Tree(Comparator<T> cmp) {

		this.root = null;
		this.cmp = cmp;
	}

	@Override
	public void add(Node<T> toAdd) {
		if(root==null) {
			root=toAdd;
		}else {
			this.root=add(root,toAdd);		
		}
	}
	
	@Override
	public Node<T> add(Node<T> actNd, Node<T> toAdd) {
		//Cuando es 0 se esta enviando a la izquierda como predeterminado
		if(actNd.getItem()==toAdd.getItem()) {
			//Cambiar por un alert
			System.out.println("No se pueden agregar dos items iguales.");
			return null;
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
		//Se rebalancea a medida que se devuelve
		//Se va a usar derecha menos izquierda para el factor de balanceo
		if(actNd!=null) {
			actNd=rebalance(actNd);
			System.out.println("A");
			//Falta devolver bien el dato
		}
		return actNd;
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
		if(root==null) {
			return null;
		}
		return search(root, obj);
	}
	
	@Override
	public Node<T> search(Node<T> actNd, T obj) {
		if(actNd.getItem().compareTo(obj)==0) {
			return actNd;
		}
		if(cmp.compare(actNd.getItem(), obj)>=0 ) {
			return search(actNd.getLeft(), obj);
		}else {
			return search(actNd.getRight(), obj);
		}
	}
	
	@Override
	public int height(Node<T> nd) {
		if(nd==null) {
			return 0;
		}
		return 1+Math.max(height(nd.getLeft()), height(nd.getRight()));
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	@Override
	public Node<T> rebalance(Node<T> nd) {
		int fb=height(nd.getRight())-height(nd.getLeft());
		if(fb==2) {
			if(height(nd.getRight().getRight())>height(nd.getRight().getLeft())) {
				nd=leftRotate(nd);
				System.out.println("R izq");
				return nd;
			}else {
				nd.setRight(rightRotate(nd.getRight()));
				nd=leftRotate(nd);
				System.out.println("R izq 2");
				return nd;
			}
		}else if(fb==-2) {
			if(height(nd.getLeft().getLeft())>height(nd.getLeft().getRight())) {
				nd=rightRotate(nd);
				System.out.println("R der");
				return nd;
			}else {
				nd.setLeft(leftRotate(nd.getLeft()));
				nd=rightRotate(nd);
				System.out.println("R der 2");
				return nd;
			}
		}
		return nd;
	}
	
	@Override
	public Node<T> leftRotate(Node<T> nd) {
		Node<T> a=nd.getRight();
		Node<T> b=a.getLeft();
		a.setLeft(nd);
		nd.setRight(b);
		return a;
	}

	@Override
	public Node<T> rightRotate(Node<T> nd) {
		Node<T> a=nd.getLeft();
		Node<T> b=a.getRight();
		a.setRight(nd);
		nd.setLeft(b);
		return a;
	}
}