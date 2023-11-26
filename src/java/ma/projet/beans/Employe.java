/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

/**
 *
 * @author hp
 */


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
        private byte[] photo;

    

   
    
   @ManyToOne
@JoinColumn(name = "service1_id")
    private Service1 service1;

    @ManyToOne
    private Employe chef;
    @OneToMany(mappedBy = "chef")
    
    private List<Employe> employes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

  
    public Service1 getService1() {
        return service1;
    }

    public Employe getChef() {
        return chef;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setService1(Service1 service1) {
        this.service1 = service1;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
    
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    
    
}
