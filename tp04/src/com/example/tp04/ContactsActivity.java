package com.example.tp04;

import com.example.tp04putain.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

public class ContactsActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
		    while (cur.moveToNext()) {
		        String id = cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts._ID));
		        String name = cur.getString(
	                        cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
		        if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
	 		    //Query phone here.  Covered next
		            Cursor pCur = cr.query(
		  		    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, 
		  		    null, 
		  		    ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", 
		  		    new String[]{id}, null);
		  	        while (pCur.moveToNext()) {
		  		    // Do something with phones
		  	        	
		  	        } 
		  	        pCur.close();
		  	    }
	 	        
	        }
        }
    }
}

