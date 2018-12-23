// Christopher Wong (#111386693)
// CSE 214 R09
/**
 * Directory node represents a node in a file tree.
 */
public class DirectoryNode {
	String name = "";
	boolean isFile = false;
	DirectoryNode left = null, middle = null, right = null;
	/**
	 * Name getter.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Name setter
	 * @param newName
	 */
	public void setName(String newName) {
		name = newName;
	}
	/**
	 * Left child node getter.
	 * @return left child node
	 */
	public DirectoryNode getLeft() {
		return left;
	}
	/**
	 * Left child node setter.
	 * @param newLeft
	 */
	public void setLeft(DirectoryNode newLeft) {
		left = newLeft;
	}
	/**
	 * Right child node getter
	 * @return right child node
	 */
	public DirectoryNode getRight() {
		return right;
	}
	/**
	 * Right child node setter
	 * @param newRight
	 */
	public void setRight(DirectoryNode newRight) {
		right = newRight;
	}
	/**
	 * Middle child node getter
	 * @return middle child node
	 */
	public DirectoryNode getMiddle() {
		return middle;
	}
	/**
	 * Middle child node setter
	 * @param newMiddle
	 */
	public void setMiddle(DirectoryNode newMiddle) {
		middle = newMiddle;
	}
	/**
	 * DirectoryNode constructor
	 * @param name
	 * @param b
	 */
	public DirectoryNode(String name, boolean b) {
		this.name = name;	
		isFile = b;
	}
	/**
	 * Adds a child to the current directory going from left - > middle - > right in priority.
	 * @param newChild
	 * @throws FullDirectoryException
	 * @throws NotADirectoryException
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, NotADirectoryException{
		if (newChild.isFile)
			throw new NotADirectoryException();
		if (left == null) {
			left = newChild;
			return;
		}
		if (middle == null) {
			middle = newChild;
			return;
		}
		if (right == null) {
			right = newChild;
			return;
		}
		throw new FullDirectoryException();
	}
}
	class FullDirectoryException extends Exception{
		
	}
	class NotADirectoryException extends Exception{
		
	}
