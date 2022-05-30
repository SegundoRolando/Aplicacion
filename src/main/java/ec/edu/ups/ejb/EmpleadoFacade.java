package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Empleado;
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
public class EmpleadoFacade extends AbstractFacade<Empleado>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public EmpleadoFacade(){
        super(Empleado.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Empleado>getEmpleado() {
        Query query = em.createNamedQuery("getEmpleado");
        List<Empleado> res = query.getResultList();
        return res;
    }
    
    public void guardarEmpleado(Empleado empleado){
        if(empleado.getId() != null && empleado.getId() > 0) {
            em.merge(empleado);
        } else {
            em.persist(empleado);
        }
    }
    
    //Buscar al empleado por la ID
    public Empleado BuscoPorId(Long id){
        return em.find(Empleado.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(Long id){
        Empleado empleado = BuscoPorId(id);
        em.remove(empleado);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Empleado> opcional(Long id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
    public Empleado cargo(String correo){
        return em.createQuery("select p from Empleado p where p.correo=:correo", Empleado.class)
                .setParameter("correo", correo)
                .getSingleResult();
    }
    
}
