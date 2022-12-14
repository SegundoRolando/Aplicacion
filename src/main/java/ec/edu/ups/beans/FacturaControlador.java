
package ec.edu.ups.beans;

import ec.edu.ups.ejb.FacturaFacade;
import ec.edu.ups.ejb.ProductoFacade;
import ec.edu.ups.entidades.Cliente;
import ec.edu.ups.entidades.Factura;
import ec.edu.ups.entidades.FacturaDetalle;
import ec.edu.ups.entidades.Producto;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rolan
 */
@Model
public class FacturaControlador {
    
    @EJB
    private FacturaFacade facturaFacade;
   
    @EJB
    private ProductoFacade prodFacade;
    private Factura factura;
    private Cliente cliente;
    private Producto producto;
    private Long idProducto;
    private String nombreProducto;
    private FacturaDetalle detalle;
    static List<FacturaDetalle> detalles = new ArrayList<>();
    
    //calcular stock nuevo despues de venta
    private int stockActualizado;
    
    //Datos de la factura cabecera.
    private int idCliente;
    private String nombreCliente;
    private String apellido;
    private int stockAnterior;
    private int cant;
    
    //Datos del detalle factura
    private int cantidad;
    private double precio;
    private double totalXproducto;
    private double subtotal;
    private double iva;
    private double total;
    private int cod;
    
    
    
    public FacturaControlador(){
        cliente = new Cliente();
        factura = new Factura();
        producto = new Producto();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public List<FacturaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalle> detalles) {
        this.detalles = detalles;
    }

    public FacturaDetalle getDetalle() {
        return detalle;
    }
    
    public void setDetalle(FacturaDetalle detalle) {
        this.detalle = detalle;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public void buscarCliente(){
        //cliente = new Cliente();
        cliente = facturaFacade.buscarClientePorId(idCliente);
        nombreCliente = cliente.getNombre();
        
    }
    public void buscarProducto(){
        System.out.println("CODIGO DEL PRODUCTO **** : " + idProducto);
        
        producto = facturaFacade.buscarProductoPorId(idProducto);
        nombreProducto = producto.getNombre(); 
        precio = producto.getPrecio();   
        
    }   
    
    
    public void add() {
        totalXproducto = cantidad * precio;
        detalle = new FacturaDetalle();
        detalle.setId(idProducto);
        detalle.setDescripcion(nombreProducto);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(precio);
        detalle.setTotalXproducto(totalXproducto);
        
        producto.setId(idProducto);
        int id = 0;
        id = id + 1;
        factura.setId(id);
        detalle.setFactura(factura);
        detalle.setProducto(producto);
        detalles.add(detalle);
        
        subtotal = 0;
        iva = 0;
        total = 0;
        for (int i = 0; i < detalles.size(); i++) {
             subtotal =  subtotal + detalles.get(i).getTotalXproducto();
             iva = subtotal * 0.12;
             total = subtotal + iva;  
        }	
        cant = cantidad+stockAnterior;
    }
    
    public void guardarFactura(){
    
        cliente.setId(idCliente);
        factura.setCliente(cliente);
        
        factura.setIva(iva);
        factura.setSubtotal(subtotal);
        factura.setTotal(total);
       
        factura.setDetalles(detalles);
        
        facturaFacade.create(factura);
        updateStock();
        detalles.clear();
    }
    
    public void updateStock(){
        for (int i = 0; i < detalles.size(); i++) {
            Producto p=prodFacade.getProductoByName(detalles.get(i).getDescripcion());
            p.setStock(p.getStock()-detalles.get(i).getCantidad());
            prodFacade.edit(p);
        }
    }
}
