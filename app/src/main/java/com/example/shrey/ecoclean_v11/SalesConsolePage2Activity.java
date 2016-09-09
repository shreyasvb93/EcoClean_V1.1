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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SalesConsolePage2Activity extends AppCompatActivity {

    EditText invoice_number;
    Spinner executives;
    Button submit;
    InputStream is = null;
    private List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
    int Exec_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_console_page2);

        invoice_number = (EditText) findViewById(R.id.sales_invoice_textfield);
        executives = (Spinner) findViewById(R.id.sales_exec_spinner);
        submit = (Button) findViewById(R.id.sales_final_submit);

        final List<String> Exec_list;
        final Map<Integer, String> exec_list = new HashMap<>();
        getExecInfo(exec_list);
        Exec_list = new ArrayList<>(exec_list.values());

        executives.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Exec_list));
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String invoice = invoice_number.getText().toString();
               // String selected_exec = executives.getSelectedItem().toString();
                //Exec_ID = exec_list.
             //   Exec_ID = Integer.parseInt(exec_list.get(selected_exec));

               // System.out.println(Exec_ID);
                addNewSales(ChooseDealerActivity.SELECTED_DEALER, SalesConsolePage1Activity.VEHICLE_NUMBER, 1, Integer.parseInt(invoice));
                Intent intent = new Intent(com.example.shrey.ecoclean_v11.SalesConsolePage2Activity.this, com.example.shrey.ecoclean_v11.FinalConfirmationActivity.class);
                startActivity(intent);

//                System.out.println("Dealer: " + ChooseDealerActivity.SELECTED_DEALER + "VHNM: " + SalesConsolePage1Activity.VEHICLE_NUMBER);
//                System.out.println("Invoice Number" + Integer.parseInt(invoice));


            }
        });


    }

    void getExecInfo(Map<Integer, String> a) {
        connectToDb("getExecs.php", true);
        String result = null;
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
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

        try {
            String[] arr;
            String temp1 = "";
            JSONArray jsonArray = new JSONArray(result);
            int count = jsonArray.length();
            for (int i = 0; i < count; i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                String temp2 = json_data.getString("First_Name") + " " + json_data.getString("Last_Name");
                a.put(json_data.getInt("ID"), temp2);
               // json_data.getString("ID");

                temp1 += temp2 + ":";
            }

        } catch (Exception e) {
            System.out.println("SomeShit Happens");
        }
    }

    void addNewSales(String clientId, String vehicleNumber, int execId, int invoiceNumber){
        nameValuePairs.add(new BasicNameValuePair("clientid", clientId));
        nameValuePairs.add(new BasicNameValuePair("vehicleNumber", vehicleNumber));
        nameValuePairs.add(new BasicNameValuePair("execID", "" + execId));
        nameValuePairs.add(new BasicNameValuePair("invoiceNumber", "" + invoiceNumber));

        for (NameValuePair a : nameValuePairs) {
            System.out.println(a.getValue());
        }

        //System.out.println("Reached Method 1");
        connectToDb("newSalesRecord.php", false);
    }



    void connectToDb(String phpScript, boolean Get){
        try {
            String  url = "http://192.168.26.1/ecoclean_info/" + phpScript;


            HttpEntity httpEntity = null;
            DefaultHttpClient httpClient = new DefaultHttpClient();  // Default HttpClient
            if (Get) {
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }
            else {
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpClient.execute(httpPost);
                httpEntity = response.getEntity();
                is = httpEntity.getContent();
                Toast.makeText(getApplicationContext(), "Record installed", Toast.LENGTH_LONG).show();
            }


            // getAllBrands();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.println("Client PRotocol!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O");
        }

    }

}
