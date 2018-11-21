package com.demo.nishant.exoteldemo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecentCallList{


       @SerializedName("id")
       @Expose
       private Integer id;


    @SerializedName("date")
        @Expose
        private String date;

        @SerializedName("phoneno")
        @Expose
        private String phoneno;



    @SerializedName("duration")
    @Expose
    private Long duration;

    @SerializedName("calltype")
    @Expose
    private String calltype;


    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }




    private  String contact_id;

        private  String image;

        private int viewType;

        public RecentCallList() {
            //  this.viewType = viewType;
        }



        public String getDate() {
         return date;
        }

       public void setDate(String date) {
        this.date = date;
         }




    public String getImage() {
            return image;
        }

        public String setImage(String image) {
            this.image = image;
            return image;
        }

        public int getViewType() {
            return viewType;
        }


        public Integer getid()
        {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }



        public String getPhoneno() {
            return phoneno;
        }

        public void setPhoneno(String phoneno) {
            this.phoneno = phoneno;
        }




        public String getContact_id() {
            return contact_id;
        }

        public void setContact_id(String contact_id) {
        }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }



}
