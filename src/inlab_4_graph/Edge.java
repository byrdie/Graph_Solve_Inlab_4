
package inlab_4_graph;

/**
 *
 * @author Roy Smart
 */
public class Edge {
    public Node target;
    public Node source;
    public int weight;
    
    public Edge(Node destination, int distance, Node start){
        target = destination;
        source = start;
        weight = distance;
    }
}
