/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author hp
 */
@Entity
public class Service1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "service1")  // Assuming "service" is the property in Employee that maps back to Service1
    private List<Employe> employes;

    public Service1() {
        // Default constructor
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    
     public Employe getChef() {
        if (employes != null && !employes.isEmpty()) {
            // Retourne le premier employé de la liste comme chef
            return employes.get(0);
        } else {
            return null;
        }
    }
     
      @Override
    public String toString() {
        return String.valueOf(id);
    }
    
   @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Service1 other = (Service1) obj;
    return id != null && id.equals(other.id);
}

    

}
