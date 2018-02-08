package vh.authentication;

import vh.user.User;
import vh.user.UserDAO;
import vh.user.UserDTO;
import vh.vhutils.VHResponse;
import vh.vhutils.VHValidator;
import vhexception.VHBusinessException;
import vhexception.VHDataAccessException;

public class VHAuthentication {
	public static boolean isValidCredentials(UserDTO userDTO) {
		if(userDTO != null && userDTO.getFirstName() != null)
			return true;

		return false;
	}
	
	public static VHResponse registrationInputValidation(UserDTO userDTO) throws VHBusinessException {
		VHResponse response = new VHResponse();
		
		if(!VHValidator.isValidName(userDTO.getFirstName())) {
			response.setValid(false);
			response.setMsg("Enter valid first name");
			return response;
		} else if(!VHValidator.isValidName(userDTO.getLastName())) {
			response.setValid(false);
			response.setMsg("Enter valid last name");
			return response;
		} else if(!VHValidator.validEmail(userDTO.getEmailId())) {
			response.setValid(false);
			response.setMsg("Enter valid email");
			return response;
		} else if(User.emailExists(userDTO.getEmailId())) {
			response.setValid(false);
			response.setMsg("Email already exists");
			return response;
		} else if(!VHValidator.validMobileNo(userDTO.getMobileNo())) {
			response.setValid(false);
			response.setMsg("Enter valid mobile number");
			return response;
		} else if(User.mobileNoExists(userDTO.getMobileNo())) {
			response.setValid(false);
			response.setMsg("Mobile Number already exists");
			return response;
		} else {
			response.setValid(true);
		}
			
		return response;
	}
}
