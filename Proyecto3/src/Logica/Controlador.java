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
    
    public void ConeccionAdministradorNuevoUsuario(String eUsuario,String Contrasenia){
        Cliente miCliente = new Cliente(this);
        boolean temp = miCliente.ConeccionAdminNuevoUsuario(eUsuario, Contrasenia);
        
    }
    
    public void ConeccionAdministradorEliminarUsuario(String eUsuario){
        Cliente miCliente = new Cliente(this);
        boolean temp = miCliente.ConeccionAdminEliminarUsuario(eUsuario);
        
    }
    
    public void VentanaAdmin(String[] ListaUsuarios){
        miVentana.dispose();
        miVentana= new FrmClienteAdmin(ListaUsuarios);
        miVentana.setVisible(true);
    }
    
    public void VentanaUsuario(){
        miVentana.dispose();
        miVentana= new FrmClienteVentanaUsuario();
        miVentana.setVisible(true);
    }
    
    public void NuevoUsuarioAceptado(){
        FrmClienteAdmin temp = (FrmClienteAdmin) miVentana;
        temp.NuevoUsuarioAceptado();
    }
    
    public void NuevoUsuarioDenegado(){
        FrmClienteAdmin temp = (FrmClienteAdmin) miVentana;
        temp.NuevoUsuarioDenegado();
    }
    
    public void EliminarUsuarioAceptado(){
        FrmClienteAdmin temp = (FrmClienteAdmin) miVentana;
        temp.EliminarUsuarioAceptado();
    }
    
    public void EliminarUsuarioDenegado(){
        FrmClienteAdmin temp = (FrmClienteAdmin) miVentana;
        temp.EliminarUsuarioDenegado();
    }
    
    public void RegrezarLoging(){
        miVentana.dispose();
        FrmClienteLogin temp = new FrmClienteLogin();
        temp.setVisible(true);
        miVentana = temp;
    }
}
