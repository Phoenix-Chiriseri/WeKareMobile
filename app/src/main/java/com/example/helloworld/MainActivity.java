package com.example.helloworld;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.helloworld.Adapters.ImageListAdapter;
import com.example.helloworld.Models.ListItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.Manifest;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton callForEnquiries;
    ListView listView;
    String phoneNumber="0771 255 849";
    int requestCode = 3;

    //the textview that will be clicked to show the b-e.digital website
    TextView txtBranding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = findViewById(R.id.listView);
        txtBranding = (TextView)findViewById(R.id.txtBranding);
        //button to call for any enquiries
        callForEnquiries = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //Create a list of items and add it to the list items
        List<ListItem> itemList = new ArrayList<>();
        itemList.add(new ListItem(R.drawable.go, "Navigate To Online Job Board","Visit Our Job Board And Easily Apply For A Job"));
        itemList.add(new ListItem(R.drawable.pencil, "Save Notes On Job","Save Quick Notes On Your Phone"));
        itemList.add(new ListItem(R.drawable.quality, "Recommend A Job","Recommend A Job To Someone Via WhatsApp"));
        //itemList.add(new ListItem(R.drawable.quality, "Search Content On Job"));
        itemList.add(new ListItem(R.drawable.facebook,"Our Facebook Page","Visit And Like Our Facebook Page "));
        // Create and set the adapter
        ImageListAdapter adapter = new ImageListAdapter(this, itemList);
        listView.setAdapter(adapter);

        txtBranding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //replaced with the url of the webpage that i want to use
                String url = "https://www.b-e.digital/";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    //this is the main navigation to the view available jobs activity
                    case (0):
                        if (isNetworkAvailable()) {
                            Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                            myWebLink.setData(Uri.parse("https://munanacreatives.co.zw/job-board/"));
                            startActivity(myWebLink);
                        } else {
                            // Network is available, show a Snackbar.
                            Snackbar.make(findViewById(android.R.id.content), "Network not available", Snackbar.LENGTH_SHORT).show();
                        }

                        break;

                    //this is the navigation that will go to the navigate to jobboard activity
                    case 1:
                        startActivity(new Intent(MainActivity.this, SaveNotes.class));
                        break;

                    case 2:
                        startActivity(new Intent(getApplicationContext(), RecommendAFriend.class));
                        break;
                    case 3:
                            if (isNetworkAvailable()) {
                                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                                myWebLink.setData(Uri.parse("https://www.facebook.com/profile.php?id=61551243421157"));
                                startActivity(myWebLink);
                            } else {
                                //Network is not  available, show a Snackbar
                                Snackbar.make(findViewById(android.R.id.content), "Network not available", Snackbar.LENGTH_SHORT).show();
                            }
                        break;
                     }
                 }

        });

        //this is the button that will make the phone calls to the server
        callForEnquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    // Permission is already granted, so you can make the call
                    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);
                } else {
                    // Permission has not been granted. Request it from the user.
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, requestCode);
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int YOUR_REQUEST_CODE = 1;

        if (requestCode == YOUR_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted, you can make the call now
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                startActivity(callIntent);
            } else {
                // Permission denied by the user; handle it as needed (e.g., show a message or ask again)
            }
            //ArrayAdapter<String> adapter = new ArrayAdapter<>(String)

        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Exit?");
        alertDialog.setMessage("are you sure you want to exit?");
        alertDialog.setPositiveButton("OK",new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alertDialog.setNegativeButton("NO",new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();
    }
}
