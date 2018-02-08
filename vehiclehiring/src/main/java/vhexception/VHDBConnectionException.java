package vhexception;

public class VHDBConnectionException extends Exception {
	public VHDBConnectionException(String msg,Exception e) {
		super(msg, e);
		System.out.println(msg);
		e.printStackTrace();
	}
}
