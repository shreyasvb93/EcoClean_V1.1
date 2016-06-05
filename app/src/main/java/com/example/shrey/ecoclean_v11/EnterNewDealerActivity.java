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

public class EnterNewDealerActivity extends AppCompatActivity {


    EditText client_name;
  //  EditText client_brand;
    EditText client_location;
    Button submit;

    InputStream input = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_dealer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        client_name = (EditText) findViewById(R.id.newdealer_name_editText);
      //  client_brand = (EditText) findViewById(R.id.newdealer_brand_editText);
        client_location = (EditText) findViewById(R.id.newdealer_location_editText);

        submit = (Button) findViewById(R.id.newdealer_submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = client_name.getText().toString();
                String location = client_location.getText().toString();
                if (ChooseBrandActivity.SELECTED_BRAND == null) {
                    Toast.makeText(getApplicationContext(), "No was Selected", Toast.LENGTH_LONG).show();
                }
                else if (!name.equals("") || !location.equals("")) {
                    addNewClient(name, location);
                } else {
                    Toast.makeText(getApplicationContext(), "Something Missing", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    protected void addNewClient(String name, String location){
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
        nameValuePairs.add(new BasicNameValuePair("clientname", name));
        nameValuePairs.add(new BasicNameValuePair("clientlocation", location));
        try {

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("http://192.168.26.1/ecoclean_info/newdealeradd.php?key=" + ChooseBrandActivity.SELECTED_BRAND);

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            input = entity.getContent();

            String msg = "Data entered Successfully";
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

            Intent i = new Intent(EnterNewDealerActivity.this, ChooseDealerActivity.class);
            startActivity(i);

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
