
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author rolan
 */
@Stateless 
public class CategoriaFacade extends AbstractFacade<Categoria> {
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaFacade() {
        super(Categoria.class);
    }
    
    public List<Categoria> listar() {
        return em.createQuery("SELECT u FROM  Categoria u", Categoria.class).getResultList();
    }
    
    public void guardar(Categoria categoria) {
        if (categoria.getId() != null && categoria.getId() > 0) {
            em.merge(categoria);
        } else {
            em.persist(categoria);
        }
    }
    
    public Categoria porId(Long id) {
        return em.find(Categoria.class, id);
    }
    public void eliminar(Long id) {
        Categoria categoria = porId(id);
        em.remove(categoria);
    }
    
    public Optional<Categoria> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
}
