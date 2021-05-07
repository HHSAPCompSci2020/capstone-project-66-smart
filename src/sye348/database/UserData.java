package sye348.database;

public class UserData 
{
	public String author;
	public String password;
	private boolean trigger;
	
	/**
	 * Creates a account information tab with the given username and password
	 * @param username The username of the account
	 * @param password The password of the account
	 */
	public UserData(String username, String password, boolean trigger) 
	{
		this.author = username;
		this.password = password;
		this.trigger = trigger;
	}
	
	/**
	 * Returns the username of the account
	 * @return The username of the account
	 */
	public String getUsername() { return author; }
	
	/**
	 * Returns the password of the account
	 * @return The password of the account
	 */
	public String getPassword() { return password; }
	
	
	/**
	 * Returns a string of all the information in this class, which is useful for debugging
	 * @return All the information contained in this class
	 */
	public String toString() { return "NAME: " + author + " with a password of " + password; }
	
}

