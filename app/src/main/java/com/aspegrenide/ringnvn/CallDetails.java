package com.aspegrenide.ringnvn;


import java.util.Date;

public class CallDetails {

    private String caller;
    private String phoneNr;
    private Date timeStampStart;
    private Date timeStampStop;

    public CallDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    // caller - who is calling? phoneNr - who is called?
    public CallDetails(String caller, String phoneNr, Date timeStampStart ) {
        this.caller = caller;
        this.phoneNr = phoneNr;
        this.timeStampStart = timeStampStart;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public Date getTimeStampStart() {
        return timeStampStart;
    }

    public void setTimeStampStart(Date timeStampStart) {
        this.timeStampStart = timeStampStart;
    }

    public Date getTimeStampStop() {
        return timeStampStop;
    }

    public void setTimeStampStop(Date timeStampStop) {
        this.timeStampStop = timeStampStop;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }
}