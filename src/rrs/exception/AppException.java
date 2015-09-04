package rrs.exception;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -837562222714934559L;

	/**
	public AppException(){
		super();
	}
	**/
	
	public AppException(String msg) {
		super(msg);
	}

	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
