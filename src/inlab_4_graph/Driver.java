/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    static int size = 5;
    static int maxPath = 9;
    
    public static void main(String[] args) {
        int[][] adjacency = new int[size][size];
        int[][] weightedAdjacency = new int[size][size];
        
        adjacency = randomAdjacencyMatrix();
        weightedAdjacency = randomWeightedMatrix(adjacency);
        Graph network;

        network = new Graph(size);
        network.connectNodes(weightedAdjacency);

        System.out.println("Depth-First Search");
        network.depthFirst(network.getFirst());
        network.resetGraph();
        System.out.println();

        System.out.println("Breadth-First Search");
        network.breadthFirst();

    }

    public static int[][] retrieveAdjacencyMatrix() {        
        int[][] adjacency = new int[1][1];
        
        try {
            String path = "E:\\Users\\byrdie\\Documents\\NetBeansProjects\\CSCI_232\\Inlab_4_Graph\\src\\inlab_4_graph\\adjacencyMatrix.txt";
            Scanner scanner = new Scanner(new File(path));
            String row;
            char[] rowChar;
            
            
            System.out.println("Adjacency Matrix");
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
                System.out.println(row);
                i++;
            }
            System.out.println();            
        } catch (FileNotFoundException e) {
            System.out.println("File Not found");
        }
        return adjacency;
    }

    public static int[][] randomAdjacencyMatrix() {
        Random generator = new Random();
        int[][] adjacency = new int[size][size];
        
        System.out.println("Adjacency Matrix");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){
                if(i == j){
                    adjacency[i][j] = 0;
                }
                else{
                    adjacency[i][j] = generator.nextInt(2);
                }
                System.out.print(adjacency[i][j]);
            }
            System.out.println();
        }
        return adjacency;
    }
    
    public static int[][] randomWeightedMatrix(int[][] adjacency){
        Random generator = new Random();
        int[][]weighted = new int[size][size];
        
        System.out.println("Weighted Adjacency Matrix");
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(adjacency[i][j] == 1){
                    weighted[i][j] = generator.nextInt(maxPath) + 1;
                }
                else{
                    weighted[i][j] = 0;
                }
                System.out.print(weighted[i][j]);
            }
            System.out.println();
        }
        return weighted;
    }
  
}
