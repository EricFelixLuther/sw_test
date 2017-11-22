package BinarySearchTree;

public class BinarySearchTree<T extends Comparable> implements Tree<T> {

	private Node<T> root;
	
	@Override
	public void insert(T data){
		if(root == null){
			root = new Node<T>(data);
		} else {
			insertNode(data, root);
		}
	}
	
	@Override
	public T getMinValue() {
		if(root == null) return null;
		return getMin(root);
		
	}
	
	@Override
	public T getMaxValue() {
		if(root == null) return null;
		return getMax(root);
		
	}
	
	@Override
	public void traversal() {
		if(root != null){
			inOrderTraversal(root);
		}
		
	}
	
	private Node<T> delete(Node<T> node, T data){
		if(node == null) return node;
		
		if(data.compareTo(node.getData()) < 0) {
			node.setLeftChild(delete(node.getLeftChild(), data));
		} else if (data.compareTo(node.getData()) > 0){
			node.setRightChild(delete(node.getRightChild(), data));
		} else {
			if(node.getLeftChild() == null && node.getRightChild() == null) {
				System.out.println("Removing leaf node...");
				return null;
			}
			
			if( node.getLeftChild() == null) {
				System.out.println("Removing the right child...");
				Node<T> tempNode = node.getRightChild();
				node = null;
				return tempNode;
			} else if (node.getRightChild() == null) {
				System.out.println("Removing the left child...");
				Node<T> tempNode = node.getLeftChild();
				node = null;
				return tempNode;
			}
			
			// This is the node with two children case
			System.out.println("Removing item with two children...");
			Node<T> tempNode = getPredecessor(node.getLeftChild());
			node.setData(tempNode.getData());
			// Call method recursively again
			node.setLeftChild(delete(node.getLeftChild(), tempNode.getData()));
		}
		return node;
	}
	
	private Node<T> getPredecessor(Node<T> node){
		if(node.getRightChild() != null){
			return getPredecessor(node.getRightChild());
		}
		return node;
	}
	
	private void inOrderTraversal(Node<T> node){
		if ( node.getLeftChild() != null){
			inOrderTraversal(node.getLeftChild());
		}
		System.out.print(node+"  -->  ");
		
		if (node.getRightChild() != null){
			inOrderTraversal(node.getRightChild());
		}
	}

	private void insertNode(T newData, Node<T> node) {
		if( newData.compareTo(node.getData()) < 0){
			if(node.getLeftChild() != null){
				insertNode(newData, node.getLeftChild());
			} else {
				Node<T> newNode = new Node<T>(newData);
				node.setLeftChild(newNode);
			}
		} else {
			if(node.getRightChild() != null) {
				insertNode(newData, node.getRightChild());
			} else {
				Node<T> newNode = new Node<T>(newData);
				node.setRightChild(newNode);
			}
		}
		
	}

	@Override
	public void delete(T data) {
		if(root != null){
			root = delete(root, data);
		}
		
	}

	private T getMax(Node<T> node) {
		if(node.getRightChild() != null){
			return getMin(node.getRightChild());
		}
		
		return node.getData();
	}

	private T getMin(Node<T> node) {
		
		if(node.getLeftChild() != null){
			return getMin(node.getLeftChild());
		}
		
		return node.getData();
	}
	
}
