/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaServidor;

import UI.FrmServidor;
import datosServidor.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author SSDesth
 */
public class ControladorServidor {

    private JFrame miVentana;
    private List<Usuario> ListaUsuarios;

    public ControladorServidor() {
        this.ListaUsuarios = new ArrayList<Usuario>();
    }

    public JFrame getMiVentana() {
        return miVentana;
    }

    public void setMiVentana(JFrame miVentana) {
        this.miVentana = miVentana;
    }

    public List<Usuario> getListaUsuarios() {
        return ListaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> ListaUsuarios) {
        this.ListaUsuarios = ListaUsuarios;
    }
    
    

    public void IniciarServidor() {
        //ListaUsuarios.add(new Usuario("Admin", "123Adm$"));
        DesencriptadorPorLetra(EncriptadorPorLetra("?$#@!HOLA995"));
        CargarArchivo();
        EscribirArchivo();
        
        System.out.println(ListaUsuarios.get(0).toString());
        
        FrmServidor temp = new FrmServidor();
        temp.setVisible(true);
        temp.IniciarServidor();
        miVentana=temp;
        
    }

    public void CargarArchivo() {
        try {
            File archivo = new File("src/ArchivosServidor/Users.txt");
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String Linea;
            while ((Linea = br.readLine()) != null) {
                System.out.println(Linea);
                String[] Separador = Linea.split(",");
                ListaUsuarios.add(new Usuario(
                        DesencriptadorPorLetra(Separador[0]),
                        DesencriptadorPorLetra(Separador[1])));
            }
            //cierra el archivo
            fr.close();
            System.out.println("Cant Usuarios " + ListaUsuarios.size());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void EscribirArchivo() {

        try {
            FileWriter fichero = new FileWriter("src/ArchivosServidor/Users.txt");
            PrintWriter pw = new PrintWriter(fichero);
            for (Usuario aux : ListaUsuarios) {
                String salida = "";
                salida += EncriptadorPorLetra(aux.getUsuario());
                salida += ",";
                salida += EncriptadorPorLetra(aux.getContrasena());
                pw.println(salida);
            }
            fichero.close();
            System.out.println("Cant Usuarios " + ListaUsuarios.size());
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public String EncriptadorPorLetra(String entrada) {
        String salida = "";
        String[] temporal = entrada.split("");
        for (String aux : temporal) {
            char variable = aux.charAt(0);
            variable += 4;
            salida += variable;
        }
        System.out.println(salida);
        return salida;
    }

    public String DesencriptadorPorLetra(String entrada) {
        String salida = "";
        String[] temporal = entrada.split("");
        for (String aux : temporal) {
            char variable = aux.charAt(0);
            variable -= 4;
            salida += variable;
        }
        System.out.println(salida);
        return salida;
    }

    public boolean ValidarUsuario(String eUsuario,String Contrasenia){
        for(Usuario temp:ListaUsuarios){
            if(temp.getUsuario().equals(eUsuario) && 
                    temp.getContrasena().equals(Contrasenia)){
                return true;
            }
        }
        return false;
    }

    public boolean ValidarAdministrador(String eUsuario,String Contrasenia){
        for(Usuario temp:ListaUsuarios){
            if(eUsuario.equals("Admin") && 
                   Contrasenia.equals("123Adm$")){
                return true;
            }
        }
        return false;
    }
    
    public boolean AgregarUsuario(Usuario nuevoUsuario){
        System.out.println("Se quiere agregar a un nuevo usuario");
        for(Usuario temp :ListaUsuarios){
            if(temp.getUsuario().equals(nuevoUsuario.getUsuario())){
                System.out.println("El nuevo usuario fue rechasado");
                return false;
            }
        }
        
        ListaUsuarios.add(nuevoUsuario);
        System.out.println("El nuevo usuario fue agregado");
        EscribirArchivo();
        return true;
    }
    
    public String UsuariosActuales(){
        String salida="";
        for(Usuario temp:ListaUsuarios){
            if(!temp.getUsuario().equals("Admin")){
                salida+=temp.getUsuario()+",";
            }
        }
        System.out.println(salida);
        return salida;
    }
    
    public boolean EliminarUsuario(String UsuarioPorEliminar){
        for(Usuario temp:ListaUsuarios){
            if(temp.getUsuario().equals(UsuarioPorEliminar)){
                
                ListaUsuarios.remove(temp);
                EscribirArchivo();
                return true;
            }
        }
        return false;
    }
    
}
