/*
 * To allow for graphs to be generated from a text file
 *you must comment out the random graph generation and enable reading
 */
package inlab_4_graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author roy.smart
 */
public class Driver {
    static int size = 6;
    static int maxPath = 9;
    static Random generator = new Random();
    static String adjacencyPath = "adjacencyMatrix.txt";
    static String weightedPath = "weightedMatrix.txt";
    
    public static void main(String[] args) {
        int[][] adjacency = new int[size][size];
        int[][] weightedAdjacency = new int[size][size];
        
        
        /**
         * change this section to read in a graph
         * instead of generating a random one
         */
        boolean read = false;
        if(read){
            System.out.println("Reading matrix");
            adjacency = readMatrix(adjacencyPath);
            weightedAdjacency = readMatrix(weightedPath);
        }else{
            System.out.println("Generating random matrix");
            adjacency = randomAdjacencyMatrix();
            
            weightedAdjacency = randomWeightedMatrix(adjacency);
        }
        System.out.println("Adjacency Matrix");
        printMatrix(adjacency);        
        System.out.println("Weighted Adjacency Matrix");
        printMatrix(weightedAdjacency);
        Graph network = new Graph(size);

        network.connectNodes(weightedAdjacency);        
        
        int start = 0;
        network.Dijkstra(start);
        network.resetGraph();
        
        network.prim(start);
        network.Warshall(adjacency);
        network.floydWarshalls(weightedAdjacency);

    }

    public static int[][] readMatrix(String path) {        
        int[][] adjacency = new int[1][1];
        
        try {
            
            Scanner scanner = new Scanner(new File(path));
            String row;
            char[] rowChar;
            
            
            int i = 0;
            while (scanner.hasNext()) {
                row = scanner.nextLine();
                rowChar = row.toCharArray();
                if (i == 0) {
                    size = row.length();
                    adjacency = new int[size][size];
                }

                for (int j = 0; j < size; j++) {
                    adjacency[i][j] = (int) (rowChar[j] - '0');
                }
                i++;
            }            
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        }
        return adjacency;
    }

    public static int[][] randomAdjacencyMatrix() {
        
        Random generator = new Random();
        int[][] adjacency = new int[size][size];
        
        for(int i = 0; i < size; i++) {
            for(int j = i; j < size; j++){
                if(i == j){
                    adjacency[i][j] = 0;
                }
                else{
                    int next = generator.nextInt(2);
                    adjacency[i][j] = next;
                    adjacency[j][i] = next;
                }
                
            }
        }
        return adjacency;
    }
    
    public static int[][] randomWeightedMatrix(int[][] adjacency){
        
        
        int[][]weighted = new int[size][size];
        
        System.out.println("Weighted Adjacency Matrix");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(adjacency[i][j] == 1){
                    int next = generator.nextInt(maxPath) + 1;
                    weighted[i][j] = next;
                    weighted[j][i] = next;
                }
            }
        }
        return weighted;
    }
    
    public static void printMatrix(int[][] matrix){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(matrix[i][j] + "");
            }
            System.out.println();
        }
    }
  
}
