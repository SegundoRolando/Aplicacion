
package ec.edu.ups.beans;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.entidades.Categoria;
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
public class CategoriaControlador {
    @EJB
    private CategoriaFacade cateFacade;
    private Categoria categoria;
    private Long id;
   
    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    @Produces
    @RequestScoped
    @Named("listadoCategorias")
    public List<Categoria> listarCategoria() {
        List<Categoria> prod = cateFacade.listar();
        return prod;
    }
    
    
    @Produces
    @Model
    public String tituloCategoria() {
        return "CRUD CATEGORIA";
    }
    
    public String guardar(){
            this.cateFacade.guardar(categoria);
        return "CrudCategoria.xhtml?faces-redirect=true";
    }
    
    public String eliminar(Long id){
         cateFacade.eliminar(id);
        return "CrudCategoria.xhtml?faces-redirect=true";
    }
    
    public String editar(Long id){
        this.id = id;
        
        if (id != null && id > 0) {
            cateFacade.opcional(id).ifPresent(p -> {
                this.categoria = p;
            });
        }
        return "formCategoria.xhtml";
    }
}