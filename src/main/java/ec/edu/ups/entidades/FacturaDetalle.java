package ec.edu.ups.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

/**
 *
 * @author rolan
 */
@Entity
public class FacturaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private double precio;
    private String descripcion;
    private double totalXproducto;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Factura factura;
    
    @OneToOne
    @JoinColumn
    private Producto producto;
    
    public FacturaDetalle() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public double getTotalXproducto() {
        return totalXproducto;
    }

    public void setTotalXproducto(double totalXproducto) {
        this.totalXproducto = totalXproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    @Override
    public String toString() {
        return "FacturaDeatalle{" + "id=" + id + ", cantidad=" + cantidad + ", precio=" + precio + ", totalXproducto=" + totalXproducto + '}';
    }
    
}
