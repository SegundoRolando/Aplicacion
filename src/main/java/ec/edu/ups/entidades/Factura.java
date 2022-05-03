
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
/**
 *
 * @author rolan
 */
@Entity
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

   

    @Override
    public String toString() {
        return "ec.edu.ups.entidades.Factura[ id=" + id + " ]";
    }
    
}
