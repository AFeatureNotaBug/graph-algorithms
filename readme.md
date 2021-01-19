# Graph Algorithms and Utilities
This was initially part of university coursework.  
The original coursework required an implementation of Dijkstra's Algorithm.  

The graph is maintained using adjacency lists for vertices.  

## Algorithms (Graph.java)
### Breadth and Depth First Search
  
### Dijkstra's Algorithm
The algorithm makes use of a priority queue  in the interest of efficiency.  
The priority queue can be found under "SimpleQueuePrio.java" and can be used as both a priority queue or a LIFO queue.  

### Prim-Jarnik Algorithm
Constructs a minimum weight spanning tree of the graph.  

### Topological Ordering
Constructs a topological ordering if possible, that is if the graph is an acyclic digraph.


## Utility Files
### SimpleQueuePrio
Very basic priority queue that functions as both a priority queue and a LIFO queue.  

### Utils.java
#### calcDist
Calculates the distance between two strings based on their position in the alphabet as their "value".  
Useful for constructing "word ladders".  

#### BSFLadder and DijLadder
Outputs a word ladder if constructed using breadth-first search or dijkstra's algorithm.  

#### fromWordFile and fromMatrix
Create graphs from appropriately formatted text files.