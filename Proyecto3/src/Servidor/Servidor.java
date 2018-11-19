/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import UI.FrmServidor;
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
                /*
                //Le dice al cliente que va a decirle su numero de jugador
                hiloServidor.getSalida().writeInt(0);
                //le envia al cliente su numero de jugador
                hiloServidor.getSalida().writeInt(1);
                */
                
            }
        } catch (IOException ex) {
            System.out.println("A ocurrido un problema con el servidor");
        }
    }

    public void EscribirMensajeServidor(String entrada) {
        ventana.agregarMensaje(entrada);
    }

}
