package com.example.muhtamimnahid.lu_hakaton_admin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity implements View.OnClickListener {

    private CardView cardSummaries, cardchangestatus, cardtopvoters, cardtopsubmitter;
    Button Logout;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cardSummaries = findViewById(R.id.cardsummaries);
        cardchangestatus = findViewById(R.id.ChangeStatus);
        cardtopvoters = findViewById(R.id.cardtopvoters);
        cardtopsubmitter = findViewById(R.id.cardtopsubmitters);
        Logout = findViewById(R.id.logout);


        cardSummaries.setOnClickListener(this);
        cardchangestatus.setOnClickListener(this);
        cardtopvoters.setOnClickListener(this);
        cardtopsubmitter.setOnClickListener(this);
        Logout.setOnClickListener(this);


       Home.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return Home.context;
    }

    public static int getPx(Context context, int dimensionDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
             case R.id.logout:
                 FirebaseAuth.getInstance().signOut();
                 finish();
                 startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                 break;

            case R.id.ChangeStatus:
                startActivity(new Intent(this, HomePostActivity.class));
                break;
//            case R.id.cardtopvoters:
//                startActivity(new Intent(this, CalculatorActivity.class));
//                break;
//            case R.id.cardtopsubmitters:
//                startActivity(new Intent(this, CgpaActivity.class));
//                break;

        }
    }
}
