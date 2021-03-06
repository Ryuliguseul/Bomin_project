package com.example.bomin_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Mypage extends AppCompatActivity {
    USER user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        user = (USER) intent.getSerializableExtra("User");

        setContentView(R.layout.activity_mypage);

        LinearLayout mypage = (LinearLayout) findViewById (R.id.mypage);
        mypage.setBackgroundColor(Color.LTGRAY);

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(user.getName());

        TextView safetyscore = (TextView)findViewById(R.id.safetyscore);
        safetyscore.setText(Integer.toString(user.getSafety_score()));
        
        //나의 안전점수
        RelativeLayout mysafetyscore = (RelativeLayout)findViewById(R.id.mysafetyscore);
        mysafetyscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MySafetyScore.class);
                Log.d("check", "mysafetyscore 클릭");
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        //나의 포인트 및 쿠폰
        RelativeLayout mypoint = (RelativeLayout)findViewById(R.id.mypoint);
        mypoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mypoint.class);
                Log.d("check", "mypoint 클릭");
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        //보험 가입
        LinearLayout insurance = (LinearLayout) findViewById (R.id.insurance);
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Insurance.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        //나의 보험
        LinearLayout myinsure = (LinearLayout) findViewById (R.id.myinsure);
        myinsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Myinsure.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

        //마이페이지
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mypage.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });

    }
}