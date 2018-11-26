package datos;

import Logica.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ssdesth
 */
public class ConeccionMariaSQL {

    public boolean ConeccionMaria(String entradaComando, String dbName) {
        try {
            System.out.println("Aqui");
            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/" + dbName,
                    "root",
                    "7410");
            System.out.println("Aqui2");
            //AQUI SE CREA LA FORMA DE COMUNICAR CON MARIADB
            Statement stmt = con.createStatement();
            // INJECT STATEMENT STRING
            //EL STRING DENTRO DE ESTE METODO ES LA MANERA DE 
            //INYECTAR CODIGO YT PODER USAR L;A BASE DE DATOS
            
            stmt.executeUpdate(entradaComando);
            //stmt.executeUpdate("DROP DATABASE db1");  
            stmt.close();
            con.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", 2);
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public String ConeccionMariaDatos(String entradaComando, String dbName, String busqueda) throws SQLException {
        String salida = "";
        //SE CREA LA CONECCION CON LA BASE DE DATOS
        Connection con = DriverManager.getConnection(
                "jdbc:mariadb://localhost/" + dbName,
                "root",
                "7410");

        //AQUI SE CREA LA FORMA DE COMUNICAR CON MARIADB
        Statement stmt = con.createStatement();
        // INJECT STATEMENT STRING
        //EL STRING DENTRO DE ESTE METODO ES LA MANERA DE 
        //INYECTAR CODIGO YT PODER USAR L;A BASE DE DATOS
        //stmt.executeUpdate(entradaComando);
        //stmt.executeUpdate("DROP DATABASE db1");  
        System.out.println(entradaComando);
        ResultSet rs = stmt.executeQuery(entradaComando);
        while (rs.next()) {
            salida += rs.getString(busqueda) + ",";
        }

        stmt.close();
        con.close();
        return salida;

    }

    public boolean ConeccionMaria(String entradaComando) {
        return ConeccionMaria(entradaComando, "");
    }

    public boolean EliminarBase(String nombreBase) {
        String Comando = "DROP DATABASE " + nombreBase;
        return ConeccionMaria(Comando);
    }

    public boolean CrearBase(String nombreBase) {
        String Comando = "CREATE DATABASE " + nombreBase;
        System.out.println(Comando);
        return ConeccionMaria(Comando);
    }

    public String MostrarBases() {
        String Comando = "SHOW DATABASES;";
        try {
            return ConeccionMariaDatos(Comando, "", "Database");
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionMariaSQL.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public boolean CrearTabla(String nombreBase, String tabla) {
        String Comando = "Create Table ";
        Comando += tabla;
        return ConeccionMaria(Comando, nombreBase);
    }

    public String mostrarTablas(String nombreBase){
        String Salida="";
        String comando="SHOW TABLES;";
        try {
            Salida=ConeccionMariaDatos(comando, nombreBase, "table_name");
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionMariaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(Salida);
        return Salida;
    }
    
    public boolean AgregarInformacionTabla(String nombreBase,String tabla,
            String informacion){
        
        String comando="INSERT INTO ";
        comando+=tabla+" VALUES ";
        comando+= informacion;
        return ConeccionMaria(comando, nombreBase);
        
    }
    
    public boolean EliminarTabla(String nombreBase,String nombreTabla){
        
        String comando="DROP TABLE "+nombreTabla+";";
        return ConeccionMaria(comando,nombreBase);
        
    }
    
    
    public boolean CambiarNombre(String nombreBase,String nombreTabla,String nuevoNombre){
        String comando="RENAME TABLE "+ nombreTabla+" TO "+nuevoNombre+";";
        return ConeccionMaria(comando, nombreBase);
    }
    
    public String EstructuraTabla(String base,String tabla,String consulta){
        
        String comando="SHOW COLUMNS FROM "+ tabla+";";
        
        try {
            /*
                Type da el tipo de campo
                Null da si o no si el campo es requerido
             */
            return ConeccionMariaDatos(comando, base, consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ConeccionMariaSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    
    
}
