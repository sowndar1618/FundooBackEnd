package com.bridgeit.service;

import com.bridgeit.model.Note;
import com.bridgeit.model.User;

public interface INoteService {
	public boolean addNote(Note note,String token);

	public void deleteNote(Note Note,String token);

	public void updateNote(Note note,String token);
}
