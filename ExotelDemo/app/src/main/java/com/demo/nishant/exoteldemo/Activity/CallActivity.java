package com.demo.nishant.exoteldemo.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.nishant.exoteldemo.R;
import com.demo.nishant.exoteldemo.databinding.ActivityCallBinding;

public class CallActivity extends AppCompatActivity

{
   CallHelper callHelper = new CallHelper(this);
    static CallActivity instance;
    TelephonyManager mTelephonyManager;
    MyPhoneStateListener mPhoneStatelistener;
    int mSignalStrength = 0;
    ActivityCallBinding callBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        callBinding = DataBindingUtil.setContentView(this, R.layout.activity_call);
        callBinding.setCallactivity(this);
        instance = this;


        Intent intent = this.getIntent();
        Uri uri = intent.getData();
        String number = uri.getSchemeSpecificPart();
        callBinding.textDisplayName.setText(number);
        Log.e("callno",number);

        mPhoneStatelistener = new MyPhoneStateListener();
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mTelephonyManager.listen(mPhoneStatelistener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        }

    @Override
    protected void onStart() {
        super.onStart();

        callHelper.updatecall();

      //  Log.e("statusactivity","" +callHelper.updatecall());

    }

    public void callingSecondactivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void buttonBehaviourforDialing()
    {
        callBinding.buttonAnswer.setVisibility(View.INVISIBLE);
        callBinding.buttonHangup.setVisibility(View.INVISIBLE);
        callBinding.dailerbuttonHangup.setVisibility(View.VISIBLE);

        callBinding.dailerbuttonHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHelper.hangup();
            }
        });

    }


    public void buttonBehaviourforRinging()
    {
        callBinding.buttonAnswer.setVisibility(View.VISIBLE);
        callBinding.buttonHangup.setVisibility(View.VISIBLE);
        callBinding.dailerbuttonHangup.setVisibility(View.GONE);

        callBinding.buttonAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHelper.answer();
            }
        });

        callBinding.buttonHangup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHelper.hangup();
            }
        });



    }


    public void updateInfostatus(String state) {

        callBinding.textStatus.setText(state);
    }


    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            mSignalStrength = signalStrength.getGsmSignalStrength();

            mSignalStrength = (2 * mSignalStrength) - 113; // -> dBm
            Log.e("Signal Strength", "" + mSignalStrength);
        }
    }



}
