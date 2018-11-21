package com.demo.nishant.exoteldemo.viewmodel;


import com.demo.nishant.exoteldemo.Model.ContactList;


public class ContactListViewmodel {
    private ContactList contactList;

    public ContactListViewmodel(ContactList contactList) {
        this();
        this.contactList = contactList;
    }

    private ContactListViewmodel() {
    }

    public Integer getid() {
        return contactList.getid();

    }

    public String getPhoneno() {

        return contactList.getPhoneno();
    }

    public String getName() {

        return contactList.getName();
    }


    public String getImage() {

        return String.valueOf(contactList.getImage().charAt(0));
    }

    public String getcontactid() {

        return contactList.getContact_id();
    }


}
