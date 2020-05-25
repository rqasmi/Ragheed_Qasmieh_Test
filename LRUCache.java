

import java.util.HashMap;


/**
 * 
 * @author Ragheed Qasmieh 
 * Last Updated: 2019-10-19
 *
 */



/**
 * This Node class is used to represent an element in LRU cache
 * Each Node has a pointer to the next and previous nodes in the LRU cache. The cache is implemented using a doubly linked list ADT
 * The data takes form of dictionary (key + value)
 * The key stores the input given to a function
 * The value stores the output corresponding to the given input, to be retrieved if the input is cached
 * 
 *
 */

class Node{
	int key;
	int value;
	Node next;
	Node prev;
	
	
	public Node(int key, int value){
		this.key = key;
		this.value = value;
	}
}

/**
 * This class implements the LRU cache. The hashmap is used to support O(1) lookup for cache entries. The key for the hashmap is the value of the 
 * key stored in the node and the value is the reference of the corresponding node. The doubly linked list supports 
 * insertion and deletion of an Node in O(1) time. The methods implemented support the insertion of a node to the cache, updating an
 * Node to be the most recently one whenever it is accessed and the removal of the least recently used Node in case the cache is full.
 * 
 *  Note: The cache expiration feature is not implemented.
 * 
 * @author Ragheed Qasmieh
 *
 */

public class LRUCache {
	
	private int capacity;
	private HashMap<Integer, Node> map;
	private Node head;
	private Node tail;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, Node>(capacity);
	}
	
	/**
	 * This method takes a key as input and returns the value associated with the node that contains the input key, if the 
	 * Node is in the hashmap. It updates the position of the Node in the linked list to be at the beginning (i.e. the head)
	 * @param key
	 * @return int
	 */
	public int getEntry(int key) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			moveToFront(node);
			return node.value;
		}
		return -1;
	}
	
	/**
	 * This method takes as input a key and a value and uses the hashmap to lookup the node (entry) associated with that key. If the the node 
	 * already exists in the list, we modify the value of the it and move it to the front of the list and return. Else we create a new 
	 * node with the key,value inputs. If the maximum capacity of the cache is reached, we evict the last recently used entry (i.e.tail) and add
	 * the new entry to the start of the list.   
	 * 
	 * @param key
	 * @param value
	 */
	
	public void putEntry(int key, int value) {
		if(map.containsKey(key)) {
			Node node = map.get(key);
			node.value = value;
			moveToFront(node);
			return;
		}
		Node newNode = new Node(key, value);
		if(map.size() == capacity) {
			map.remove(tail.key);
			removeNode(tail);
			
		}
		map.put(key, newNode);
		addFirst(newNode);
	}
	/**
	 * This method moves the node with the input reference to the front of the linked list by calling removeNode and then addFirst.
	 * 
	 * @param node
	 */
	private void moveToFront(Node node) {
		removeNode(node);
		addFirst(node);
	}
	/**
	 * This method adds a node to the start of the list. If the head is null, this means that the list is empty so head and tail point to the 
	 * node. Else, the next pointer is set to point to the head, the prev of head points to the node and the head points to the node.
	 * 
	 * @param node
	 */
	private void addFirst(Node node) {
		if(head == null) {
			head = node;
			tail = node;
		}else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}
	/**
	 * This method removes the node from the list. There are 4 cases including edge cases.
	 * 
	 * @param node
	 */
	private void removeNode(Node node) {
		//If the node is not on the edge of the list
		if(node.prev != null && node.next != null) {
			node.prev.next = node.next; //move the next pointer of the previous node to point to the next node 
			node.next.prev = node.prev;	//move the previous pointer of the next node to point to the previous node 
		}else if(node.prev == null && node.next != null) {
			head = node.next; //the node is at the start, so move the head pointer to the next node
			head.prev = null; //move the previous of head to null
		}else if(node.prev != null && node.next == null) {
			tail = node.prev; //the node is at the end, so move the tail pointer to the previous node
			tail.next = null; // move the next pointer of tail to null
		}else {
			head = null; //head is null	
			tail = null; //tail is null
		}
	}
	/**
	 * An example of an input function for the values of the cache.
	 * @param input
	 * @return
	 */
	private static int cacheOutput(int input) {
		return (input * input);
	}
	/**
	 * This print method is just for testing
	 */
	private void printCache() {
		for(Integer key: this.map.keySet()) {
			System.out.println(String.valueOf(key) + ": " + String.valueOf(map.get(key).value));	
		}
	}
	
	/**
	 *
	 * This is the main method that contains a collection of test cases
	 * @param args
	 */
	
	public static void main(String[] args) {
		LRUCache LruCache = new LRUCache(4);
		LruCache.putEntry(4, cacheOutput(4));
		LruCache.putEntry(5, cacheOutput(5));
		LruCache.putEntry(2, cacheOutput(2));
		LruCache.putEntry(8, cacheOutput(8));
		LruCache.putEntry(8, 16); //test if the key is already in the map
		LruCache.putEntry(10, cacheOutput(10));		
		LruCache.printCache();
	}

}
