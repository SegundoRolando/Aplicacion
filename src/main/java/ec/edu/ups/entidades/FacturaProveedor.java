/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author PAUL
 */
@Entity
public class FacturaProveedor implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double total;
    private LocalDate fecha;
    private double subtotal;
    private double iva;
    private Sucursal sucursal;
    
    @ManyToOne
    @JoinColumn
    private Proveedor proveedor;
    
    @OneToMany(fetch = FetchType.LAZY)
    private List<FacturaDetalleProveedor> detalles;
    
    public FacturaProveedor() {
    }

    public FacturaProveedor(int id, double total, LocalDate fecha, double subtotal, double iva, Sucursal sucursal, Proveedor proveedor, List<FacturaDetalleProveedor> detalles) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.iva = iva;
        this.sucursal = sucursal;
        this.proveedor = proveedor;
        this.detalles = detalles;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<FacturaDetalleProveedor> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalleProveedor> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "FacturaProveedor{" + "id=" + id + ", total=" + total + ", fecha=" 
                + fecha + ", subtotal=" + subtotal + ", iva=" + iva + ", sucursal=" 
                + sucursal + ", proveedor=" + proveedor + ", detalles=" + detalles + '}';
    }
}
