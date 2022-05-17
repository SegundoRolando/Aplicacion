/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Empleado;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author ASUS
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Empleado>getEmpleado() {
        Query query = em.createNamedQuery("getEmpleado");
        List<Empleado> res = query.getResultList();
        return res;
    }
    
    
}
