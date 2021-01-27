package org.formation.controler;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.formation.model.Member;
import org.formation.service.AlreadyExistException;
import org.formation.service.UserDocumentServiceLocal;

@Named("inscriptionController")
@RequestScoped
public class InscriptionController {

	@EJB
	UserDocumentServiceLocal userDocumentService;
	
	private Member member = new Member();
	
	
	public InscriptionController() {
		// TODO Auto-generated constructor stub
	}

	public String register() {
		try {
			member = userDocumentService.registerUser(member);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedUser", member);
		} catch (AlreadyExistException e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Please choose another login"));
			return "inscription";
		}
		return "documents";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	
	
	
}
