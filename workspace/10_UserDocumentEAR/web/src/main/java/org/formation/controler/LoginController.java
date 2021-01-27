package org.formation.controler;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.formation.model.Member;
import org.formation.service.AuthenticationException;
import org.formation.service.UserDocumentServiceLocal;

@Named("loginController")
@RequestScoped
public class LoginController {

	@EJB
	UserDocumentServiceLocal userDocumentService;
	
	private String email,password;
	
	public LoginController() {
		// TODO Auto-generated constructor stub
		System.out.println("NEW  LOGIN CONTROLLER");
	}

	public String login() {
		Member user = new Member();
		user.setEmail(email);
		user.setPassword(password);
		try {
			user = userDocumentService.authenticate(user);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", user);
		} catch (AuthenticationException e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("invalid login/password"));
			return "login";
		}
		return "documents";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
