package com.example.contentarticle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contentarticle.R;

public class DetailContentActivity extends AppCompatActivity {

    TextView txt_nama, txt_content, txt_gender, txt_tgl, txt_phone;
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);

        txt_nama = (TextView) findViewById(R.id.viewnama);
        txt_content = (TextView) findViewById(R.id.viewcontent);
        txt_gender = (TextView) findViewById(R.id.viewgender);
        txt_tgl = (TextView) findViewById(R.id.viewtanggal);
        txt_phone = (TextView) findViewById(R.id.viewphone);
        btn_back = (ImageView) findViewById(R.id.back);
    }
}
