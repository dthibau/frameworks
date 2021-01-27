package org.formation.controler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.formation.model.Document;
import org.formation.model.Member;
import org.formation.service.UserDocumentServiceLocal;

@Named("documentsController")
@RequestScoped
public class DocumentsController {

	@EJB
	UserDocumentServiceLocal userDocumentService;

	private Part file;

	private Set<Document> documents;

	public DocumentsController() {
		// TODO Auto-generated constructor stub
	}

	public Set<Document> getDocuments() {
		if (documents == null) {
			Member m = (Member) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get("loggedUser");
			documents = userDocumentService.getDocuments(m);
		}
		return documents;
	}

	public void uploadFile() {
		Member loggedUser = (Member) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("loggedUser");
		Document document = new Document();
		document.setUploadedDate(new Date());
		document.setContentType(file.getContentType());
		byte[] b = new byte[(int) file.getSize()];
		try (InputStream input = file.getInputStream()) {
			input.read(b);
		} catch (IOException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something bad happened ! " + e));
		}
		document.setData(b);
		document.setName(file.getSubmittedFileName());
		userDocumentService.uploadFile(loggedUser, document);
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
