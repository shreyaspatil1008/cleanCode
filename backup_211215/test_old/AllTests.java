package main.java.dao.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestSaveNote.class, TestUpdateNote.class ,TestGetAllNotesOfUser.class, TestGetNoteById.class,
	TestGetUserByEmailAndPassword.class,TestDeleteNote.class })
public class AllTests {

}
