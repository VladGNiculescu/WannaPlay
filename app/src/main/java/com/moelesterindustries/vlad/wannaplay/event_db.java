package com.moelesterindustries.vlad.wannaplay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;





        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.AsyncTask;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;

public class event_db extends AsyncTask<String,Void,String> {


    private TextView statusField,idField,staField;
    private Context context;
    private int byGetOrPost = 0;
    private ProgressDialog upDialog;
    private Boolean server_down = false;
    private String nume_tabel_date;

    public static String filename = "Vlad";
    SharedPreferences data;
    SharedPreferences.Editor editor;

    //flag 0 means get and 1 means post.(By default it is get.)
    public event_db(Context context, TextView staField,
                                        int flag) {
        this.context = context;
        this.staField = staField;
        this.idField = idField;
        byGetOrPost = flag;

        upDialog = new ProgressDialog(context);

    }

    protected void onPreExecute(){
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
                nume_tabel_date = (String)arg0[0];
//        String password = (String)arg0[1];
////                String serie    = (String)arg0[2];
//        String email=  (String)arg0[2];
                String link="http://www.vladniculescu.com/databases/android/wannaplay/event_db_gen.php";
                String data  = URLEncoder.encode("nume_tabel_date", "UTF-8")
                        + "=" + URLEncoder.encode(nume_tabel_date, "UTF-8");
//        data += "&" + URLEncoder.encode("password", "UTF-8")
//        + "=" + URLEncoder.encode(password, "UTF-8");
//        data += "&" + URLEncoder.encode("email", "UTF-8")
//        + "=" + URLEncoder.encode(email, "UTF-8");

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

            //         staField.setText(result);
        }

        if(result.toString().equals("down"))
        {
            Toast.makeText(context, "Our server is currently down", Toast.LENGTH_SHORT).show();
        }






    }

}