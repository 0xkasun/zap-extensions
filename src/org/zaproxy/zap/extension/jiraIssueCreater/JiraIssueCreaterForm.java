/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.zaproxy.zap.extension.jiraIssueCreater;

import com.sun.jersey.core.util.Base64;
import org.apache.log4j.Logger;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.parosproxy.paros.Constant;
import org.parosproxy.paros.view.View;

import javax.naming.AuthenticationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author kasun
 */
public class JiraIssueCreaterForm extends javax.swing.JFrame {


    /**
     * Creates new form JiraIssueCreaterForm
     */

    public JiraIssueCreaterForm() {
        initComponents();
    }
    private Logger log = Logger.getLogger(this.getClass());

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnExport = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cbProjectKeys = new javax.swing.JComboBox();
        cbSelectAssignee = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        cbHigh = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        cbMedium = new javax.swing.JCheckBox();
        cbLow = new javax.swing.JCheckBox();

        jLabel1.setText("Project Key");

        jLabel5.setText("Select Assignee");
        jLabel5.setFocusCycleRoot(true);

        btnExport.setText("Export");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbProjectKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbProjectKeysActionPerformed(evt);
            }
        });

        cbSelectAssignee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSelectAssigneeActionPerformed(evt);
            }
        });

        jPanel1.setForeground(new java.awt.Color(177, 151, 151));

        cbHigh.setText("High");

        jLabel2.setText("Create Issues For");

        cbMedium.setText("Medium");

        cbLow.setText("Low");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(115, 115, 115)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbLow)
                                        .addComponent(cbHigh)
                                        .addComponent(cbMedium))
                                .addContainerGap(168, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(cbHigh))
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMedium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbLow)
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                .addComponent(jLabel5)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(100, 100, 100)
                                                                                .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(31, 31, 31)
                                                                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(0, 3, Short.MAX_VALUE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(92, 92, 92)
                                                                                .addComponent(cbSelectAssignee, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(123, 123, 123)
                                                                .addComponent(cbProjectKeys, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addGap(73, 73, 73))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbProjectKeys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(cbSelectAssignee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnExport)
                                        .addComponent(btnCancel))
                                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed

        String project_key = cbProjectKeys.getSelectedItem().toString().substring(0, cbProjectKeys.getSelectedItem().toString().indexOf(" "));
        String issueList[];
        JiraRestClient jira = new JiraRestClient();
        int issueCount;
        String issue;
        Properties prop = new Properties();
        InputStream input = null;


        try {

            input = new FileInputStream(Constant.getZapHome() + "/cred.properties");
            prop.load(input);
            String auth = new String(Base64.encode(prop.getProperty("jiraUsername") + ":" + prop.getProperty("jiraPass")));
            String BASE_URL = prop.getProperty("jiraUrl");

            if (cbProjectKeys.getSelectedItem().toString() != null &&
                    cbSelectAssignee.getSelectedItem().toString() != null) {

                XmlDomParser xmlParser = new XmlDomParser();
                if(cbHigh.isSelected()|| cbMedium.isSelected()|| cbLow.isSelected()) {
                        issueList = xmlParser.parseXmlDoc(project_key, cbSelectAssignee.getSelectedItem().toString(),
                                cbHigh.isSelected(), cbMedium.isSelected(), cbLow.isSelected()); // parse xml report with filters
                        issueCount = issueList.length; //get the issue count from the preset last index
                    if (issueCount != 0) { //proceed if the issue count is > 1
                        for (int i = 0; i < issueCount; i++) { //create Issues in jira
                            issue = jira.invokePostMethod(auth, BASE_URL + "/rest/api/2/issue", issueList[i]);
                            System.out.println(issue); //TODO remove this apon release
                        }
                        this.dispose();
                        View.getSingleton().showMessageDialog("Done creating issues!!");

                    } else { //abort if the issue count is = 0
                        this.dispose();
                        View.getSingleton().showMessageDialog("No alerts found !!");
                    }

                }else{
                    View.getSingleton().showMessageDialog("Select alert levels to create issues !!");
                }

            }

        } catch (AuthenticationException e) { //authentication faliure

            log.error(e.getMessage(), e);
            View.getSingleton().showWarningDialog("Authentication failed Check credentials ");
            this.dispose();

        } catch (FileNotFoundException e) { //credential file not found ; show the credential form to recreate

            log.error(e.getMessage(), e);
            View.getSingleton().showWarningDialog("Credential file not found !!");
            CredentialForm credForm = new CredentialForm();
            credForm.show();
            this.dispose();

        } catch (IOException e) { //failed to read file

            log.error(e.getMessage(), e);
            this.dispose();
        }


    }//GEN-LAST:event_btnExportActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cbProjectKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbProjectKeysActionPerformed
        String current_project = cbProjectKeys.getSelectedItem().toString().substring(0, cbProjectKeys.getSelectedItem().toString().indexOf(" "));
        this.getAssignees(current_project);

    }//GEN-LAST:event_cbProjectKeysActionPerformed

    private void cbSelectAssigneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSelectAssigneeActionPerformed


    }//GEN-LAST:event_cbSelectAssigneeActionPerformed

    private void getAssignees(String project) { //get a list of assignees for a project, list in combo box cbSelectAssignee
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(Constant.getZapHome() + "/cred.properties");
            prop.load(input);
            String BASE_URL = prop.getProperty("jiraUrl");
            String auth = new String(Base64.encode(prop.getProperty("jiraUsername") + ":" + prop.getProperty("jiraPass")));

            String asignees = JiraRestClient.invokeGetMethod(auth, BASE_URL + "/rest/api/2/user/assignable" +
                    "/multiProjectSearch?projectKeys=" + project);
            JSONArray assigneeArray = new JSONArray(asignees);

            if (cbSelectAssignee.getSelectedItem() != null) { //to stop regenrating users
                cbSelectAssignee.removeAllItems();
            }

            for (int i = 0; i < assigneeArray.length(); i++) {
                JSONObject user = assigneeArray.getJSONObject(i);
                cbSelectAssignee.addItem(user.getString("name"));
            }
            AutoCompleteDecorator.decorate(cbSelectAssignee);
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
        }

    }

    public void listJiraProjects() throws IOException, AuthenticationException { //list all the projects in comboBox cbProjectKeys


        Properties prop = new Properties();
        InputStream input = new FileInputStream(Constant.getZapHome() + "/cred.properties");
        prop.load(input);

        if (!(prop.getProperty("jiraUrl").equals("")) && !(prop.getProperty("jiraUsername").equals(""))
                && !(prop.getProperty("jiraPass").equals(""))) {

            String BASE_URL = prop.getProperty("jiraUrl");
            String auth = new String(Base64.encode(prop.getProperty("jiraUsername") + ":" + prop.getProperty("jiraPass")));

            String projects = JiraRestClient.invokeGetMethod(auth, BASE_URL + "/rest/api/2/project"); // rest call to get the list of projects
            JSONArray projectArray = new JSONArray(projects);

            for (int i = 0; i < projectArray.length(); i++) {
                JSONObject proj = projectArray.getJSONObject(i);
                cbProjectKeys.addItem(proj.getString("key") + " - " + proj.getString("name"));// add the projects to the combobox
            }
            AutoCompleteDecorator.decorate(cbProjectKeys); //enable auto-completion
        } else {
            throw (new AuthenticationException("Login Error !!"));
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(JiraIssueCreaterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JiraIssueCreaterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JiraIssueCreaterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JiraIssueCreaterForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JiraIssueCreaterForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExport;
    private javax.swing.JCheckBox cbHigh;
    private javax.swing.JCheckBox cbLow;
    private javax.swing.JCheckBox cbMedium;
    private javax.swing.JComboBox cbProjectKeys;
    private javax.swing.JComboBox cbSelectAssignee;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
