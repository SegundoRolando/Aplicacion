/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.ejb;

import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.FacturaProveedor;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Proveedor;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author PAUL
 */
@Stateless
public class FacturaProveedorFacade extends AbstractFacade<FacturaProveedor>{
    @PersistenceContext(name="my_persistence_unit")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaProveedorFacade() {
        super(FacturaProveedor.class);
    }
    
     public Proveedor buscarProveedorPorId(int id) {
        //return em.find(Producto.class, id);
        return em.createQuery("SELECT pr FROM  Proveedor pr where pr.codigo=:id", Proveedor.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    public Producto buscarProductoPorId(int codigo) {
        //return em.find(Producto.class, id);
        return em.createQuery("select p from Producto p where p.id=:id", Producto.class)
                .setParameter("id", codigo)
                .getSingleResult();
    }
    
    public void guardarFactura(FacturaProveedor factura) {
        if(factura.getId() != 0) {
            em.merge(factura);
        } else {
            em.persist(factura);
        }
    }
}
