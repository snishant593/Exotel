package com.demo.nishant.exoteldemo.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.nishant.exoteldemo.Adapter.ContactAdapter;
import com.demo.nishant.exoteldemo.Utils.Constants;
import com.demo.nishant.exoteldemo.Model.ContactList;
import com.demo.nishant.exoteldemo.R;
import com.demo.nishant.exoteldemo.callbacks.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by shilpa on 20/11/18.
 */

public class Contact_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener  {

    RecyclerView recyclerView;

    ArrayList<ContactList> contactData;
    private Constants.ContactListClickListener listener;


    Cursor cursor ;
    SwipeRefreshLayout mSwipeRefreshLayout;





    public Contact_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       listener = (Constants.ContactListClickListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.contacts_fragment, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // SwipeRefreshLayout
        mSwipeRefreshLayout = (SwipeRefreshLayout)rootView. findViewById(R.id.swipe_container);

        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);








        // Inflate the layout for this fragment
        return rootView;






    }







    @Override
    public void onStart() {

        super.onStart();
    }


    public ArrayList<ContactList> GetContactsIntoArrayList() {
        contactData = new ArrayList<>();


        cursor = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                null, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");


        if (cursor.moveToFirst())

        {
            do {

                ContactList contactList = new ContactList();




                contactList.setContact_id(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)));

                contactList.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                contactList.setPhoneno(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contactList.setId(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
                contactList.setImage(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));


                contactData.add(contactList);

            } while (cursor.moveToNext());
        }




        return contactData;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        recyclerView.setAdapter(new ContactAdapter(getActivity(), GetContactsIntoArrayList()
                ,
                new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, View view, int position) {
                        listener.onContactListClicked((ContactList) o,null);

                    }
                }, null));
    }


    @Override
    public void onRefresh() {


        mSwipeRefreshLayout.setRefreshing(false);

    }
}
