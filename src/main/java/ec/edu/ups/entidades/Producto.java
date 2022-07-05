package ec.edu.ups.entidades;

import jakarta.persistence.*;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private int stock;
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;
   // @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToOne(fetch = FetchType.LAZY)
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
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

   
    @Override
    public String toString() {
        return "Producto{" 
                + "id=" 
                + id + ", nombre=" 
                + nombre + ", precio=" 
                + precio + ", stock=" 
                + stock + ", descripcion=" 
                + descripcion + ", cantidad=" 
                + categoria + ", sucursal=" 
                + sucursal + '}';
    }
}