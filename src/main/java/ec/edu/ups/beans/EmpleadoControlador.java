/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EmpleadoFacade;
import ec.edu.ups.entidades.Empleado;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author ASUS
 */
@Model
public class EmpleadoControlador {
    @EJB
    private EmpleadoFacade empleadoFacade;
    
    @Produces
    @RequestScoped
    @Named("listadoEmpleados")
    public List<Empleado>listarEmpleado(){
        System.out.println("Listar empleados por nivel: ");
        List<Empleado> empleado = empleadoFacade.getEmpleado();
        return empleado;
    }
}
