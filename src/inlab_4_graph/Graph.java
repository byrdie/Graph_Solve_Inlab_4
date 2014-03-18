/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inlab_4_graph;

/**
 *
 * @author byrdie
 */
public class Graph {
    private final Node[] graph;
    private int size;
    private Node[] queue;
    
    public Graph(int s){
        size = s;
        graph = new Node[size];
        queue = new Node[size];
        initNodes();
    }
    
    public Node getFirst(){
        return graph[0];
    }
    
    private void initNodes(){
        for(int i = 0; i < size; i++){
            char a = (char) (i + 65); //convert int to char
            graph[i] = new Node(a, size);
        }
    }
    
    public void connectNodes(int[][] reach){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(reach[i][j] == 1){
                    graph[i].setConnection(graph[j], j);
                }
            }
        }
    }
    
    public void resetGraph(){
        for(int i = 0; i < size; i++){
            graph[i].resetVisited();
        }
    }
    
    public void depthFirst(Node currentNode){
        if(currentNode == null){
            return;
        }
        else{
            currentNode.setVisited();  //set node as being visited
            for(int j = 0; j < size; j++){
                Node nextConnection = currentNode.getConnection(j);
                if(nextConnection != null && nextConnection.visited() ){   //make sure the node hasn't been visited
                    continue;
                }
                depthFirst(nextConnection);
            }
            System.out.println(currentNode.getItem());
        }
    }
    
    public void breadthFirst(){
        int start = 0;
        int end = 0;
        
        graph[0].setIdentified();
        queue[end] = graph[0];
        end++;
        while(start != end){
            Node u = queue[start];
            start++;
            for(int i = 0; i < size; i++){
                Node v = u.getConnection(i);
                if(v != null && !v.identified() && !v.visited()){
                    v.setIdentified();
                    queue[end] = v;
                    end++;
                }
            }
            u.setVisited();
        }
        for(int i = 0; i < size; i++){
            if(queue[i] != null)
            System.out.println(queue[i].getItem());
        }
    }
    
    public void Dijkstra(int startNode, int endNode){
        int[] heap = new int[size*size];
        int start = 0;
        int end = 0;
        
         graph[startNode].setIdentified();
    }
}
