// Christopher Wong (#111386693)
// CSE 214 R09
import java.util.*;
public class BashTerminal {
	/** User client.
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("serial")
		DirectoryTree myTree = new DirectoryTree();
		Scanner input = new Scanner(System.in);
		boolean cont = true;
		while (cont) {
			try {
				System.out.print("[111386693@host]: $ ");
				String answer = input.nextLine();
				if (answer.contains("mkdir")) {
					String newDir = answer.trim().substring(6, answer.trim().length());
					myTree.makeDirectory(newDir);
				}
				if (answer.contains("touch")) {
					String newFile = answer.trim().substring(6, answer.trim().length());
					myTree.makeFile(newFile);
				}
				if (answer.contains("cd") && !answer.contains("cd /")) {
					String dir = answer.trim().substring(3, answer.trim().length());
					if (!myTree.isInDirectory(dir, myTree.cursor)) {
						System.out.println("ERROR: No such directory named '" +dir+"'.");
					}
					myTree.changeDirectory(dir);
				}
				else {
					switch (answer) {
					case("pwd"):
						System.out.println(myTree.presentWorkingDirectory());
						break;
					case("ls"):
						System.out.println(myTree.listDirectory());
						break;
					case("ls -R"):
						myTree.printDirectoryTree(myTree.cursor, 1);
						break;
					case("cd /"):
						myTree.resetCursor();
						break;
					case("exit"):
						System.out.println("Program terminating normally");
						cont = false;
						break;
					default:

					}
				}
			}
			catch(FullDirectoryException ex) {
				System.out.println("ERROR: Present directory is full.");
			}
			catch(NotADirectoryException ex) {
				System.out.println("ERROR: Cannot change directory into a file.");
			}
			catch(Exception ex) {
				System.out.println("Something went wrong, please try again.");
			}
		}
	}
}
class FullDirectoryException extends Exception{

}
class NotADirectoryException extends Exception{

}
