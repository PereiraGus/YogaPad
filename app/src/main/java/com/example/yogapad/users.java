package com.example.yogapad;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;

public class users implements Serializable {

    private int _id;
    private String _name;
    private String _loc;
    private String _descr;

    public users(){ }

    public users(int id, String name)
    {
        this.set_id(id);
        this.set_name(name);
    }

    public users(String name, String loc, String descr)
    {
        this.set_name(name);
        this.set_loc(loc);
        this.set_descr(descr);
    }
    
    public users(int _id, String _name, String _loc, String _descr)
    {
        this._id = _id;
        this._name = _name;
        this._loc = _loc;
        this._descr = _descr;
    }

    public int get_id() {return _id;}
    public void set_id(int _id) {this._id = _id;}

    public String get_name() {return _name;}
    public void set_name(String _name) {this._name = _name;}

    public String get_loc() {return _loc;}
    public void set_loc(String _loc) {this._loc = _loc;}

    public String get_descr() {return _descr;}
    public void set_descr(String _descr) {this._descr = _descr;}

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("ID", this._id);
            obj.put("Nome", this._name);
            obj.put("Localização", this._loc);
            obj.put("Descrição", this._descr);

        } catch (JSONException e) {
        }
        return obj;
    }
}
