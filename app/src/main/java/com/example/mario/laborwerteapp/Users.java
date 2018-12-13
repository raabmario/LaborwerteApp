package com.example.mario.laborwerteapp;

public class Users {
    private int _id;
    private String _usernamefirst;
    private String _usernamelast;
    private String _email;
    private String _DOB;
    private String _GEN;

    //Konstruktor
    public Users (String usernamefirst, String usernamelast, String email, String DOB, String GEN){
        this._usernamefirst = usernamefirst;
        this._usernamelast = usernamelast;
        this._email = email;
        this._DOB = DOB;
        this._GEN = GEN;
    }

    //Getter Methoden
    public int get_id() {
        return _id;
    }

    public String get_usernamefirst() {
        return _usernamefirst;
    }

    public String get_usernamelast() {
        return _usernamelast;
    }

    public String get_email() {
        return _email;
    }

    public String get_DOB() {
        return _DOB;
    }

    public String get_GEN(){
        return _GEN;
    }


    //Setter Methoden
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_usernamefirst(String _usernamefirst) {
        this._usernamefirst = _usernamefirst;
    }

    public void set_usernamelast(String _usernamelast) {
        this._usernamelast = _usernamelast;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public void set_DOB(String _DOB) {
        this._DOB = _DOB;
    }

    public void set_GEN(String _GEN){this._GEN = _GEN;}
}
