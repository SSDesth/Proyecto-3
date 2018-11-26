
package HTML;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author manue
 */
public class Archivo {
    FileOutputStream archivo;
    String texto = "<div><h1>Reporte de Tablas</h1><p>Parrafo de prueba</p></div>";
    PrintStream p;
    
    public Archivo(){
        
        Fecha objFecha = new Fecha();
        String fecha = objFecha.getFechaCreacion();
        
        try{
            archivo = new FileOutputStream("reporte.html");
            p = new PrintStream(archivo);
            p.println(texto);
            p.close();
        } catch (FileNotFoundException e){
            System.out.println("No se encontro el archivo");
        }
    }
}
