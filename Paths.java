
package se.kth.id1020.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Edge;
import se.kth.id1020.Vertex;

public class Paths 
{
    private boolean[] marked;
    
    public static void main(String[] args) 
    {
        Graph g = DataSource.load();
        
        int count = 1;
        int verticesNumber = g.numberOfVertices();
        int start = 0;
        int end = 0;
        
        for (Vertex v : g.vertices())
        {
            if (v.label.equals("Renyn"))
                start = v.id;
            else if (v.label.equals("Parses"))
                end = v.id;
        }
        
        DepthFirstSearch search = new DepthFirstSearch();
        search.DepthFirstSearch(g, 0);
        ArrayList<Integer> notConnected = new ArrayList<Integer>();
        LinkedList<Integer> connected = new LinkedList<Integer>();
        // 其实可以把所有component都存起来，然后先判断起点单词是哪个connected components里面的
        // 缩小找最短途径的范围，不过其实也就只是节省了第一步而已。。。
        
        for (int i = 0; i < verticesNumber; i++)
        {
            if (!search.marked(i))
            {
                count++;
                notConnected.add(i);
            }    
        }
        
        for (int i = 0; i < notConnected.size(); i++)
        {
            //System.out.println("######## " + notConnected.get(i) + " ##############");
            search.DepthFirstSearch(g, notConnected.get(i));
            for (int j = 0; j < verticesNumber; j++)
                if (search.marked(j) && j != notConnected.get(i))
                {
                    //System.out.println(j + "marked true");
                    notConnected.remove(notConnected.indexOf(j));
                    count--;
                }
            //System.out.println("------------------");
        }
        System.out.println("#components in graph g = " + count);
        
        BreadthFirstPaths path = new BreadthFirstPaths ();
        path.BreadthFirstPaths(g, start);
        System.out.println("Number of componnets (ignore weights) = " + (path.distanceTo(end) + 1));
        
        DijkstraPaths weightPath = new DijkstraPaths();
        weightPath.DijkstraPaths(g, start);
        System.out.println("Number of components (consider weights) = " + (weightPath.pathTo(end) + 1));    
    } 
}
    

