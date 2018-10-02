
package se.kth.id1020.graph;

import java.util.LinkedList;

public class Queue<Item> 
{
    
    private Node<Item> first;
    private Node<Item> last;
    int n;
    
    private class Node<Item>
    {
        private Item item;
        private Node<Item> next;    
    }
    
    // to initialize an empty queue
    public Queue()
    {
        first = null;
        last = null;
    }
    
    public boolean isEmpty()
    {
        if (first == null)
            return true;
        else
            return false;
    }
    
    public int size()
    {
        return n;
    }
    
    public void enqueue(Item item)
    {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) 
            first = last;
        else
            oldLast.next = last;
        n++;
    }
    
    public Item dequeue()
    {
       Item item = first.item;
       first = first.next;
       n--;
       return item;
    }
}
