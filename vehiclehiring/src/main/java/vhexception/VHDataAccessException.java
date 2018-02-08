package vhexception;

public class VHDataAccessException extends Exception {

	public VHDataAccessException(String msg, Exception e) {
		super(msg, e);
		System.out.println(msg);
		e.printStackTrace();
	}
}
