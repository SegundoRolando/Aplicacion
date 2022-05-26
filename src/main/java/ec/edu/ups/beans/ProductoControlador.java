
package ec.edu.ups.beans;

import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Producto;
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
 * @author rolan
 */
@Model
public class ProductoControlador {
    @EJB
    private ProductoFacade prodFacade;
    private Producto producto;
    private Long id;
    private Sucursal sucursal;
    
    @Produces
    @Model
    public String titulo() {
        return "GESTIÓN DE PRODUCTOS";
    }
    
    @PostConstruct
    public void init() {
        this.producto = new Producto();
        this.sucursal = new Sucursal();
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

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
            producto.setSucursal(sucursal);
            this.prodFacade.guardar(producto);
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
