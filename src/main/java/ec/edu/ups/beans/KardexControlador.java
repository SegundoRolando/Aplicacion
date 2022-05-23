/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.KardexFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Kardex;
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
public class KardexControlador {
    @EJB
    private KardexFacade kardexfacade;
    private Kardex kardex;
    private int id;
    
    @Produces
    @Model
    public String tituloKardex() {
        return "kardex";
    }
    
    @PostConstruct
    public void init() {
        this.kardex = new Kardex();
    }

    public Kardex getKardex() {
        return kardex;
    }

    public void setKardex(Kardex kardex) {
        this.kardex = kardex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }      
        
    @Produces
    @RequestScoped
    @Named("listadoKardex")
    public List<Kardex>listarKardex(){
        System.out.println("Listar Kardex : ");
        List<Kardex> kar = kardexfacade.getKardex();
        return kar;
    }
    
    public String guardar(){
        try {
            this.kardexfacade.guardarKardex(kardex);
        } catch (Exception e) {
        }
        return "CrudKardex.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int id){
        kardexfacade.eliminar(id);
        return "CrudKardex.xhtml?faces-redirect=true";
    }
    
    @Produces
    @Model
    public Kardex kardex(){
        if(id != 0){
            kardexfacade.opcional(id).ifPresent(e ->{
                this.kardex = e;
            });
        }
        return kardex;
    }
    
    public String editar(int id){
        this.id = id;
        if(id != 0){
            kardexfacade.opcional(id).ifPresent(e ->{
                    this.kardex = e;
            });
        }
        return "CrearKardex.xhtml";
    }
    
}
