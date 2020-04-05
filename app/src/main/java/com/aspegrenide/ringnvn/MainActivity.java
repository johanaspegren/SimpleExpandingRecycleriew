package com.aspegrenide.ringnvn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
Look for the firebase here
https://console.firebase.google.com/u/0/project/ringnvn/database/ringnvn/data
 */
public class MainActivity extends AppCompatActivity {

    // save username in prefs
    RecAdapter adapter;
    SharedPreferences prefs;
    public static final String PREFS = "mypref";
    public static final String USER = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // get user if not known
        prefs = getPreferences(Context.MODE_PRIVATE);
        String user_name = prefs.getString(USER, null);

        if (user_name == null) {
            promptUserName();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create mock data
        //DataManager dm = new DataManager();
        //dm.populateContactList();

        final List<Contact> contactList = new ArrayList<Contact>();
        final ArrayList<CallDetails> callList = new ArrayList<CallDetails>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();

        databaseReference.child("calls").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // get all children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //shake hands with all items
                for (DataSnapshot child : children) {
                    CallDetails callDetail = child.getValue(CallDetails.class);
                    callList.add(callDetail);
                    for (Contact contact : contactList) {
                        if (callDetail.getPhoneNr().equalsIgnoreCase(contact.getPhoneNr())) {
                            // kolla om den är tidigare
                            contact.setLastCallDate(callDetail.getTimeStampStart());
                            contact.setLastCaller(callDetail.getCaller());
                        }
                    }
                }
                adapter.notifyDataSetChanged(); //update the screen
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("contacts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // get all children at this level
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                //shake hands with all items
                for (DataSnapshot child : children) {
                    Contact contact = child.getValue(Contact.class);
                    contactList.add(contact);

                    for (CallDetails call : callList) {
                        if (call.getPhoneNr().equalsIgnoreCase(contact.getPhoneNr())) {
                            // kolla om den är tidigare
                            contact.setLastCallDate(call.getTimeStampStart());
                            contact.setLastCaller(call.getCaller());
                        }
                    }
                }
                adapter.notifyDataSetChanged(); //update the screen
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter = new RecAdapter(MainActivity.this, contactList, callList);
        RecyclerView recyclerView = findViewById(R.id.recview);
        ImageView hbg = (ImageView)findViewById(R.id.hbg_staende);
        hbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the log activity
                startCallLog();
            }
        });

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).
                setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void promptUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registrerar dig med namn första gången");

        // Set up the input
        final EditText input = new EditText(this);

        // Specify the type of input expected; this, for example, sets the input as a password,
        // and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String user_name = input.getText().toString();
                Context context = getApplicationContext();

                prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(USER, user_name);
                editor.commit();
                String new_user_name = prefs.getString(USER, null);
                //Log.d("PROMPTPOST","receiveds " + user_name);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void startCallLog() {
        Intent intent = new Intent(this, CallLogActivity.class);
        startActivity(intent);
    }

//    Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon"> www.flaticon.com</a>
}
