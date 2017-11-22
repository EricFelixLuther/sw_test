package BinarySearchTree;

public interface Tree<T> {
	public void traversal();
	public void insert(T newData);
	public void delete(T data);
	public T getMaxValue();
	public T getMinValue();
}
