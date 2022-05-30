
package ec.edu.ups.beans;
import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidades.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;



/**
 *
 * @author ASUS
 */
@Model
public class UsuarioControlador {
    @EJB
    private UsuarioFacade userFacade;
    private Usuario user;
    private Long id;
    
    @PostConstruct
    public void init() {
        this.user = new Usuario();
    }

    public UsuarioFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UsuarioFacade userFacade) {
        this.userFacade = userFacade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    
    public String guardar(){
        this.userFacade.guardarU(user);
        return "Usuario.xhtml?faces-redirect=true";
    }
   
    @Produces
    @RequestScoped
    @Named("listadoUsario")
    public List<Usuario> listadoUsers() {
        List<Usuario> ro = userFacade.listar();
        return ro;
    }
   
    public String editar(Long id){
        this.id = id;
        if(id != null){
            userFacade.msm(id).ifPresent(u ->{
                    this.user = u;
            });
        }
        return "NuevoUsuario.xhtml";
    }
     
    public String eliminar(Long codigo){
         userFacade.eliminar(codigo);
        return "Usuario.xhtml?faces-redirect=true";
        
    }
    
    public String validar(String correo, String password){
        Usuario comp = userFacade.rolUs(correo, password);
        String comparar = comp.getRol();
        if (comparar.equals("Administrador")){
            return "Admin.xhtml?faces-redirect=true";
        }
        if(comparar.equals("Empleado")){
            return "Empleado/Empleado.xhtml?faces-redirect=true";
        }
        if(comparar.equals("Cliente")){
            return "Cliente/Clientes.xhtml?faces-redirect=true";
        }
        return "LogIn.xhtml?faces-redirect=true";
    }
}
