/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Factura;
import ec.edu.ups.entidades.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author rolan
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
     public Cliente buscarClientePorId(int id) {
        //return em.find(Producto.class, id);
        return em.createQuery("select c from Cliente c  where c.id=:id", Cliente.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public Producto buscarProductoPorId(int codigo) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Producto p where p.codigo=:codigo", Producto.class)
                .setParameter("codigo", codigo)
                .getSingleResult();
    }
    
    public void guardarFactura(Factura factura) {
        if(factura.getId() != 0) {
            em.merge(factura);
        } else {
            em.persist(factura);
        }
    }
}
