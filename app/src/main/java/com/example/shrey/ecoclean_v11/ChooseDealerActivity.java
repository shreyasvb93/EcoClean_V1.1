package com.example.shrey.ecoclean_v11;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class ChooseDealerActivity extends AppCompatActivity {
    Button dealer_next, dealer_new;
    Spinner dealers;

    ArrayList<String> dealer_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dealer);

        dealers = (Spinner) findViewById(R.id.spinner_dealer);
        dealer_next = (Button) findViewById(R.id.dealer_next_btn);
        dealer_new = (Button) findViewById(R.id.dealer_new_btn);

        dealer_list.add("Andheri Chakala");
        dealer_list.add("Andheri Marol");
        dealer_list.add("SantaCruz");
        dealer_list.add("Malad");
        dealer_list.add("Kandivali");

        ArrayAdapter<String> dealer_dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dealer_list);

        dealer_dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dealers.setAdapter(dealer_dataAdapter);

        //Next Page Button
        dealer_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseDealerActivity.this, ChooseSelectionActivity.class);
                startActivity(i);
            }
        });
        
        dealer_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseDealerActivity.this, EnterNewDealerActivity.class);
                startActivity(i);
            }
        });

    }

}
