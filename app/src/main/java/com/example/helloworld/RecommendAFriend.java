package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.helloworld.Adapters.NotesAdapter;
import com.google.android.material.snackbar.Snackbar;

public class RecommendAFriend extends AppCompatActivity {


    EditText jobName,jobDate,jobNotes;
    Button reccomend;
    Spinner shiftSpinner;
    String selectedShift;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend);

        jobName = (EditText) findViewById(R.id.jobNameEditText);
        jobDate = (EditText) findViewById(R.id.dateEditText);
        reccomend = (Button) findViewById(R.id.recommend);
        shiftSpinner = (Spinner)findViewById(R.id.shiftSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shift_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shiftSpinner.setAdapter(adapter);

        // Set a listener for the spinner
        shiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //Toast.makeText(SaveNotes.this, "Selected Shift: " + selectedShift, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        reccomend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = jobName.getText().toString();
                String date = jobDate.getText().toString();
                selectedShift = shiftSpinner.getSelectedItem().toString();

                if(name.equals("")||date.equals("")||selectedShift.equals("")){

                    Snackbar.make(view,"Please Dont Leave Any Empty Fields",Snackbar.LENGTH_SHORT).show();
                }
                else{
                    //NotesAdapter adapter = new NotesAdapter();
                    try {
                        String message = "Theres a job that im recommending you from  WeKare Intergrated Services requiring " + name + "  " +
                                "\nDate for the job is " + date +
                                "\nThe shift is: " + selectedShift;
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                        sendIntent.setType("text/plain");
                        sendIntent.setPackage("com.whatsapp"); // Specify WhatsApp package
                        startActivity(sendIntent);
                    } catch (android.content.ActivityNotFoundException ex) {
                        // Handle the case where WhatsApp is not installed on the device
                        Toast.makeText(RecommendAFriend.this, "WhatsApp is not installed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }
}
