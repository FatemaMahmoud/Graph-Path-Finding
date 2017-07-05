
package graph;

import java.awt.Color;
import java.util.ArrayList;

public class GraphFrame extends javax.swing.JFrame {

    public static ToolsPanel tp;
    public static PathsPanel pp;
    private DrawingPanel dp;
    public static  ArrayList<Node> nodes;
    public static ArrayList<Edge> edges;
    
    public GraphFrame() {
        initComponents();
        scndInitComponents();
    }
    
    private void scndInitComponents(){
        this.setTitle("Graph");
        this.setResizable(false);
        this.setBounds(650, 70, 660, 745);
        this.getContentPane().setBackground(Color.WHITE);
        tp = new ToolsPanel();
        tp.setBounds(1, 1, 180, 450);
        this.add(tp);
        pp = new PathsPanel();
        pp.setBounds(1, 455, 640, 240);
        this.add(pp);
        dp = new DrawingPanel();
        dp.setBounds(180, 1, 460, 450);
        this.add(dp);
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }
    
    public static void addNode(Node n){
        nodes.add(n);
        pp.addNode(n.getName());
    }
    
    public static void addEdge(Edge e){
        edges.add(e);
        tp.appConnected(e);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
      
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraphFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraphFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
