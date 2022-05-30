
package ec.edu.ups.ejb;
import ec.edu.ups.entidades.Rol;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


/**
 *
 * @author ASUS
 */
@Stateless 
public class RolFacade extends AbstractFacade<Rol>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }
    
    public RolFacade() {
        super(Rol.class);
    }
    /*
    public List<Rol> listar() {
        return em.createQuery("SELECT r FROM  Rol r", Rol.class).getResultList();
    }*/
    
    public void guardarRol(Rol rol){
      if(rol.getCodigo()!= 0) {
            em.merge(rol);
        } else {
            em.persist(rol);
        }
    }  
}
