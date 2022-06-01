
package ec.edu.ups.beans;

import ec.edu.ups.ejb.FacturaFacade;
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
    private Factura factura;
    private Cliente cliente;
    private Producto producto = new Producto();
    private int idProducto;
    private String nombreProducto;
    private FacturaDetalle detalle;
    static List<FacturaDetalle> detalles = new ArrayList<>();
    
    //Datos de la factura cabecera.
    private int idCliente;
    private String nombreCliente;
    private String apellido;
    
    //Datos del detalle factura
    private int cantidad;
    private double precio;
    private double totalXproducto;
    private double subtotal;
    private double iva;
    private double total;
    
    
    public FacturaControlador(){
        cliente = new Cliente();
        factura = new Factura();
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

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
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
        
        cliente.setId(idCliente);
        cliente.setNombre(nombreCliente);
        factura.setCliente(cliente);
        factura.setIva(iva);
        factura.setSubtotal(subtotal);
        factura.setTotal(total);
        factura.setId(idCliente);
        factura.setDetalles(detalles);
        facturaFacade.guardarFactura(factura);
        detalles.clear();
        
    }
}
