/*
 * Copyright (C) 2019 ashary
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package app;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import lib.Koneksi;
import entity.UserEntity;
import javax.swing.table.TableModel;
import model.UserTableModel;

/**
 *
 * @author ashary
 */
public class User extends javax.swing.JFrame {
    private final Connection db;

    /**
     * Creates new form User
     */
    public User() {
        initComponents();
        db = (new Koneksi()).getCon();
        
        changeInputState(false);
        changeBtnState(false, "");
        showData();
    }
    
    private void showData() {
        List<UserEntity> kat = new ArrayList<>();
        try (PreparedStatement ps = db.prepareStatement("SELECT * FROM `user`")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserEntity user = new UserEntity();
                user.setKode(rs.getString("kd_user"));
                user.setNama(rs.getString("nm_user"));
                user.setUsername(rs.getString("username"));
                user.setLevel(rs.getString("level"));
                kat.add(user);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        UserTableModel tabelUser = new UserTableModel(kat);
        userTbl.setModel(tabelUser);
    }
    
    private void reset() {
        kodeUserTxt.setText(null);
        namaUserTxt.setText(null);
        usernameUserTxt.setText(null);
        passwordUserTxt.setText(null);
        levelUserTxt.setSelectedItem("Administrator");
        userTbl.clearSelection();
    }

    private void changeInputState(boolean b) {
        kodeUserTxt.setEnabled(b);
        namaUserTxt.setEnabled(b);
        usernameUserTxt.setEnabled(b);
        passwordUserTxt.setEnabled(b);
        levelUserTxt.setEnabled(b);
    }
    
    private void changeBtnState(boolean b, String btn) {
        switch (btn) {
            case "new":
                newBtn.setEnabled(!b);
                batalBtn.setEnabled(b);
                
                hapusBtn.setEnabled(!b);
                editBtn.setEnabled(!b);
                simpanBtn.setEnabled(b);
            break;
            case "edit/delete":
                newBtn.setEnabled(!b);
                batalBtn.setEnabled(b);
                
                hapusBtn.setEnabled(b);
                editBtn.setEnabled(b);
                simpanBtn.setEnabled(!b);
            break;
            case "simpan": 
            default:
                newBtn.setEnabled(!b);
                batalBtn.setEnabled(b);
                
                hapusBtn.setEnabled(b);
                editBtn.setEnabled(b);
                simpanBtn.setEnabled(b);
            break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kodeUserTxt = new javax.swing.JTextField();
        namaUserTxt = new javax.swing.JTextField();
        usernameUserTxt = new javax.swing.JTextField();
        passwordUserTxt = new javax.swing.JPasswordField();
        levelUserTxt = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTbl = new javax.swing.JTable();
        batalBtn = new javax.swing.JButton();
        simpanBtn = new javax.swing.JButton();
        newBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        hapusBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User");

        jLabel2.setText("Kode User");

        jLabel3.setText("Nama User");

        jLabel4.setText("Username");

        jLabel5.setText("Password");

        jLabel6.setText("Level");

        levelUserTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Kepala Perpustakaan", "Petugas" }));

        userTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        userTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTbl);

        batalBtn.setText("Batal");
        batalBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalBtnActionPerformed(evt);
            }
        });

        simpanBtn.setText("Simpan");
        simpanBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanBtnActionPerformed(evt);
            }
        });

        newBtn.setText("New");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        hapusBtn.setText("Hapus");
        hapusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(batalBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hapusBtn)
                        .addGap(92, 92, 92)
                        .addComponent(simpanBtn))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(levelUserTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordUserTxt)
                            .addComponent(namaUserTxt)
                            .addComponent(usernameUserTxt)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kodeUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kodeUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(usernameUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passwordUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(levelUserTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batalBtn)
                    .addComponent(simpanBtn)
                    .addComponent(newBtn)
                    .addComponent(editBtn)
                    .addComponent(hapusBtn))
                .addGap(0, 29, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void batalBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalBtnActionPerformed
        reset();
        changeInputState(false);
        changeBtnState(false, "batal");
    }//GEN-LAST:event_batalBtnActionPerformed

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        changeInputState(true);
        changeBtnState(true, "new");
    }//GEN-LAST:event_newBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        String kd_user = kodeUserTxt.getText();
        String nm_user = namaUserTxt.getText();
        String username = usernameUserTxt.getText();
        String password = passwordUserTxt.getText();
        String level = convertLevel(levelUserTxt.getSelectedItem().toString(), "cb2db");
        try (Statement stmt = db.createStatement()) {
            stmt.execute("UPDATE `user` SET "
            + "`nm_user` = '"+nm_user+"', `username` = '"+username+"', `password` = '"+password+"', `level` = '"+level+"' " 
            + "WHERE `kd_user` = '"+kd_user+"'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
        userTbl.clearSelection();
    }//GEN-LAST:event_editBtnActionPerformed

    private void hapusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnActionPerformed
        String kd_user = kodeUserTxt.getText();
        try (Statement stmt = db.createStatement()) {
            stmt.execute("DELETE FROM `user` WHERE `kd_user` = '"+kd_user+"'");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
        userTbl.clearSelection();
    }//GEN-LAST:event_hapusBtnActionPerformed

    private void simpanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBtnActionPerformed
        try (PreparedStatement ps = db.prepareStatement("INSERT INTO `user` VALUES (?,?,?,?,?)")) {
            ps.setString(1, kodeUserTxt.getText());
            ps.setString(2, namaUserTxt.getText());
            ps.setString(3, usernameUserTxt.getText());
            ps.setString(4, passwordUserTxt.getText());
            ps.setString(5, convertLevel(levelUserTxt.getSelectedItem().toString(), "cb2db"));
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
    }//GEN-LAST:event_simpanBtnActionPerformed

    private void userTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTblMouseClicked
        TableModel table = userTbl.getModel();
        int row = userTbl.getSelectedRow();
        String id = table.getValueAt(row, 0).toString();

        try (PreparedStatement ps = db.prepareStatement("SELECT * FROM `user` WHERE `kd_user` = ?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kodeUserTxt.setText(rs.getString("kd_user"));
                namaUserTxt.setText(rs.getString("nm_user"));
                usernameUserTxt.setText(rs.getString("username"));
                passwordUserTxt.setText(rs.getString("password"));
                levelUserTxt.setSelectedItem(convertLevel(rs.getString("level"), "db2cb"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeInputState(true);
        changeBtnState(true, "edit/delete");
        kodeUserTxt.setEnabled(false);
    }//GEN-LAST:event_userTblMouseClicked

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
            java.util.logging.Logger.getLogger(Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kategori.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new User().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton hapusBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kodeUserTxt;
    private javax.swing.JComboBox<String> levelUserTxt;
    private javax.swing.JTextField namaUserTxt;
    private javax.swing.JButton newBtn;
    private javax.swing.JPasswordField passwordUserTxt;
    private javax.swing.JButton simpanBtn;
    private javax.swing.JTable userTbl;
    private javax.swing.JTextField usernameUserTxt;
    // End of variables declaration//GEN-END:variables

    private String convertLevel(String txt, String way) {
        if (way.equals("cb2db")) {
            switch (txt) {
                case "Administrator":
                    return "admin";
                case "Kepala Perpustakaan":
                    return "kepala";
                default: 
                    return "petugas";
            }
        } else if (way.equals("db2cb")) {
            switch (txt) {
                case "admin":
                    return "Administrator";
                case "kepala":
                    return "Kepala Perpustakaan";
                default: 
                    return "Petugas";
            }
        }
        
        return null;
    }
}