package graphComponents;


public class AdjListNode {
	private int index;
	private int weight;
	
	public AdjListNode(int i, int w) {
		this.index = i;
		this.weight = w;
	}
	
	
	/*Returns index of corresponding vertex in graph vertex list*/
	public int getIndex() {
		return this.index;
	}
	
	
	/*Returns wt(v, w) where v is the parent vertex of the AdjNode and w is the corresponding vertex*/
	public int getWeight() {
		return this.weight;
	}
	
}