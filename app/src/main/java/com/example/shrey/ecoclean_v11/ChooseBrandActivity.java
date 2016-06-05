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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ChooseBrandActivity extends AppCompatActivity {

    Spinner dropdown_brand;
    Button brand_next, brand_new;
    //ImageButton back_brand;
    ArrayList<String> brands_list = new ArrayList<String>();
    InputStream is=null;
    protected static String SELECTED_BRAND;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_brand);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        dropdown_brand = (Spinner) findViewById(R.id.spinner_brand);
        brand_next = (Button) findViewById(R.id.brand_next_button);
        brand_new = (Button) findViewById(R.id.brand_new_button);
        //connectToDB();
        // back_brand = (ImageButton)findViewById(R.id.brand_back);
        //some changes

//        brands_list.add("Audi");
//        brands_list.add("BMW");
//        brands_list.add("Hero MotoCorp");
//        brands_list.add("Honda");
//        brands_list.add("Hyundai");
//        brands_list.add("Mahendra");
//        brands_list.add("Maruti Suzuki");
//        brands_list.add("Mercedes");
//        brands_list.add("Nissan");
//        brands_list.add("Tata");
//        brands_list.add("Toyota");
//        brands_list.add("Skoda");
//        brands_list.add("Volkswagen");

        try {
            String  url = "http://192.168.0.16/ecoclean_info/getBrandNames.php";

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
                for(int i=0; i < count; i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    temp += json_data.getString("brand_name") + ":";
                }
                arr = temp.split(":");
              //  System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
                //System.out.println("Print Something");

                int i = arr.length;
                i = i-1;
                while (i >= 0){
                    brands_list.add(arr[i]);
                    i--;
                }

                for(String model : brands_list) {
                    System.out.println(model.toString());
                }

                 dropdown_brand.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, brands_list));
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
        brand_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SELECTED_BRAND = (String) dropdown_brand.getSelectedItem().toString();
                System.out.println(SELECTED_BRAND);
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseBrandActivity.this, ChooseDealerActivity.class);
                startActivity(i);




            }
        });

        /*
        * This Button when clicked, transfers the user to a new page where  new brand names can be entered.
         */
        brand_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.shrey.ecoclean_v11.ChooseBrandActivity.this, EnterNewBrandActivity.class);
                startActivity(i);

            }
        });


    }



  /*  protected void connectToDB(){
        try {
            String  url = "http://192.168.26.1/ecoclean_info/getBrandNames.php";

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
                for(int i=0; i < count; i++){
                    JSONObject json_data = jsonArray.getJSONObject(i);
                    temp += json_data.getString("brand_name") + ":";
                }
                arr = temp.split(":");
                brands_list = (ArrayList<String>) Arrays.asList(arr);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, brands_list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dropdown_brand.setAdapter(dataAdapter);
                // lv.setAdapter(new ArrayAdapter<String>(DisplayDB.this, android.R.layout.simple_list_item_1, arr));
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
    }

    protected void getAllBrands(){
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
            for(int i=0; i < count; i++){
                JSONObject json_data = jsonArray.getJSONObject(i);
                temp += json_data.getString("brand_name") + ":";
            }
            arr = temp.split(":");
            brands_list = (ArrayList<String>) Arrays.asList(arr);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, brands_list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dropdown_brand.setAdapter(dataAdapter);
            // lv.setAdapter(new ArrayAdapter<String>(DisplayDB.this, android.R.layout.simple_list_item_1, arr));
        }catch(Exception  e){
            System.out.println("SomeShit Happens");
        }
    }*/

}
