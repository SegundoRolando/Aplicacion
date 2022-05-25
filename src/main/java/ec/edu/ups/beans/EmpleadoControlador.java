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
    private Long id;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Empleado.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        empleadoFacade.eliminar(id);
        return "Empleado.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Empleado empleado(){
        if(id != null && id > 0){
            empleadoFacade.opcional(id).ifPresent(e ->{
                this.empleado = e;
            });
        }
        return empleado;
    }
    
    public String editar(Long id){
        this.id = id;
        if(id != null && id > 0){
            empleadoFacade.opcional(id).ifPresent(e ->{
                    this.empleado = e;
            });
        }
        return "NuevoEmpleado.xhtml";
    }
    
}
