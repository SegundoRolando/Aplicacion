
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Producto;
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
public class ProductoFacade extends AbstractFacade<Producto> {
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    private Producto producto;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> listar() {
        return em.createQuery("select p from Producto p left outer join fetch p.categoria", Producto.class).getResultList();
    }
    
    public void guardar(Producto producto) {
        if (producto.getId() != null && producto.getId() > 0) {
            em.merge(producto);
        } else {
            em.persist(producto);
        }
    }
    
    public Producto porId(Long id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Producto p left outer join fetch p.categoria where p.id=:id", Producto.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    
    public void eliminar(Long id) {
        Producto producto = porId(id);
        em.remove(producto);
    }
    
    public Optional<Producto> opcional(Long id) {
        return Optional.ofNullable(porId(id));
    }
    
    public Producto getProductoByName(String name) {
        String jpql = "SELECT s FROM Producto s WHERE s.nombre = '" + name + "'";
        Producto producto = (Producto) em.createQuery(jpql).getSingleResult();
        return producto;
    }
    /*public void editar(Long id){
        em.createQuery("select p from Producto p", Producto.class)
         .setParameter("id", id)
         .getSingleResult();
        em.merge(producto);
    }*/
}
