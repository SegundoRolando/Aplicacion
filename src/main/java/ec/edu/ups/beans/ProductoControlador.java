/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

/**
 *
 * @author rolan
 */
@Model
public class ProductoControlador {
    @EJB
    private ProductoFacade prodFacade;
    private Producto producto;
    
    @Produces
    @Model
    public String titulo() {
        return "CRUD PRODUCTO";
    }
    
    @PostConstruct
    public void init() {
        this.producto = new Producto();
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoProductos")
    public List<Producto> listarProductos() {
        List<Producto> prod = prodFacade.listar();
        return prod;
    }
    
    public String guardar(){
        try {
            this.prodFacade.guardar(producto);
        } catch (Exception e) {
        }
        return "index.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id) {
//        Producto producto = prodFacade.remove(id);
//        em.remove(producto);
        return "index.xhtml?faces-redirect=true";
    }
    
    
    
}
