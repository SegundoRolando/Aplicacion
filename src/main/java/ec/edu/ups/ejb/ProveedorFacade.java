
package ec.edu.ups.ejb;


import ec.edu.ups.entidades.Proveedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */
@Stateless 
public class ProveedorFacade extends AbstractFacade<Proveedor>{
    @PersistenceContext(name = "my_persistence_unit")
    private EntityManager em;

    public ProveedorFacade() {
        super(Proveedor.class);
    }
    

    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }
    
     public List<Proveedor> listar() {
        return em.createQuery("SELECT p FROM  Proveedor p", Proveedor.class).getResultList();
    }
     
    public void guardar(Proveedor proveedor) {
        if ( proveedor.getCodigo()> 0) {
            em.merge(proveedor);
        } else {
            em.persist(proveedor);
        }
    }
    public Proveedor buscarxCod(int codigo) {
        return em.find(Proveedor.class, codigo);
    }
    public void eliminar(int codigo) {
        Proveedor proveedor = buscarxCod(codigo);
        em.remove(proveedor);
    }
    
    public Optional<Proveedor> opcional(int codigo) {
        return Optional.ofNullable(buscarxCod(codigo));
    }

    
}
