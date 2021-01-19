package graphUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import graphComponents.*;


public class Utils {
	/*Checks by how many characters two words differ*/
	public static int getDiff(String word1, String word2) {
		int diffCount = 0;
		
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				diffCount++;
			}
		}
		
		return diffCount;
	}
	
	
	/*Calculates the absolute distance between two words based on letter position in the alphabet*/
	public static int calcDist(String word1, String word2) {
		int dist = 0;
		
		for (int i = 0; i < word1.length(); i++) {
			if (word1.charAt(i) != word2.charAt(i)) {
				dist = word1.charAt(i) - word2.charAt(i);
			}
		}
		
		if (dist < 0) {
			dist = (-1) * dist;
		}
		
		return dist;
	}
	
	
	/*Create an unweighted word ladder "in reverse" using predecessor value, used with BFS*/
	public static String BFSLadder(Vertex current) {
		String ladder = "";
		int length = 0;
		
		while (current != null) {
			ladder = "\t" + current.getVal() + "\n" + ladder;
			length++;
			
			current = current.getPred();
		}
		
		ladder = "shortest word ladder of length " + (length - 1) + "\nexample shortest word ladder:\n" + ladder;
		return ladder;
	}
	
	
	/*Create a word ladder "in reverse" using predecessor value, used with Dijkstra*/
	public static String DijLadder(Vertex current) {
		String ladder = "";
		
		while (current != null) {
			ladder = "\t" + current.getVal() + "\n" + ladder;
			current = current.getPred();
		}
		
		ladder = "path with minimum distance:\n" + ladder;
		return ladder;
	}
	
	
	/*Create a graph from a text file*/
	public static Graph fromWordFile(String inFile) throws IOException {
		FileReader reader = new FileReader(inFile);
		Scanner in = new Scanner(reader);
		
		ArrayList<String> words = new ArrayList<String>();
		
		while (in.hasNext()) {
			words.add(in.nextLine());
		}
		
		in.close();
		reader.close();
		
		Graph wordGraph = new Graph(words.size());
		
		for (int i = 0; i < words.size(); i++) {
			String currentWord = words.get(i);
			
			Vertex current = wordGraph.getVertex(i);
			current.setVal(currentWord);
			
			for (int j = 0; j < words.size(); j++) {
				String neighbourWord = words.get(j);
				
				if (Utils.getDiff(currentWord, neighbourWord) == 1) {
					current.addToAdj(j, Utils.calcDist(currentWord, neighbourWord));
				}
			}
		}
		
		return wordGraph;
	}
	
	
	/*Create a graph from a "matrix" text file*/
	public static Graph fromMatrix(String inFile) throws IOException {
		FileReader reader = new FileReader(inFile);
		Scanner in = new Scanner(reader);
		
		Graph matrixGraph = new Graph(Integer.parseInt(in.nextLine()));
		int currentVert = 0;
		
		while (in.hasNextLine()) {
			String[] adjacencyList = in.nextLine().split(" ");
			int currentAdj = 0;
			
			
			for (String adj : adjacencyList) {
				if (!adj.equals("0")) {
					matrixGraph.getVertex(currentVert).addToAdj(currentAdj, Integer.parseInt(adj));
				}
				
				currentAdj++;
			}
			
			currentVert++;
		}
		
		in.close();
		reader.close();
		
		return matrixGraph;
	}
}
