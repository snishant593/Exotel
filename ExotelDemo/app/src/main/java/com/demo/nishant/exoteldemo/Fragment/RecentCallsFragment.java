package com.demo.nishant.exoteldemo.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.demo.nishant.exoteldemo.Adapter.ContactAdapter;
import com.demo.nishant.exoteldemo.Adapter.RecentCallAdapter;
import com.demo.nishant.exoteldemo.Model.RecentCallList;
import com.demo.nishant.exoteldemo.R;
import com.demo.nishant.exoteldemo.Utils.Constants;
import com.demo.nishant.exoteldemo.callbacks.OnItemClickListener;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.READ_CALL_LOG;


public class RecentCallsFragment extends Fragment {

    RecyclerView recyclerView;
    String dir;

    ArrayList<RecentCallList> recentCallLists;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;


    Cursor phones;
    //  private Constants.RecentCallListClickListener listener;


    public RecentCallsFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recentrecyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return rootView;
    }

    @Override
    public void onStart() {
       getCallDetail();
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // listener = (Constants.RecentCallListClickListener) context;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setAdapter(new RecentCallAdapter(getActivity(), getCallDetail()
                ,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, View view, int position) {
                        // listener.onRecentcallListClicked((RecentCallList) o, null);

                    }
                }, null));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {

                    getCallDetail();
                   // setupViewPager(binding.viewpager);
                }
                break;
        }
    }

    public ArrayList<RecentCallList> getCallDetail() {
        recentCallLists = new ArrayList<>();


        String[] projection = new String[]{
                CallLog.Calls._ID,
                CallLog.Calls.NUMBER,
                CallLog.Calls.DATE,
                CallLog.Calls.DURATION,
                CallLog.Calls.TYPE
        };
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
           // return TODO;

            Cursor c = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, null,
                    null, CallLog.Calls.DATE + " DESC");

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    RecentCallList recentCallList = new RecentCallList();


                    recentCallList.setContact_id(c.getString(c.getColumnIndex(CallLog.Calls._ID)));

                    recentCallList.setPhoneno(c.getString(c.getColumnIndex(CallLog.Calls.NUMBER)));
                    recentCallList.setDuration(c.getLong(c.getColumnIndex(CallLog.Calls.DURATION)));
                    recentCallList.setDate(c.getString(c.getColumnIndex(CallLog.Calls.DATE)));
                    recentCallList.setCalltype(c.getString(c.getColumnIndex(CallLog.Calls.TYPE)));

                    Log.e("number","" + recentCallList.getDuration());
                    Log.e("date","" + recentCallList.getDate());
                    Log.e("calltype","" + recentCallList.getCalltype());



                    int callType = c.getInt(c.getColumnIndex(CallLog.Calls.TYPE));
                    //  Log.e("Recent call", "" + callerNumber);
                    if (callType == CallLog.Calls.INCOMING_TYPE) {


                        //incoming call
                    } else if (callType == CallLog.Calls.OUTGOING_TYPE) {


                        //outgoing call
                    } else if (callType == CallLog.Calls.MISSED_TYPE) {

                        //missed call
                    }
                    recentCallLists.add(recentCallList);

                } while (c.moveToNext());
            }
        }


          //  }

        else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{READ_CALL_LOG}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }

        return recentCallLists;
    }
}








