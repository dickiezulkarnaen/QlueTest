package com.example.dickiez.cluetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dickiez.cluetest.Model.Data;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    Data dataList;
    TextView username;
    TextView kelurahan;
    TextView description;
    ImageView image;
    Button btnMap1;
    Button btnMap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        username = (TextView)findViewById(R.id.txt_det_username);
        kelurahan = (TextView)findViewById(R.id.txt_det_kelurahan);
        description = (TextView)findViewById(R.id.txt_det_descripion);
        image = (ImageView)findViewById(R.id.det_img);
        btnMap1 = (Button)findViewById(R.id.btn_map1);
        btnMap2 = (Button)findViewById(R.id.btn_map2);

        dataList = (Data) new GsonBuilder().create()
                .fromJson(getIntent().getStringExtra("id"), Data.class);

        username.setText(dataList.getUsername());
        kelurahan.setText(dataList.getKelurahan());
        description.setText(dataList.getDescription());
        Picasso.with(DetailActivity.this)
                .load(dataList.getImage_url())
                .into(image);

        btnMap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = dataList.getUsername();
                String strKelurahan = dataList.getKelurahan();
                String lat = dataList.getLat();
                String lng = dataList.getLng();

                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                intent.putExtra("username", strUsername);
                intent.putExtra("kelurahan", strKelurahan);
                intent.putExtra("lat", lat);
                intent.putExtra("lng", lng);
                startActivity(intent);
            }
        });

    }
}
