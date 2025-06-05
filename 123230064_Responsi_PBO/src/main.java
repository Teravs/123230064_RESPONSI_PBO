/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import view.ViewInput;
import controller.ControllerTransaksi;
/**
 *
 * @author Lab Informatika
 */

//start programmnya
public class main {
    public static void main(String[] args) {
        ViewInput view = new ViewInput();
        ControllerTransaksi controller = new ControllerTransaksi(view);
        view.setVisible(true);
        
    }
}
