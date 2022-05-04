package model;

import java.util.Comparator;
import java.util.ArrayList;


public class Tree<T extends Comparable<T>> implements TreeInter<T>{
	
	private Node<T> root;
	private Comparator<T> cmp;
	private Comparator<T> srch;
	private int weight;
	
	public Tree(Comparator<T> cmp, Comparator<T> srch) {

		this.root = null;
		this.cmp = cmp;
		this.srch=srch;
		weight=0;
	}

	@Override
	public void add(Node<T> toAdd) {
		if(root==null) {
			root=toAdd;
		}else {
			this.root=add(root,toAdd);		
		}
		this.weight++;
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
				actNd.setLeft(add(actNd.getLeft(), toAdd));
			}
		}else {
			if(actNd.getRight()==null) {
				actNd.setRight(toAdd);
			}else {
				actNd.setRight(add(actNd.getRight(), toAdd));
			}
		}
		//Se rebalancea a medida que se devuelve
		//Se va a usar derecha menos izquierda para el factor de balanceo
		return rebalance(actNd);
		
			
	}
	
	@Override
	public void delete(T obj) {
		if(root!=null) {
			this.root=delete(root, new Node<>(obj));
			this.weight--;
		}else {
			System.out.println("Debe agregar personas antes de poder eliminar.");
		}
	}
	
	@Override
	public Node<T> delete(Node<T> actNd, Node<T> toDel) {
		//Si el critero de comparacion del arbol me devuelve 0 pero no son el mismo objeto se envia a la Izq
		if(actNd!=null) {
			if((cmp.compare(actNd.getItem(), toDel.getItem()))>0) {
				actNd.setLeft(delete(actNd.getLeft(), toDel));
			}else if((cmp.compare(actNd.getItem(), toDel.getItem()))<0) {
				actNd.setRight(delete(actNd.getRight(), toDel));
			}else if((actNd.getItem()).compareTo(toDel.getItem())!=0) {
				actNd.setLeft(delete(actNd.getLeft(), toDel));	
			}else {
				if(actNd.getLeft()!=null && actNd.getRight()!=null) {
					Node<T> suce=actNd.getRight().findSuccessor();
					actNd.setItem(suce.getItem());
					actNd.setRight(delete(actNd.getRight(), suce));
				}else if(actNd.getLeft()!=null) {
					return actNd.getLeft();
				}else if(actNd.getRight()!=null) {
					return actNd.getRight();
				}else {
					return null;
				}
			}
		}
		//Se rebalancea a medida que se devuelve
		//Se va a usar derecha menos izquierda para el factor de balanceo
		if(actNd!=null){
			return rebalance(actNd);
		}
		return actNd;
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
		if(nd==null) {
			return null;
		}
		int fb=height(nd.getRight())-height(nd.getLeft());
		if(fb==2) {
			if(height(nd.getRight().getRight())>height(nd.getRight().getLeft())) {
				return leftRotate(nd);
			}else {
				nd.setRight(rightRotate(nd.getRight()));
				return leftRotate(nd);
			}
		}else if(fb==-2) {
			if(height(nd.getLeft().getLeft())>height(nd.getLeft().getRight())) {
				return rightRotate(nd);
			}else {
				nd.setLeft(leftRotate(nd.getLeft()));
				return rightRotate(nd);
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

	@Override
	public ArrayList<T> searchCoincidences(T toSrch) {
		ArrayList<T> reslt=new ArrayList<>();
		reslt.addAll(searchCoincidences(root, toSrch));
		//El metodo de abajo no se elimino porque tambien es una solucion unicamente que es un poco menos eficiente
		//a pesar de ello no se usa en la version final.
//		reslt.addAll(subTreeCoincidences(root, toSrch));
		return reslt;
	}

	@Override
	public ArrayList<T> searchCoincidences(Node<T> actNd, T toSrch) {
		ArrayList<T> reslt=new ArrayList<>();
		//Buscar la primera coincindencia y devolver esa coincidencia y todas las coincidencias en sus subarboles
		Node<T> nd=firstCncidence(actNd, toSrch);
		//Buscar todas las coincindencias en los subarboles
		if(nd!=null) {
			reslt.add(nd.getItem());
			ArrayList<T> lft=new ArrayList<>();
			lft.addAll(searchCoincidences(nd.getLeft(), toSrch));
			ArrayList<T> rgt=new ArrayList<>();
			rgt.addAll(searchCoincidences(nd.getRight(), toSrch));
			reslt.addAll(lft);
			reslt.addAll(rgt);	
		}
		return reslt;
	}
	
	@Override
	public ArrayList<T> subTreeCoincidences(Node<T> nd, T toSrch){
		ArrayList<T> reslt=new ArrayList<>();
		if(nd!=null) {
			if(srch.compare(nd.getItem(), toSrch)==0) {
				reslt.add(nd.getItem());
			}
			ArrayList<T> lft=new ArrayList<>();
			lft.addAll(subTreeCoincidences(nd.getLeft(), toSrch));
			ArrayList<T> rgt=new ArrayList<>();
			rgt.addAll(subTreeCoincidences(nd.getRight(), toSrch));
			reslt.addAll(lft);
			reslt.addAll(rgt);
		}
		
		return reslt;
	}
	
	@Override
	public Node<T> firstCncidence(Node<T> actNd, T toSrch) {
		if(actNd==null) {
			return null;
		}
		if(srch.compare(actNd.getItem(), toSrch)>0) {
			return firstCncidence(actNd.getLeft(), toSrch);
		}else if(srch.compare(actNd.getItem(), toSrch)<0){
			return firstCncidence(actNd.getRight(), toSrch);
		}else {
			return actNd;
		}
	}
	
	@Override
	public int getWeight() {
		return weight;
	}
}