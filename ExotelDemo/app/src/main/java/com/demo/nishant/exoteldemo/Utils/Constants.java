package com.demo.nishant.exoteldemo.Utils;


import com.demo.nishant.exoteldemo.Model.ContactList;
import com.demo.nishant.exoteldemo.Model.RecentCallList;

/**
 * Created by nitesh on 13/8/17.
 */

public class Constants {

    public interface ViewType{
        int ITEM = 0;
        int FOOTER = 1;
    }

    public interface BundleKeys {
        String URL = "url";
        String POSITION = "pos";
        String NAVIGATION = "navigation";
    }
    public interface ProjectType{
        String NORMAL = "normal";
        String LOVELY = "lovely";
    }


    public interface ContactListClickListener{
        void onContactListClicked(ContactList contactList, String type);
    }

    public interface RecentCallListClickListener{
        void onRecentcallListClicked(RecentCallList recentCallList, String type);
    }

    public interface ProjectListListener {
        void onSearchResult(int resultCount);
    }

    public interface ProjectFilterListener {
        void onFilterClicked();
    }


}
