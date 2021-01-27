package org.formation.service;

import java.util.Date;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.formation.model.Document;
import org.formation.model.Member;

/**
 * Session Bean implementation class UserDocumentService
 */
@Stateless
@LocalBean
public class UserDocumentService implements UserDocumentServiceRemote, UserDocumentServiceLocal {

	@PersistenceContext
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserDocumentService() {
		// TODO Auto-generated constructor stub
	}

	public Member authenticate(Member user) throws AuthenticationException {
		Query q = em.createQuery("from Member m where m.email = :email and m.password = :password");
		q.setParameter("email", user.getEmail());
		q.setParameter("password", user.getPassword());
		Member ret = null;
		try {
			ret = (Member)q.getSingleResult();
		} catch (PersistenceException e) {
			throw new AuthenticationException(e);
		}
		return ret;
	}
	
	public Member registerUser(Member newMember) throws AlreadyExistException {
		Query q = em.createQuery("from Member m where m.email = :email ");
		q.setParameter("email", newMember.getEmail());
		try {
			q.getSingleResult();
			throw new AlreadyExistException();
		} catch (PersistenceException e) {
			// All rigth no one exist with this email
		}
		newMember.setRegisteredDate(new Date());
		try {
			em.persist(newMember);
		} catch (PersistenceException e) {
			throw new AlreadyExistException(e);
		}
		return newMember;
	}
	
	public void uploadFile(Member user, Document document) {
		user = em.find(Member.class, user.getId());
		user.addDocument(document);
		
		
	}

	@Override
	public Set<Document> getDocuments(Member member) {
		Query q = em.createQuery("from Member m left join fetch m.documents where m = :member");
		q.setParameter("member", member);
		Member m = (Member)q.getSingleResult();
		return m.getDocuments();
	}
	
	
	
}
