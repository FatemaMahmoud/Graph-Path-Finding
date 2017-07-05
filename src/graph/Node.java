
package graph;

import java.awt.Color;
import java.util.ArrayList;

public class Node extends Shape {
    
    private String name;
    private ArrayList<String> connNodes;
    
    
    public Node(Color clr, int x, int y, String name){
        
        super(clr, x, y);
        this.name = name;
        connNodes = new ArrayList<String>();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void addConnNode(String node){
        connNodes.add(node);
        
    }
    
    public boolean isConnected(String n){
        for (int i = 0 ; i < connNodes.size() ; i++){
            if (n.equals(connNodes.get(i)))
                return true;
        }
        return false;
    }
  
    
}
