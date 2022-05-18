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
    
    
    public String titulo() {
        return "Hola mundo SOY IVAN...";
    }
    
    @Produces
    @RequestScoped
    @Named("listadoUsuarios")
    public List<User> listarUsuarios() {
        System.out.println("Usuarios por level: ");
        List<User> user = facadeUser.getUser();
        
        return user;
    }
}