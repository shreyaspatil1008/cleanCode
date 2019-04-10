package main.java.dao.test;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import main.java.dao.interfaces.NoteDao;
import main.java.model.Note; 

public class TestDeleteNote {
	
	@Inject
	private NoteDao noteDao;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteNote() {
		Note note = new Note();
		note.setId(1l);
		note.setNoteString("noteOne");
		noteDao.deleteNote(note);
	}
}
