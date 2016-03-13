package com.moelesterindustries.vlad.wannaplay;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 12/10/2015.
 */
public class getEvent extends AppCompatActivity {


    ArrayList<String> username = new ArrayList<String>();
    ArrayList<String> timestamp = new ArrayList<String>();
    ArrayList<String> sport = new ArrayList<String>();
    ArrayList<String> postcode = new ArrayList<String>();
    ArrayList<String> city = new ArrayList<String>();
    ArrayList<String> country = new ArrayList<String>();
    ArrayList<String> event_date = new ArrayList<String>();
    ArrayList<String> players = new ArrayList<String>();
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<String> id = new ArrayList<String>();
    ArrayList<String> numberof = new ArrayList<String >();


    ListView liste;
    BaseAdapterClass adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        liste = (ListView) findViewById(R.id.listView1);
//        init();
//        outputLine("Request started...");
        new getData().execute("http://www.vladniculescu.com/databases/android/wannaplay/getevents.php");


        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my);

          setSupportActionBar(myToolbar);
        setTitle("");


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.men, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.news:
                newevent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void newevent()
    {
        Intent ins = new Intent(this, createEvent.class);
finish();
                startActivity(ins);
    }


    private class getData extends AsyncTask<String,Double,JSONArray> {

        @Override
        protected  JSONArray doInBackground(String... params) {

            if(params.length!=1) return null;

            try{
                URL url = new URL(params[0]);
                InputStream inStream = url.openStream();
                DataInputStream dataInStream = new DataInputStream(inStream);

                byte[] buffer = new byte[1024];
                int bufferLength;

                ByteArrayOutputStream output  = new ByteArrayOutputStream();

                while((bufferLength = dataInStream.read(buffer))>0)
                {
                    output.write(buffer,0,bufferLength);
                }
             //   outputLine("Success1!");
                System.out.println(output);
                return new JSONArray(output.toString("UTF-8"));

            }catch (Exception e){

             //   outputLine("Something went wrong1!");
                return null;
            }
        }

        protected void onPostExecute(JSONArray result)
        {
            System.out.println("LENGTH IS "+result.length());
            if(result ==null ) return;
            try {
                JSONArray list = result.getJSONArray(0);
                System.out.println(list.length());
                JSONObject l;
                for(int i= list.length()-1;i>=0;i--)
                {
                    l = list.getJSONObject(i);
               //     System.out.println(l.length());
                    username.add("User: "+l.getString("username"));
                    timestamp.add("Posted on: "+l.getString("timestamp"));
                    sport.add(l.getString("sport"));
                    postcode.add("Location: "+l.getString("postcode"));
                    city.add(l.getString("city")+",");
                    country.add(l.getString("country"));
                    event_date.add("Available: "+l.getString("event_date"));
                    players.add(l.getString("members"));
                    name.add(l.getString("name"));
                    id.add(l.getString("id"));
                    numberof.add(l.getString("numberof"));

//System.out.println("!!");
                //    outputLine(l.getString("timestamp"));
                //    outputLine("Success2!");

                }
                adapter = new BaseAdapterClass(getEvent.this,timestamp,sport,city,country,event_date,players,username,postcode,name,id,numberof);
                liste.setAdapter(adapter);

            }catch (Exception e)
            {
            //    outputLine("Something went wrong2!");
            }
        }
    }
    protected void init() {
        outputView = (TextView) findViewById(R.id.output);
    }
    private TextView outputView;
    private String output = "";
    protected void outputLine(String s) {
        output += (new DateTime()).toString("HH:mm:ss") + " <strong>" + s + "</strong><br />";
        outputView.setText(Html.fromHtml(output));
    }

}
