/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaServidor;

/**
 *
 * @author SSDesth
 */

public class mainServidor {
    public static  ControladorServidor miControlador = new ControladorServidor();   
    
    public static void main(String[] args) {
       miControlador.IniciarServidor();
        
        
    }
    
}
