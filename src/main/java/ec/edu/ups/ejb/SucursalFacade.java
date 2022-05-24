package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Sucursal;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jose
 */
@Stateless
public class SucursalFacade extends AbstractFacade<Sucursal>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    public SucursalFacade(){
        super(Sucursal.class);
    }    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Sucursal>getSucursal(){
        Query query = em.createNamedQuery("getSucursal");
        List<Sucursal> res = query.getResultList();
        return res;
    }
    
    //Guardar sucursal
    public void GuardarSucursal(Sucursal sucursal){
        if(sucursal.getId() != null && sucursal.getId() > 0){
            em.merge(sucursal);
        }else{
            em.persist(sucursal);
        }
    }
    
    //Buscar la sucursal por ID
    public Sucursal BuscoPorId(Long id){
        return em.find(Sucursal.class, id);
    }
    
    //Eliminar empleado por ID
    public void eliminar(Long id){
        Sucursal sucursal = BuscoPorId(id);
        em.remove(sucursal);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Sucursal> opcional(Long id){
        return Optional.ofNullable(BuscoPorId(id));
    }
}
