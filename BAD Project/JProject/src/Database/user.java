package Database;

public class user {

	private String UserID, Username, UserEmail, UserPassword,
	UserGender, UserDOB, UserPhoneNumber, UserAddress, UserRole, currUser;
	
	
	public user() {
		// TODO Auto-generated constructor stub
		
	}
	public user(String username, String pass, String id, String role) {
		// TODO Auto-generated constructor stub
		this.Username = username;
		this.UserPassword = pass;
		this.UserID = id;
		this.UserRole = role;
	}
	
	public user(String phone) {
		// TODO Auto-generated constructor stub
		this.UserPhoneNumber = phone;
	}
	
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public String getUserGender() {
		return UserGender;
	}
	public void setUserGender(String userGender) {
		UserGender = userGender;
	}
	public String getUserDOB() {
		return UserDOB;
	}
	public void setUserDOB(String userDOB) {
		UserDOB = userDOB;
	}
	public String getUserPhoneNumber() {
		return UserPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		UserPhoneNumber = userPhoneNumber;
	}
	public String getUserAddress() {
		return UserAddress;
	}
	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}
	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	
	

}
