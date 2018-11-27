
package HTML;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
    private String horaCreacion;
    private int hora;
    private int min;
    private int sec;
    
    public Fecha(){
        setFechaCreacion();
        setHoraCreacion();
    }
    
    public void setFechaCreacion(){
        Calendar calendario;
        calendario = Calendar.getInstance();
        fechaCreacion = calendario.getTime();
    }
    
    public String getFechaCreacion(){
        SimpleDateFormat mascara = new SimpleDateFormat("dd-MM-yy");
        return mascara.format(fechaCreacion);
    }
    
    public void setHoraCreacion(){
        Calendar calendario;
        calendario = Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        min = calendario.get(Calendar.MINUTE);
        sec = calendario.get(Calendar.SECOND);
        horaCreacion = hora + "-" + min + "-" + sec;
    }
    
    public String getHoraCreacion(){
        return horaCreacion;
    }
}
