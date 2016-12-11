package com.example.shrey.ecoclean_v11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ServicesConsolePage1Activity extends AppCompatActivity {
    Button next;
    EditText vehicle_number;
    protected static String VEHICLE_NUMBER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_console_page1);
        //  vehicle_number = (EditText)findViewById(R.id.services_vehicle_number_edittext);
        next = (Button)findViewById(R.id.services_vehicle_number_next);
        vehicle_number =(EditText) findViewById(R.id.services_vehicle_number_edittext);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VEHICLE_NUMBER = vehicle_number.getText().toString();
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ServicesConsolePage1Activity.this, ServicesConsolePage1Activity.class);
                startActivity(i);
            }
        });
    }

}
