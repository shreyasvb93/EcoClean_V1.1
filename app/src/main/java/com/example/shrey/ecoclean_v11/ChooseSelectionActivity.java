package com.example.shrey.ecoclean_v11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ChooseSelectionActivity extends AppCompatActivity {

    Button Sale;
    Button Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_selection);
        Sale = (Button) findViewById(R.id.Sales_Selection_btn);
        Service = (Button) findViewById(R.id.Serivice_Selection_btn);

        Sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseSelectionActivity.this, SalesConsolePage1Activity.class);
                startActivity(i);
            }
        });

        Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseSelectionActivity.this, ServicesConsolePage1Activity.class);
                startActivity(i);
            }
        });
    }

}
