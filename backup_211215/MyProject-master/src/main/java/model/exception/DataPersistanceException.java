package main.java.model.exception;

import org.hibernate.TransactionException;
import org.junit.Ignore;

/**
 * This is a custom exception for save, update, delete operations
 * @Author shreyas patil
 */
@Ignore
public class DataPersistanceException extends TransactionException{

	private static final long serialVersionUID = 1L;

	public DataPersistanceException(String message) {
		super(message);
	}

}
