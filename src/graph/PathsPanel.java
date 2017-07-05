
package graph;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;


public class PathsPanel extends javax.swing.JPanel {

    private ArrayList<ArrayList<String>> paths;
    private ArrayList<ArrayList<String>> finalPaths;
    private ArrayList<String> passingNodes;
    private int[][] matrix;
    private ArrayList<Integer> newCounts;
    private ArrayList<Integer> weights;
    private boolean first;
    
    public PathsPanel() {
        initComponents();
        first = true;
        passingNodes = new ArrayList<>();
        paths = new ArrayList<>();
        finalPaths = new ArrayList<>();
        weights = new ArrayList<>();
    }
    private void computeWeights(){
        int sum = 0;
        for (int k = 0 ; k < finalPaths.size() ; k++){
            for (int i = 0 ; i < finalPaths.get(k).size()-1 ; i++){
                for (int j = 0 ; j < GraphFrame.edges.size() ; j++){
                    if (GraphFrame.edges.get(j).isConnected(finalPaths.get(k).get(i)) && GraphFrame.edges.get(j).isConnected(finalPaths.get(k).get(i+1))){
                        sum += GraphFrame.edges.get(j).getWeight();
                    }
                }
            }
            weights.add(sum);
            sum = 0;
        }
    }
    
    public void addNode(String nodeName){
        startNode.addItem(nodeName);
        endNode.addItem(nodeName);
    }

    private void adjMatrix(){
        matrix = new int[GraphFrame.nodes.size()][GraphFrame.nodes.size()];
        for (int i = 0 ; i < GraphFrame.nodes.size() ; i++){
            matrix[i][i] = 0;
            for (int j = 0 ; j < GraphFrame.nodes.size() ; j++){
                if (GraphFrame.nodes.get(i).isConnected(GraphFrame.nodes.get(j).getName())){
                    for (int k = 0 ; k < GraphFrame.edges.size() ; k++){
                        if (GraphFrame.edges.get(k).checkConnectivity(GraphFrame.nodes.get(i).getName(), GraphFrame.nodes.get(j).getName())){
                            matrix[i][j] = GraphFrame.edges.get(k).getWeight();
                            break;
                        }
                    }
                }
                else
                    matrix[i][j] = 0;
            }
        }
    }
    
    private boolean isPassing(String s){
        for (int i = 0 ; i < passingNodes.size() ; i++){
            if (s.equals(passingNodes.get(i)) || s.equals(GraphFrame.nodes.get(endNode.getSelectedIndex()).getName()))
                return true;
        }
        return false;
    }
    
    private void checkFinalPaths(){
        for (int i = 0 ; i < paths.size() ; i++){
            for (int j = i+1 ; j < paths.size() ; j++){
                if (paths.get(i).equals(paths.get(j))){
                    paths.remove(i);
                    i++;
                }
            }
        }
        for (int i = 0  ; i < paths.size() ; i++){
            if (paths.get(i).size()-1 == passingNodes.size() && !checkEquality(paths.get(i), finalPaths)){
                if (GraphFrame.nodes.get(endNode.getSelectedIndex()).isConnected(paths.get(i).get(paths.get(i).size()-1))){
                    finalPaths.add(new ArrayList<>());
                    copyElements(paths.get(i),finalPaths.get(finalPaths.size()-1),paths.get(i).size());
                    finalPaths.get(finalPaths.size()-1).add(GraphFrame.nodes.get(endNode.getSelectedIndex()).getName());
                }
                paths.remove(i);
            }
            
        }
    }
    
    private boolean checkEquality(ArrayList<String> l1, ArrayList<ArrayList<String>> l2){
        boolean equal = true;
        for (int j = 0 ; j < l2.size() ; j++){
            if (l1.size() == l2.get(j).size()){
                for (int i = 0 ; i < l1.size() ; i++){
                    if (!l1.get(i).equals(l2.get(j).get(i)))
                        equal = false;
                }
                if (equal)
                    return true;
                else
                    equal = true;
            }
        }
        return false;
    }
    
    private void copyElements(ArrayList src, ArrayList dst, int size){
        for(int i = 0 ; i < size ; i++){
            dst.add(src.get(i));
        }
    }
    
    private boolean checkFuture(int i,int j){
        for (int k = j + 1 ; k < newCounts.size() ; k++){
            if (newCounts.get(k) == i || GraphFrame.nodes.get(i).getName().equals(GraphFrame.nodes.get(endNode.getSelectedIndex()).getName()))
                return true;
        }
        return false;
    }
    
    private boolean extendPath(String n, int x, String prev){
        boolean check = true;
        boolean extend = false;
        if (x == 0){
            for (int i = 0 ; i < paths.size() ; i++){
                if (paths.get(i).get(paths.get(i).size()-1).equals(prev)){
                    for (int j = 0 ; j < paths.get(i).size() ; j++){
                        if (n.equals(paths.get(i).get(j)))
                            check = false;
                    }
                    if (check){
                        paths.get(i).add(n);
                        extend = true;
                    }
                    else
                        check = true;
                }
            }
            return extend;
        }
        else {
            int size = paths.size();
            for (int i = 0 ; i < size ; i++){
                check = true;
                if (prev.equals(paths.get(i).get(paths.get(i).size()-2))){
                    for (int j = 0 ; j < paths.get(i).size()-2 ; j++){
                        if (paths.get(i).get(j).equals(n)){
                            check = false;
                            break;
                        }
                            
                    }
                    if (check){
                        paths.add(new ArrayList<>());
                        copyElements(paths.get(i),paths.get(paths.size()-1),paths.get(i).size()-1);
                    }
                }
            }
            extend = extendPath(n, 0, prev);
            return extend;
        }
    }
    
