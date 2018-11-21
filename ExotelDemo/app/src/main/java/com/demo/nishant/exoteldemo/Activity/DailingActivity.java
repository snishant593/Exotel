package com.demo.nishant.exoteldemo.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.demo.nishant.exoteldemo.R;
import com.demo.nishant.exoteldemo.databinding.ActivityDialingBinding;

import static android.Manifest.permission.CALL_PHONE;

public class DailingActivity extends AppCompatActivity {

ActivityDialingBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dialing);
        binding.setDialingactivity(this);

        binding.callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialcall();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.phoneNumberInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
              //  Dialcall();

                return true;
            }
        });

    }

    private void Dialcall()
    {
        if (PermissionChecker.checkSelfPermission((Context)this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
        {

            String phonenumber = binding.phoneNumberInput.getText().toString();
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumber)));

        }
        else {
            ActivityCompat.requestPermissions((Activity)this, new String[]{CALL_PHONE}, 0);

        }
    }
}
