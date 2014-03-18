/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inlab_4_graph;


/**
 *
 * @author roy.smart
 */
public class Node {
    final private Node[] connections;
    final private char item;
    private boolean visited = false;    // check if the node has already been searched
    private boolean identified = false;
    
    Node(char a, int s){
        item = a;
        connections = new Node[s];
    }
    
    public char getItem(){         
        visited = true;
        return item;
    }    
    
    public boolean visited(){
        return visited;     //item has been searched   
    }   
    public void setVisited(){
        visited = true;
    }   
    public void resetVisited(){
        visited = false;
    }
    
    public boolean identified(){
        return identified;
    }    
    public void setIdentified(){
        identified = true;
    }
    
    public void setConnection(Node node, int i){
        connections[i] = node;
    }
    
    public Node getConnection(int i){
        return connections[i];
    }
}
