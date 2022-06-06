/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.FacturaProveedorFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.FacturaProveedor;
import ec.edu.ups.entidades.FacturaDetalleProveedor;
import ec.edu.ups.entidades.Producto;
import ec.edu.ups.entidades.Proveedor;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PAUL
 */
@Model
public class FacturaProveedorControlador {
    
    @EJB
    private FacturaProveedorFacade facturaProveedorFacade;
    @EJB
    private ProductoFacade prodFacade;
    private FacturaProveedor factura;
    private Proveedor proveedor;
    private Producto producto = new Producto();
    private int idProducto;
    private String nombreProducto;
    private FacturaDetalleProveedor detalle;
    static List<FacturaDetalleProveedor> detalles = new ArrayList<>();
    
    //calcular stock nuevo despues de venta
    private int stockActualizado;
    
    //Datos de la factura cabecera.
    private int idProveedor;
    private String nombreProveedor;
    private String apellido;
    private int stockAnterior;
    //Datos del detalle factura
    private int cantidad;
    private double precio;
    private double totalXproducto;
    private double subtotal;
    private double iva;
    private double total;
    private Long f;

    public FacturaProveedorFacade getFacturaProveedorFacade() {
        return facturaProveedorFacade;
    }

    public void setFacturaProveedorFacade(FacturaProveedorFacade facturaProveedorFacade) {
        this.facturaProveedorFacade = facturaProveedorFacade;
    }

    public ProductoFacade getProdFacade() {
        return prodFacade;
    }

    public void setProdFacade(ProductoFacade prodFacade) {
        this.prodFacade = prodFacade;
    }

    public FacturaProveedor getFactura() {
        return factura;
    }

    public void setFactura(FacturaProveedor factura) {
        this.factura = factura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(int stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public FacturaDetalleProveedor getDetalle() {
        return detalle;
    }

    public void setDetalle(FacturaDetalleProveedor detalle) {
        this.detalle = detalle;
    }

    public List<FacturaDetalleProveedor> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalleProveedor> detalles) {
        this.detalles = detalles;
    }

    

    public int getStockActualizado() {
        return stockActualizado;
    }

    public void setStockActualizado(int stockActualizado) {
        this.stockActualizado = stockActualizado;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public FacturaProveedorControlador(){
        proveedor = new Proveedor();
        factura = new FacturaProveedor();
    }
    
    public void buscarProveedor(){
        proveedor = facturaProveedorFacade.buscarProveedorPorId(idProveedor);
        nombreProveedor = proveedor.getNom_Proveedor();
        System.out.println("*****"+proveedor+"********");
        System.out.println("***/*/*/*"+nombreProveedor+"***/*/*/*");
    }
    public void buscarProducto(){
        System.out.println("CODIGO DEL PRODUCTO **** : " + idProducto);
        
        producto = facturaProveedorFacade.buscarProductoPorId(idProducto);
        nombreProducto = producto.getNombre(); 
        precio = producto.getPrecio();
        stockAnterior = producto.getCantidadIngresos();
    }   
    
    
    public void add() {
        totalXproducto = cantidad * precio;
        detalle = new FacturaDetalleProveedor();
       
        detalle.setId(idProducto);
        detalle.setDescripcion(nombreProducto);
        detalle.setCantidad(cantidad);
        producto.setCantidad(cantidad);
        detalle.setPrecio(precio);
        detalle.setTotalXproducto(totalXproducto);
        detalles.add(detalle);
        
        subtotal = 0;
        iva = 0;
        total = 0;
        for (int i = 0; i < detalles.size(); i++) {
             subtotal =  subtotal + detalles.get(i).getTotalXproducto();
             iva = subtotal * 0.12;
             total = subtotal + iva;  
        }	
    }
    
    public void guardarFactura(){
        proveedor.setCodigo(idProveedor);
        proveedor.setNom_Proveedor(nombreProveedor);
        factura.setProveedor(proveedor);
        factura.setIva(iva);
        factura.setSubtotal(subtotal);
        factura.setTotal(total);
        factura.setId(idProveedor);
        factura.setDetalles(detalles);
        facturaProveedorFacade.guardarFactura(factura);
        updateStock();
        detalles.clear();
    }
    
    public void updateStock(){
        for (int i = 0; i < detalles.size(); i++) {
            Producto p=prodFacade.getProductoByName(detalles.get(i).getDescripcion());
            p.setStock(p.getStock()+detalles.get(i).getCantidad());
            p.setCantidadIngresos(cantidad+stockAnterior);
            prodFacade.edit(p);
        }
    }
}

