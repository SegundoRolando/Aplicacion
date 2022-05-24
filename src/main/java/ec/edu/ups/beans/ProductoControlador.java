
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
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
    private Long id;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            producto = new Producto();
        } catch (Exception e) {
        }
        return "Producto.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
        prodFacade.eliminar(id);
        return "Producto.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            prodFacade.opcional(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return "form.xhtml";
    }
    
    
    
}
