/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datosServidor;

/**
 *
 * @author SSDesth
 */
public class Usuario {
    private String Usuario;
    private String Contrasena;

    public Usuario() {
    }

    public Usuario(String Usuario, String Contrasena) {
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    @Override
    public String toString() {
        return  Usuario + "," + Contrasena ;
    }
    
    
}
