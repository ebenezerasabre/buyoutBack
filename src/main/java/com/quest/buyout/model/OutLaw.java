package com.quest.buyout.model;

public class OutLaw {
    private String msg;
    private String _id;

    public OutLaw() {
    }

    public OutLaw(String msg) { this.msg = msg; }

    public OutLaw(String msg, String customerId){
        this.msg = msg;
        this._id = customerId;
    }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public String get_id() { return _id; }

    public void set_id(String _id) { this._id = _id; }

    @Override
    public String toString() {
        return "OutLaw{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
