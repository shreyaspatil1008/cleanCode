package main.java.dao.test;

import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.dao.interfaces.NoteDao;
import main.java.model.Note;
import main.java.model.User;

public class TestSaveNote  {
	@Inject
	private NoteDao noteDao;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSaveNoteAndReturnSavedNote() {
		Note note = new Note();
		note.setNoteString("noteOne");
		note.setNoteTitle("noteOne");
		note.setNoteCreatedTime(new Date());
		note.setUser(new User());
		noteDao.saveNoteAndReturnSavedNote(note);
	}
}
