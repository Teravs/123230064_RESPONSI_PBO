/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.*;

/**
 *
 * @author Lab Informatika
 */
//nyambungin ke db
public class Connector {
    String DBurl="jdbc:mysql//localhost:3306/apotek";
    String DBusername = "root";
    String DBpassword = "";
    
    public Connection koneksi;
    
    public Connector(){
    try{
        Class.forName("com.mysqyl.cj.jdbc.Driver");
        koneksi = DriverManager.getConnection(DBurl,DBusername,DBpassword);
        System.out.println("Koneksi Berhasil");
    } catch(Exception e){
        System.out.println("Koneksi Gagal : " + e.getMessage());
        e.printStackTrace();
    }
    }
}
