package models;

public class Tutor {

	static int count = 0;
	
	int id;
	String firstName;
	String lastName;	
	String email;
	String courses;
	
	public Tutor( String firstName, String lastName, String email, String courses) {
		this.id = ++count;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.courses = courses;
	}

	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCourses() {
		return courses;
	}
	
	public void setCourses(String courses) {
		this.courses = courses;
	}
	
	public int getId() {
		return id;
	}
	
}