package graphComponents;

import java.util.ArrayList;

import graphUtils.*;


public class Graph {
	private Vertex[] vertices;
	private int vertCount;
	
	public Graph(int n) {
		this.vertices = new Vertex[n];
		this.vertCount = n;
		
		for (int i = 0; i < n; i++) {
			this.vertices[i] = new Vertex(i);
		}
	}
	
	
	/*Returns the vertex at index i in the list of vertices*/
	public Vertex getVertex(int i) {
		return this.vertices[i];
	}
	
	
	/*Returns the total amount of vertices stored in the graph*/
	public int getCount() {
		return this.vertCount;
	}
	
	
	/*Conduct a breadth-first search of the graph*/
	public void BFS(Vertex start) {
		for (Vertex v : vertices) { v.setVisited(false); v.setPred(null); }	//Mark all vertices as unvisited with no predecessor
		
		SimpleQueuePrio<Vertex> toProcess = new SimpleQueuePrio<Vertex>();	//Queue of vertices to process
		toProcess.insert(start);	//Start at the start node
		
		Vertex currentVert;
		
		while ((currentVert = toProcess.get()) != null) {				//While there are still vertices to process
			for (AdjListNode adjToCurrent : currentVert.getAdjList()) {	//Iterate adjacent nodes
				Vertex nextVert = getVertex(adjToCurrent.getIndex());
				
				if (!nextVert.getVisited()) {		//For unvisited vertices:
					nextVert.setPred(currentVert);	//Set predecessor to current
					toProcess.insert(nextVert);		//Insert into process queue
					nextVert.setVisited(true);		//Mark as visited
				}
			}
		}
		
	}
	
	
	/*Conducts a depth-first search of the graph*/
	public void DFS(Vertex current, Vertex pred) {
		for (Vertex v : vertices) { v.setVisited(false); v.setPred(null); }	//Mark all vertices as unvisited with no predecessor
		
		current.setVisited(true);
		current.setPred(pred);
		
		for (AdjListNode adjToCurrent : current.getAdjList()) {
			Vertex nextVert = getVertex(adjToCurrent.getIndex());
			
			if (!nextVert.getVisited()) {
				DFS(nextVert, current);
			}
		}
	}
	
	
	/*Perform Dijkstra's shortest path algorithm using a priority queue. Returns array of shortest paths and sets predecessor values in order to construct paths*/
	public int[] dijkstra(Vertex start) {
		for (Vertex v : vertices) { v.setVisited(false); v.setPred(null); }
		
		/*Initialise weights*/
		int[] weights = new int[vertCount];	//Shortest paths to each vertex
		for (int i = 0; i < vertCount; i++) { weights[i] = Integer.MAX_VALUE; }
		weights[start.getIndex()] = 0;		//Set start's weight to itself to zero
		
		SimpleQueuePrio<Vertex> toProcess = new SimpleQueuePrio<Vertex>();
		toProcess.insert(start, 0);
		start.setVisited(true);
		
		Vertex minVert;
		
		while ((minVert = toProcess.get()) != null) {	//Find v with minimum d(v) (Using priority queue)
			minVert.setVisited(true);	//"Add v to S"
			
			for (AdjListNode w : minVert.getAdjList()) {
				Vertex neighbour = this.getVertex(w.getIndex());	//Get w as a vertex				
				int newWeight = weights[minVert.getIndex()] + w.getWeight();
				
				if (!neighbour.getVisited() && newWeight < weights[neighbour.getIndex()]) {
					toProcess.updateWeight(neighbour, newWeight);	//Add or update Vertex in priority queue
					
					weights[neighbour.getIndex()] = newWeight;
					neighbour.setPred(minVert);
				}
				
			}
		}
		
		return weights;
	}
	
	
	/*Construct paths to all other vertices where path cost is the cost of the largest edge in that path*/
	public int[] largestWeightPath(Vertex start) {
		for (Vertex v : vertices) { v.setVisited(false); v.setPred(null); }
		
		/*Initialise weights*/
		int[] weights = new int[vertCount];	//Shortest paths to each vertex
		for (int i = 0; i < vertCount; i++) { weights[i] = Integer.MAX_VALUE; }
		weights[start.getIndex()] = 0;		//Set start's weight to itself to zero
		
		SimpleQueuePrio<Vertex> toProcess = new SimpleQueuePrio<Vertex>();
		toProcess.insert(start, 0);
		
		Vertex minVert;
		
		while ((minVert = toProcess.get()) != null) {
			minVert.setVisited(true);
			
			for (AdjListNode w : minVert.getAdjList()) {
				Vertex neighbour = this.getVertex(w.getIndex());	//Get w as a vertex
				
				int oldWeight = weights[neighbour.getIndex()];
				int newWeight = (weights[minVert.getIndex()] > w.getWeight()) ? weights[minVert.getIndex()] : w.getWeight();			//w.getWeight();
				
				if (!neighbour.getVisited() && newWeight < oldWeight) {
					weights[neighbour.getIndex()] = newWeight;
					neighbour.setPred(minVert);
					
					toProcess.insert(neighbour, newWeight);
				}
			}
		}
		
		return weights;
	}
	
	
	/*Construct Prim-Jarnik minimum spanning tree algorithm*/
	public void primJarnik() {
		Vertex start = vertices[0];	//Choose r
		start.setVisited(true);		//Set visited ("as tv")
		
		ArrayList<Vertex> tv = new ArrayList<Vertex>();	//Stores tree vertices
		tv.add(start);
		
		int ntv = vertCount - 1;	//Count of non-tree vertices
		
		while (ntv > 0) {
			Vertex currentPred = null;
			AdjListNode minAdj = null;
			
			for (Vertex v : tv) {
				for (AdjListNode ntvPossible : v.getAdjList()) {
					if (!getVertex(ntvPossible.getIndex()).getVisited()) {
						if (minAdj == null) {
							currentPred = v;
							minAdj = ntvPossible;
							
						} else if (ntvPossible.getWeight() < minAdj.getWeight()) {
							currentPred = v;
							minAdj = ntvPossible;
							
						}
					}
				}
			}
			
			if (minAdj == null) { return; }	//Unconnected graph
			
			Vertex minVert = getVertex(minAdj.getIndex());
			
			tv.add(minVert);
			minVert.setPred(currentPred);
			minVert.setVisited(true);
			
			ntv--;
		}
	}
	
	
	public void topOrder() {
		for (Vertex v : vertices) {
			for (AdjListNode adjToV : v.getAdjList()) {
				Vertex current = getVertex(adjToV.getIndex());
				current.setInDegree(current.getInDegree() + 1);	//Initialise Vertex in-degrees
			}
		}
		
		SimpleQueuePrio<Vertex> sourceQueue = new SimpleQueuePrio<Vertex>();				//Initialise source queue
		for (Vertex v : vertices) { if (v.getInDegree() == 0) { sourceQueue.insert(v); } }	//Populate with all sources
		
		Vertex currentVert;
		int currentLabel = 1;
		
		while ((currentVert = sourceQueue.get()) != null) {
			currentVert.setVal(Integer.toString(currentLabel));	//Apply current label
			currentLabel++;
			
			for (AdjListNode adjToCurrent : currentVert.getAdjList()) {
				Vertex neighbourVert = getVertex(adjToCurrent.getIndex());
				neighbourVert.setInDegree(neighbourVert.getInDegree() - 1);	//Decrement in-degree
				
				if (neighbourVert.getInDegree() == 0) { sourceQueue.insert(neighbourVert); }	//Update source queue
			}
		}
		
		if (currentLabel <= getCount()) {
			System.out.println("Deadlock detected, ordering is impossible. All possible labels have been applied.");
		}
	}
	
}
