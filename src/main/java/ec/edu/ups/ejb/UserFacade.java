
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author rolan
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    public UserFacade(){
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
    public List<User>getUser(){
        Query query = em.createNamedQuery("getUser");
        List <User> res = query.getResultList();
        return res;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Integer> ObtenerUsuarios(){
        String jpql1 = "SELECT u.id FROM  User u";
        List<Integer> res = em.createQuery(jpql1).getResultList();
        return res;
    }
    
    public User getUserById(Integer id){
        String jpql = "SELECT u FROM User u Where u.id =" + id;
        User user = (User)em.createQuery(jpql).getSingleResult();
        return user;
    }
    
    
    public List<User>getUserByLevel(int id){
        Query query = em.createNamedQuery("getByLevel");
        query.setParameter("id", id);
        List <User> res = query.getResultList();
        return res;
    }
    
    
    
    public List<User> getUserByNameAndLevel(String name, int level){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        
        Root root = cq.from(User.class);
        cq.select(root);
        
        Predicate p1 = cb.like(root.get("name").as(String.class), name);
        Predicate p2 = cb.greaterThanOrEqualTo(root.get("level").as(Integer.class), level);
        
        Predicate pf = cb.and(p1,p2);
        cq.where(pf);
        
        Query query = em.createQuery(cq);
        return query.getResultList();
    }
}
