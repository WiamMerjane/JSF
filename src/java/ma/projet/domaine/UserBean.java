/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

/**
 *
 * @author hp
 */
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import ma.projet.beans.User;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private User user = new User();
    // Constructeur, getters et setters

   public String login() throws IOException {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    // Redirection vers index.xhtml
    externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");

    // Retourne une chaîne non vide pour la navigation (s'il est configuré dans le faces-config.xml)
    return "";
}


    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}