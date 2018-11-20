/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Cliente.Cliente;
import UI.FrmClienteAdmin;
import UI.FrmClienteLogin;
import UI.FrmClienteVentanaUsuario;
import javax.swing.JFrame;

/**
 *
 * @author SSDesth
 */
public class Controlador {
    private JFrame miVentana;
    
    
    public Controlador() {
    }
    
    
    public void IniciarPrograma(){
        miVentana= new FrmClienteLogin();
        miVentana.setVisible(true);
        
    }
    
    public void ConeccionCliente(String eUsuario,String Contrasenia){
        Cliente miCliente = new Cliente(this);
        boolean temp = miCliente.ConeccionUsuario(eUsuario, Contrasenia);
        
    }
    
    public void VentanaAdmin(){
        miVentana.dispose();
        miVentana= new FrmClienteAdmin();
        miVentana.setVisible(true);
    }
    
    public void VentanaUsuario(){
        miVentana.dispose();
        miVentana= new FrmClienteVentanaUsuario();
        miVentana.setVisible(true);
    }
    
    
}
