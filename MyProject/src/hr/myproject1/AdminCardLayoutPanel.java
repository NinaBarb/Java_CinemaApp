/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject1;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

/**
 *
 * @author Nina
 */
public class AdminCardLayoutPanel extends javax.swing.JPanel {

    //private static final Color UNSELECTED = Color.BLACK;
    private static final Color SELECTED = Color.LIGHT_GRAY;

    private static final String PNL_UPLOAD = "pnlUpload";
    
    private CardLayout layout;
    
    /**
     * Creates new form AdminCardLayoutPanel
     */
    public AdminCardLayoutPanel() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        pnlOrder = new javax.swing.JPanel();
        lblMenu = new javax.swing.JLabel();
        lblUpload = new javax.swing.JLabel();
        pnlCards = new javax.swing.JPanel();
        pnlUpload = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        pnlOrder.setBackground(new java.awt.Color(102, 102, 102));

        lblMenu.setFont(new java.awt.Font("Felix Titling", 1, 18)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(51, 51, 0));
        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMenu.setText("Menu");

        lblUpload.setBackground(new java.awt.Color(204, 204, 255));
        lblUpload.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        lblUpload.setForeground(new java.awt.Color(0, 0, 0));
        lblUpload.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUpload.setText("Upload");
        lblUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUploadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlOrderLayout = new javax.swing.GroupLayout(pnlOrder);
        pnlOrder.setLayout(pnlOrderLayout);
        pnlOrderLayout.setHorizontalGroup(
            pnlOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUpload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlOrderLayout.setVerticalGroup(
            pnlOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrderLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(lblMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(lblUpload, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlOrder);

        pnlCards.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlUploadLayout = new javax.swing.GroupLayout(pnlUpload);
        pnlUpload.setLayout(pnlUploadLayout);
        pnlUploadLayout.setHorizontalGroup(
            pnlUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );
        pnlUploadLayout.setVerticalGroup(
            pnlUploadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        pnlCards.add(pnlUpload, "pnlUpload");

        jSplitPane1.setRightComponent(pnlCards);

        add(jSplitPane1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void lblUploadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUploadMouseClicked
        layout.show(pnlCards, PNL_UPLOAD);
        lblUpload.setForeground(SELECTED);
    }//GEN-LAST:event_lblUploadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblUpload;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlOrder;
    private javax.swing.JPanel pnlUpload;
    // End of variables declaration//GEN-END:variables

    private void init() {
        initUploadPnl();
    }

    private void initUploadPnl() {
        layout = (CardLayout) pnlCards.getLayout();
        lblUpload.setForeground(SELECTED);
        
        pnlUpload.setLayout(new BorderLayout());
        pnlUpload.add(new UploadPanel());
    }
}