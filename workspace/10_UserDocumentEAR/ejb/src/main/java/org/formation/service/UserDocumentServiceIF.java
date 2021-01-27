package org.formation.service;

import java.util.Set;

import org.formation.model.Document;
import org.formation.model.Member;

public interface UserDocumentServiceIF {

	public Member authenticate(Member user) throws AuthenticationException;
	public Member registerUser(Member newUser) throws AlreadyExistException;
	
	public Set<Document> getDocuments(Member user);
	public void uploadFile(Member user, Document document);
}
