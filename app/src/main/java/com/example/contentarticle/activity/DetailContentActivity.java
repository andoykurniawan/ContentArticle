package com.example.contentarticle.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contentarticle.R;
import com.example.contentarticle.model.room.Content;

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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailContentActivity.this, HomeActivity.class));
                finish();
            }
        });

        final Content content = (Content) getIntent().getSerializableExtra("contentData");
        contentDetail(content);

        //untuk namabah permission telpon
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && checkSelfPermission(Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        new String[]{Manifest.permission.CALL_PHONE},
                        110);
            }
            return;
        }
    }

    private void contentDetail(final Content content) {

        txt_nama.setText(content.getJudul());
        txt_content.setText(content.getMycontent());
        txt_gender.setText(content.getCategory());
        txt_tgl.setText(content.getTanggal());
        txt_phone.setText(content.getPhone());


        txt_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + content.getPhone()));

                startActivity(callIntent);
            }
        });
    }
}
