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
@Model
public class SucursalControlador {
    @EJB
    private SucursalFacade sucursalFacade;
    private Sucursal sucursal;
    private Long id;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
            sucursal = new Sucursal();
        }catch (Exception e){            
        }
        return "Sucursal.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        sucursalFacade.eliminar(id);
        return "Sucursal.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Sucursal sucursal(){
        if(id != null && id > 0){
            sucursalFacade.opcional(id).ifPresent(s ->{
                this.sucursal = s;
            });
        }
        return sucursal;
    }
    
    public String editar(Long id){
        this.id = id;
        if(id != null && id > 0){
            sucursalFacade.opcional(id).ifPresent(s ->{
                this.sucursal = s;
            });
        }
        return "CrearSucursal.xhtml";
    }
    
}
