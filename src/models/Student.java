package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Student {

	static int count = 0;
	static final int NUMBER_OF_ASSIGNMENTS = 5;
	
	int id;
	String hashcode;
	String firstName;
	String lastName;	
	String email;
	String password;
	double[] scores = new double[NUMBER_OF_ASSIGNMENTS];
	
	private static String bytesToHex( String idString) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("models-Student-bytesToHex-Algorith SHA-256 is not supported in this platform.");
			e.printStackTrace();
		}
		byte[] encodedhash = digest.digest( idString.getBytes(StandardCharsets.UTF_8) );
	    StringBuffer hexString = new StringBuffer();
	    for ( int i = 0; i < encodedhash.length; i++ ) {
	    		String hex = Integer.toHexString(0xff & encodedhash[i]);
	    		if ( hex.length() == 1 )
	    			hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public Student() {}
	
	public Student( String firstName, String lastName, String email, String password) {
		this.id = ++count;
		this.hashcode = bytesToHex(Integer.toString(this.id));
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		// Randomly assign scores to this student;
		for ( int i = 0; i < NUMBER_OF_ASSIGNMENTS; i++ )
			scores[i] = Math.random() * 100;
	}
	
	public Student( int id, String firstName, String lastName, String email, String password, int score1, int score2, int score3, int score4, int score5) {
		this.id = id;
		this.hashcode = bytesToHex(Integer.toString(id));
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		scores[0] = score1;
		scores[1] = score2;
		scores[2] = score3;
		scores[3] = score4;
		scores[4] = score5;
	}
	
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public double[] getScores() {
		return this.scores;
	}

	public void setScores(double[] scores) {
		this.scores = scores;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId( int id) {
		this.id = id;
	}
	
	public String getHashcode() {
		return this.hashcode;
	}
	
	public void setHashcode( String hashcode) {
		this.hashcode = hashcode;
	}
	
}