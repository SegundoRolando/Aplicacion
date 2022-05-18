/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.EmpleadoFacade;
import ec.edu.ups.entidades.Empleado;
import jakarta.annotation.PostConstruct;
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
    private Empleado empleado;
    private int id;
    
    @Produces
    @Model
    public String tituloEmpleado() {
        return "CRUD de Empleados";
    }
    
    @PostConstruct
    public void init() {
        this.empleado = new Empleado();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }      
        
    @Produces
    @RequestScoped
    @Named("listadoEmpleados")
    public List<Empleado>listarEmpleado(){
        System.out.println("Listar empleados por nivel: ");
        List<Empleado> emp = empleadoFacade.getEmpleado();
        return emp;
    }
    
    public String guardar(){
        try {
            this.empleadoFacade.guardarEmpleado(empleado);
        } catch (Exception e) {
        }
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        empleadoFacade.eliminar(id);
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Empleado empleado(){
        if(id != 0){
            empleadoFacade.opcional(id).ifPresent(p ->{
                this.empleado = p;
            });
        }
        return empleado;
    }
    
}
