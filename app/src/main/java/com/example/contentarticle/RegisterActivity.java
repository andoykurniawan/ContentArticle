package com.example.contentarticle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    TextView text_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_login = (TextView) findViewById(R.id.login);
        btn_register = (Button) findViewById(R.id.register);

        //SetClickOnlistener berfungsi untuk memberi aksi
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                //bikin toast (massagebox)
                Toast.makeText(RegisterActivity.this, "Silahkan Login", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

    }

            @Override
            public void onBackPressed() {
                RegisterActivity.super.onBackPressed();
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }

}
