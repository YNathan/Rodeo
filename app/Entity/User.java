package Entity;

/**
 * 
 * @author Yaacov
 *
 */
public class User {
	private String szUserName;
	private String szTelephone;
	private String szEmail;
	private String szPassword;
	private String szUserId;

	public User(String szUserName, String szTelephone, String szEmail,
			String szPassword, String szUserId) {
		super();
		this.szUserName = szUserName;
		this.szTelephone = szTelephone;
		this.szEmail = szEmail;
		this.szPassword = szPassword;
		this.szUserId = szUserId;
	}
	public String getUserName() {
		return szUserName;
	}

	public void setUserName(String szUserName) {
		this.szUserName = szUserName;
	}

	public String getTelephone() {
		return szTelephone;
	}

	public void setTelephone(String szTelephone) {
		this.szTelephone = szTelephone;
	}

	public String getEmail() {
		return szEmail;
	}

	public void setEmail(String szEmail) {
		this.szEmail = szEmail;
	}

	public String getPassword() {
		return szPassword;
	}

	public void setPassword(String szPassword) {
		this.szPassword = szPassword;
	}

	public String getUserId() {
		return szUserId;
	}

	public void setUserId(String szUserId) {
		this.szUserId = szUserId;
	}

	@Override
	public String toString() {
		return "User{" +
				"szUserName='" + szUserName + '\'' +
				", szTelephone='" + szTelephone + '\'' +
				", szEmail='" + szEmail + '\'' +
				", szPassword='" + szPassword + '\'' +
				", szUserId='" + szUserId + '\'' +
				'}';
	}
}
