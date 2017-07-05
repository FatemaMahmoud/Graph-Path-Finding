
package graph;

import java.awt.Color;
import java.util.ArrayList;

public class Edge extends Shape {

    private int weight;
    private boolean dir;
    private int x2;
    private int y2;
    private ArrayList<Node> connNodes;

    public Edge(int x1, int x2, int y1, int y2, int weight, boolean dir, ArrayList<Node> connNodes) {
        super(Color.BLACK, x1, y1);
        this.weight = weight;
        this.dir = dir;
        this.connNodes = new ArrayList<>();
        this.connNodes.add(connNodes.get(0));
        this.connNodes.add(connNodes.get(1));
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public int getX2(){
        return x2;
    }
    
    public int getY2(){
        return y2;
    }
    
    public double getLength(){
        return Math.sqrt( ( ( x2 - this.getX() ) * ( x2 - this.getX() ) ) + ( ( y2 - this.getY() ) * ( y2 - this.getY() ) ) );
    }
    
    public boolean checkConnectivity(String n1, String n2){
        if ((n1.equals(connNodes.get(0).getName()) && n2.equals(connNodes.get(1).getName())) || ( n2.equals(connNodes.get(0).getName()) && n1.equals(connNodes.get(1).getName())))
            return true;
        return false;
    }
    
    public String getConnNode(int n){
        return connNodes.get(n).getName();
    }
    
    public boolean isConnected(String n){
        if (n.equals(connNodes.get(0).getName()) || n.equals(connNodes.get(1).getName()))
            return true;
        return false;
    }
    
    
    
   

}
