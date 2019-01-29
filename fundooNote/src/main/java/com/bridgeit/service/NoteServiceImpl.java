package com.bridgeit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.INoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;
import com.bridgeit.utility.UserToken;

@Service
@Transactional
public class NoteServiceImpl implements INoteService
{

	@Autowired
	private INoteDao noteDao;
	
	@Autowired 
	private IUserService userService;
	
	
	@Override
	public boolean addNote(Note note, String token) {
		//noteDao.addNote(note,);
		
		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
			System.out.println(id);
			note.setUser(user);
			
			noteDao.addNote(note);
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public void deleteNote(Note note,String token) {

		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
			System.out.println(id);
			note.setUser(user);
			noteDao.deleteNote(note);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	@Override
	public void updateNote(Note note,String token) {
		// TODO Auto-generated method stub
		
		try {
			int id=UserToken.tokenVerify(token);
			User user=userService.getUser(id);
			System.out.println(id);
			note.setUser(user);

			noteDao.updateNote(note);
			
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		}

	

}
