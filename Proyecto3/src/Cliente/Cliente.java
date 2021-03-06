/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Logica.Controlador;
import Logica.main;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SSDesth
 */
public class Cliente {

    /*----------Variables----------*/
    private DataInputStream entrada = null;//leer comunicacion
    private DataOutputStream salida = null;//escribir comunicacion
    private Socket cliente = null;//para la comunicacion
    private Controlador micontrolador;

    /*----------Constructor(es)----------*/
    /**
     * Constructor Default de la clase
     */
    public Cliente() {
    }

    /**
     * Constructor sobrecargado de la clase
     *
     * @param eControlador:Controlador
     */
    public Cliente(Controlador eControlador) {
        this.micontrolador = eControlador;
    }

    /*----------Setters y Getters----------*/
    /**
     * Get de la variable entrada
     *
     * @return DataInputStream
     */
    public DataInputStream getEntrada() {
        return entrada;
    }

    /**
     * Set de la variable entrada
     *
     * @param entrada:DataInputStream
     */
    public void setEntrada(DataInputStream entrada) {
        this.entrada = entrada;
    }

    /**
     * Get de la variable salida
     *
     * @return DataOutputStream
     */
    public DataOutputStream getSalida() {
        return salida;
    }

    /**
     * Set de la variable salida
     *
     * @param salida:DataOutputStream
     */
    public void setSalida(DataOutputStream salida) {
        this.salida = salida;
    }

    /**
     * Get de la variable cliente
     *
     * @return Socket
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Set de la variable cliente
     *
     * @param cliente:Socket
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    /**
     * Get de la variable micontrolador
     *
     * @return Controlador
     */
    public Controlador getMicontrolador() {
        return micontrolador;
    }

    /**
     * Set de la variable micontrolador
     *
     * @param micontrolador:Controlador
     */
    public void setMicontrolador(Controlador micontrolador) {
        this.micontrolador = micontrolador;
    }

    /*----------Metodos Especializados----------*/
    /**
     * Metodo encargado de la coneccion con el servidor y ademas inicializar el
     * hilo de ejecucion
     */
    public void coneccion() {
        try {//Manejo de erroroes
            //(IP , Puerto)
            cliente = new Socket("localhost", 9998);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro ningun\n"
                    + "servidor activo", "Alerta", 0);
        }
    }

    /**
     * Metodo encargado de la coneccion con el servidor y ademas inicializar el
     * hilo de ejecucion
     */
    public boolean ConeccionUsuario(String eUsuario, String Contrasenia) {
        try {//Manejo de erroroes
            //(IP , Puerto)
            cliente = new Socket("localhost", 9998);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            if (eUsuario.equals("Admin")) {
                salida.writeInt(0);
                salida.writeUTF(eUsuario);
                salida.writeUTF(Contrasenia);
                if (entrada.readInt() == 1) {
                    System.out.println("Admin Aceptado");
                    String EntadaUsuarios = entrada.readUTF();
                    String[] ListaUsuarios = EntadaUsuarios.split(",");
                    System.out.println(ListaUsuarios);
                    micontrolador.VentanaAdmin(ListaUsuarios);
                } else {
                    System.out.println("Admin Denegado");
                }
                return true;
            } else {
                salida.writeInt(1);
                salida.writeUTF(eUsuario);
                salida.writeUTF(Contrasenia);
                if (entrada.readInt() == 1) {
                    System.out.println("Usuario Aceptado");
                    micontrolador.VentanaUsuario();
                } else {
                    System.out.println("Usuario Denegado");
                }
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro ningun\n"
                    + "servidor activo", "Alerta", 0);
        }
        return false;
    }

    /**
     * Metodo encargado de la coneccion con el servidor y ademas inicializar el
     * hilo de ejecucion
     */
    public boolean ConeccionAdminNuevoUsuario(String eUsuario, String Contrasenia) {
        try {//Manejo de erroroes
            //(IP , Puerto)
            cliente = new Socket("localhost", 9998);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            salida.writeInt(2);
            salida.writeUTF(eUsuario);
            salida.writeUTF(Contrasenia);
            if (entrada.readInt() == 1) {
                System.out.println("Usuario Creado");
                main.miControlador.NuevoUsuarioAceptado();
                return true;
            } else {
                System.out.println("El Usuario no pudo ser creado");
                main.miControlador.NuevoUsuarioDenegado();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro ningun\n"
                    + "servidor activo", "Alerta", 0);
        }
        return false;
    }

    /**
     * Metodo encargado de la coneccion con el servidor y ademas inicializar el
     * hilo de ejecucion
     */
    public boolean ConeccionAdminEliminarUsuario(String eUsuario) {
        try {//Manejo de erroroes
            //(IP , Puerto)
            cliente = new Socket("localhost", 9998);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            salida.writeInt(3);
            salida.writeUTF(eUsuario);

            if (entrada.readInt() == 1) {
                System.out.println("Usuario Eliminado");
                main.miControlador.EliminarUsuarioAceptado();
                return true;
            } else {
                System.out.println("El Usuario no pudo ser Eliminado");
                main.miControlador.EliminarUsuarioDenegado();
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encontro ningun\n"
                    + "servidor activo", "Alerta", 0);
        }
        return false;
    }
}
