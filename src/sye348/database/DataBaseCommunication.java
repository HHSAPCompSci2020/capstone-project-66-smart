package sye348.database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

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
 *
 */

public class DataBaseCommunication implements ActionListener, ChildEventListener
{
	
	private DatabaseReference postsRef;
	
	private final String JSONFILE = "TODO", DATABASEURL = "TODO";
	
	public DataBaseCommunication()
	{
		FileInputStream refreshToken;
		try 
		{
			refreshToken = new FileInputStream(JSONFILE);
			
			FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(refreshToken)).setDatabaseUrl(DATABASEURL).build();

			FirebaseApp.initializeApp(options);
			DatabaseReference database = FirebaseDatabase.getInstance().getReference();
			postsRef = database.child("posts");

			postsRef.addChildEventListener(this);
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCancelled(DatabaseError arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildAdded(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildChanged(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildMoved(DataSnapshot arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChildRemoved(DataSnapshot arg0) {
		// TODO Auto-generated method stub

	}

}
