package com.moelesterindustries.vlad.wannaplay;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class InsertClass extends AsyncTask<String,Void,String> {



    private Context context;
    private int byGetOrPost = 0;
    //private ProgressDialog upDialog;
    private Boolean server_down = false;
    private String username;

    public static String filename = "Vlad";
    SharedPreferences data;
    SharedPreferences.Editor editor;



    //flag 0 means get and 1 means post.(By default it is get.)
    public InsertClass(Context context,int flag) {
        this.context = context;
        byGetOrPost = flag;
        // upDialog = new ProgressDialog(context);

    }

    protected void onPreExecute(){
        //     Handler mHandler = new Handler();
//        upDialog.setMessage("Loading... Please wait");
//        upDialog.show();
//        super.onPreExecute();
        // pDialog = new TransparentProgressDialog(context,R.drawable.loadingcircle);
        //  pDialog.show();

//        mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                        pDialog.dismiss();
//                }
//        }, 2500);

    }
    @Override
    protected String doInBackground(String... arg0) {
        if(byGetOrPost == 1){ //means by Get Method



            try{
                username = (String)arg0[0];

//                String serie    = (String)arg0[2];
                String email=  (String)arg0[1];
                String link="http://www.vladniculescu.com/databases/android/wannaplay/register.php";
                String data  = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");

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

        System.out.println(result);

        if(result.equals("down"))
        {

            Toast.makeText(context, "Our server is currently down", Toast.LENGTH_SHORT).show();
        }
        if(result.toString().equals("succesfull"))
        {


            data = context.getSharedPreferences(filename, 0);
            editor = data.edit();

            editor.putString("usr", username);
            editor.putBoolean("logged",true);
            editor.commit();




//	   pDialog.dismiss();


        }
        if(result.toString().equals("failed"))
        {

            Toast.makeText(context, "Username or e-mail already in use", Toast.LENGTH_SHORT).show();
        }
        if(result.toString().equals("indata"))
        {

            Toast.makeText(context, "Already registered", Toast.LENGTH_SHORT).show();
            data = context.getSharedPreferences(filename, 0);
            editor = data.edit();

            editor.putString("usr", username);
            editor.putBoolean("logged",true);
            editor.commit();

            System.out.println("indata");
        }





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