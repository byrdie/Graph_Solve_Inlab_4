/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inlab_4_graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

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
        initNodes(graph);
    }

    public Node getFirst() {
        return graph[0];
    }

    private void initNodes(Node[] list) {
        for (int i = 0; i < size; i++) {
            char a = (char) (i + 65); //convert int to char
            list[i] = new Node(a, size);
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
            graph[i].reset();
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

    public void Dijkstra(int startNode) {
        System.out.println("Dijkestra's shortest path to all nodes");
        Node[] S = new Node[size];  //All vertices for which we have computed the shortest distance
        Node[] p = new Node[size];    //array of predecessors of v in the path s to v
        PriorityQueue<Node> V_S = new PriorityQueue(); //Vertices waiting to be processed

        int S_tail = 0;
        Node start = graph[startNode];
        S[S_tail] = start;    //Initialize S with start vertex
        start.d = 0;
        S_tail++;

        /*Initialize V_S with the remaining vertices*/
        int currentNode = startNode;
        currentNode = (currentNode + 1) % size;
        while (currentNode != startNode) {

            Node v = graph[currentNode];

            /*for all v in V_S (line 2 - 6)*/
            int vIndex = (int) (v.getItem() - 65);   //find index in matrix
            p[vIndex] = S[0];
            if (S[0].connections[vIndex] != null) {   //if there is an edge (s,v)
                v.d = S[0].connections[vIndex].weight;
            }
            V_S.add(v);
            currentNode = (currentNode + 1) % size;
        }

        /*Dijkestra's main loop*/
        Node u = S[0];
        /*While V_S is not empty*/
        while (!V_S.isEmpty()) {

            u = V_S.remove();  //find smallest d and remove
            S[S_tail] = u;
            S_tail = (S_tail + 1) % size;

            /*For all v adjacent to u in V_S*/
            int uIndex = u.getIndex();
            int vIndex = uIndex;
            vIndex = (vIndex + 1) % size;
            while (vIndex != uIndex) {
                Edge u_v = u.connections[vIndex];
                if (u_v != null && vIndex != startNode) {
                    Node v = u_v.target;
                    if ((u.d + u_v.weight) < v.d) {
                        v.d = (u.d + u_v.weight);
                        p[vIndex] = u;
                    }
                }
                vIndex = (vIndex + 1) % size;
            }
        }
        for (int i = 0; i < size; i++) {
            System.out.println(graph[i].getItem() + " " + graph[i].d);
        }
    }

    public void prim(int startNode) {
        Node[] S = new Node[size];  //vertices in the spanning tree
        PriorityQueue<Node> V_S = new PriorityQueue();  //remaining vertices
        Node[] p = new Node[size];  //source vertex for edge

        Node[] spanningTree = new Node[size];
        initNodes(spanningTree);

        /*Initialize S with start vertex, s, and V_S with the remaining vertices*/
        int S_tail = 0;
        Node start = graph[startNode];
        S[S_tail] = spanningTree[start.index];    //Initialize S with start vertex
        start.d = 0;
        p[start.index] = spanningTree[start.index];
        S_tail++;

        /*Initialize V_S with the remaining vertices*/
        int currentNode = startNode;
        currentNode = (currentNode + 1) % size;
        while (currentNode != startNode) {
            Node v = graph[currentNode];

            /*for all v in V_S (line 2 - 6)*/
            int vIndex = v.index;   //find index in matrix
            p[vIndex] = S[0];
            if (start.connections[vIndex] != null) {   //if there is an edge (s,v)
                v.d = start.connections[vIndex].weight;
            }
            V_S.add(v);

            currentNode = (currentNode + 1) % size; //increment looping index
        }
        /*main loop*/
        while (!V_S.isEmpty()) {
            Node uTemp = V_S.remove();  //remove smallest d item
            Node u = spanningTree[uTemp.index];
            S[S_tail] = u;
            S_tail++;

            try {
                p[u.index].setConnection(u, uTemp.connections[p[u.index].index].weight, u.index);
            } catch (NullPointerException e) {
            }

            PriorityQueue<Node> V_Stemp = new PriorityQueue();  //need temporary Queue to store updated values
            /*for all v in V_S*/
            while (!V_S.isEmpty()) {
                Node vTemp = V_S.remove();
                Node v = spanningTree[vTemp.index];
                Edge u_v = uTemp.connections[v.index];
                if (u_v != null && u_v.weight < vTemp.d) {
                    vTemp.d = u_v.weight;
                    p[v.index] = u;
                }
                V_Stemp.add(vTemp);
            }
            V_S = V_Stemp;
        }
        /*traverse tree to find distances*/
        System.out.println("Primm's minimum spanning tree");
        primmTraversal(S[0], 0, startNode);
    }

    public void primmTraversal(Node vertex, int distance, int startNode) {

        System.out.println(vertex.getItem() + " " + distance);
        for (int i = 0; i < size; i++) {
            Edge edge = null;
            if (i != vertex.index && i != startNode) {
                edge = vertex.connections[i];
            }
            if (edge != null) {
                int newDistance = distance + edge.weight;
                primmTraversal(edge.target, newDistance, startNode);
            }
        }
    }

    public void Warshall(int[][] adjacency) {
        boolean[][] a = new boolean[size][size];
        /*Copy Array*/
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjacency[i][j] == 1) {
                    a[i][j] = true;
                } else {
                    a[i][j] = false;
                }
            }
        }
        /*Warshall's*/
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    a[i][j] = a[i][j] || (a[i][k] && a[k][j]);                    
                }
            }
        }
        /*print*/
        System.out.println("Warshall's reachability matrix");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (a[i][j] == true) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
    
    public void floydWarshalls(int[][] weightedAdjacency){
        int[][] a = new int[size][size];
        /*Copy Array*/
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(weightedAdjacency[i][j] == 0 && i != j){
                    a[i][j] = infinity;
                }
                else{
                    a[i][j] = weightedAdjacency[i][j];
                }
            }
        }
        /*Floyd Warshall's*/
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int sum = a[i][k] + a[k][j];
                    if(a[i][j] > sum){
                        a[i][j] = sum;
                    }                    
                }
            }
        }
        /*print*/
        System.out.println("Floyd Warshall's reachability matrix");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = a[i][j];
                if(num == infinity) num = 0;
                String string = Integer.toString(num);
                if(string.length() == 1){
                   System.out.print(" " + num + " ");
                }else{
                    System.out.print(num + " ");
                }
                
            }
            System.out.println();
        }
    }
}