    private void pathsMaker(){
        newCounts = new ArrayList<>();
        int i = startNode.getSelectedIndex();
        newCounts.add(i);
        for (int j = 0 ; j < matrix.length ; j++){
            if (matrix[i][j] > 0){
                if (isPassing(GraphFrame.nodes.get(j).getName())){
                    paths.add(new ArrayList<>());
                    paths.get(paths.size()-1).add(GraphFrame.nodes.get(i).getName());
                    paths.get(paths.size()-1).add(GraphFrame.nodes.get(j).getName());
                    if (!GraphFrame.nodes.get(j).getName().equals(GraphFrame.nodes.get(endNode.getSelectedIndex()).getName()))
                        newCounts.add(j);
                }
            }
        }
        checkFinalPaths();
        int occ = 0;
        boolean extend = false;
        for (int j = 1 ; j < newCounts.size() ; j++){
            i = newCounts.get(j);
            for (int k = 0 ; k < matrix.length ; k++){
                if (matrix[i][k] > 0 && isPassing(GraphFrame.nodes.get(newCounts.get(j)).getName())){
                    if (occ == 0)
                        extend = extendPath(GraphFrame.nodes.get(k).getName(),0,GraphFrame.nodes.get(i).getName());
                    else
                        extend = extendPath(GraphFrame.nodes.get(k).getName(),1,GraphFrame.nodes.get(i).getName());
                    if (extend){
                        if (!checkFuture(k, j))
                            newCounts.add(k);
                        occ++;  
                        extend = false;
                    }
                }
                checkFinalPaths();
            }
            if (paths.isEmpty())
                break;
            occ = 0;
        }
    }
    
    private void showPaths(){
        computeWeights();
        if (finalPaths.isEmpty()){
            JOptionPane.showMessageDialog(null, "There is no existing path!");
        }
        else{
            for (int i = 0 ; i < finalPaths.size() ; i++){
                for (int j = 0 ; j < finalPaths.get(i).size() ; j++){
                    if (j == finalPaths.get(i).size() -1)
                        pathsArea.append(finalPaths.get(i).get(j) + ".");
                    else
                        pathsArea.append(finalPaths.get(i).get(j) + ", ");
                }
                pathsArea.append("  Weight: " + weights.get(i));
                pathsArea.append("\n");
            }
            int x = Collections.min(weights);
            String s = "";
            for (int i = 0 ; i < finalPaths.size() ; i++){
                if (weights.get(i) == x){
                    for (int j = 0 ; j < finalPaths.get(i).size() ; j++){
                        if (j == finalPaths.get(i).size()-1){
                            s = s + finalPaths.get(i).get(j) + ".   ";
                            shortPath.setText(s + "\n");
                        }
                        else
                            s = s + finalPaths.get(i).get(j) + ", ";
                    }
                }
            }
        }
    }
    
    private void addPassingNodes(){
        String s = passNode.getText();
        for (int i = 0 ; i < s.length() ; i++){
            for (int j = 0 ; j < GraphFrame.nodes.size() ; j++){
               if (s.charAt(i) == GraphFrame.nodes.get(j).getName().charAt(0))
                   passingNodes.add(GraphFrame.nodes.get(j).getName());
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        startNode = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        endNode = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        passNode = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pathsArea = new javax.swing.JTextArea();
        shortPath = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paths", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tw Cen MT Condensed", 1, 24), new java.awt.Color(204, 0, 0))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Starring Node:");

        startNode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startNodeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Ending Node:");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Passing by nodes:");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Possible Path(s):");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Shortest Path(s):");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 0, 0));
        jButton1.setText("Show Paths");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pathsArea.setColumns(20);
        pathsArea.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        pathsArea.setRows(5);
        jScrollPane1.setViewportView(pathsArea);

        shortPath.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        shortPath.setForeground(new java.awt.Color(255, 0, 0));
        shortPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shortPathActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passNode, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(endNode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startNode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(shortPath))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endNode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(passNode))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(shortPath)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!first){
            finalPaths.clear();
            matrix = null;
            newCounts.clear();
            passingNodes.clear();
            pathsArea.setText("");
        }
        adjMatrix();
        addPassingNodes();
        pathsMaker();
        showPaths();
        first = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void startNodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startNodeActionPerformed
        
    }//GEN-LAST:event_startNodeActionPerformed

    private void shortPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shortPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shortPathActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> endNode;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField passNode;
    private javax.swing.JTextArea pathsArea;
    private javax.swing.JTextField shortPath;
    private javax.swing.JComboBox<String> startNode;
    // End of variables declaration//GEN-END:variables
}
