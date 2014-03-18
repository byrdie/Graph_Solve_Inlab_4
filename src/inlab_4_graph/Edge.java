
package inlab_4_graph;

/**
 *
 * @author Roy Smart
 */
public class Edge {
    public Node target;
    public int weight;
    
    public Edge(Node node, int distance){
        target = node;
        weight = distance;
    }
}
