package ec.edu.ups.beans;


import ec.edu.ups.ejb.UsuarioFacade;
import ec.edu.ups.entidades.Usuario;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

@Model
public class UserControlador {
    @EJB
    private UsuarioFacade usariofacade;
   
    @Produces
    @Model
    public String titulo() {
        return "Hola mundo SOY IVAN...";
    }
   
    @Produces
    @RequestScoped
    @Named("listado")
    public List<Usuario> buscarTodos(){
        System.out.println("Usuarios por level: ");
          List<Usuario> user = usariofacade.getUserByLevel(1);
          for (Usuario i : user) {
              System.out.println(i);
              //i.setId(i.getId());
          }  
       return user;
    }
}
