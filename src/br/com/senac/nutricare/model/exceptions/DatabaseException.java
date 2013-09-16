package br.com.senac.nutricare.model.exceptions;

/**
 *
 * @author Alexandre Finger Sobrinho
 * @author Alexandre Pereira Souza
 */
public class DatabaseException extends Exception {

    private static final long serialVersionUID = 1L;

    public DatabaseException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public DatabaseException(String msg) {
        this(msg, null);
    }
}
