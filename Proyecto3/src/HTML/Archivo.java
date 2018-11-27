
package HTML;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.time.LocalTime;

/**
 *
 * @author Manuel Arias
 * @since 25/11/18
 * @version 1.1
 */
public class Archivo {
    FileOutputStream archivo;
    String texto = "<center><h1>Reporte de Tablas</h1></center>"
            + "<div><h2>Estudiante: </h2>"
            + "<p>Cedula: <br />"
            + "Nombre: <br />"
            + "Direccion: <br />"
            + "Telefono: </p></div>"
            + "<div><footer><p><h4>Creado por: </h4>"
            + "-Carlos Montero <br />"
            + "-Manuel Arias Medina <br />"
            + "-Justin Bogantes Rodriguez</p></footer></div>";
    PrintStream p;
    
    public Archivo(){
        
        Fecha objFecha = new Fecha();
        String fecha = objFecha.getFechaCreacion();
        String hora = objFecha.getHoraCreacion();
        
        String nombre = "reporte "+fecha+" a las "+hora+".html";
        
        try{
            archivo = new FileOutputStream(nombre);
            p = new PrintStream(archivo);
            p.println(texto);
            p.close();
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
    }
    
    public Archivo(String tabla,String info){
        
        texto = "<center><h1>Reporte de Tablas</h1></center>"
            + "<div><h2>Nombre de la tabla: </h2>"
            + "<p>" +tabla+" </p></div>"
            + "<div><footer><p><h4>Informacion de la tabla: </h4>"
            +  info + "<br />"
            + "</p></footer></div>";
        
        Fecha objFecha = new Fecha();
        String fecha = objFecha.getFechaCreacion();
        String hora = objFecha.getHoraCreacion();
        
        String nombre = "reporte "+fecha+" a las "+hora+".html";
        
        try{
            archivo = new FileOutputStream(nombre);
            p = new PrintStream(archivo);
            p.println(texto);
            p.close();
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
    }
}
