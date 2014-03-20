/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inlab_4_graph;

import java.util.Comparator;
import java.util.PriorityQueue;
import static javax.swing.text.html.HTML.Tag.S;

/**
 *
 * @author byrdie
 */
public class Graph {
    final int infinity = 1000;
    private final Node[] graph;
    private int size;
    private Node[] queue;

    public Graph(int s) {
        size = s;
        graph = new Node[size];
        queue = new Node[size];
        initNodes();
    }

    public Node getFirst() {
        return graph[0];
    }

    private void initNodes() {
        for (int i = 0; i < size; i++) {
            char a = (char) (i + 65); //convert int to char
            graph[i] = new Node(a, size);
        }
    }

    public void connectNodes(int[][] adjacent) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjacent[i][j] >= 1) {
                    graph[i].setConnection(graph[j], adjacent[i][j], j);
                }
            }
        }
    }

    public void resetGraph() {
        for (int i = 0; i < size; i++) {
            graph[i].resetVisited();
        }
    }

    public void depthFirst(Node currentNode) {
        if (currentNode == null) {
            return;
        } else {
            currentNode.setVisited();  //set node as being visited
            for (int j = 0; j < size; j++) {
                Node nextConnection = currentNode.getNode(j);
                if (nextConnection != null && nextConnection.visited()) {   //make sure the node hasn't been visited
                    continue;
                }
                depthFirst(nextConnection);
            }
            System.out.println(currentNode.getItem());
        }
    }

    public void breadthFirst() {
        int start = 0;
        int end = 0;

        graph[0].setIdentified();
        queue[end] = graph[0];
        end++;
        while (start != end) {
            Node u = queue[start];
            start++;
            for (int i = 0; i < size; i++) {
                Node v = u.getNode(i);
                if (v != null && !v.identified() && !v.visited()) {
                    v.setIdentified();
                    queue[end] = v;
                    end++;
                }
            }
            u.setVisited();
        }
        for (int i = 0; i < size; i++) {
            if (queue[i] != null) {
                System.out.println(queue[i].getItem());
            }
        }
    }

    public void Dijkstra(int startNode, int endNode) {

        Node[] S = new Node[size];  //All vertices for which we have computed the shortest distance
        
        PriorityQueue<Node> V_S = new PriorityQueue(); //Vertices waiting to be processed
        //PriorityQueueBST<Node> V_S = new PriorityQueueBST();
        int S_tail = 0;
        //
        //int[] d = new int[size];    //array of shortest paths from start to d[index]
        Node[] p = new Node[size];    //array of predecessors of v in the path s to v

        S[S_tail] = graph[startNode];    //Initialize S with start vertex
        System.out.println(S[S_tail].getItem());
        S_tail++;

        /*Initialize V_S with the remaining vertices*/
        int currentNode = startNode;
        do {
            currentNode = (currentNode + 1) % size;
            Node v = graph[currentNode];
            
            /*for all v in V_S (line 2 - 6)*/
            int vIndex = (int) (v.getItem() - 65);   //find index in matrix
            p[vIndex] = S[0];
            if (S[0].connections[vIndex] != null) {   //if there is an edge (s,v)
                v.d = S[0].connections[vIndex].weight;
            } else {
                v.d = infinity;
            }
            V_S.add(v);
            
        } while (currentNode != startNode);

        /*Dijkestra's main loop*/
        Node u = S[0];
        /*While V_S is not empty*/
        while (!V_S.isEmpty() && (int)(u.getItem() - 65) != endNode) {
             
            u = V_S.remove();  //find smallest d and remove
            S[S_tail] = u;
            S_tail = (S_tail + 1) % size;
            System.out.println(u.getItem() + " " + u.d);

            /*For all v adjacent to u in V_S*/
            int uIndex = (int) (u.getItem() - 65);
            int vIndex = uIndex;
            do {
                vIndex = (vIndex + 1) % size;
                Edge u_v = u.connections[vIndex];
                if (u_v != null && vIndex != startNode) {
                    Node v = u_v.target;
                    if ((u.d + u_v.weight) < v.d) {
                        v.d = (u.d + u_v.weight);
                        p[vIndex] = u;
                    }
                }
            } while (vIndex != uIndex);
        }
    }
}
