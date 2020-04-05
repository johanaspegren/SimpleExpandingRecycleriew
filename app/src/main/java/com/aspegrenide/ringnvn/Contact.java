package com.aspegrenide.ringnvn;

import android.util.Log;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contact {

    private String name;
    private String interest;
    private String boende;
    private String description;

    private String phoneNr;
    private int imgId;
    private String lastCall;


    private String isbrytare;

    public String getLastCaller() {
        return lastCaller;
    }

    public void setLastCaller(String lastCaller) {
        this.lastCaller = lastCaller;
    }

    private String lastCaller;
    private Date lastCallDate;
    private int year;
    private boolean expanded;

    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Contact(String name, String phoneNr, String interest, int imgId, String lastCall, int year, String boende, String isbrytare) {
        this.name = name;
        this.phoneNr = phoneNr;
        this.interest = interest;
        this.imgId = imgId;
        this.lastCall = lastCall;
        this.year = year;
        this.boende = boende;
        this.isbrytare = isbrytare;
    }

    public String getBoende() {
        return boende;
    }

    public void setBoende(String boende) {
        this.boende = boende;
    }

    public String getName() {
        return name;
    }

    public String getInterest() {
        return interest;
    }

    public int getYear() {
        return year;
    }
    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLastCall() {
        return lastCall;
    }

    public void setLastCall(String lastCall) {
        this.lastCall = lastCall;
    }

    public Date getLastCallDate() {
        return lastCallDate;
    }

    public void setLastCallDate(Date lastCallDate) {
        this.lastCallDate = lastCallDate;
    }

    public String tjohoo() {
        Date now = java.util.Calendar.getInstance().getTime();
        Date then = getLastCallDate();
        if (then == null) {
            return "NA";
        }
        long duration  = now.getTime() - then.getTime();
        long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

        //Log.d("CONTACT", "now = " + now.toString());
        //Log.d("CONTACT", "then = " + then.toString());

        String ret = "";
        if (diffInDays > 0) {
            ret += diffInDays + " dgr ";
            diffInHours = diffInHours - (diffInDays * 24);
        }
        if (diffInHours > 0) {
            ret += diffInHours + " tim ";
            diffInMinutes = diffInMinutes - (diffInHours * 60);
        }
        if (diffInMinutes >= 0) {
            ret += diffInMinutes + " min";
        }
        ret += " sedan";
        return ret;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getIsbrytare() {
        return isbrytare;
    }

    public void setIsbrytare(String isbrytare) {
        this.isbrytare = isbrytare;
    }
}