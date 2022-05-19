/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.SucursalFacade;
import ec.edu.ups.entidades.Sucursal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author Jose
 */
public class SucursalControlador {
    @EJB
    private SucursalFacade sucursalFacade;
    private Sucursal sucursal;
    private int id;
    
    @Produces
    @Model
    public String tituloSucursal(){
        return "CRUD de Sucursal";
    }
    
    @PostConstruct
    public void init(){
        this.sucursal = new Sucursal();
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoSucursales")
    public List<Sucursal>listarSucursal(){
        System.out.println("Listar sucursales: ");
        List<Sucursal> suc = sucursalFacade.getSucursal();
        return suc;
    }
    
    public String guardar(){
        try{
            this.sucursalFacade.GuardarSucursal(sucursal);
        }catch (Exception e){            
        }
        return "CrudSucursal.xhtml?faces-redirect=true";
    }
    
    public String elmiminar(int id){
        sucursalFacade.eliminar(id);
        return "CrudSucursal.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Sucursal sucursal(){
        if(id != 0){
            sucursalFacade.opcional(id).ifPresent(s ->{
                this.sucursal = s;
            });
        }
        return sucursal;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            sucursalFacade.opcional(id).ifPresent(s ->{
                this.sucursal = s;
            });
        }
        return "CrearSucursal.xhtml";
    }
    
}