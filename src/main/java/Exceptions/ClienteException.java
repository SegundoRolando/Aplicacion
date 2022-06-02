/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions;

/**
 *
 * @author PAUL
 */
public class ClienteException extends Exception {
    
    public static String mensaje = "La cedula no es valida";
    
    public ClienteException() {
        super(mensaje);
    }
}
    

