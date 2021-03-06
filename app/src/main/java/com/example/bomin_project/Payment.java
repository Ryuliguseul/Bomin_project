package com.example.bomin_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.*;

public class Payment extends AppCompatActivity {

    Button join;
    USER user;
    int product_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("User");
        product_index = intent.getIntExtra("Product", 0);

        //입금 확인이나 신용카드 결제 관련 로직 필요//
        Log.d("check", "Payment");

        //PRODUCT UPDATE
        //user info update -> current product
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );
                    Log.d("check", "ProductUser Update");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        String name = user.getName();
        String email = user.getEmail();

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");
        String date = simpleDate.format(mDate);

        ProductUserUpdateRequest updateRequest = new ProductUserUpdateRequest(name, email, product_index, date, responseListener);
        RequestQueue queue = Volley.newRequestQueue( Payment.this );
        queue.add( updateRequest );

        join  = findViewById(R.id.join);
        join.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Success.class);
                intent.putExtra("User", user);
                intent.putExtra("Product", product_index);
                startActivity(intent);
            }
        });
    }
}
