package com.example.mario.laborwerteapp;

public class Eintrag {
    private int _id;
    private String _blutID;
    private String _userID;
    private String _blutWert;
    private String _datum;

    //Konstruktor

    public Eintrag(String _blutID, String _userID, String _blutWert, String _datum) {
        this._blutID = _blutID;
        this._userID = _userID;
        this._blutWert = _blutWert;
        this._datum = _datum;
    }

    //Getter Methoden

    public int get_id() {
        return _id;
    }

    public String get_blutID() {
        return _blutID;
    }

    public String get_userID() {
        return _userID;
    }

    public String get_blutWert() {
        return _blutWert;
    }

    public String get_datum() { return _datum; }

    //Setter Methoden

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_blutID(String _blutID) {
        this._blutID = _blutID;
    }

    public void set_userID(String _userID) {
        this._userID = _userID;
    }

    public void set_blutWert(String _blutWert) {
        this._blutWert = _blutWert;
    }

    public void set_datum(String _datum) {
        this._datum = _datum;
    }
}
