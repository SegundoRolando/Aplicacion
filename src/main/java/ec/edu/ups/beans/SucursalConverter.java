package ec.edu.ups.beans;

import ec.edu.ups.ejb.SucursalFacade;
import ec.edu.ups.entidades.Sucursal;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.Optional;

/**
 *
 * @author Jose
 */
@RequestScoped
@Named("sucursalConverter")
public class SucursalConverter implements Converter<Sucursal>{
    
    @Inject
    private SucursalFacade sucfacade;

    @Override
    public Sucursal getAsObject(FacesContext context, UIComponent component, String id) {
        if (id == null) {
            return null;
        }
        Optional<Sucursal> sucursalOptional = sucfacade.opcional(Long.valueOf(id));
        if (sucursalOptional.isPresent()) {
            return sucursalOptional.get();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Sucursal sucursal) {
        if(sucursal == null){
            return "0";
        }
        return sucursal.getId().toString();
    }
    
}
