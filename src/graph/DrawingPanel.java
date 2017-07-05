
package graph;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawingPanel extends javax.swing.JPanel {

  
    
    public DrawingPanel() {
        initComponents();
        ArrayList<Node> connNodes = new ArrayList<Node>();
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                if (ToolsPanel.shape.equals("Node")){
                    GraphFrame.addNode(new Node(ToolsPanel.node.getColor(),e.getX()-25,e.getY()-25,ToolsPanel.node.getName()));
                    repaint();
                }
                else if (ToolsPanel.shape.equals("UndirectedEdge")){
                    int x = e.getX();
                    int y = e.getY();
                    System.out.println("unedge");
                    for (int i = 0 ; i < GraphFrame.nodes.size() ; i++){
                        if (x >= GraphFrame.nodes.get(i).getX() && x <= GraphFrame.nodes.get(i).getX()+50 && y >= GraphFrame.nodes.get(i).getY() && y <= GraphFrame.nodes.get(i).getY()+50){
                            connNodes.add(GraphFrame.nodes.get(i));
                            break;
                        }
                    }
                    for (int i = 0 ; i < connNodes.size() ; i++){
                        System.out.println(connNodes.get(i).getName());
                    }
                    if (connNodes.size() == 2){
                        GraphFrame.addEdge(new Edge(connNodes.get(0).getX()+25,connNodes.get(1).getX()+25,connNodes.get(0).getY()+25,connNodes.get(1).getY()+25,ToolsPanel.wt,false,connNodes));
                        connNodes.get(0).addConnNode(connNodes.get(1).getName());
                        connNodes.get(1).addConnNode(connNodes.get(0).getName());
                        connNodes.clear();
                        repaint();
                    }
                }
                
            }
        });
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0 ; i < GraphFrame.edges.size() ; i++){
            g.setColor(Color.BLACK);
            g.drawLine(GraphFrame.edges.get(i).getX(), GraphFrame.edges.get(i).getY(), GraphFrame.edges.get(i).getX2(), GraphFrame.edges.get(i).getY2());
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD,26 ));
            //(int)Math.sqrt(Math.pow(GraphFrame.edges.get(i).getY()*1.0f, 2.0f) -  Math.pow(GraphFrame.edges.get(i).getY2()*1.0f, 2.0f));
            //(int) Math.sqrt(Math.pow(GraphFrame.edges.get(i).getX()*1.0f, 2.0f) -  Math.pow(GraphFrame.edges.get(i).getX2()*1.0f, 2.0f));
            //System.out.println( Math.sqrt(Math.pow(GraphFrame.edges.get(i).getX()*1.0f, 2.0f) -  Math.pow(GraphFrame.edges.get(i).getX2()*1.0f, 2.0f))+"");
//            int x,y;
//            System.out.println("x1 = " + GraphFrame.edges.get(i).getX() + " x2 = " + GraphFrame.edges.get(i).getX2());
//             System.out.println("y1 = " + GraphFrame.edges.get(i).getY() + " x2 = " + GraphFrame.edges.get(i).getY2());
//            if (GraphFrame.edges.get(i).getX() > GraphFrame.edges.get(i).getX2())
//                x = (int) Math.sqrt(Math.pow(GraphFrame.edges.get(i).getX()*1.0f, 2.0f) -  Math.pow(GraphFrame.edges.get(i).getX2()*1.0f, 2.0f))/2;
//            else
//                x = (int) Math.sqrt(-Math.pow(GraphFrame.edges.get(i).getX()*1.0f, 2.0f) +  Math.pow(GraphFrame.edges.get(i).getX2()*1.0f, 2.0f))/2;
//            System.out.println(x);
//            if (GraphFrame.edges.get(i).getY() > GraphFrame.edges.get(i).getY2())
//                y = (int) Math.sqrt(Math.pow(GraphFrame.edges.get(i).getY()*1.0f, 2.0f) -  Math.pow(GraphFrame.edges.get(i).getY2()*1.0f, 2.0f))/2;
//            else
//                y = (int) Math.sqrt(-Math.pow(GraphFrame.edges.get(i).getY()*1.0f, 2.0f) +  Math.pow(GraphFrame.edges.get(i).getY2()*1.0f, 2.0f))/2;
//            System.out.println(y);
//            g.drawString(GraphFrame.edges.get(i).getWeight()+"",x,y);
        }
        for (int i = 0 ; i < GraphFrame.nodes.size() ; i++){
            g.setColor(GraphFrame.nodes.get(i).getColor());
            g.fillOval(GraphFrame.nodes.get(i).getX(), GraphFrame.nodes.get(i).getY(), 50, 50);   
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD,26 ));
            g.drawString(GraphFrame.nodes.get(i).getName(),GraphFrame.nodes.get(i).getX()+19,GraphFrame.nodes.get(i).getY()+32);

        }
  }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drawing Area", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tw Cen MT Condensed", 1, 24), new java.awt.Color(204, 0, 0))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
