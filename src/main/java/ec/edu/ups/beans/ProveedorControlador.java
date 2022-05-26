package ec.edu.ups.beans;

import ec.edu.ups.ejb.ProveedorFacede;
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
    private ProveedorFacede proveedorFacede;
    private Proveedor proveedor;
    private int codigo;
    
    
    @PostConstruct
    public void init() {
        this.proveedor = new Proveedor();
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoProveedor")
    public List<Proveedor>listaProvedor(){
        List<Proveedor> pro = proveedorFacede.getProveedor();
        return pro;
    }
    public String eliminar(int codigo){
        proveedorFacede.eliminar(codigo);
        return "Proveedor.xhtml?faces-redirect=true";
    }
    public String guardarProveedor(){
        try {
            this.proveedorFacede.guardarProveedor(proveedor);
        } catch (Exception e) {
        }
        return "Proveedor.xhtml?faces-redirect=true";
    }
    public String editar(int codigo){
        this.codigo = codigo;
        if(codigo != 0){
            proveedorFacede.opcional(codigo).ifPresent(p ->{
                    this.proveedor = p;
            });
        }
        return "NuevoProveedor.xhtml";
    } 
    
    
}
