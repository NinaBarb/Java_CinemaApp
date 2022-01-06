/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myproject1;

import hr.myProject.dal.MovieRepository;
import hr.myProject.dal.sql.RepositoryFactory;
import hr.myProject.model.XMLData;
import hr.myUilities.utils.FileUtils;
import hr.myUilities.utils.MessageUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Nina
 */
public class AdminFrame extends javax.swing.JFrame {

    private static final String ADMIN = "Admin";
    
    private MovieRepository repository;
    
    /**
     * Creates new form MainFrame
     */
    public AdminFrame() {
        initComponents();
        initPanels();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpContainer = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        miDownloadXML = new javax.swing.JMenuItem();
        miExit = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu2.setText("Options");

        miDownloadXML.setText("Download XML");
        miDownloadXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDownloadXMLActionPerformed(evt);
            }
        });
        jMenu2.add(miDownloadXML);

        miExit.setText("Log out");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        jMenu2.add(miExit);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miDownloadXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDownloadXMLActionPerformed
        try {
            if (repository == null) {
                initRepository();

            }
            FileUtils.saveToXml(new XMLData(repository.selectMovies()));
        } catch (JAXBException ex) {
            Logger.getLogger(UserFrame.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showInformationMessage("Error", "Couldn't download XML to file!");
        } catch (Exception ex) {
            Logger.getLogger(UserFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_miDownloadXMLActionPerformed

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            new LoginDialog(this, rootPaneCheckingEnabled).setVisible(true);
        });
        dispose();
    }//GEN-LAST:event_miExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miDownloadXML;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JTabbedPane tpContainer;
    // End of variables declaration//GEN-END:variables

    private void initPanels() {
        tpContainer.add(ADMIN, new AdminCardLayoutPanel());
    }

    private void initRepository() {
        repository = RepositoryFactory.getSqlMovieRepository();
    }
}
