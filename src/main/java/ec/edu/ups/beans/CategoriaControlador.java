
package ec.edu.ups.beans;

import ec.edu.ups.ejb.CategoriaFacade;
import ec.edu.ups.entidades.Categoria;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import java.util.List;

/**
 *
 * @author rolan
 */
public class CategoriaControlador {
    @EJB
    private CategoriaFacade categoriaFacade;
    private List<Categoria> listaCategoria;
    private Categoria categoria;

    
    public List<Categoria> getlistacategoria() {
        this.listaCategoria = this.categoriaFacade.listar();
        return listaCategoria;
    }

    public void setListacategoria(List<Categoria> listacategoria) {
        this.listaCategoria = listacategoria;

    }

    public Categoria getcategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @PostConstruct
    public void init() {
        this.categoria = new Categoria();
    }
}