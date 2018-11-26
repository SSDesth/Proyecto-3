package Logica;

import datos.ConeccionMariaSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static Controlador miControlador;

    public static void main(String[] args) {
        
            /*  miControlador = new Controlador();
            miControlador.IniciarPrograma();
             */
            //SE CREA LA CONECCION CON LA BASE DE DATOS
            
            ConeccionMariaSQL bd= new ConeccionMariaSQL();
            System.out.println(bd.CrearBase("bd1"));
            System.out.println(bd.MostrarBases());
            System.out.println(bd.mostrarTablas("bd1"));
    }

}
