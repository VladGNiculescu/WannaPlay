package com.moelesterindustries.vlad.wannaplay;

/**
 * Created by vlad on 12/13/2015.
 */

import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



/**
 * Created by vlad on 12/13/2015.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class postevent extends AsyncTask<String,Void,String> {


    private int byGetOrPost = 0;
    private createEvent c;
    private String nume_tabel_date;
    String username;

//        public static String filename = "Vlad";
//        SharedPreferences data;
//        SharedPreferences.Editor editor;

    //flag 0 means get and 1 means post.(By default it is get.)
    public postevent(int flag) {
        byGetOrPost = flag;




    }

    protected void onPreExecute(){
        //   username = data.getString("usr","unknown");
//        Handler mHandler = new Handler();
//        upDialog.setMessage("Loading... Please wait");
//        upDialog.show();
//        super.onPreExecute();
//        mHandler.postDelayed(new Runnable()
//        {
//                        @Override
//                        public void run() {
//                                upDialog.dismiss();
//                        }
//        }, 1500);

    }
    @Override
    protected String doInBackground(String... arg0) {
        if(byGetOrPost == 1){ //means by Get Method



            try{

                String name = (String)arg0[0];
                String sport   = (String)arg0[1];
                String postcode = (String)arg0[2];
                String city   = (String)arg0[3];
                String country = (String)arg0[4];
                String numberof   = (String)arg0[5];
                String finaldate = (String)arg0[6];
                String username = (String)arg0[7];

                System.out.println("SE TRIMITE");
                String link="http://www.vladniculescu.com/databases/android/wannaplay/createevent.php";
                String
                        data = "&" + URLEncoder.encode("name", "UTF-8")
                        + "=" + URLEncoder.encode(name, "UTF-8");
                data += "&" + URLEncoder.encode("sport", "UTF-8")
                        + "=" + URLEncoder.encode(sport, "UTF-8");
                data += "&" + URLEncoder.encode("postcode", "UTF-8")
                        + "=" + URLEncoder.encode(postcode, "UTF-8");
                data += "&" + URLEncoder.encode("city", "UTF-8")
                        + "=" + URLEncoder.encode(city, "UTF-8");
                data += "&" + URLEncoder.encode("country", "UTF-8")
                        + "=" + URLEncoder.encode(country, "UTF-8");
                data += "&" + URLEncoder.encode("numberof", "UTF-8")
                        + "=" + URLEncoder.encode(numberof, "UTF-8");
                data += "&" + URLEncoder.encode("finaldate", "UTF-8")
                        + "=" + URLEncoder.encode(finaldate, "UTF-8");
                data += "&" + URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(username, "UTF-8");

                System.out.println("SE TRIMITE2");
                URL url = new URL(link);
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter
                        (conn.getOutputStream());
                wr.write( data );
                wr.flush();
                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                // Read Server Response
                while((line = reader.readLine()) != null)
                {


                    sb.append(line);

                    break;
                }
                return sb.toString();
            }catch(Exception e){
                return //new String("Exception: " + e.getMessage());
                        new String("down");
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result){
        //this.statusField.setText(result);

        if(result.toString().equals("succesfull"))
        {

//staField.setText(result);
        }
        if(result.toString().equals("failed"))
        {
            System.out.println("fail");
            //         staField.setText(result);
        }

        if(result.toString().equals("down"))
        {
            // this.idField.setText("Our server is currently down");
        }

        System.out.println(result);


//     if(result!="")
//     {
//     Intent intent = new Intent(context, Log_in.class);
//     context.startActivity(intent);
//     }
    }
//   protected void onPostExecute2(String sta)
//   {
//	   this.staField.setText(sta);
//   }
}