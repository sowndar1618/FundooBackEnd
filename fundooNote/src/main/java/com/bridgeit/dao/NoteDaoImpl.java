package com.bridgeit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

@Repository
public class NoteDaoImpl implements INoteDao{


	@Autowired
	private SessionFactory sessionFactory;
	

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public User getUser(Integer id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}
	
	
	
	@Override
	public void addNote(Note note) {
	
		
		
	 

		getCurrentSession().save(note);
		System.out.println("added");
	}


	@Override
	public void deleteNote(Note note) {
		// TODO Auto-generated method stub
	
		getCurrentSession().delete(note);
	
	}


	@Override
	public int updateNote(Note note) {
		// TODO Auto-generated method stub
		String hql = "UPDATE Note set title =:title WHERE noteId = :noteID";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("noteID", note.getNoteId());
		query.setParameter("title", note.getTitle());
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);

        return result;

	
		
	}
	
}
