
package graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JColorChooser;

public class ToolsPanel extends javax.swing.JPanel {

    public static Node node;
    public static String shape;
    public static int wt;
    
    public ToolsPanel() {
        initComponents();
        //connected.setEnabled(false);
        node = new Node(Color.CYAN,57,50, "");
        shape = "";
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                if (x >= 57 && x <= 107 && y >= 50 && y <= 100){
                    shape = "Node";
                    repaint();
                }
                else if (x >= 48 && x <= 122 && y >= 243 && y <= 249){
                    shape = "UndirectedEdge";
                    repaint();
                }
            }
        });
        
        this.addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){
                int x = e.getX();
                int y = e.getY();
                if (x >= 57 && x <= 107 && y >= 50 && y <= 100){
                    if (!shape.equals("Node")){
                        shape = "Node Entered";
                        repaint();
                    }
                }
                
                else if (x >= 48 && x <= 122 && y >= 240 && y <= 249){
                    if(!shape.equals("UndirectedEdge")){
                        shape = "UndirectedEdge Entered";
                        repaint();
                    }
                }
                
                else if (!(shape.equals("Node")||shape.equals("UndirectedEdge"))){
                    shape = "";
                    repaint();
                }
            }
        });
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(node.getColor());
        g.fillOval(node.getX(), node.getY(), 50, 50);
        if (shape.equals("Node") || shape.equals("Node Entered")){
            g.setColor(Color.RED);
            g.drawRect(56, 49, 52, 52);
        }
        else if (shape.equals("UndirectedEdge") || shape.equals("UndirectedEdge Entered")){
            g.setColor(Color.RED);
            g.drawRect(46, 241, 78, 11);
        }
        g.setColor(Color.BLACK);
        g.fillRect(50, 245, 70, 3);
        if (!node.getName().equals("")){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Tw Cen MT Condensed", Font.BOLD,26));
            g.drawString(node.getName(),75,83);
        }
    }
    
    public void appConnected(Edge e){
        connected.append(e.getConnNode(0) + ", " + e.getConnNode(1) + ": " + e.getWeight() + "\n" );
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        ColorChooser = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        weight = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        connected = new javax.swing.JTextArea();
        wtLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tools", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT Condensed", 1, 24), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("  Node");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel2.setText("Name:");

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        ColorChooser.setBackground(new java.awt.Color(255, 255, 255));
        ColorChooser.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        ColorChooser.setForeground(new java.awt.Color(204, 0, 0));
        ColorChooser.setText("Color ");
        ColorChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorChooserActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel4.setText("Undirected Edge");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jLabel6.setText("Weight:");

        weight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weightActionPerformed(evt);
            }
        });

        connected.setColumns(20);
        connected.setRows(5);
        connected.setMaximumSize(new java.awt.Dimension(100, 2147483647));
        jScrollPane1.setViewportView(connected);

        wtLabel.setBackground(new java.awt.Color(255, 255, 255));
        wtLabel.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 24)); // NOI18N
        wtLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ColorChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(wtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ColorChooser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(weight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        node.setName(name.getText());
        name.setText("");
        repaint();
    }//GEN-LAST:event_nameActionPerformed

    private void ColorChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorChooserActionPerformed
        node.setColor(JColorChooser.showDialog(null, "Choose a color", Color.lightGray));
        repaint();
    }//GEN-LAST:event_ColorChooserActionPerformed

    private void weightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weightActionPerformed
        wt = Integer.parseInt(weight.getText());
        weight.setText("");
        wtLabel.setText(wt + "");
        repaint();
    }//GEN-LAST:event_weightActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ColorChooser;
    private javax.swing.JTextArea connected;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField weight;
    private javax.swing.JLabel wtLabel;
    // End of variables declaration//GEN-END:variables
}
