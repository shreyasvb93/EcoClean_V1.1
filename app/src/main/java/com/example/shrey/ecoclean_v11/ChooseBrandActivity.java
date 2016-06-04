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

public class ChooseBrandActivity extends AppCompatActivity {

    Spinner dropdown_brand;
    Button brand_next, brand_new;
    //ImageButton back_brand;
    ArrayList<String> brands_list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_brand);

        dropdown_brand = (Spinner) findViewById(R.id.spinner_brand);
        brand_next = (Button) findViewById(R.id.brand_next_button);
        brand_new = (Button) findViewById(R.id.brand_new_button);
        // back_brand = (ImageButton)findViewById(R.id.brand_back);
        //some changes

        brands_list.add("Audi");
        brands_list.add("BMW");
        brands_list.add("Hero MotoCorp");
        brands_list.add("Honda");
        brands_list.add("Hyundai");
        brands_list.add("Mahendra");
        brands_list.add("Maruti Suzuki");
        brands_list.add("Mercedes");
        brands_list.add("Nissan");
        brands_list.add("Tata");
        brands_list.add("Toyota");
        brands_list.add("Skoda");
        brands_list.add("Volkswagen");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, brands_list);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown_brand.setAdapter(dataAdapter);

        //Next Page Button
        brand_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseBrandActivity.this, ChooseDealerActivity.class);
                startActivity(i);
            }
        });

        brand_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseBrandActivity.this, EnterNewBrandActivity.class);
                startActivity(i);
            }
        });


    }

}
