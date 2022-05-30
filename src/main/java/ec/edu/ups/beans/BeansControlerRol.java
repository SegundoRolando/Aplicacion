/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.beans;

import ec.edu.ups.ejb.RolFacade;
import ec.edu.ups.entidades.Rol;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.inject.Model;

/**
 *
 * @author ASUS
 */
@Model
public class BeansControlerRol {
    @EJB
    private RolFacade rolFacade;
    private Rol rol;
    private int codigo;
    
    @PostConstruct
    public void init() {
        this.rol = new Rol();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

   public String guardar(){
        try {
            this.rolFacade.guardarRol(rol);
            rol = new Rol();
        } catch (Exception e) {
        }
        return " ";
    }
}
