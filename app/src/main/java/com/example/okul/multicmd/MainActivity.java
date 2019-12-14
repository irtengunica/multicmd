package com.example.okul.multicmd;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    SharedPreferences preferences;
    public static String URL = "http://turulay.com/multiisim4.php";//Bilgisayarýn IP adresi
    boolean tumunuac=false;
    boolean tumunukapa=false;
    boolean adurum1=false;
    boolean adurum2=false;
    boolean adurum3=false;
    boolean adurum4=false;
    boolean kdurum1=false;
    boolean kdurum2=false;
    boolean kdurum3=false;
    boolean kdurum4=false;
    String CihazID;
    boolean internetBaglantisiVarMi() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {

            return false;

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ac_butonu=(Button)findViewById(R.id.ac_button);
        Button kapat_butonu=(Button) findViewById(R.id.kapat_button);
        Button d1ac_buton=(Button) findViewById(R.id.d1ac_button);
        Button d1kapat_buton=(Button) findViewById(R.id.d1kapat_button);
        Button d2ac_buton=(Button) findViewById(R.id.d2ac_button);
        Button d2kapat_buton=(Button) findViewById(R.id.d2kapat_button);
        Button d3ac_buton=(Button) findViewById(R.id.d3ac_button);
        Button d3kapat_buton=(Button) findViewById(R.id.d3kapat_button);
        Button d4ac_buton=(Button) findViewById(R.id.d4ac_button);
        Button d4kapat_buton=(Button) findViewById(R.id.d4kapat_button);
        final EditText editText=(EditText)findViewById(R.id.editText1);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        CihazID=preferences.getString("CihazID", "Cihaz ID Gir");
        editText.setText(CihazID);
        //URL="http://turulay.com/isim4.php?did="+CihazID+"&durumu=";
       threadcalistir();

        kapat_butonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String isim="0";
                //fetchJsonTask a = new fetchJsonTask();
                //a.execute(URL + isim);
                tumunukapa = true;
                threadcalistir();


            }
        });

        ac_butonu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //String isim=editText.getText().toString();
                //String isim = "1";
                //fetchJsonTask a = new fetchJsonTask();
                //a.execute(URL + isim);
                tumunuac = true;
                threadcalistir();


            }
        });
        d1ac_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adurum1=true;
                threadcalistir();
            }
        });
        d1kapat_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdurum1=true;
                threadcalistir();
            }
        });
        d2ac_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adurum2=true;
                threadcalistir();
            }
        });
        d2kapat_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdurum2=true;
                threadcalistir();
            }
        });
        d3ac_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adurum3=true;
                threadcalistir();
            }
        });
        d3kapat_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdurum3=true;
                threadcalistir();
            }
        });
        d4ac_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adurum4=true;
                threadcalistir();
            }
        });
        d4kapat_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kdurum4=true;
                threadcalistir();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(this,"ayarlar sayfasý gelecek",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),ayarlarfrm.class);
                startActivity(i);
                break;

            case R.id.item2:
                Toast.makeText(this,"Program Sonlanacak",Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);


        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            //return true;
        }

        //return super.onOptionsItemSelected(item);
        return true;
    }

    public static String connect(String url,String CihazID,String durum1,String durum2,String durum3,String durum4){
        HttpClient httpClient=new DefaultHttpClient();
        //HttpGet httpget = new HttpGet(url);
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=UTF-8");
        httppost.addHeader("User-Agent", "Mozilla/4.0");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("did", CihazID));
        params.add(new BasicNameValuePair("durum1", durum1));
        params.add(new BasicNameValuePair("durum2", durum2));
        params.add(new BasicNameValuePair("durum3", durum3));
        params.add(new BasicNameValuePair("durum4", durum4));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpResponse response;
        try {
            response=httpClient.execute(httppost);
            HttpEntity entity=response.getEntity();
            if(entity!=null){
                InputStream instream=entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
                StringBuilder sb = new StringBuilder();
                String line = null;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        instream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return sb.toString();
            }
        } catch (Exception e) {
        }
        return null;
    }




    class fetchJsonTask extends AsyncTask<String, Integer, JSONObject> {
        //public String CihazID="13872516";
        public String durum1="_";
        public String durum2="_";
        public String durum3="_";
        public String durum4="_";


        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                if(tumunuac){
                    tumunuac=false;
                    durum1="1";
                    durum2="1";
                    durum3="1";
                    durum4="1";
                }
                if(tumunukapa){
                    tumunukapa=false;
                    durum1="0";
                    durum2="0";
                    durum3="0";
                    durum4="0";
                }
                if(adurum1){
                    adurum1=false;
                    durum1="1";
                }
                if(kdurum1){
                    kdurum1=false;
                    durum1="0";
                }
                if(adurum2){
                    adurum2=false;
                    durum2="1";
                }
                if(kdurum2){
                    kdurum2=false;
                    durum2="0";
                }
                if(adurum3){
                    adurum3=false;
                    durum3="1";
                }
                if(kdurum3){
                    kdurum3=false;
                    durum3="0";
                }
                if(adurum4){
                    adurum4=false;
                    durum4="1";
                }
                if(kdurum4){
                    kdurum4=false;
                    durum4="0";
                }
                String ret = connect(params[0],CihazID,durum1,durum2,durum3,durum4);
                ret = ret.trim();
                JSONObject jsonObj = new JSONObject(ret);
                return jsonObj;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(JSONObject result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (result != null) {
                parseJson(result);
            } else {
                TextView tv = (TextView) findViewById(R.id.textView1);
                tv.setText("Kayýt Bulunamadý");
                tv.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Sistemden Herhangi bir bilgi gelmedi.",
                        Toast.LENGTH_LONG).show();
            }

        }
    }

    public void parseJson(JSONObject ogrenciJson) {
        TextView Durum1 = (TextView) findViewById(R.id.Durum1);
        TextView Durum2 = (TextView) findViewById(R.id.Durum2);
        TextView Durum3 = (TextView) findViewById(R.id.Durum3);
        TextView Durum4 = (TextView) findViewById(R.id.Durum4);
        TextView devicead1 = (TextView) findViewById(R.id.devicead1);
        TextView devicead2 = (TextView) findViewById(R.id.devicead2);
        TextView devicead3 = (TextView) findViewById(R.id.devicead3);
        TextView devicead4 = (TextView) findViewById(R.id.devicead4);
        TextView multideviceID = (TextView) findViewById(R.id.multideviceID);
        TextView multideviceIP = (TextView) findViewById(R.id.multideviceIP);
        TextView multideviceDate = (TextView) findViewById(R.id.multideviceDate);
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setText("");
        System.out.println(ogrenciJson);
        try {
            Durum1.setText(ogrenciJson.getString("Durum1"));
            Durum2.setText(ogrenciJson.getString("Durum2"));
            Durum3.setText(ogrenciJson.getString("Durum3"));
            Durum4.setText(ogrenciJson.getString("Durum4"));
            multideviceDate.setText("Tarih="+ogrenciJson.getString("Durum5"));
            multideviceIP.setText("IP No="+ogrenciJson.getString("Durum6"));
            multideviceID.setText("Cihaz ID="+ogrenciJson.getString("Durum7"));
            devicead1.setText(ogrenciJson.getString("Durum8"));
            devicead2.setText(ogrenciJson.getString("Durum9"));
            devicead3.setText(ogrenciJson.getString("Durum10"));
            devicead4.setText(ogrenciJson.getString("Durum11"));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public  void threadcalistir(){
        fetchJsonTask a = new fetchJsonTask();

        a.execute(URL);
        Thread t6 = new Thread() {
            public void run() {

                try {
                    //sleep(5000);
                    fetchJsonTask b = new fetchJsonTask();
                    Thread.sleep(10000);
                    b.execute(URL);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //finish();
                }

            }
            //public void finish(){

            //}
        };

        if (!internetBaglantisiVarMi()) {
            Toast.makeText(getApplicationContext(), "Internet Baðlantýnýz yok", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Internet Baðlantýnýz var. Ýþlemin Tamamlanmasý Ýçin 15 Saniye Bekleyiniz.", Toast.LENGTH_LONG).show();
            t6.start();


        }
    }

}
