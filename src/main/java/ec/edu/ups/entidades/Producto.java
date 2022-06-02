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
    private int cantidad;
    private int stockTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;
   // @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToOne(fetch = FetchType.LAZY)
    private Sucursal sucursal; 

//    @Column(name="fecha_registro")
//    private LocalDate fechaRegistro;

    public Producto() {
    }

    public Producto(Long id, String nombre, double precio, int stock, String descripcion, 
            Categoria categoria, Sucursal sucursal, int cantidad,int stockTotal) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.sucursal = sucursal;
        this.cantidad = cantidad;
        this.stockTotal = stockTotal;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }
    
    
    
//    @PrePersist
//    public void prePersist() {
//        fechaRegistro = LocalDate.now();
//    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" 
                + precio + ", stock=" + stock + ", descripcion=" + descripcion + ", cantidad=" + cantidad 
                + ", stockTotal=" + stockTotal + ", categoria=" + categoria + ", sucursal=" + sucursal + '}';
    }

    

   
}
