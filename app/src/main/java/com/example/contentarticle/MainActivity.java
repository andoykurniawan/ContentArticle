package com.example.contentarticle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    TextView text_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_register = (TextView) findViewById(R.id.register);
        btn_login = (Button) findViewById(R.id.login);

        //SetClickOnlistener berfungsi untuk memberi aksi
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                //bikin toast (massagebox)
                Toast.makeText(MainActivity.this, "Silahkan Registrasi", Toast.LENGTH_SHORT).show();

                finish();
            }
        });


    }
}
