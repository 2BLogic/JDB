package com.example.tp04;

import com.example.tp04putain.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/*import com.google.gdata.client.*;
import com.google.gdata.client.contacts.*;
import com.google.gdata.data.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;*/
import java.io.IOException;
import java.net.URL;



public class MainActivity extends Activity {

	public static Journal jdb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        if(jdb == null){// Un peu le "First Launch"
        	jdb = new Journal("Journal1"); // On cr�e un nouveau Journal
	        jdb.Path = getFilesDir(); // On lui donne la variable du Path o� on enregistre les fichiers
        }
         
        // Loader les sessions de travail pr�c�dente
        //jdb.JournaldeBord = jdb.loadFromDevice(); 
        //Afficher une Toast le nombre d'�v�nement dans le Journal | Facultatif
        Toast toast;
		Context context = this;
		String text;
		text = ("Nombre d'evenement au Journal : " + String.valueOf(MainActivity.jdb.size()));
		toast = Toast.makeText(context, text, 1);
		toast.setGravity(Gravity.TOP, 0, 15);
		toast.show();       
	}
	
	/**
	*
	* @author Charles Perreault
	*
	* M�thode qui part l'Activit� TimeTrack
	*/
    public void mTimeTrack(View view) {
    	Intent intent = new Intent (this, TimeTrackActivity.class); 
    	startActivity(intent);
    }
    
	/**
	*
	* @author Charles Perreault
	*
	* M�thode qui part l'activit� ReadLog
	*/
    public void mReadLog(View view) {
    	Intent intent = new Intent (this, ReadLogActivity.class); 
   
    	startActivity(intent);
    }

	/**
	*
	* @author Charles Perreault
	*
	* M�thode qui part l'activit� d'�criture dans le journal
	*/
    public void mWriteLog(View view) {
    	//Determiner si on peut ecrire
    	if (PunchedIn()) { // si on est PUNCH� ACTIF, ALORS ON PEUT TRAVAILLER ET ECRIRE DANS LE JOURNAL
    		Intent intent = new Intent (this, WriteLogActivity.class); //on part l'activit�
        	startActivity(intent);
		} else {
			Toast toast;
			Context context = this;
			String text;
			text = ("Vous devez avoir punch� IN pour �crire dans le journal"); //qui est �crit �a dessus
			toast = Toast.makeText(context, text, 1);
			toast.setGravity(Gravity.TOP, 0, 15);
			toast.show();
		}
    }
    
	/**
	*
	* @author Anthony Pugliese
	*
	* V�rifie dans la liste si le dernier �l�ment est un punch IN 
	*/
    private boolean PunchedIn(){
    	if(!MainActivity.jdb.JournaldeBord.isEmpty()){//Si
			evenementJournal dernierEnevement =	MainActivity.jdb.findLastEvent();
			if(dernierEnevement.type == 1){ //c'est un punch in
				return true;
			}
    	}
			return false;
    }
    
	/**
	*
	* @author Charles Perreault, Anthony Pugliese
	*
	* 
	*/
	/*private boolean PunchedInOrFalse(){
		/*
		 *  Event Type
		 * 	Type 0 = Undefined Event Type (or RAW)
		 *  Type 1 = PunchIn
		 *  Type 2 = PunchOut Event
		 *  Type 3 = Comment Or Note Event
		 *  Type 4 = other
		 * 
		 * 		 
		evenementJournal ejPunchedIN;
		evenementJournal ejPunchedOut;
		ejPunchedIN = jdb.findLastEvent(1);
		ejPunchedOut = jdb.findLastEvent(2);
		
		//Regarde si les valeures ne sont pas null pour �viter les erreures
		if(ejPunchedIN != null && ejPunchedOut != null){
		long l_ejPI = Long.parseLong(ejPunchedIN.Data);
		long l_ejPO = Long.parseLong(ejPunchedOut.Data);
		
			if (l_ejPI >= l_ejPO) {
				return true;
			}
		}
		//Les valeures sont nulls ou punchIn < punchOut
		return false;		
	}
    */
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void ContactsActivity(View view){
    	System.out.println("bouton pese");
    	Intent intent = new Intent(this, ContactsActivity.class);
    	startActivity(intent);
    }
}
