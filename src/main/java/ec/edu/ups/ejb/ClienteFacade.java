/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author PAUL
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public ClienteFacade(){
        super(Cliente.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Cliente>getCliente() {
        Query query = em.createNamedQuery("getCliente");
        List<Cliente> res = query.getResultList();
        return res;
    }
    
    public void guardarCliente(Cliente cliente){
        if(cliente.getId() != 0) {
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }
    
    //Buscar al empleado por la ID
    public Cliente BuscoPorId(int id){
        return em.find(Cliente.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(int id){
        Cliente cliente = BuscoPorId(id);
        em.remove(cliente);
    }
    
    //Para que no exista error al no existir empleados en la base
    public Optional<Cliente> opcional(int id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
    public Cliente getClienteByName(String name) {
        String jpql = "SELECT s FROM Cliente s WHERE s.nombre = '" + name + "'";
        Cliente cliente = (Cliente) em.createQuery(jpql).getSingleResult();
        return cliente;
    }
    
}
