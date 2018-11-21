package com.demo.nishant.exoteldemo.callbacks;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.telecom.InCallService;
import android.util.Log;

import com.demo.nishant.exoteldemo.Activity.CallActivity;
import com.demo.nishant.exoteldemo.Activity.CallHelper;

public class CallingService extends InCallService
{


    public static final String TAG = "MyService";
    CallHelper callHelper = new CallHelper(this);



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Log.d(TAG, "On Start");

        return START_STICKY;
    }


    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);



        callHelper.setCall(call);
        int v = call.getState();
        Intent intent = new Intent(getApplicationContext(),CallActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(call.getDetails().getHandle());

        startActivity(intent);

        Log.d(TAG, ""+ v);
        Log.d(TAG, "onCallAdded details" + call.getDetails());
    }


    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        callHelper.setCall(null);


        Log.d(TAG, "onCallRemoved");
        Log.d(TAG, "onCallRemoved details" + call.getDetails());
    }

    @Override
    public void onConnectionEvent(Call call, String event, Bundle extras) {
        super.onConnectionEvent(call, event, extras);

        Log.d(TAG, "getDisconnect code: " + call.getDetails().getDisconnectCause().getCode());
        Log.d(TAG, "getDisconnect reason: " + call.getDetails().getDisconnectCause().getReason());
        Log.d(TAG, "getDisconnect description: " + call.getDetails().getDisconnectCause().getDescription());
        Log.d(TAG, "event : " + event);

    }
}