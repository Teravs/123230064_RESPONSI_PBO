/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 *
 * @author Lab Informatika
 */
public class ViewInput extends JFrame{
    //inisiasi texfield
    public JTextField tfNamaPemliki = new JTextField();
    public JTextField tfPlat = new JTextField();
    public JTextField tfMerk = new JTextField();
    public JTextField tfDurasi = new JTextField();
    
    public JButton btnSimpan = new JButton("Simpan");
    public JButton btnEdit = new JButton("Edit");
    public JButton btnHapus = new JButton("Hapus");
    
    public JTable tabelTransaksi;
    public DefaultTableModel modelTable;
    //persetingan tempat dsb
    public ViewInput(){
    setTitle("Transaksi prakir Mall XYZ");
    setSize(700,450);
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout(10,10));
    
    JPanel panelinput = new JPanel(new GridLayout(5,2,5,5));
    panelinput.add(new JLabel("Nama Pemilik"));
    panelinput.add(tfNamaPemliki);
    panelinput.add(new JLabel("Plat Nomor"));
    panelinput.add(tfPlat);
    panelinput.add(new JLabel("Merk Kendaraan"));
    panelinput.add(tfMerk);
    panelinput.add(new JLabel("Durasi Parkir"));
    panelinput.add(tfDurasi);
    
    panelinput.add(btnSimpan);
    panelinput.add(btnEdit);
    
    add(panelinput,BorderLayout.NORTH);
    modelTable = new DefaultTableModel(new String[]{"Pemilik","Plat Nomor", "Merk", "Durasi","Harga Total"}, 0){
    @Override
    public boolean isCellEditable(int row, int column){
    return false;
    }
    };
    tabelTransaksi = new JTable(modelTable);
    JScrollPane scrollPane = new JScrollPane(tabelTransaksi);
    add(scrollPane,BorderLayout.CENTER);
    
    JPanel panelHapus = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    panelHapus.add(btnHapus);
    add(panelHapus,BorderLayout.SOUTH);
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
