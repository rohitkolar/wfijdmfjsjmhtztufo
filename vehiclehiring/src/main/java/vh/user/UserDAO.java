package vh.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vh.dbmanager.VHDBManager;
import vh.vhutils.VHConstants;
import vhexception.VHDBConnectionException;
import vhexception.VHDataAccessException;

public class UserDAO {

	public static UserDTO getUserByCredentials(UserCredentialsDTO credentialsDTO) throws VHDataAccessException {
		UserDTO userDTO = null;

		Connection con = null;
		String errorMsg = "Error while getting user detials by credentials "
				+ "where username : " + credentialsDTO.getUserName()
				+ " & passowrd : " + credentialsDTO.getPassword();

		try {
			con = VHDBManager.getConnection(con);
		} catch (VHDBConnectionException e) {
			throw new VHDataAccessException(errorMsg, e);
		}

		try {
			String sql = "SELECT USER_ID, FIRST_NAME, LAST_NAME, MOBILE_NO, EMAIL_ID, CREATED_ON, MODIFIED_ON "
					+ "FROM vh_user_details "
					+ "WHERE EMAIL_ID = ? AND PASSWORD = ? AND IS_DELETED = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, credentialsDTO.getUserName());
			statement.setString(2, credentialsDTO.getPassword());
			statement.setString(3, VHConstants.IS_DELETED_NO);
			
			System.out.println(statement.toString());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("USER_ID"));
				userDTO.setFirstName(resultSet.getString("FIRST_NAME"));
				userDTO.setLastName(resultSet.getString("LAST_NAME"));
				userDTO.setEmailId(resultSet.getString("EMAIL_ID"));
				userDTO.setMobileNo(resultSet.getString("MOBILE_NO"));
			}
			
		} catch(SQLException e) {
			throw new VHDataAccessException(errorMsg, e);
		}
		
		return userDTO;
	}
	
	public static boolean emailExists(String email) throws VHDataAccessException {
		boolean emailExists = false;

		Connection con = null;
		String errorMsg = "Error while checking if email exists or not where email Id is: " + email;

		try {
			con = VHDBManager.getConnection(con);
		} catch (VHDBConnectionException e) {
			throw new VHDataAccessException(errorMsg, e);
		}

		try {
			String sql = "SELECT EMAIL_ID "
					+ "FROM vh_user_details "
					+ "WHERE EMAIL_ID = ? AND IS_DELETED = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, VHConstants.IS_DELETED_NO);
			
			System.out.println(statement.toString());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				emailExists = true;
			} else {
				emailExists = false;
			}
			
		} catch(SQLException e) {
			throw new VHDataAccessException(errorMsg, e);
		} finally {
			if(con != null)
				VHDBManager.closeConnection(con);
		}
		
		return emailExists;
	}
	
	public static boolean mobileNoExists(String mobileNo) throws VHDataAccessException {
		boolean mobileNoExists = false;

		Connection con = null;
		String errorMsg = "Error while checking if mobile no exists or not where mobile no is: " + mobileNo;

		try {
			con = VHDBManager.getConnection(con);
		} catch (VHDBConnectionException e) {
			throw new VHDataAccessException(errorMsg, e);
		}

		try {
			String sql = "SELECT MOBILE_NO "
					+ "FROM vh_user_details "
					+ "WHERE MOBILE_NO = ? AND IS_DELETED = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, mobileNo);
			statement.setString(2, VHConstants.IS_DELETED_NO);
			
			System.out.println(statement.toString());
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				mobileNoExists = true;
			} else {
				mobileNoExists = false;
			}
			
		} catch(SQLException e) {
			throw new VHDataAccessException(errorMsg, e);
		} finally {
			if(con != null)
				VHDBManager.closeConnection(con);
		}
		
		return mobileNoExists;
	}
	
	public static int addNewUser(UserDTO userDTO) throws VHDataAccessException {
		Connection con = null;
		String errorMsg = "Error while adding new user";
		int rowsAffected = 0;

		try {
			con = VHDBManager.getConnection(con);
		} catch (VHDBConnectionException e) {
			throw new VHDataAccessException(errorMsg, e);
		}
		
		try {
			con.setAutoCommit(false);
			String sql = "INSERT INTO vh_user_details (FIRST_NAME, LAST_NAME, MOBILE_NO, EMAIL_ID, PASSWORD, CREATED_ON) "
					+ "VALUES (?,?,?,?,?,NOW())";
			
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, userDTO.getFirstName());
			statement.setString(2, userDTO.getLastName());
			statement.setString(3, userDTO.getMobileNo());
			statement.setString(4, userDTO.getEmailId());
			statement.setString(5, userDTO.getPassword());
			
			System.out.println(statement.toString());
			rowsAffected = statement.executeUpdate();
			
			con.commit();
			
		} catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new VHDataAccessException(errorMsg, e1);
			}
			
			throw new VHDataAccessException(errorMsg, e);
		} finally {
			if(con != null)
				VHDBManager.closeConnection(con);
		}
		
		return rowsAffected;
	}
}
