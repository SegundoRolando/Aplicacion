/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

/**
 *
 * @author PAUL
 */
@NamedQuery(name = "getKardex", query = "SELECT k FROM  Kardex k")
@Entity
public class Kardex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Kardex() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
