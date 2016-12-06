package com.example.alana.exercise22;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MyTag";
    private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        resolver=this.getContentResolver();

        Button buttonAll=(Button)findViewById(R.id.buttonAll);
        buttonAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cusor=resolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,
                        null);
                while(cusor.moveToNext()){
                    String msg;
                    String id=cusor.getString(cusor.getColumnIndex(ContactsContract.Contacts._ID));
                    msg="id:"+id;
                    String name=cusor.getString(cusor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    msg=msg+"name:"+name;
                    Cursor phoneNumbers=resolver.query(ContactsContract.CommonDataKinds.Phone.
                            CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +"="+id,null,null);
                    while(phoneNumbers.moveToNext()){
                        String phoneNumber=phoneNumbers.getString(phoneNumbers.
                                getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        msg=msg+" phone:"+phoneNumber;
                    }
                    Cursor emails=resolver.query(ContactsContract.CommonDataKinds.Email.
                            CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID +"="+id,null,null);
                    while(emails.moveToNext()){
                        String email=emails.getString(emails.getColumnIndex(ContactsContract.
                                CommonDataKinds.Email.DATA));
                        msg=msg+" email:"+email;
                    }
                    Log.v(TAG,msg);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
