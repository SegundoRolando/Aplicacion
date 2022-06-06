/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 *
 * @author PAUL
 */
@Entity
public class FacturaDetalleProveedor {
    @Id
    private int id;
    private int cantidad;
    private double precio;
    private String descripcion;
    private double totalXproducto;
    
    @OneToOne
    @JoinColumn
    private Producto producto;
    
    @ManyToOne
    @JoinColumn
    private Factura factura;
    
    public FacturaDetalleProveedor() {
    }

    public FacturaDetalleProveedor(int id, int cantidad, double precio, String descripcion, double totalXproducto, Producto producto, Factura factura) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.totalXproducto = totalXproducto;
        this.producto = producto;
        this.factura = factura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotalXproducto() {
        return totalXproducto;
    }

    public void setTotalXproducto(double totalXproducto) {
        this.totalXproducto = totalXproducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "FacturaDetalleProveedor{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", descripcion=" 
                + descripcion + ", totalXproducto=" + totalXproducto + ", producto=" + producto + ", factura=" + factura + '}';
    }

    
    
    
    
}
