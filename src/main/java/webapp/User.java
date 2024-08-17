package webapp;

public class User {
	private String name;
	private String message;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String name, String message, String email) {
		super();
		this.name = name;
		this.message = message;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", message=" + message + ", email=" + email + "]";
	}
	
	

}
