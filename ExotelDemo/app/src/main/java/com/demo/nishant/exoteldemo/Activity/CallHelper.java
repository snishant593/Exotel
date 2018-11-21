package com.demo.nishant.exoteldemo.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.util.Log;


public class CallHelper extends Call.Callback {
    Bundle bundle;


    @Nullable
    private static Call call;

    public static CallHelper INSTANCE;
    private CallActivity callActivity;




    String TAG = "CallbackTelecomHelper_TAG";



    private Context context;

    public CallHelper(Context context){
        this.context = context;

    }

    @Override
    public void onStateChanged(Call call, int state) {
        super.onStateChanged(call, state);
        CallActivity activity = CallActivity.instance;



        switch (state) {
            case Call.STATE_DISCONNECTED:


                if (activity != null) {
                    // we are calling here activity's method
                    activity.updateInfostatus("Disconnected");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    activity.callingSecondactivity();
                }

                break;

            case Call.STATE_DIALING:
                if (activity != null) {
                    // we are calling here activity's method
                    activity.updateInfostatus("Dialing");
                    activity.buttonBehaviourforDialing();

                }

                break;


            case Call.STATE_DISCONNECTING:


                if (activity != null) {
                    // we are calling here activity's method
                    activity.updateInfostatus("Disconnecting");

                }

                break;

            case Call.STATE_CONNECTING:
                if (activity != null) {
                    activity.updateInfostatus("Connecting");


                    // we are calling here activity's method
                    // activity.callingSecondactivity();
                }
                break;

            case Call.STATE_RINGING:
                if (activity != null) {
                    activity.updateInfostatus("Ringing");
                    Log.e("CallService", "Call.Callback onStateChanged: " + call + ", state: " + call.getState());


                    // we are calling here activity's method
                    // activity.callingSecondactivity();
                }
                break;

            case Call.STATE_ACTIVE:
                if (activity != null) {
                    activity.updateInfostatus("Active");

                    // we are calling here activity's method
                    // activity.callingSecondactivity();
                }
                break;





        }
    }

    @Override
    public void onDetailsChanged(Call call, Call.Details details) {
        super.onDetailsChanged(call, details);

        Log.i(TAG, "onDetailsChanged");
    }

    @Override
    public void onCallDestroyed(Call call) {
        super.onCallDestroyed(call);

        Log.i(TAG, "onCallDestroyed");
    }

    public final void setCall(@Nullable Call value) {
        Call calls = call;
        if (call != null) {
            calls.unregisterCallback(this);
        }

        if (value != null) {
            value.registerCallback(this);
            value.getState();

            Log.e("nishant","" + value);
        }

        call = value;
    }

    public final void answer() {
        Call answercall = call;
        if (call == null) {
            throw new NullPointerException("Null Value");
        }

        answercall.answer(0);
    }

    public final void hangup() {
        Call hangup = call;
        if (call == null) {
            throw new NullPointerException("Null Value");

        }

        hangup.disconnect();
    }


    public final String updatecall (){
        CallActivity activity = CallActivity.instance;
        Call answercall = call;
        if (call == null) {
            throw new NullPointerException("Null Value");
        }

        if (answercall.getState()==2)
        {
            activity.buttonBehaviourforRinging();
        }



        Log.e("CallService", "Call.Callback onStateChanged: " + call + ", state: " + answercall.getState());


        return String.valueOf(answercall.getState());

    }




//	/**
//	 * Listener to detect incoming calls.
//	 */
//	private class CallStateListener extends PhoneStateListener {
//		@Override
//		public void onCallStateChanged(int state, String incomingNumber) {
//			switch (state) {
//			case TelephonyManager.CALL_STATE_RINGING:
//				// called when someone is ringing to this phone
//
//                Intent intent = new Intent(ctx,DailingActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                ctx.startActivity(intent);
//
//
//
//				Toast.makeText(ctx,
//						"Incoming: "+incomingNumber,
//						Toast.LENGTH_LONG).show();
//				break;
//			}
//		}
//	}
//
//	/**
//	 * Broadcast receiver to detect the outgoing calls.
//	 */
//	public class OutgoingReceiver extends BroadcastReceiver {
//	    public OutgoingReceiver() {
//	    }
//
//	    @Override
//	    public void onReceive(Context context, Intent intent) {
//	        String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
//
//	        Toast.makeText(ctx,
//	        		"Outgoing: "+number,
//	        		Toast.LENGTH_LONG).show();
//	    }
//
//	}
//
//	private Context ctx;
//	private TelephonyManager tm;
//	private CallStateListener callStateListener;
//
//	private OutgoingReceiver outgoingReceiver;
//
//	public CallHelper(Context ctx) {
//		this.ctx = ctx;
//
//		callStateListener = new CallStateListener();
//		outgoingReceiver = new OutgoingReceiver();
//	}
//
//	/**
//	 * Start calls detection.
//	 */
//	public void start() {
//		tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
//		tm.listen(callStateListener, PhoneStateListener.LISTEN_CALL_STATE);
//
//		IntentFilter intentFilter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);
//		ctx.registerReceiver(outgoingReceiver, intentFilter);
//	}
//
//	/**
//	 * Stop calls detection.
//	 */
//	public void stop() {
//		tm.listen(callStateListener, PhoneStateListener.LISTEN_NONE);
//		ctx.unregisterReceiver(outgoingReceiver);
//	}

}
