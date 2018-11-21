package com.demo.nishant.exoteldemo.viewmodel;

import com.demo.nishant.exoteldemo.Model.RecentCallList;

public class RecentCallviewmodel  {
        private RecentCallList recentCallList;

        public RecentCallviewmodel(RecentCallList recentCallList) {
            this();
            this.recentCallList = recentCallList;
        }

        private RecentCallviewmodel() {
        }

        public Integer getid() {
            return recentCallList.getid();

        }

        public String getPhoneno() {

            return recentCallList.getPhoneno();
        }

    public String getDuration() {

        return String.valueOf(recentCallList.getDuration());
    }

    public String getDate() {

        return recentCallList.getDate();
    }




        /*public String getName() {

            return recentCallList.getName();
        }
*/

        public String getImage() {

            return String.valueOf(recentCallList.getImage().charAt(0));
        }

        public String getcontactid() {

            return recentCallList.getContact_id();
        }

        public String getCallType ()
        {
            return recentCallList.getCalltype();
        }
}
