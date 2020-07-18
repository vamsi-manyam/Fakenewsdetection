package com.vam.firebasedemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button submit, fetch;
    TextView demoValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.etValue);
        demoValue = (TextView) findViewById(R.id.tvValue);
        submit = (Button) findViewById(R.id.btnSubmit);
        fetch = (Button) findViewById(R.id.btnFetch);


       DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
       final DatabaseReference sub = mDatabase.child("demo");

        sub.push().setValue("helooooo");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = editText.getText().toString();

                //push creates a unique id in database
                sub.child("satheesh").setValue(value);
                Log.e("a","errroorroororororo");
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sub.child("value").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        demoValue.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
    }
}
