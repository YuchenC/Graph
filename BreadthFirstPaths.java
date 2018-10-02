
package se.kth.id1020.graph;

//import edu.princeton.cs.algs4.Queue;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

public class BreadthFirstPaths 
{
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;      
    private int[] distance;
    
    // get vetices that are connected with a certain vertex
    public void BreadthFirstPaths (Graph G, int s) 
    {
        // array of boolean, length is number of vertices
        marked = new boolean[G.numberOfVertices()];
        // array of int, length is number of vertices, stores distance of edge
        distance = new int[G.numberOfVertices()];
        // call method bfs, the one that accept graph and integer as parameter
        bfs(G, s);
    }
    
    private void bfs(Graph G, int s) 
    {
        
        // FIFO, save the shortes path
        Queue<Integer> q = new Queue<Integer>();
        // preparation work, set distance of each vertex to infinity, will be change later
        for (int v = 0; v < G.numberOfVertices(); v++)
            distance[v] = INFINITY;
        // change the distance of starting vertex to 0
        distance[s] = 0;
        // the starting vertex is of course visited, so mark it as true
        marked[s] = true;
        // enqueue the start vertex
        q.enqueue(s);
        
        // while q is not empty
        while (!q.isEmpty()) 
        {
            // examine each item in the queue, at last the queue will be empty 
            // and all items in the queue before while loop will be removed
            int v = q.dequeue();
            // operation did for each item in the queue
            // for each adjacent edges of this vertex
            for (Edge e : G.adj(v)) 
            {
                // w is neighbour of v
                int w = e.to;
                // if have never visited this neighbour vertex before
                if (!marked[w]) 
                {
                    // change value for w, from infinity to distance[v] + 1
                    distance[w] = distance[v] + 1;
                    // now has w been visited, mark it as true
                    marked[w] = true;
                    // add v's neighbour w to the queue
                    // prepare for next while loop, all items are not connected to the starting point, 
                    // but connected to starting vertex v's neighbout w
                    q.enqueue(w);
                }
            }
        }
    }
    
    public boolean hasPathTo(int v) 
    {
        return marked[v];
    }
    
    public int distanceTo(int v) 
    {
        return distance[v];
    }
}
