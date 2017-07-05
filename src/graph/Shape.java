
package graph;

import java.awt.Color;

public class Shape {
    private Color clr;
    private int x;
    private int y;
    
    public Shape (Color clr, int x, int y){
        this.clr = clr;
        this.x = x;
        this.y = y;
    }
    
    public void setX (int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setColor(Color clr){
        this.clr = clr;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Color getColor(){
        return this.clr;
    }
    
    
    
    
    
    
}
