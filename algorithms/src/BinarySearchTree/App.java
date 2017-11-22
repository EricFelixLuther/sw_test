package BinarySearchTree;

public class App {

	public static void main(String[] args) {
		Tree<Integer> bst = new BinarySearchTree<>();
		
		bst.insert(10);
		bst.insert(-1);
		bst.insert(1);
		bst.insert(0);
		bst.insert(1000);
		bst.insert(2);
		bst.insert(-22);
		
		System.out.println(bst.getMaxValue());
		
		bst.traversal();

		bst.delete(2);
		
		bst.traversal();
		
	}

}
