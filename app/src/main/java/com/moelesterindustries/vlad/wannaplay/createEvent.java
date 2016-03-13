package com.moelesterindustries.vlad.wannaplay;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import javax.microedition.khronos.egl.EGLDisplay;

public class createEvent extends AppCompatActivity {


    private String name;
   private  String sport;
   private  String postcode;
   private  String city;
  private   String country;
    private String numberof;

   DatePicker datep;
    TimePicker timep;


    private String y;
    private String m;
    private String d;

    private  String h;
    private String mi;

    private String dates;
    private  String time;
    private String finaldate;
    EditText nm,sp,po,ci,co,nop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        nm  = (EditText) findViewById(R.id.evname);
        sp  = (EditText)findViewById(R.id.spo);
         po = (EditText) findViewById(R.id.postal);
          ci = (EditText) findViewById(R.id.cit);
         co = (EditText) findViewById(R.id.con);
         nop = (EditText) findViewById(R.id.nopl);


        cleartext(nm);
        cleartext(sp);
        cleartext(po);
        cleartext(ci);
        cleartext(co);
        cleartext(nop);


        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my);

        setSupportActionBar(myToolbar);
        setTitle("");

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.men2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.news:
                create();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void create()
    {
        //CALL CLASS TO CREATE
        name =  nm.getText().toString();
        sport = sp.getText().toString();
        postcode =  po.getText().toString();
        city =  ci.getText().toString();
        country =  co.getText().toString();
        numberof = nop.getText().toString();


        finaldate = getDate()+":"+getTime();
        postevent task = new postevent(1);
        task.execute(name, sport, postcode, city, country, numberof, finaldate, "Vlad", "1");

        Intent ins = new Intent(getApplicationContext(), getEvent.class);
        finish();
        startActivity(ins);
    }
    public void fini()
    {
        this.finish();
    }

    public void cleartext(EditText ed)
    {
        final EditText eds = ed;
        eds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eds.setText("");
            }
        });
    }

    private String getDate()
    {

        datep = (DatePicker) findViewById(R.id.datePicker);

        int   day  = datep.getDayOfMonth();
        int   month= datep.getMonth() + 1;
        int   year = datep.getYear();

        d = checkDigit(day);
        m = checkDigit(month);
        y = checkDigit(year);
       return  dates = d+":"+m+":"+y;


    }

    private String getTime()
    {

        timep = (TimePicker) findViewById(R.id.timePicker);

        int   hour  = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = timep.getHour();
        }
        int   min= 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            min = timep.getMinute();
        }


        h = checkDigit(hour);
        mi = checkDigit(min);

      return   time = h+":"+mi;


    }


    public String checkDigit(int number)
    {
        return String.valueOf(number);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ins = new Intent(getApplicationContext(), getEvent.class);
        finish();
        startActivity(ins);
    }
}
