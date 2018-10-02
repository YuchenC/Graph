
package se.kth.id1020.graph;

import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.ArrayList;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

public class DijkstraPaths 
{
    private double[] distTo;          
    private Edge[] edgeTo;    
    private IndexMinPQ<Double> pq; 
    private int count;
    
    public void DijkstraPaths (Graph G, int s) 
    { 
        for (Edge e : G.edges()) 
        {
            if (e.weight < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        // array of double, save distance, does not take weight into consideration, length = number of vertices
        distTo = new double[G.numberOfVertices()];
        // array of double, save distance, take weight into consideration, length = number of vertices
        edgeTo = new Edge[G.numberOfVertices()];

        // save infinity for each vertex at first, will change later
        for (int v = 0; v < G.numberOfVertices(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        // make the start vertex with value 0
        distTo[s] = 0.0;
        
        // arrayList of double
        pq = new IndexMinPQ<Double>(G.numberOfVertices());
        // insert distTo[s] at index s
        pq.insert(s, distTo[s]);
        
        // while arrayList list is not empty
        while (!pq.isEmpty()) 
        {
            int v = pq.delMin();
            for (Edge e : G.adj(v))
                nextStep(e);
        }
    }
    
    private void nextStep(Edge e) 
    {
        int v = e.from, w = e.to;
        if (distTo[w] > distTo[v] + e.weight) 
        {
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }
    
    public double distTo(int v) 
    {
        return distTo[v];
    }
    
    public boolean hasPathTo(int v) 
    {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    public int pathTo(int v) 
    {
        if (!hasPathTo(v)) return -1;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[e.from]) 
            count++;
        return count;
    }   
}
