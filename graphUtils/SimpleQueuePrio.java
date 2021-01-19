package graphUtils;


/***********************************************************************************\
|Simple queue data structure														|
|Nodes stored in the queue are retrieved from the head (index value is returned)	|
|Items can be inserted in two ways:													|
| - At the tail (FIFO)																|
| - With priority																	|
|The updateWeight function is used to update the weight of an existing node			|
\***********************************************************************************/
public class SimpleQueuePrio<Item> {
	private static class Node<Item> {
		private Item item;
		private int weight;
		
		private Node<Item> next;
		
		public Node(Item item, int w) {
			this.item = item;
			this.weight = w;
			
			this.next = null;
		}
	}
	
	
	Node<Item> head, tail;
	
	public SimpleQueuePrio() {
		this.head = null;
		this.tail = null;
	}
	
	
	/*Insert with no priority (FIFO queue)*/
	public void insert(Item i) {
		Node<Item> newNode = new Node<Item>(i, 0);
		
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
			
		} else {
			this.tail.next = newNode;
			this.tail = newNode;
		}
	}
	
	
	/*Insert an item with priority (Priority queue)*/
	public void insert(Item i, int weight) {
		Node<Item> newNode = new Node<Item>(i, weight);
		
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
			
		} else {
			Node<Item> trailing = null;
			Node<Item> current = this.head;
			
			while (current != null && current.weight < weight) {
				trailing = current;
				current = current.next;
			}
			
			if (trailing == null) {
				newNode.next = this.head;
				this.head = newNode;
				
			} else {
				trailing.next = newNode;
				newNode.next = current;
			}
			
		}
	}
	
	
	/*Return item at the head of the queue and remove it, returns null if queue empty*/
	public Item get() {
		if (this.head != null) {
			Item temp = this.head.item;
			this.head = this.head.next;
			
			return temp;
		}
		
		return null;
	}
	
	
	/*Return a specific item in the priority queue based on its index value*/
	private Item get(Item item) {
		if (this.head == null) {
			return null;
			
		} else if (this.head.item == item) {
			Item temp = this.head.item;
			this.head = this.head.next;
			
			return temp;
		}
		
		Node<Item> trailing = null;
		Node<Item> current = this.head;
		
		while (current != null && current.item != item) {
			trailing = current;
			current = current.next;
		}
		
		if (current != null) {
			trailing.next = current.next;
			return current.item;
		}
		
		return null;
	}
	
	
	public void updateWeight(Item item, int weight) {
		this.get(item);
		this.insert(item, weight);
		
		//if (this.get(item) != null) {
		//	this.insert(item, weight);
		//}
	}
	
}
