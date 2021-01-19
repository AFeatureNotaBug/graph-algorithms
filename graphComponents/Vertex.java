package graphComponents;

import java.util.ArrayList;


public class Vertex {
	private int index;						//Index in graph vertex list
	private ArrayList<AdjListNode> adjList;	//List of adjacent vertices
	private boolean visited;				//True if vertex visited, false otherwise
	private Vertex predecessor;				//Predecessor Vertex of current Vertex
	
	String value;	//String value of current Vertex
	int inDegree;	//Count of incoming edges in a digraph (Used for topological ordering)
	
	public Vertex(int i) {
		this.index = i;
		this.adjList = new ArrayList<AdjListNode>();
		this.visited = false;
		this.predecessor = null;
		
		this.value = "";
		this.inDegree = 0;
	}
	
	
	/*Returns the index of the vertex in the graph vertex list*/
	public int getIndex() {
		return this.index;
	}
	
	
	/*Returns adjacency list of the current vertex*/
	public ArrayList<AdjListNode> getAdjList() {
		return this.adjList;
	}
	
	/*Adds an "unweighted" vertex to the adjacency list*/
	public void addToAdj(int i) {
		this.adjList.add(new AdjListNode(i, 1));
	}
	
	/*Adds a weighted vertex to the adjacency list*/
	public void addToAdj(int i, int w) {
		this.adjList.add(new AdjListNode(i, w));
	}
	
	
	/*Returns the value of this.visited*/
	public boolean getVisited() {
		return this.visited;
	}
	
	/*Sets the value of this.visited to b*/
	public void setVisited(boolean b) {
		this.visited = b;
	}
	
	
	/*Returns the predecessor vertex of the current vertex*/
	public Vertex getPred() {
		return this.predecessor;
	}
	
	/*Sets the predecessor of the current vertex*/
	public void setPred(Vertex v) {
		this.predecessor = v;
	}
	
	
	/*Returns the String value of the vertex*/
	public String getVal() {
		return this.value;
	}
	
	/*Sets the String value of the vertex to s*/
	public void setVal(String s) {
		this.value = s;
	}
	
	
	/*Returns the in-degree of a vertex*/
	public int getInDegree() {
		return this.inDegree;
	}
	
	/*Increments the in-degree of a vertex*/
	public void setInDegree(int degree) {
		this.inDegree = degree;
	}
	
}