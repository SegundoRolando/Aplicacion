package ec.edu.ups.entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer precio;
    private String stock;
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    private Sucursal sucursal;
//    @Column(name="fecha_registro")
//    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
//    @PrePersist
//    public void prePersist() {
//        fechaRegistro = LocalDate.now();
//    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + ", descripcion=" + descripcion + ", categoria=" + categoria + ", sucursal=" + sucursal + '}';
    }

}
