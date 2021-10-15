package com.demo.nishant.exoteldemo.Activity;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;


import com.demo.nishant.exoteldemo.Fragment.Contact_Fragment;
import com.demo.nishant.exoteldemo.Fragment.RecentCallsFragment;
import com.demo.nishant.exoteldemo.Model.ContactList;
import com.demo.nishant.exoteldemo.R;
import com.demo.nishant.exoteldemo.Utils.Constants;
import com.demo.nishant.exoteldemo.Utils.PermissionsUtil;
import com.demo.nishant.exoteldemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;
import static android.telecom.TelecomManager.ACTION_CHANGE_DEFAULT_DIALER;
import static android.telecom.TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME;

public class MainActivity extends AppCompatActivity implements Constants.ContactListClickListener {

    TelecomManager telecomManager;
    private static final int REQUEST_CODE_SET_DEFAULT_DIALER = 123;
    TextInputEditText phone;
    private ActionBar toolbar;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    ActivityMainBinding binding;

   // private static final int REQUEST_CODE_SET_DEFAULT_DIALER = 123;
   // TextInputEditText phone;
   // private ActionBar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainactivity(this);

        toolbar = getSupportActionBar();


        binding.tabs.setupWithViewPager(binding.viewpager);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        Intent intent = new Intent(getApplicationContext(),DailingActivity.class);
        startActivity(intent);
            }

        });

        }


    @Override
    protected void onStart() {

        super.onStart();
       checkDefaultDialer();
        getAllContacts();




    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Contact_Fragment(), "Contacts");
        adapter.addFragment(new RecentCallsFragment(), "Recent Call");
        viewPager.setAdapter(adapter);
        viewPager.getAdapter().notifyDataSetChanged();


    }

    @Override
    public void onContactListClicked(ContactList contactList, String type) {

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            // POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
{
    switch (requestCode) {
        case MY_PERMISSIONS_REQUEST_READ_CONTACTS:
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                setupViewPager(binding.viewpager);
            }
            break;
    }
}



    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void  checkDefaultDialer()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            telecomManager = (TelecomManager) this.getSystemService(TelecomManager.class);
        }
        if (telecomManager.getDefaultDialerPackage() !=this.getPackageName()) {
                Intent intent =   new Intent(ACTION_CHANGE_DEFAULT_DIALER)
                          .putExtra(EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, this.getPackageName());
                  ((MainActivity)this).startActivity(intent);
                 // getAllContacts();


              }

              }


    public  void getAllContacts() {

        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED)
        {

            setupViewPager(binding.viewpager);


        } else {

            requestForContactPermission();

       }
    }

    private void requestForContactPermission()
    {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS))
        {

           setupViewPager(binding.viewpager);
        }
        else {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }

        else if {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},MY_PERMISSIONS_REQUEST_READ_CONTACTS);
   }
    }



}
