package com.example.contentarticle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contentarticle.R;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    TextView text_register;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text_register = (TextView) findViewById(R.id.register);
        btn_login = (Button) findViewById(R.id.login);

        email = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Harap Isi Username dan Password", Toast.LENGTH_SHORT).show();
                } else {

                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    //bikin toast (massagebox)
                    Toast.makeText(LoginActivity.this, "Silahkan Registrasi", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });

        //SetClickOnlistener berfungsi untuk memberi aksi
        text_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    //bikin toast (massagebox)
                    Toast.makeText(LoginActivity.this, "Silahkan Registrasi", Toast.LENGTH_SHORT).show();

                    finish();

            }
        });


    }
}
