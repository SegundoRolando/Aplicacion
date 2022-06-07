/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.entidades.Cliente;
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
    private String nombres;
    
    @Produces
    @Model
    public String tituloCliente() {
        return "CRUD de Clientes";
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoClientes")
    public List<Cliente>listarCliente(){
        List<Cliente> cli = clienteFacade.getCliente();
        return cli;
    }
    
    public String guardar(){
        try {
            this.clienteFacade.guardarCliente(cliente);
        } catch (Exception e) {
        }
        return "Clientes.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        clienteFacade.eliminar(id);
        return "Clientes.xhtml?faces-redirect=true";
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
        return "Formulario.xhtml";
    }
    
    public static boolean validarCedula(String cedula) {
        char digito[] = cedula.toCharArray();
        int total = 0;
        for (int i = 0; i < digito.length - 1; i++) {
            int dato = Integer.parseInt(digito[i] + "");
            if (i % 2 == 0) {
                //dato = dato*2 > 9 ? dato*2 - 9 : dato*2;
                if (dato * 2 > 9) {
                    dato = (dato * 2) - 9;
                } else {
                    dato = dato * 2;
                }
            }
            total += dato;
        }
        int ultimo = Integer.parseInt(digito[digito.length - 1] + "");
        if (total % 10 == 0 && 0 == ultimo) {
            return true;
        } else {
            total = 10 - total % 10;
            if (total == ultimo) {
                return true;
            }
        }
        return false;
    }
    
    public void buscarPorNombre(String nombre){
        System.out.println("*************************************************"+ nombre);
        Cliente cli = clienteFacade.getClienteByName(nombre);
        System.out.println("*************************************************"+ cli.getNombre()+" "+cli.getApellido());
        nombres=(cli.getNombre()+" "+cli.getApellido());
    }
    
}