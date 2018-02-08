package vhexception;

public class VHBusinessException extends Exception {

	public VHBusinessException(String msg, Exception e) {
		super(msg);
		System.out.println(msg);
		e.printStackTrace();
	}
}
