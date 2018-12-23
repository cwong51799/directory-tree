// Christopher Wong (#111386693)
// CSE 214 R09
import java.util.*;
public class DirectoryTree{
	DirectoryNode root = new DirectoryNode("root", false);
	DirectoryNode cursor;
	/**
	 * Default constructor, sets cursor to the root.
	 */
	public DirectoryTree() {
		cursor = root;
	}
	/**
	 * Resets the cursor to the root.
	 */
	public void resetCursor() {
		cursor = root;
	}
	/** 
	 * Moves the cursor to the child with the given name, if it exists.
	 * @param name
	 * @throws NotADirectoryException
	 */
	public void changeDirectory(String name) throws NotADirectoryException{
		if (cursor.getLeft() != null) {
			if (cursor.getLeft().getName().equals(name)) {
				if (cursor.getLeft().isFile)
					throw new NotADirectoryException();
				cursor = cursor.getLeft();
				return;
			}
		}
		if (cursor.getMiddle() != null) {
			if (cursor.getMiddle().getName().equals(name)) {
				if (cursor.getLeft().isFile)
					throw new NotADirectoryException();
				cursor = cursor.getMiddle();
				return;
			}
		}
		if (cursor.getRight() != null) {
			if (cursor.getRight().getName().equals(name)) {
				if (cursor.getLeft().isFile)
					throw new NotADirectoryException();
				cursor = cursor.getRight();
				return;
			}
		}
	}
	/**
	 * Checks if the named directory is nested within the current node recursively.
	 * @param name
	 * @param node
	 * @return the node if found.
	 * @throws NotADirectoryException
	 */
	public DirectoryNode findDirectory(String name, DirectoryNode node) throws NotADirectoryException{ //helper method, unused
		if (node == null) {
			return null;
		}
		if (node.getName().equals(name)) {
			return node;
		}
		findDirectory(name, cursor.getLeft());
		findDirectory(name, cursor.getMiddle());
		findDirectory(name, cursor.getRight());
		throw new NotADirectoryException();
	}
	/**
	 * Checks if the named directory is nested within the current node recursively.
	 * @param name
	 * @param node
	 * @return true if found
	 */
	public boolean isInDirectory(String name, DirectoryNode node){ //helper method
		if (node != null) {
			if (node.getName().equals(name)) {
				return true;
			}
			if (node.getLeft() != null) {
				if(isInDirectory(name, node.getLeft())) {
					return true;
				}
			}
			if (node.getMiddle() != null) {
				if(isInDirectory(name, node.getMiddle())) {
					return true;
				}
			}
			if (node.getRight() != null) {
				if(isInDirectory(name, node.getRight())) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Prints out the path, starting from the root, leading towards the directory where the cursor is.
	 * @return
	 */
	public String presentWorkingDirectory() {
		DirectoryNode tempCursor = root;
		String path = "root";
		String targetName = cursor.getName();
		while (tempCursor.getName() != targetName) {
			if (isInDirectory(targetName, tempCursor.getLeft())) {
				path += ("/" + tempCursor.getLeft().getName());
				tempCursor = tempCursor.getLeft();
				continue;
			}
			if (isInDirectory(targetName, tempCursor.getMiddle())) {
				path += ("/" + tempCursor.getMiddle().getName());
				tempCursor = tempCursor.getMiddle();
				continue;
			}
			if (isInDirectory(targetName, tempCursor.getRight())) {
				path += ("/" + tempCursor.getRight().getName());
				tempCursor = tempCursor.getRight();
				continue;
			}
		}
		return path;
	}
	/**
	 * Lists the children nodes of the current directory.
	 * @return
	 */
	public String listDirectory() {
		String s = "";
		if (cursor.getLeft() != null) {
			s+= (cursor.getLeft().getName() + " ");
		}
		if (cursor.getMiddle() != null) {
			s+= (cursor.getMiddle().getName() + " ");
		}
		if (cursor.getRight() != null) {
			s+= (cursor.getRight().getName() + " ");
		}
		return (s);
	}
	public void printDirectoryTree() {
		printDirectoryTree(cursor, 1);
	}
	public void printDirectoryTree(DirectoryNode cursor,int depth) { // helper method?
		String spaces = "";
		for (int i=1;i<depth;i++) {
			spaces += "  ";
		}
		if (!cursor.isFile) {
			System.out.print(spaces);
			System.out.println("|- "+ cursor.getName());
		}
		else {
			System.out.print(spaces);
			System.out.println("- "+ cursor.getName());
		}
		DirectoryNode tempCursor = cursor;
		if (tempCursor.getLeft() != null) {
			System.out.print(spaces);
			printDirectoryTree(cursor.getLeft(), depth+1);
		}
		if (tempCursor.getMiddle() != null) {
			System.out.print(spaces);
			printDirectoryTree(cursor.getMiddle(), depth+1);
		}
		if (tempCursor.getRight() != null) {
			System.out.println(spaces);
			printDirectoryTree(cursor.getRight(),depth+1);
		}
	}
	/**
	 * Creates a new directory as a child of the current one.
	 * @param name
	 * @throws IllegalArgumentException
	 * @throws FullDirectoryException
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, FullDirectoryException{
		try {
		for (int i=0;i<name.length();i++) {
			if (name.charAt(i) == ' ' || name.charAt(i) == '/') {
				throw new IllegalArgumentException("Invalid name argument.");
			}
		}
		DirectoryNode newDirectory = new DirectoryNode(name, false);
		cursor.addChild(newDirectory);
		}
		catch(NotADirectoryException e) {}
	}
	/**
	 * Creates a new file as a child of the current directory.
	 * @param name
	 * @throws IllegalArgumentException
	 * @throws FullDirectoryException
	 */
	public void makeFile(String name) throws IllegalArgumentException, FullDirectoryException{
			for (int i=0;i<name.length();i++) {
				if (name.charAt(i) == ' ' || name.charAt(i) == '/') {
					throw new IllegalArgumentException("Invalid name argument.");
				}
			}
			DirectoryNode newFile = new DirectoryNode(name, true);
			if (cursor.getLeft() == null) {
				cursor.setLeft(newFile);
				return;
			}
			if (cursor.getMiddle()== null) {
				cursor.setMiddle(newFile);
				return;
			}
			if (cursor.getRight()== null) {
				cursor.setRight(newFile);
				return;
			}
			throw new FullDirectoryException();
			}
}

	

