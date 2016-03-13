package com.moelesterindustries.vlad.wannaplay;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by vlad on 12/10/2015.
 */
public class BaseAdapterClass extends BaseAdapter {

    private Activity activity;
    private ArrayList timestamp;
    private ArrayList sport;

    private ArrayList city;
    private ArrayList country;
    private ArrayList event_date;
    private ArrayList players;
    private ArrayList username;
    private ArrayList postcode;
    private ArrayList name;
    private ArrayList id;
    private ArrayList numberof;

    private String sp;
    private String dt;
    private String ct;
    private String ts;
    private String p;
    private String u;
    private String pst;
    private String n;
    private String numof;

    private String co;
    private Intent ine;


    public BaseAdapterClass(Activity a,ArrayList timestamp,ArrayList sport,  ArrayList city, ArrayList country, ArrayList event_date,ArrayList players,ArrayList username,ArrayList postcode,ArrayList name,ArrayList id,ArrayList numberof)
    {
        this.activity = a;
        this.timestamp = timestamp;
        this.sport = sport;

        this.city = city;
        this.country = country;
        this.event_date = event_date;
        this.players = players;

        this.username = username;
        this.postcode = postcode;
        this.name = name;
        this.id = id;
        this.numberof = numberof;


System.out.println("APELATA");

    }
    @Override
    public int getCount() {
        if(timestamp==null) return 0;
        return timestamp.size();
    }

    @Override
    public Object getItem(int position) {
        if(timestamp==null) return null;
        return timestamp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;


        if(convertView==null)
        {
            LayoutInflater inflater= activity.getLayoutInflater();
            v = inflater.inflate(R.layout.row_listitem,parent,false);
            System.out.println("CACAT");
        }


        System.out.println("AFISARE");

                TextView sport2 = (TextView) v.findViewById(R.id.sport);
                 sp = sport.get(position).toString();
                sport2.setText(sp);



        TextView date2 = (TextView) v.findViewById(R.id.date);
        dt = event_date.get(position).toString();
        date2.setText(dt);



        TextView city2 = (TextView) v.findViewById(R.id.city);
        ct = city.get(position).toString();
        city2.setText(ct);

        TextView country2 = (TextView) v.findViewById(R.id.country);
         co = country.get(position).toString();
        country2.setText(co);

        TextView timestamp2 = (TextView) v.findViewById(R.id.times);
         ts = timestamp.get(position).toString();
        timestamp2.setText(ts);

        TextView pla = (TextView) v.findViewById(R.id.play);
         p = players.get(position).toString();
        pla.setText("Players: "+p);

        TextView usr = (TextView) v.findViewById(R.id.user);
         u = username.get(position).toString();
       usr.setText(u);

        TextView po = (TextView) v.findViewById(R.id.post);
         pst = postcode.get(position).toString();
        po.setText(pst);


        TextView na = (TextView) v.findViewById(R.id.nams);
        n = name.get(position).toString();
        na.setText(n);

        TextView nuof = (TextView) v.findViewById(R.id.numbero);
        numof = numberof.get(position).toString();
        nuof.setText("/"+numof);

        ine = new Intent(activity.getApplicationContext(), evpage.class);
        sendToPage(ine, position,v);




        return v;
    }


    public void sendToPage(final Intent in,final int position,View v)
    {
        final int pos = position;


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                in.putExtra("sport",sport.get(pos).toString());
                in.putExtra("eventdate",event_date.get(pos).toString());
                in.putExtra("city",city.get(pos).toString());
                in.putExtra("country",country.get(pos).toString());
                in.putExtra("timestamp",timestamp.get(pos).toString());
                in.putExtra("players",players.get(pos).toString());
                in.putExtra("username",username.get(pos).toString());
                in.putExtra("postcode",postcode.get(pos).toString());
                in.putExtra("name",name.get(pos).toString());
                in.putExtra("id",id.get(pos).toString());
                in.putExtra("numberof",numberof.get(position).toString());
            activity.finish();
                activity.startActivity(in);



            }
        });

    }


}
