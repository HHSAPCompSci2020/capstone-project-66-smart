package sye348.database;


import java.io.FileInputStream;
import java.io.IOException;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Title: Username
 * Necessary:
 *  Password
 * For Fun:
 *  Time Played
 *  
 * @author Spencer Ye
 * @version 1.0
 *
 */

public class DataBaseCommunication
{
	
	private DatabaseReference postsRef;
	
	private final String JSONFILE = "TODO", DATABASEURL = "TODO";
	
	private DataSnapshot data;
	
	public DataBaseCommunication()
	{
		FileInputStream refreshToken;
		try 
		{
			refreshToken = new FileInputStream(JSONFILE);
			
			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(refreshToken)).setDatabaseUrl(DATABASEURL).build();

			FirebaseApp.initializeApp(options);
			DatabaseReference database = FirebaseDatabase.getInstance().getReference();
			postsRef = database.child("accounts");

			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		/* Might be useful for future reference
		Runtime.getRuntime().addShutdownHook(new Thread() {
			      public void run()
			      {
			    	  //code here runs after the program is shut down
			      }
			    });
		*/ 
		
	}
	
	public UserData getInfo(String username)
	{
		DatabaseReference userRef = postsRef.child(username);
	
		
		userRef.addChildEventListener(new ChildEventListener()
				{
					@Override
					public void onCancelled(DatabaseError arg0) {}

					@Override
					public void onChildAdded(DataSnapshot arg0, String arg1) {}

					@Override
					public void onChildChanged(DataSnapshot dataSnap, String arg1) 
					{
						data = dataSnap;
					}
					@Override
					public void onChildMoved(DataSnapshot arg0, String arg1) {}					
					
					@Override
					public void onChildRemoved(DataSnapshot arg0) {}
		
				});
		userRef.child("trigger").setValueAsync(false);
		return data.getValue(UserData.class);
	}
	
	public void saveData(UserData data)
	{
		DatabaseReference userRef = postsRef.child(data.author);
		userRef.setValueAsync(data);
	}
	
	
	

}
