
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;


/**
 *
 * @author ASUS
 */
@Entity
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;
    private String nom_Proveedor;
    private String correo;
    private String telefono;
    private String direccion;
    private String ciudad;

    public Proveedor() {
    }

    public Proveedor(int codigo, String nom_Proveedor, String correo, String telefono, String direccion, String ciudad) {
        this.codigo = codigo;
        this.nom_Proveedor = nom_Proveedor;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNom_Proveedor() {
        return nom_Proveedor;
    }

    public void setNom_Proveedor(String nom_Proveedor) {
        this.nom_Proveedor = nom_Proveedor;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "codigo=" + codigo + ", nom_Proveedor=" + nom_Proveedor + ", correo=" + correo + ", telefono=" + telefono + ", direccion=" + direccion + ", ciudad=" + ciudad + '}';
    }
    
    
}
