package vh.user;

import vhexception.VHBusinessException;
import vhexception.VHDataAccessException;

public class User {
	
	public static UserDTO findUserByCredentials(UserCredentialsDTO credentialsDTO) throws VHBusinessException {
		UserDTO userDTO = null;
		try {
			userDTO = UserDAO.getUserByCredentials(credentialsDTO);
		} catch(VHDataAccessException e) {
			throw new VHBusinessException("Error while getting user details by credentials", e);
		}
		return userDTO;
	}
	
	public static boolean emailExists(String email) throws VHBusinessException {
		boolean exists = false;
		try {
			exists = UserDAO.emailExists(email);
		} catch(VHDataAccessException e) {
			throw new VHBusinessException("Error while checking if email exists or not where email Id is: " + email, e);
		}
		return exists;
	}
	
	public static boolean mobileNoExists(String mobileNo) throws VHBusinessException {
		boolean exists = false;
		try {
			exists = UserDAO.emailExists(mobileNo);
		} catch(VHDataAccessException e) {
			throw new VHBusinessException("Error while checking if mobile no exists or not where email Id is: " + mobileNo, e);
		}
		return exists;
	}
	
	public static int addNewUser(UserDTO userDTO) throws VHBusinessException {
		int rowsAffected = 0;
		try {
			rowsAffected = UserDAO.addNewUser(userDTO);
		} catch (VHDataAccessException e) {
			throw new VHBusinessException("Error while adding new user", e);
		}
		
		return rowsAffected;
	}
}
