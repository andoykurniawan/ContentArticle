package com.example.contentarticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.contentarticle.R;
import com.example.contentarticle.helper.DatabaseClient;
import com.example.contentarticle.model.room.Content;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {

    ImageView btn_back;
    EditText txt_judul, txt_content, txt_tgl, txt_phone;
    RadioButton radio_male, radio_female;
    RadioGroup radio_status;
    Button btn_save;
    String status;

    final Calendar myCalender = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        status = "";

        btn_back = (ImageView) findViewById(R.id.edit_back);
        txt_judul = (EditText) findViewById(R.id.edit_judul);
        txt_content = (EditText) findViewById(R.id.edit_content1);
        txt_tgl = (EditText) findViewById(R.id.edit_tanggal);
        txt_phone = (EditText) findViewById(R.id.edit_phone);
        radio_male = (RadioButton) findViewById(R.id.edit_male);
        radio_female = (RadioButton) findViewById(R.id.edit_female);
        btn_save = (Button) findViewById(R.id.edit_save);
        radio_status = (RadioGroup) findViewById(R.id.edit_radio_sts);

        final Content updateContent = (Content) getIntent().getSerializableExtra("contentEdit");

        txt_judul.setText(updateContent.getJudul());
        txt_content.setText(updateContent.getMycontent());
        txt_tgl.setText(updateContent.getTanggal());
        txt_phone.setText(updateContent.getPhone());
        status = updateContent.getCategory();

        //switch status untuk set text radio button
        switch (status) {
            case (String) "Male":
                radio_male.setChecked(true);
                break;
            case (String) "Female":
                radio_female.setChecked(true);
                break;
        }

        radio_status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.edit_male:
                        status = "Male";
                        break;
                    case R.id.edit_female:
                        status = "Female";
                        break;
                }
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this, HomeActivity.class));
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txt_content.getText().toString().equals("") || txt_judul.getText().toString().equals("") ||
                        txt_phone.getText().toString().equals("") || txt_phone.getText().toString().equals("") ||
                        status.equals("")) {
                    Toast.makeText(UpdateActivity.this, "Harap Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
                } else {
                    Update(updateContent);
                }
            }
        });


        txt_tgl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateActivity.this, date, myCalender.get(Calendar.YEAR),
                        myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, monthOfYear);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        private void updateLabel() {
            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            txt_tgl.setText(sdf.format(myCalender.getTime()));
        }

        private void Update(final Content content) {

            final String mJudul   = txt_judul.getText().toString();
            final String mTanggal = txt_tgl.getText().toString();
            final String mPhone   = txt_phone.getText().toString();
            final String mGender  = status;
            final String mContent = txt_content.getText().toString();

            class updateContent extends AsyncTask<Void, Void, Void> {
                @Override
                protected Void doInBackground(Void... voids) {

                    content.setJudul(mJudul);
                    content.setTanggal(mTanggal);
                    content.setPhone(mPhone);
                    content.setCategory(mGender);
                    content.setMycontent(mContent);

                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().contentDao().update(content);

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);

                    Toast.makeText(UpdateActivity.this, "Data Berhasil Diedit", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this, HomeActivity.class));
                    finish();
                }
            }

            updateContent updateContent = new updateContent();
            updateContent.execute();
        }
}
