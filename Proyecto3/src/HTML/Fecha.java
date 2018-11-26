
package HTML;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Manuel Arias
 * @since 24/11/18
 * @version 1.0
 */
public class Fecha {
    private Date fechaCreacion;
    
    public Fecha(){
        setFechaCreacion();
    }
    
    public void setFechaCreacion(){
        Calendar calendario;
        calendario = Calendar.getInstance();
        fechaCreacion = calendario.getTime();
    }
    
    public String getFechaCreacion(){
        SimpleDateFormat mascara = new SimpleDateFormat("dd/MM/yy");
        return mascara.format(fechaCreacion);
    }
}
