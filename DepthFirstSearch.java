
package se.kth.id1020.graph;

import java.util.LinkedList;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

public class DepthFirstSearch 
{
    private boolean[] marked;
    private int count;
    
    public void DepthFirstSearch (Graph G, int v)
    {
        marked = new boolean [G.numberOfVertices()];
        dfs(G, v);   
    }
    
    public void dfs (Graph G, int v)
    {
        marked[v] = true;
        count++;
        for (Edge e : G.adj(v))    
        {
            v = e.to;
            if (!marked[v]) 
                dfs(G, v);
        }
    }
    
    public boolean marked (int w)
    {
        return marked[w];
    }
    
    public int count()
    {
        return count;
    }
}
