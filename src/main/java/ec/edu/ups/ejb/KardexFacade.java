/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Kardex;
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
public class KardexFacade extends AbstractFacade<Kardex>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;

    public KardexFacade(){
        super(Kardex.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Kardex>getKardex() {
        Query query = em.createNamedQuery("getKardex");
        List<Kardex> res = query.getResultList();
        return res;
    }
    
    public void guardarKardex(Kardex kardex){
        if(kardex.getId() != 0) {
            em.merge(kardex);
        } else {
            em.persist(kardex);
        }
    }
    
    //Buscar al empleado por la ID
    public Kardex BuscoPorId(int id){
        return em.find(Kardex.class, id);
    }
    
    //Elimiar al empleado por ID
    public void eliminar(int id){
        Kardex kardex = BuscoPorId(id);
        em.remove(kardex);
    }
    
    //
    public Optional<Kardex> opcional(int id){
        return Optional.ofNullable(BuscoPorId(id));
    }
    
}
