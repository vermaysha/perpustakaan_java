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
import entity.BukuEntity;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import model.BukuTableModel;

/**
 *
 * @author ashary
 */
public class Buku extends javax.swing.JFrame {
    private final Connection db;
    
    /**
     * Creates new form Buku
     */
    public Buku() {
        initComponents();
        db = (new Koneksi()).getCon();
        
        changeInputState(false);
        changeBtnState(false, "");
        showData();
        showCategory();
        showPublisher();
    }
    
    private void showCategory() {
        try (Statement stat = db.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM `kategori`");
            kategoriBukuTxt.removeAllItems();
            while (rs.next()) {
                kategoriBukuTxt.addItem(rs.getString("kd_kategori") + " - " + rs.getString("nm_kategori"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchCategory(String category) {
        try (PreparedStatement stat = db.prepareStatement("SELECT * FROM `kategori` WHERE `nm_kategori` LIKE ?")) {
            stat.setString(1, "%"+ category +"%");
            ResultSet rs = stat.executeQuery();
            kategoriBukuTxt.removeAllItems();
            while (rs.next()) {
                kategoriBukuTxt.addItem(rs.getString("kd_kategori") + " - " + rs.getString("nm_kategori"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showPublisher() {
        try (Statement stat = db.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM `penerbit`");
            penerbitBukuTxt.removeAllItems();
            while (rs.next()) {
                penerbitBukuTxt.addItem(rs.getString("kd_penerbit") + " - " + rs.getString("nm_penerbit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void searchPublisher(String penerbit) {
        try (PreparedStatement stat = db.prepareStatement("SELECT * FROM `penerbit` WHERE `nm_penerbit` LIKE ?")) {
            stat.setString(1, "%"+ penerbit +"%");
            ResultSet rs = stat.executeQuery();
            penerbitBukuTxt.removeAllItems();
            while (rs.next()) {
                penerbitBukuTxt.addItem(rs.getString("kd_penerbit") + " - " + rs.getString("nm_penerbit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showData() {
        List<BukuEntity> kat = new ArrayList<>();
        try (PreparedStatement ps = db.prepareStatement("SELECT `buku`.*, `kategori`.`nm_kategori`, `penerbit`.`nm_penerbit` FROM `buku` INNER JOIN `kategori` ON `buku`.`kd_kategori` = `kategori`.`kd_kategori` INNER JOIN `penerbit` ON `buku`.`kd_penerbit` = `penerbit`.`kd_penerbit` ")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BukuEntity buku = new BukuEntity();
                buku.setKode(rs.getString("kd_buku"));
                buku.setJudul(rs.getString("judul"));
                buku.setKategori(rs.getString("nm_kategori"));
                buku.setPenerbit(rs.getString("nm_penerbit"));
                buku.setIsbn(rs.getString("isbn"));
                buku.setPengarang(rs.getString("pengarang"));
                buku.setHalaman(rs.getString("halaman"));
                buku.setJumlah(rs.getString("jumlah"));
                buku.setThTerbit(rs.getString("th_terbit"));
                kat.add(buku);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        BukuTableModel tabelBuku = new BukuTableModel(kat);
        bukuTbl.setModel(tabelBuku);
    }
        
    private void reset() {
        kodeBukuTxt.setText(null);
        judulBukuTxt.setText(null);
        kategoriBukuTxt.setSelectedIndex(0);
        penerbitBukuTxt.setSelectedIndex(0);
        isbnBukuTxt.setText(null);
        pengarangBukuTxt.setText(null);
        halamanBukuTxt.setText(null);
        jumlahBukuTxt.setText(null);
        thTerbitBukuTxt.setText(null);
    }

    private void changeInputState(boolean b) {
        kodeBukuTxt.setEnabled(b);
        judulBukuTxt.setEnabled(b);
        kategoriBukuTxt.setEnabled(b);
        penerbitBukuTxt.setEnabled(b);
        isbnBukuTxt.setEnabled(b);
        pengarangBukuTxt.setEnabled(b);
        halamanBukuTxt.setEnabled(b);
        jumlahBukuTxt.setEnabled(b);
        thTerbitBukuTxt.setEnabled(b);
        searchKategoriBtn.setEnabled(b);
        searchPenerbitBtn.setEnabled(b);
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
        kodeBukuTxt = new javax.swing.JTextField();
        judulBukuTxt = new javax.swing.JTextField();
        kategoriBukuTxt = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        penerbitBukuTxt = new javax.swing.JComboBox<>();
        isbnBukuTxt = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        pengarangBukuTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        halamanBukuTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jumlahBukuTxt = new javax.swing.JTextField();
        thTerbitBukuTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        bukuTbl = new javax.swing.JTable();
        batalBtn = new javax.swing.JButton();
        simpanBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        newBtn = new javax.swing.JButton();
        hapusBtn = new javax.swing.JButton();
        searchKategoriBtn = new javax.swing.JButton();
        searchPenerbitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buku");
        setLocationByPlatform(true);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Buku");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel2.setText("Kode Buku");

        jLabel3.setText("Judul");

        jLabel4.setText("Kategori");

        jLabel5.setText("Penerbit");

        jLabel6.setText("ISBN");

        jLabel7.setText("Pengarang");

        jLabel8.setText("Halaman");

        jLabel9.setText("Jumlah");

        jLabel10.setText("Tahun Terbit");

        bukuTbl.setModel(new javax.swing.table.DefaultTableModel(
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
        bukuTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bukuTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bukuTbl);

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

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        newBtn.setText("New");
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        hapusBtn.setText("Hapus");
        hapusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnActionPerformed(evt);
            }
        });

        searchKategoriBtn.setText("Search");
        searchKategoriBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchKategoriBtnActionPerformed(evt);
            }
        });

        searchPenerbitBtn.setText("Search");
        searchPenerbitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPenerbitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(batalBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newBtn)
                        .addGap(18, 18, 18)
                        .addComponent(editBtn)
                        .addGap(18, 18, 18)
                        .addComponent(hapusBtn)
                        .addGap(126, 126, 126)
                        .addComponent(simpanBtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kodeBukuTxt)
                            .addComponent(judulBukuTxt)
                            .addComponent(kategoriBukuTxt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(penerbitBukuTxt, 0, 230, Short.MAX_VALUE)
                            .addComponent(isbnBukuTxt))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchKategoriBtn)
                            .addComponent(searchPenerbitBtn))
                        .addGap(178, 178, 178)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 42, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pengarangBukuTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(halamanBukuTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(jumlahBukuTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(thTerbitBukuTxt))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(kodeBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(pengarangBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(judulBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(halamanBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(kategoriBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jumlahBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchKategoriBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(penerbitBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(thTerbitBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPenerbitBtn))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(isbnBukuTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batalBtn)
                    .addComponent(simpanBtn)
                    .addComponent(editBtn)
                    .addComponent(newBtn)
                    .addComponent(hapusBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        String kd_buku = kodeBukuTxt.getText();
        String judul = judulBukuTxt.getText();
        String kd_kategori = kategoriBukuTxt.getSelectedItem().toString().split(" - ")[0];
        String kd_penerbit = penerbitBukuTxt.getSelectedItem().toString().split(" - ")[0];
        String isbn = isbnBukuTxt.getText();
        String pengarang = pengarangBukuTxt.getText();
        String halaman = halamanBukuTxt.getText();
        String jumlah = jumlahBukuTxt.getText();
        String tahun_terbit = thTerbitBukuTxt.getText();
        
        try (Statement stmt = db.createStatement()) {
            stmt.execute("UPDATE `buku` SET "
                    + "`judul` = '"+judul+"', "
                    + "`kd_kategori` = '"+kd_kategori+"', "
                    + "`kd_penerbit` = '"+kd_penerbit+"', "
                    + "`isbn` = '"+isbn+"', "
                    + "`pengarang` = '"+pengarang+"', "
                    + "`halaman` = '"+halaman+"', "
                    + "`jumlah` = '"+jumlah+"', "
                    + "`th_terbit` = '"+tahun_terbit+"' "
                + "WHERE `kd_buku` = '"+kd_buku+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
        bukuTbl.clearSelection();
    }//GEN-LAST:event_editBtnActionPerformed

    private void hapusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnActionPerformed
        String kd_buku = kodeBukuTxt.getText();
        try (Statement stmt = db.createStatement()) {
            stmt.execute("DELETE FROM `buku` WHERE `kd_buku` = '"+kd_buku+"'");
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
        bukuTbl.clearSelection();
    }//GEN-LAST:event_hapusBtnActionPerformed

    private void simpanBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanBtnActionPerformed
        try (PreparedStatement ps = db.prepareStatement("INSERT INTO `buku` VALUES (?,?,?,?,?,?,?,?,?)")) {
            ps.setString(1, kodeBukuTxt.getText());
            ps.setString(2, judulBukuTxt.getText());
            ps.setString(3, kategoriBukuTxt.getSelectedItem().toString().split(" - ")[0]);
            ps.setString(4, penerbitBukuTxt.getSelectedItem().toString().split(" - ")[0]);
            ps.setString(5, isbnBukuTxt.getText());
            ps.setString(6, pengarangBukuTxt.getText());
            ps.setString(7, halamanBukuTxt.getText());
            ps.setString(8, jumlahBukuTxt.getText());
            ps.setString(9, thTerbitBukuTxt.getText());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        showData();
        reset();
        changeInputState(false);
        changeBtnState(false, "simpan");
    }//GEN-LAST:event_simpanBtnActionPerformed

    private void bukuTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bukuTblMouseClicked
        TableModel table = bukuTbl.getModel();
        int row = bukuTbl.getSelectedRow();
        String id = table.getValueAt(row, 0).toString();

        try (PreparedStatement ps = db.prepareStatement("SELECT `buku`.*, `kategori`.`nm_kategori`, `penerbit`.`nm_penerbit` FROM `buku` INNER JOIN `kategori` ON `buku`.`kd_kategori` = `kategori`.`kd_kategori` INNER JOIN `penerbit` ON `buku`.`kd_penerbit` = `penerbit`.`kd_penerbit` WHERE `kd_buku` = ?")) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kodeBukuTxt.setText(rs.getString("kd_buku"));
                judulBukuTxt.setText(rs.getString("judul"));
                kategoriBukuTxt.setSelectedItem(rs.getString("kd_kategori") + " - " + rs.getString("nm_kategori") );
                penerbitBukuTxt.setSelectedItem(rs.getString("kd_penerbit") + " - " + rs.getString("nm_penerbit") );
                isbnBukuTxt.setText(rs.getString("isbn"));
                pengarangBukuTxt.setText(rs.getString("pengarang"));
                halamanBukuTxt.setText(rs.getString("halaman"));
                jumlahBukuTxt.setText(rs.getString("jumlah"));
                thTerbitBukuTxt.setText(rs.getString("th_terbit"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Buku.class.getName()).log(Level.SEVERE, null, ex);
        }
        changeInputState(true);
        changeBtnState(true, "edit/delete");
        kodeBukuTxt.setEnabled(false);
    }//GEN-LAST:event_bukuTblMouseClicked

    private void searchKategoriBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchKategoriBtnActionPerformed
        String cari = JOptionPane.showInputDialog(this, "Kategori :");
        searchCategory(cari);
    }//GEN-LAST:event_searchKategoriBtnActionPerformed

    private void searchPenerbitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPenerbitBtnActionPerformed
        String cari = JOptionPane.showInputDialog(this, "Penerbit :");
        searchPublisher(cari);
    }//GEN-LAST:event_searchPenerbitBtnActionPerformed

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
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalBtn;
    private javax.swing.JTable bukuTbl;
    private javax.swing.JButton editBtn;
    private javax.swing.JTextField halamanBukuTxt;
    private javax.swing.JButton hapusBtn;
    private javax.swing.JFormattedTextField isbnBukuTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField judulBukuTxt;
    private javax.swing.JTextField jumlahBukuTxt;
    private javax.swing.JComboBox<String> kategoriBukuTxt;
    private javax.swing.JTextField kodeBukuTxt;
    private javax.swing.JButton newBtn;
    private javax.swing.JComboBox<String> penerbitBukuTxt;
    private javax.swing.JTextField pengarangBukuTxt;
    private javax.swing.JButton searchKategoriBtn;
    private javax.swing.JButton searchPenerbitBtn;
    private javax.swing.JButton simpanBtn;
    private javax.swing.JTextField thTerbitBukuTxt;
    // End of variables declaration//GEN-END:variables
}
