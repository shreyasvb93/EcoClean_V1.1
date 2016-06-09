package com.example.shrey.ecoclean_v11;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SalesConsolePage1Activity extends AppCompatActivity {

    protected static String VEHICLE_NUMBER;
    Button sales_next_console;
    EditText vehicle_number;
    InputStream is = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_console_page1);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        sales_next_console = (Button) findViewById(R.id.Sales_vehicle_number_next);
        vehicle_number = (EditText) findViewById(R.id.vehicle_number_text);

        sales_next_console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                add_vehicle(vehicle_number.getText().toString());
                VEHICLE_NUMBER = vehicle_number.getText().toString();
                Intent i = new Intent(com.example.shrey.ecoclean_v11.SalesConsolePage1Activity.this, SalesConsolePage2Activity.class);
                startActivity(i);
            }
        });
    }

    void add_vehicle(String number_plate){
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        /*
        *Problem assumed is with the SELECTED_DEALER.
        * Get the  Dealer_id from the Dealer name and assign it a new ID String.
        * Preferred if this is done in Dealer class itself.
         */

        nameValuePairs.add(new BasicNameValuePair("ownerid", ChooseDealerActivity.SELECTED_DEALER));
        nameValuePairs.add(new BasicNameValuePair("numberplate", number_plate));
        nameValuePairs.add(new BasicNameValuePair("chasisnumber", "asdfddsfff"));
        try {

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("http://192.168.26.1/ecoclean_info/newVehicle.php");

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            is = entity.getContent();

            String msg = "Vehicle Info Entered Successfully";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
        } catch (UnsupportedEncodingException e) {
            Log.e("ClientProtocol", "Log_tag");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Log_tag", "IOException");
            e.printStackTrace();
        }
    }

}
