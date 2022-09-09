package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;
import structures.UnboundedQueueInterface;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */

	Queue<File> queue = new Queue<File>();

	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if (!rootNode.exists()){
			throw new FileNotFoundException();
		}
		queue = new Queue<File>();
		queue.enqueue(rootNode);
	}

	
	@Override
	public boolean hasNext() {
		if (queue.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public File next() throws NoSuchElementException {
		
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		File temp = queue.dequeue();
		if(temp.isDirectory()) {
			File[] children = temp.listFiles();
			if(children != null) {
				Arrays.sort(children);
				for(File child : children) {
					queue.enqueue(child);
				}
			}
		}
		return temp;
		
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
