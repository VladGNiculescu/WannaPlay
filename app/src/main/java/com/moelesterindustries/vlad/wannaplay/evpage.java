package com.moelesterindustries.vlad.wannaplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class evpage extends AppCompatActivity {

    Button join;
    String id;
    String players;
    TextView pla;
    String email = "test@gmail.com";
    private Boolean joined = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evpage);

        Intent myIntent = getIntent();
        String sport = myIntent.getStringExtra("sport");
        String eventdate= myIntent.getStringExtra("eventdate");
        String city= myIntent.getStringExtra("city");
        String country= myIntent.getStringExtra("country");
        String timestamp= myIntent.getStringExtra("timestamp");
         players= myIntent.getStringExtra("players");
        String username= myIntent.getStringExtra("username");
        String postcode= myIntent.getStringExtra("postcode");
        String name= myIntent.getStringExtra("name");
         id = myIntent.getStringExtra("id");
        String number = myIntent.getStringExtra("numberof");

        TextView sport2 = (TextView) findViewById(R.id.sport2);

        sport2.setText(sport);



        TextView date2 = (TextView) findViewById(R.id.date2);

        date2.setText(eventdate);



        TextView city2 = (TextView) findViewById(R.id.city2);

        city2.setText(city);

        TextView country2 = (TextView) findViewById(R.id.country2);

        country2.setText(country);

        TextView timestamp2 = (TextView) findViewById(R.id.times2);

        timestamp2.setText(timestamp);

         pla = (TextView) findViewById(R.id.play2);

        pla.setText("Players: "+players);

        TextView usr = (TextView)findViewById(R.id.user2);

        usr.setText(username);

        TextView po = (TextView) findViewById(R.id.post2);

        po.setText(postcode);

        TextView num = (TextView) findViewById(R.id.numbof);

        num.setText("/"+number);



        TextView n = (TextView) findViewById(R.id.nam2);

        n.setText(name);

    join = (Button) findViewById(R.id.jo);
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(joined ==false)
                {
                    System.out.println("+1");
                    joinMatch task = new joinMatch(1);
                    task.execute(email, id, "1");

join.setText("LEAVE");
                    pla.setText("Players: "+(Integer.valueOf(players)+1));
                    joined = true;
                }else
                {
                    System.out.println("-1");
                    unjoinMatch task2 = new unjoinMatch(1);
                    task2.execute(email,id,"1");
                    pla.setText("Players: "+players);
                    join.setText("JOIN");
                    joined = false;
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ins = new Intent(getApplicationContext(), getEvent.class);
        finish();
        startActivity(ins);

    }
}
