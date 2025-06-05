/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Connector;
import view.ViewInput;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Lab Informatika
 */
public class ControllerTransaksi {
    private ViewInput view; // objek tampilan
    private Connector conn; // koneksi ke db
    
    public ControllerTransaksi(ViewInput view){
    this.view = view;
    conn = new Connector(); //inisialisasi koneksi ke db
    
    loadTable(); // menampilkan data ke tabel saat awal
    //listener untuk memilih baris tabel (harusnya tapi eror mulu akupun bingung jadinya)
    view.tabelTransaksi.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
    @Override
    
    public void ValueChanged(ListSelectionEvent e){
        int row = view.tabelTransaksi.getSelectedRow();
        if(row>=0){
        view.tfNamaPemliki.setText(view.modelTable.getValueAt(row,1).toString());
        view.tfPlat.setText(view.modelTable.getValueAt(row,2).toString());
        view.tfMerk.setText(view.modelTable.getValueAt(row,3).toString());
        view.tfDurasi.setText(view.modelTable.getValueAt(row,4).toString());
            }
        }
    });
    
    view.btnSimpan.addActionListener(e -> simpanData());
    view.btnEdit.addActionListener(e -> editData());
    view.btnHapus.addActionListener(e -> hapusData());
    }
    
    //method load table
    private void loadTable(){
    view.modelTable.setRowCount(0);
    try{
    String query = "SELECT * FROM kendaraan ORDER BY tanggal DESC, id DESC";
    PreparedStatement ps = conn.koneksi.prepareStatement(query);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
    Object[]row = new Object[]{
    rs.getInt("id"), rs.getString("nama_pemilik"), rs.getString("plat_nomor"), rs.getInt("merk_kendaraan"), rs.getInt("durasi"), rs.getInt("total_harga")};
    view.modelTable.addRow(row);
    }
    } catch(Exception ex){
    JOptionPane.showMessageDialog(null, "Gagal Load Data : " + ex.getMessage());
    ex.printStackTrace();
    }
    }
    
    //method simpan data
    private void simpanData(){
        try{
    String namaPemilik = view.tfNamaPemliki.getText();
    String platnomor = view.tfPlat.getText();
    String merkKendaraan = view.tfMerk.getText();
    int durasi = Integer.parseInt(view.tfDurasi.getText());
    
    if(namaPemilik.isEmpty() || platnomor.isEmpty()){
        JOptionPane.showMessageDialog(null, "Nama Pemilik dan Plat harus diisi");
        return;
    }
    if(durasi <= 0){
        JOptionPane.showMessageDialog(null, "Durasi Tidak bisa dibawah 0");
        return;
    }
    
    //perhitungan harga parkir
    int total;
    if(durasi < 5){
        total = durasi * 5000;
    } else {
        total = 20000 + ((durasi - 4)*2000);
    }
    String query = "INSERT INTO kendaraan(nama_pemilik, plat_nomor, merk_kendaraan, durasi, total_harga)VALUES(?,?,?,?,?)";
    PreparedStatement ps = conn.koneksi.prepareStatement(query);
    ps.setString(1, namaPemilik);
    ps.setString(2, platnomor);
    ps.setString(3, merkKendaraan);
    ps.setInt(4, durasi);
    ps.setInt(5, total);
    ps.executeUpdate();
    
    JOptionPane.showMessageDialog(null,"Data Berhasil di Simpan");
    clearInput();
    loadTable();
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"Gagal Simpan Data"+ex.getMessage());
    ex.printStackTrace();
    }
    }
    
    //method edit data
    private void editData(){
    try{
    int selectedRow = view.tabelTransaksi.getSelectedRow();
    if(selectedRow == -1){
    JOptionPane.showMessageDialog(null,"Pilih Data Dari Tabel untuk diedit");
    return;
    }
    int id = (int)view.modelTable.getValueAt(selectedRow, 0);
    
    String namaPemilik = view.tfNamaPemliki.getText();
    String platnomor = view.tfPlat.getText();
    String merkKendaraan = view.tfMerk.getText();
    int durasi = Integer.parseInt(view.tfDurasi.getText());
    
    if(namaPemilik.isEmpty() || platnomor.isEmpty()){
        JOptionPane.showMessageDialog(null, "Nama Pemilik dan Plat harus diisi");
        return;
    }
    if(durasi <= 0){
        JOptionPane.showMessageDialog(null, "Durasi Tidak bisa dibawah 0");
        return;
    }
    
    int total;
    if(durasi < 5){
        total = durasi * 5000;
    } else {
        total = 20000 + ((durasi - 4)*2000);
    }
    
    //query update db
    String query = "UPDATE kendaraan SET nama_pemilik=?, plat_nomor=?,merk_kendaraan=?,durasi=?,total_harga=? WHERE id =?";
    PreparedStatement ps = conn.koneksi.prepareStatement(query);
    ps.setString(1, namaPemilik);
    ps.setString(2, platnomor);
    ps.setString(3, merkKendaraan);
    ps.setInt(4, durasi);
    ps.setInt(5, total);
    ps.setInt(6, id);
    ps.executeUpdate();
    
    JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
    clearInput();
    loadTable();
    }catch(Exception ex){
    JOptionPane.showMessageDialog(null,"Gagal Edit Data"+ex.getMessage());
    ex.printStackTrace();
    }
    }
    
    //method hapus data
    private void hapusData(){
    try{
    int selectedRow = view.tabelTransaksi.getSelectedRow();
    if(selectedRow == -1){
    JOptionPane.showMessageDialog(null,"Pilih Data Dari Tabel untuk dihapus");
    return;
        }
    int id = (int)view.modelTable.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
    if(confirm != JOptionPane.YES_OPTION){
    return;
    }
    
    //query hapus db
    String query = "DELETE FROM kendaraan WHERE id=?";
    PreparedStatement ps = conn.koneksi.prepareStatement(query);
    ps.setInt(1,id);
    ps.executeUpdate();
    
    JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
    clearInput();
    loadTable();
    } catch(Exception ex){
    JOptionPane.showMessageDialog(null,"Gagal Hapus Data"+ex.getMessage());
    ex.printStackTrace();
    }
    }
    
    
    //method clear inputan di jtextfield
    private void clearInput(){
    view.tfNamaPemliki.setText("");
    view.tfPlat.setText("");
    view.tfMerk.setText("");
    view.tfDurasi.setText("");
    view.tabelTransaksi.clearSelection();
    }
}
