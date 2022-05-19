
package ec.edu.ups.beans;



import ec.edu.ups.ejb.ProveedorFacade;
import ec.edu.ups.entidades.Proveedor;
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
public class ProveedorControlador {
    @EJB
    private ProveedorFacade proveedorFacade;
    private Proveedor proveedor;
    private int codigo;
    
    @Produces    
    @Model
    public String titulo() {
        return "CRUD Proveedor";
    }
    
    @PostConstruct
     public void init() {
        this.proveedor= new Proveedor();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    @Produces
    @RequestScoped
    @Named("Proveedores")
    public List<Proveedor> listaProveedor() {
        List<Proveedor> prod = proveedorFacade.listar();
        return prod;
    }
    
    
    public String guardar(){
        try {
            this.proveedorFacade.guardar(proveedor);
            proveedor = new Proveedor();
        } catch (Exception e) {
        }
        return "CrudProveedor.xhtml?faces-redirect=true";
    }
    
    public String eliminar(int codigo){
        proveedorFacade.eliminar(codigo);
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    
    @Produces
    @Model
    public Proveedor proveedor() {
        if (codigo > 0) {
            proveedorFacade.opcional(codigo).ifPresent(p -> {
                this.proveedor = p;
            });
        }
        return proveedor;
    }
    
    public String editar(int codigo){
        this.codigo = codigo;
        
        if (codigo > 0) {
            proveedorFacade.opcional(codigo).ifPresent(p -> {
                this.proveedor = p;
            });
        }
        return "CrudEmpleados.xhtml?faces-redirect=true";
    }
    
    
}
