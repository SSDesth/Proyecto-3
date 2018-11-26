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
import datos.ConeccionMariaSQL;
import java.util.*;
import javax.swing.JFrame;

/**
 *
 * @author SSDesth
 */
public class Controlador {
    private JFrame miVentana;
    private ConeccionMariaSQL miSQL;
    
    
    public Controlador() {
        miSQL= new ConeccionMariaSQL();
    }
    
    
    public void IniciarPrograma(){
        miVentana= new FrmClienteLogin();
        miVentana.setVisible(true);
        
    }
    
    /*----------metodos del servidor---------*/
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
    
    public boolean AgregarDatabase(String nombre){
        return miSQL.CrearBase(nombre);
    
    }
    
    public String MostrarEstructura(String Base,String tabla){
        String salida= "";
        String nombre = miSQL.EstructuraTabla(Base, tabla,"Field");
        String estructura = miSQL.EstructuraTabla(Base, tabla,"Type");
        String obligatorio = miSQL.EstructuraTabla(Base, tabla,"Null");
       // System.out.println(Estructura);
        
       String[] temporal = nombre.split(",");
       String[] temporal2 = estructura.split(",");
       String[] temporal3 = obligatorio.split(",");
        
        for(int i=0; i<temporal.length;i++){
            salida += temporal[i]+"(";
            switch(temporal2[i]){
                case "int":
                    salida += "int,";
                    break;
                 
                case "varchar(255)":
                    salida += "String,";
                    break;
                 
                case "float(8.4)":
                    salida += "Double,";
                    break;
                 
                case "boolean":
                    salida += "boolean,";
                    break;
                 
            }
            if(temporal3[i].equals("NO")){
                salida += "Obligatorio)";
            }
            else{
                salida += " No Obligatorio)";
            }
            
            if(i!=temporal.length-1){
                salida += ",";
            }
            
        }
        
        return salida;
    }
    
    public List<String> MostrarTablas(String Base){
        List<String> salida= new ArrayList();
        String bases= miSQL.mostrarTablas(Base);
        System.out.println(bases);
        String[] temporal= bases.split(",");
        
        for(String aux:temporal){
            salida.add(aux);
        }
        
        return salida;
    }
    
    public List<String> MostrarBases(){
        List<String> salida= new ArrayList();
        String bases= miSQL.MostrarBases();
        System.out.println(bases);
        String[] temporal= bases.split(",");
        for(String aux:temporal){
            if(aux.equals("information_schema") ||
                    aux.equals("mysql") ||
                    aux.equals("performance_schema") ||
                    aux.equals("sys") ){
                //estas bases no se accesan no estan permitidas
            }
            else{
                salida.add(aux);
            }
        }
        
        return salida;
    }
    
    public boolean AgregarTabla(String base,String tabla){
        return miSQL.CrearTabla(base, tabla);
    }
    
    public boolean EliminarTabla(String base,String tabla){
        return miSQL.EliminarTabla(base, tabla);
    }
    
    public boolean CambiarNombreTabla(String base,String tabla,String nuevoNombre){
        return miSQL.CambiarNombre(base, tabla, nuevoNombre);
    }
    public boolean AgregarInformacionTabla(String base,String tabla,String informacion){
        return miSQL.AgregarInformacionTabla(base, tabla, informacion);
    }
}
