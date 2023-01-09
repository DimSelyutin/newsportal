package by.htp.ex.util.validation;

public class ValidException extends Exception{
    private static final long serialVersionUID = 8814453066415187129L;

	public ValidException() {
		super();
	}
	
	public ValidException(String message) {
		super(message);
	}
	
	public ValidException(Exception e) {
		super(e);
	}
	
	public ValidException(String message, Exception e) {
		super(message, e);
	}  
}
