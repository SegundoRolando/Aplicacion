
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;
/**
 *
 * @author rolan
 */
@NamedQuery(name = "getByLevel", query = "SELECT u from Usuario u Where u.level >= :level")
@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private int level;
 
    public Usuario() {
    }

    public Usuario(int id, String name, String password, int level) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.level = level;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", name=" + name + ", password=" + password + ", level=" + level + '}';
    }
    
}
