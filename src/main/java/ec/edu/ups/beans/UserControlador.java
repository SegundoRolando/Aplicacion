package ec.edu.ups.beans;

import ec.edu.ups.ejb.UserFacade;
import ec.edu.ups.entidades.User;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import java.util.List;

@Model
public class UserControlador {
    @EJB
    private UserFacade facadeUser;
    
    @Produces
    @Model
    public String titulo() {
        return "Hola mundo SOY IVAN...";
    }
    
    @Produces
    @RequestScoped
    @Named("listado")
    public List<User> listarUsuarios(){
        System.out.println("Usuarios por level: ");
          List<User> user = facadeUser.getUser();
          for (User i : user) {
              System.out.println("hooooooolaa" + i);
              //i.setId(i.getId());
          }  
       return user;
    }
}
