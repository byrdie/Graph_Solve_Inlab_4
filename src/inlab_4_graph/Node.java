/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inlab_4_graph;


/**
 *
 * @author roy.smart
 */
public class Node implements Comparable<Node>  {
    final int infinity = 1000;
    final public Edge[] connections;
    public int d = infinity;
    final private char item;
    final public int index;
    private boolean visited = false;    // check if the node has already been searched
    private boolean identified = false;
    
    Node(char a, int s){
        item = a;
        connections = new Edge[s];
        index = (int)(item - 65);
    }
    
    public char getItem(){         
        visited = true;
        return item;
    }    
    
    public int getIndex(){
        return index;
    }
    
    public boolean visited(){
        return visited;     //item has been searched   
    }   
    public void setVisited(){
        visited = true;
    }   
    public void reset(){
        visited = false;
        identified = false;
        d = infinity;
    }
    
    public boolean identified(){
        return identified;
    }    
    public void setIdentified(){
        identified = true;
    }
    
    public void setConnection(Node node, int weight, int i){
        connections[i] = new Edge(node, weight, this);
    }
    
    public Node getNode(int i){
        if(connections[i] == null){
            return null;
        }
        else{
            return connections[i].target;
        }
    }
    
    /*set natural order in PriorityQueue*/
    @Override
    public int compareTo(Node first){
        return Integer.compare(d, first.d);
   
    }
}
