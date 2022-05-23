
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Proveedor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ASUS
 */

@Stateless
public class ProveedorFacede extends AbstractFacade<Proveedor>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }
    public  ProveedorFacede(){
       super(Proveedor.class);
    }
    public List<Proveedor>getProveedor() {
         return em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
    }
    public void guardarProveedor(Proveedor proveedor){
      if(proveedor.getCodigo()!= 0) {
            em.merge(proveedor);
        } else {
            em.persist(proveedor);
        }
    }
    public Proveedor BuscoPorCodigo(int codigo){
        return em.find(Proveedor.class, codigo);
    }
    public void eliminar(int codigo){
        Proveedor proveedor=BuscoPorCodigo(codigo);
        em.remove(proveedor);
    }
    public Optional<Proveedor> opcional(int codigo){
        return Optional.ofNullable(BuscoPorCodigo(codigo));
    }
    
    
}

