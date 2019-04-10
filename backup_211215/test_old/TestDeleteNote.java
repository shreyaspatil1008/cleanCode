package main.java.dao.test;

import javax.inject.Inject;

import org.junit.Test;

import main.java.dao.interfaces.NoteDao;
import main.java.model.Note; 

public class TestDeleteNote extends TestClassWithSetUpAndTearDown{
	
	@Inject
	private NoteDao noteDao;

	@Test
	public void testDeleteNote() {
		Note note = new Note();
		note.setId(1l);
		note.setNoteString("noteOne");
		noteDao.deleteNote(note);
	}
}
