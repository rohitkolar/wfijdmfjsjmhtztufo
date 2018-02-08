package vh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import vh.authentication.VHAuthentication;
import vh.user.User;
import vh.user.UserCredentialsDTO;
import vh.user.UserDTO;
import vh.vhutils.VHAuthenitcationEnum;
import vh.vhutils.VHCaseConstants;
import vh.vhutils.VHConstants;
import vh.vhutils.VHResponse;
import vh.vhutils.VHValidator;
import vhexception.VHBusinessException;

public class VHController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setHeader("Expires", "0"); // Proxies.

		String operationStr = request.getParameter("operation");
		if(!VHValidator.isEmptyString(operationStr)) {
			int operation = Integer.parseInt(operationStr);

			if(!validSession(request, operation)) {
				forward(request, response, "view/login.jsp");
			} else {
				switch(operation) {
				case VHCaseConstants.LOGIN:
					// following function contains the logic to validate user.(Check user is Registered user or not)
					UserCredentialsDTO credentialsDTO = prepareUserDTO(request);
					VHResponse vhResponse = loginInputValidation(credentialsDTO);
					if(vhResponse.isValid()) {
						validateUser(request, response, credentialsDTO);
					} else {
						request.setAttribute(VHConstants.LBL_RESPONSE, vhResponse);
						forward(request, response, "view/login.jsp");
					}
					break;

				case VHCaseConstants.LOGIN_OUT:
					HttpSession session = request.getSession();
					session.removeAttribute("userDetails");
					session.invalidate();
					redirect(request, response, "view/login.jsp");
					break;
				case VHCaseConstants.USER_HOME:
					forward(request, response, "view/user_home.jsp");
					break;
					
				case VHCaseConstants.REGISTRATION:
					VHResponse regResponse = new VHResponse();
					UserDTO userDTO = prepareUserDTOForRegistration(request);
					try {
						regResponse = VHAuthentication.registrationInputValidation(userDTO);
						if(regResponse.isValid()) {
							int rowsAffected = User.addNewUser(userDTO);
							if(rowsAffected > 0) {
								redirect(request, response, "view/registraion_success.jsp");
							} else {
								regResponse.setValid(false);
								regResponse.setMsg("Something went wrong, please try again");
								request.setAttribute(VHConstants.LBL_RESPONSE, regResponse);
								forward(request, response, "view/registration.jsp");
							}
						} else {
							request.setAttribute(VHConstants.LBL_RESPONSE, regResponse);
							forward(request, response, "view/registration.jsp");
						}
					} catch (VHBusinessException e) {
						redirect(request, response, "view/error_page.jsp");
					}
					break;
				default:
					redirect(request, response, "view/error_page.jsp");
					break;
				}
			}
		} else {
			redirect(request, response, "view/login.jsp");
		}
	}

	private UserCredentialsDTO prepareUserDTO(HttpServletRequest request) {
		UserCredentialsDTO userDTO = new UserCredentialsDTO();

		userDTO.setUserName(request.getParameter("username"));
		userDTO.setPassword(request.getParameter("password"));
		return userDTO;
	}
	
	private UserDTO prepareUserDTOForRegistration(HttpServletRequest request) {
		UserDTO userDTO = new UserDTO();

		userDTO.setFirstName(request.getParameter("firstName"));
		userDTO.setLastName(request.getParameter("lastName"));
		userDTO.setEmailId(request.getParameter("email"));
		userDTO.setMobileNo(request.getParameter("mobileNo"));
		userDTO.setPassword(request.getParameter("password"));
		
		return userDTO;
	}

	private VHResponse loginInputValidation(UserCredentialsDTO credentialsDTO) {
		VHResponse response = new VHResponse();
		if(!VHValidator.isEmptyString(credentialsDTO.getUserName()) && !VHValidator.isEmptyString(credentialsDTO.getUserName())) {
			response.setValid(true);
		} else {
			response.setValid(false);
			response.setMsg(VHAuthenitcationEnum.RESP_EMPTY_INPUT_CREDENTIALS);
		}
		return response;
	}

	private void validateUser(HttpServletRequest request, HttpServletResponse response, UserCredentialsDTO credentialsDTO) {
		UserDTO userDTO = null;
		try {
			userDTO = User.findUserByCredentials(credentialsDTO);
		} catch (VHBusinessException e) {
			forward(request, response, "view/error_page.jsp");
		}

		if(VHAuthentication.isValidCredentials(userDTO)) {
			// Set session attributes and go to user home page
			HttpSession session = request.getSession();
			session.setAttribute("userDetails", userDTO);
			redirect(request, response, buildUrl(
					buildUrlQuery(VHCaseConstants.LBL_OPERATION, String.valueOf(VHCaseConstants.USER_HOME))
					));
		} else {
			VHResponse vhResponse = new VHResponse();
			vhResponse.setValid(false);
			vhResponse.setMsg(VHAuthenitcationEnum.RESP_INVALID_CREDENTIALS);
			request.setAttribute(VHConstants.LBL_RESPONSE, vhResponse);
			forward(request, response, "view/login.jsp");
		}
	}

	private void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void forward(HttpServletRequest request, HttpServletResponse response, String url) {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String buildUrl(String...keyValues) {

		StringBuffer url = new StringBuffer(VHCaseConstants.LBL_VHCONTROLLER + VHConstants.URL_PATH_AND_QUERY_SEPARATOR);
		for(String keyValue : keyValues)
			url = url.append(keyValue + VHConstants.URL_PARAM_SEPERATOR);

		return removeLastChar(url.toString(), VHConstants.URL_PARAM_SEPERATOR);
	}

	private String buildUrlQuery(String key, String value) {
		return key + "=" + value;
	}

	public String removeLastChar(String str, char ch) {
		if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ch) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

	private boolean validSession(HttpServletRequest request, int operation) {
		boolean isValidSession = false;

		HttpSession session = request.getSession();
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// If operation is of type file upload
		if (isMultipart) {
			isValidSession = true;
		} else {
			if (operation == VHCaseConstants.LOGIN) { // If login request
				isValidSession = true;
			} else if (operation == VHCaseConstants.LOGIN_OUT) { // if logout
				isValidSession = true;
			}  else if (operation == VHCaseConstants.REGISTRATION) { // if logout
				isValidSession = true;
			} else {
				if (session == null) {
					isValidSession = false;
				} else {
					UserDTO userDTO = (UserDTO) session.getAttribute("userDetails");
					if (userDTO == null) {
						isValidSession = false;
					} else if (userDTO.getFirstName() == null) {
						isValidSession = false;
					} else {
						isValidSession = true;
					}
				}

			}
		}
		return isValidSession;
	}
}

