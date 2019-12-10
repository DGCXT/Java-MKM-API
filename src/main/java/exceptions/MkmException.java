package exceptions;


public class MkmException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MkmException(entities.Error error) {
		super(error.toString());
	}
	
	public MkmException(String string) {
		super(string);
	}

	public MkmException(Exception e) {
		super(e);
	}
}
