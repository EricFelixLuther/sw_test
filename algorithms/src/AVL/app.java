package AVL;

public class app {

	public static void main(String[] args) {
		Tree avl = new AvlTree();
		avl.insert(10);
		avl.insert(15);
		avl.insert(5);
		avl.insert(14);
		//avl.insert(50);
		//avl.insert(60);
		
		avl.delete(5);
		avl.traverse();
	}

}
