package com.example.qoridanisa.asuper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.util.logging.Level.parse;

public class Kontak extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        Button dial = (Button) findViewById(R.id.dial);
        dial.setOnClickListener(new Button.OnClickListener()){

            startActivity(new Intent(Intent.ACTION_DIAL));
            }

        Button SMS = (Button) findViewById(R.id.SMS);
        final EditText messageEt = (EditText) findViewById(R.id.SMS);
        SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = messageEt.getText().toString();
                String phoneNo = mPhoneET.getText().toString();
                if (!TextUtils.isEmpty(message) && !TextUtils.isEmpty(phoneNo))
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                smsIntent.putExtra("sms_body", message);
                startActivity(smsIntent);
            }

        });

        Button email = (Button) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email();
            }
        });
        }
       protected void email(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri,parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,"");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Email message goes here");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send Mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,"There is no email client installed.",Toast.LENGTH_SHORT).show();
        }
    }


}
