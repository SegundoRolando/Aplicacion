
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author rolan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public List<Usuario>getUserByLevel(int level){
        Query query = em.createNamedQuery("getByLevel");
        query.setParameter("level", level);
        List <Usuario> res = query.getResultList();
        return res;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
