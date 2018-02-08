package vh.vhutils;

public class VHValidator {
	public static boolean isEmptyString(String str) {
		if(str == null)
			return true;
		else if(str.length() == 0)
			return true;
		else
			return false;
	}
	
	public static boolean validMobileNo(String input) {
		return !VHValidator.isEmptyString(input) && input.matches("[0-9]{10}");
	}
	
	public static boolean validEmail(String input) {
		return !VHValidator.isEmptyString(input) && input.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
	}
	
	public static boolean isValidName(String input) {
		boolean a = input.matches("\\w*");
		boolean b = !VHValidator.isEmptyString(input);
		return a && b;
	}
}
