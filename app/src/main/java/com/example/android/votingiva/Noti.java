package com.example.android.votingiva;

public class Noti {
    String Pname, Uname;

    public Noti(String pname, String uname) {
        Pname = pname;
        Uname = uname;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }
}
