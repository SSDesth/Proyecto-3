/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import LogicaServidor.mainServidor;
import UI.FrmServidor;
import datosServidor.Usuario;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SSDesth
 */
public class Servidor {

    /*----------Variables----------*/
    private DataInputStream entrada;//Para leer comunicacion
    private DataOutputStream salida;//Para enviar comunicacion
    //public ArrayList<threadServidor> hilosserver;
    private Socket cliente1;//Socket del cliente 1

    private FrmServidor ventana;

    //private threadServidor hiloServidor;
    public Servidor(FrmServidor entradaVentana) {
        this.ventana = entradaVentana;
    }

    public void runServer() {
        ServerSocket ss;
        try {
            ss = new ServerSocket(9998);

            while (true) {
                //Queda a la espera del primer cliente
                cliente1 = ss.accept();
                System.out.println("Cliente Conectado");
                
                entrada = new DataInputStream(cliente1.getInputStream());
                salida = new DataOutputStream(cliente1.getOutputStream());

                switch (entrada.readInt()) {
                    //Loging Admin
                    case 0:
                        EscribirMensajeServidor("Intento de coneccion de un Administrador");
                        String Administrador = entrada.readUTF();
                        String ContraseniaAdmin = entrada.readUTF();
                        if (mainServidor.miControlador.ValidarAdministrador(Administrador,
                                ContraseniaAdmin)) {
                            salida.writeInt(1);
                            salida.writeUTF(mainServidor.miControlador.UsuariosActuales());
                            EscribirMensajeServidor("Administrador Autorizado");
                        } else {
                            EscribirMensajeServidor("Administrador NO Autorizado a entrar");
                            salida.writeInt(0);
                        }
                        break;
                    //Loging Usuario
                    case 1:
                        EscribirMensajeServidor("Intento de coneccion de un Usuario");
                        String entradaUsuario = entrada.readUTF();
                        String entradaContrasenia = entrada.readUTF();
                        if (mainServidor.miControlador.ValidarUsuario(entradaUsuario,
                                entradaContrasenia)) {
                            EscribirMensajeServidor("Acceso Permitido");
                            salida.writeInt(1);
                        } else {
                            EscribirMensajeServidor("Acceso Denegado");
                            salida.writeInt(0);
                        }
                        break;
                    //Crear Usuario
                    case 2:
                        EscribirMensajeServidor("Intento de crear un usuario nuevo");
                        String UsuarioNuevo = entrada.readUTF();
                        String ContraseniaNueva = entrada.readUTF();
                        if (mainServidor.miControlador.AgregarUsuario(new Usuario(
                                UsuarioNuevo, ContraseniaNueva))) {
                            EscribirMensajeServidor("Nuevo usuario Creado");
                            salida.writeInt(1);
                        } else {
                            EscribirMensajeServidor("No se pudo crear el nuevo Usuario");
                            salida.writeInt(0);
                        }
                        System.out.println(mainServidor.miControlador.getListaUsuarios().size());

                        break;
                    //Eliminar usuario    
                    case 3:
                        
                        String UsuarioPorEliminar = entrada.readUTF();
                        EscribirMensajeServidor("Intento de eliminar al usuario: "+UsuarioPorEliminar);
                        if(mainServidor.miControlador.EliminarUsuario(UsuarioPorEliminar)){
                            EscribirMensajeServidor("Usuario Eliminado");
                            salida.writeInt(1);
                        }
                        else{
                            EscribirMensajeServidor("Usuario no se pudo eliminar ya que no se encontro");
                            salida.writeInt(0);
                        }
                        
                        break;
                    
                    //Enviar ListaUsuarios    
                    case 10:
                        
                    break;
                }
                /*
                    EscribirMensajeServidor(entrada.readUTF());
                    EscribirMensajeServidor(entrada.readUTF());*/
 /*
                //Le dice al cliente que va a decirle su numero de jugador
                hiloServidor.getSalida().writeInt(0);
                //le envia al cliente su numero de jugador
                hiloServidor.getSalida().writeInt(1);
                 */
                 EscribirMensajeServidor("-------------------------------");
            }
        } catch (IOException ex) {
            System.out.println("A ocurrido un problema con el servidor");
        }
    }

    public void EscribirMensajeServidor(String entrada) {
        ventana.agregarMensaje(entrada);
    }

}
