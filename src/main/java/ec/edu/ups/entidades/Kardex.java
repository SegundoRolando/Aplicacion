/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author PAUL
 */
@NamedQuery(name = "getKardex", query = "SELECT k FROM  Kardex k")
@Entity
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private FacturaDetalle detalleF;
    private Producto producto;

    public Kardex() {
    }

    public Kardex(int id, FacturaDetalle detalleF, Producto producto) {
        this.id = id;
        this.detalleF = detalleF;
        this.producto = producto;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FacturaDetalle getDetalleF() {
        return detalleF;
    }

    public void setDetalleF(FacturaDetalle detalleF) {
        this.detalleF = detalleF;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "Kardex{" + "id=" + id + ", detalleF=" + detalleF + ", producto=" + producto + '}';
    }
    
    
}
