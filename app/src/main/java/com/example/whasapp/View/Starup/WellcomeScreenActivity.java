package com.example.whasapp.View.Starup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.whasapp.View.Auth.PhoneLoginActivity;
import com.example.whasapp.View.MainActivity;
import com.example.whasapp.R;

public class WellcomeScreenActivity extends AppCompatActivity {
 Button btnAgree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_wellcome_screen );

        btnAgree = (Button) findViewById( R.id.btn_agree );
         btnAgree.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(WellcomeScreenActivity.this, PhoneLoginActivity.class );
                 startActivity(intent);
                 finish();
             }
         } );
    }
}