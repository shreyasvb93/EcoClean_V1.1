package com.example.shrey.ecoclean_v11;

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

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class EnterNewBrandActivity extends AppCompatActivity {

    Button enter_brand;
    EditText brand_name;

    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_brand);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        enter_brand = (Button) findViewById(R.id.newbrand_submit_button);
        brand_name = (EditText) findViewById(R.id.newbrand_editText);

        enter_brand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String brand = brand_name.getText().toString();
                addNewBrand(brand);
            }
        });

    }

    protected void addNewBrand(String brandname){
       /* try {

            HttpClient httpClient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost("http://192.168.26.1/ecoclean_info/clientadd.php");

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            input = entity.getContent();

            String msg = "Data entered Successfully";
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
*/
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//
//        }

    }

}
