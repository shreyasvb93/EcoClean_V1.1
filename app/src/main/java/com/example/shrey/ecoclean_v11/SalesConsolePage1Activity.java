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

public class SalesConsolePage1Activity extends AppCompatActivity {

    Button sales_next_console;
    EditText vehicle_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_console_page1);
        sales_next_console = (Button) findViewById(R.id.Sales_vehicle_number_next);
        vehicle_number = (EditText) findViewById(R.id.vehicle_number_text);

        sales_next_console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.SalesConsolePage1Activity.this, SalesConsolePage2Activity.class);
                startActivity(i);
            }
        });
    }

}
