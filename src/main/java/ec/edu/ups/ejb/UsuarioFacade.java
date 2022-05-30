
package ec.edu.ups.ejb;
import ec.edu.ups.entidades.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
@Stateless 
public class UsuarioFacade extends AbstractFacade<Usuario>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return  em;
    }
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    //
    public void guardarU(Usuario users){
      if(users.getId() != null && users.getId() > 0) {
            em.merge(users);
             
        } else {
            em.persist(users);
        }
      
    }
    //
    public List<Usuario> listar() {
        return em.createQuery("SELECT u FROM  Usuario u", Usuario.class).getResultList();
    }
    //
    public Usuario buscar(Long id) {
        return em.find(Usuario.class, id);
    }
    //
    public Optional<Usuario> msm(Long id) {
        return Optional.ofNullable(buscar(id));
    }
    //
    public void eliminar(Long id) {
        Usuario users = buscar(id);
        em.remove(users);
    }
    
    public Usuario rolUs(String correo, String password){
        return em.createQuery("select p from Usuario p where p.correo=:correo AND p.password=:password", Usuario.class)
                .setParameter("correo", correo)
                .setParameter("password", password)
                .getSingleResult();
    }
    
}