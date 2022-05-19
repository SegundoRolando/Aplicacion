/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.EmpleadoFacade;
import ec.edu.ups.entidades.Cliente;
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
 * @author PAUL
 */
@Model
public class ClienteControlador {
    @EJB
    private ClienteFacade clienteFacade;
    private Cliente cliente;
    private int id;
    
    @Produces
    @Model
    public String tituloCliente() {
        return "CRUD de Empleados";
    }
    
    @PostConstruct
    public void init() {
        this.cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }      
        
    @Produces
    @RequestScoped
    @Named("listadoClientes")
    public List<Cliente>listarCliente(){
        System.out.println("Listar empleados por nivel: ");
        List<Cliente> cli = clienteFacade.getCliente();
        return cli;
    }
    
    public String guardar(){
        try {
            this.clienteFacade.guardarCliente(cliente);
        } catch (Exception e) {
        }
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        clienteFacade.eliminar(id);
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Cliente cliente(){
        if(id != 0){
            clienteFacade.opcional(id).ifPresent(e ->{
                this.cliente = e;
            });
        }
        return cliente;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            clienteFacade.opcional(id).ifPresent(e ->{
                    this.cliente = e;
            });
        }
        return "CrearCliente.xhtml";
    }
    
}