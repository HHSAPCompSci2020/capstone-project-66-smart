package sye348.database;

public class UserData 
{
	private String author;
	
	public UserData() 
	{
		
	}

	public UserData(String username) 
	{
		this.author = username;
	}
	
	public String getUsername() { return author; }
	

	public String toString() { return "NAME: " + author; }
}

