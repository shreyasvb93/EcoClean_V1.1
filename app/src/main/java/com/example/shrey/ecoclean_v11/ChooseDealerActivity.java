package com.example.shrey.ecoclean_v11;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

public class ChooseDealerActivity extends AppCompatActivity {
    Button dealer_next, dealer_new;
    Spinner dropdown_dealers;

    protected static String SELECTED_DEALER = null;

    InputStream is = null;
    ArrayList<String> dealer_list = new ArrayList<String>();

    protected class Client{
        String id;
        String name;
        String location;
        Client (String id, String name, String location){
            this.id = id;
            this.name = name;
            this.location = location;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dealer);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dropdown_dealers = (Spinner) findViewById(R.id.spinner_dealer);
        dealer_next = (Button) findViewById(R.id.dealer_next_btn);
        dealer_new = (Button) findViewById(R.id.dealer_new_btn);

        final ArrayList<Client> clients = new ArrayList<>();
//        dealer_list.add("Andheri Chakala");
//        dealer_list.add("Andheri Marol");
//        dealer_list.add("SantaCruz");
//        dealer_list.add("Malad");
//        dealer_list.add("Kandivali");

        try {
            String  url = "http://192.168.26.1/ecoclean_info/getDealers.php?key=" + ChooseBrandActivity.SELECTED_BRAND;

            HttpEntity httpEntity = null;
            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            HttpGet httpGet = new HttpGet(url);


            HttpResponse httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
            // getAllBrands();

            String result = null;
            String line = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                while((line = reader.readLine())!= null){
                    sb.append(line + "\n");

                    result = sb.toString();
                    is.close();

                    System.out.println("------------Here is my data ----------");
                    System.out.println(result);


                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                String[] arr;
                String temp = "";
                JSONArray jsonArray = new JSONArray(result);
                int count = jsonArray.length();
               // System.out.println(count);
                for(int i=0; i < count; i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    clients.add(new Client(json_data.getString("client_id"), json_data.getString("client_name"), json_data.getString("location")));
                    temp += json_data.getString("client_name") + ": " + json_data.getString("location") + ";";
                    System.out.println(temp);
                }
                arr = temp.split(";");

                int i = arr.length;
                i = i-1;
                while (i >= 0){
                    dealer_list.add(arr[i]);
                    i--;
                }

                for(String model : dealer_list) {
                    System.out.println(model.toString());
                }

                dropdown_dealers.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dealer_list));
            }catch(Exception  e){
                System.out.println("SomeShit Happens");
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println("Client PRotocol me gadbad!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O Lolita!");
        }

        //Next Page Button
        dealer_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SELECTED_DEALER = dropdown_dealers.getSelectedItem().toString();
                String selected_name = dropdown_dealers.getSelectedItem().toString();
                for (Client c : clients){
                  //  System.out.println(c.id + " " + c.name + c.location);
                    if (selected_name.equals(c.name + ": " + c.location)){
                        SELECTED_DEALER = c.id;
                        break;
                    }
                }
                //System.out.println(SELECTED_DEALER);
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
