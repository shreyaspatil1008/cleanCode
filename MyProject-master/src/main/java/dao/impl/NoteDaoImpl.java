package main.java.dao.impl;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;

import main.java.dao.interfaces.NoteDao;
import main.java.model.Note;
import main.java.model.exception.DataPersistanceException;
import main.java.util.HibernateUtil;

/**
 * A NoteDao implementation class with below operations
 * 1. save note
 * 2. get note by id
 * 3. get all notes for user
 * 4. update note
 * 5. delete note
 * @Author shreyas patil
 */
@Named
public class NoteDaoImpl implements NoteDao {
	private Session session;
	private Transaction transaction;

	public Note saveNoteAndReturnSavedNote(Note note) throws DataPersistanceException {
		HibernateUtil.initializeSessionAndTransaction(session,transaction);
		saveNote(note);
		return note;
	}

	private void saveNote(Note note) throws DataPersistanceException {
		try {
			saveAndCommitTransaction(note);
		} catch (DataPersistanceException exception) {
			HibernateUtil.rollbackTransactionAndThrowException(exception,transaction);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	private void saveAndCommitTransaction(Note note) {
		note.setId((Long) session.save(note));
		transaction.commit();
	}

	public Note getNoteById(Long notesId) throws DataAccessException {
		HibernateUtil.initializeSessionAndTransaction(session,transaction);
		return getNoteByIdWithTryCatchFinally(notesId);
	}

	private Note getNoteByIdWithTryCatchFinally(Long notesId) throws DataAccessException {
		try {
			return (Note) session.get(Note.class, notesId);
		} catch (DataAccessException exception) {
			throw returnDataAccessExceptionWithMessage(exception);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	private DataAccessResourceFailureException returnDataAccessExceptionWithMessage(DataAccessException exception) {
		return new DataAccessResourceFailureException(exception.getMessage());
	}

	@SuppressWarnings("unchecked")
	public List<Note> getAllNotesOfUser(Long userId) throws DataAccessException {
		session = HibernateUtil.getSession();
		try {
			return (List<Note>) createAndReturnGetAllNotesOfUserQuery(userId).list();
		} catch (DataAccessException exception) {
			throw returnDataAccessExceptionWithMessage(exception);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	private Query createAndReturnGetAllNotesOfUserQuery(Long userId) {
		Query query = session.createQuery("from note where userid = ?");
		query.setParameter(0, userId);
		return query;
	}

	public Note updateAndReturnNote(Note note) throws DataPersistanceException {
		HibernateUtil.initializeSessionAndTransaction(session,transaction);
		updateNoteWithTryCatchFinally(note);
		return note;
	}

	private void updateNoteWithTryCatchFinally(Note note) throws DataPersistanceException {
		try {
			updateAndCommitTransaction(note);
		} catch (DataPersistanceException exception) {
			HibernateUtil.rollbackTransactionAndThrowException(exception,transaction);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	private void updateAndCommitTransaction(Note note) {
		session.update(note);
		transaction.commit();
	}

	public Note deleteAndReturnNote(Note note) throws DataPersistanceException {
		HibernateUtil.initializeSessionAndTransaction(session,transaction);
		deleteNoteWithTryCatchFinally(note);
		return note;
	}

	private void deleteNoteWithTryCatchFinally(Note note) throws DataPersistanceException {
		try {
			deleteAndCommitTransaction(note);
		} catch (DataPersistanceException exception) {
			HibernateUtil.rollbackTransactionAndThrowException(exception,transaction);
		} finally {
			HibernateUtil.closeSession(session);
		}
	}

	private void deleteAndCommitTransaction(Note note) {
		session.delete(note);
		transaction.commit();
	}
}
