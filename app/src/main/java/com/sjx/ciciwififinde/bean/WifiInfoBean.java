package com.sjx.ciciwififinde.bean;

import android.os.Parcel;

/**
 * Created by gsy on 17/10/16.
 */

public class WifiInfoBean implements android.os.Parcelable {

    public String name;
    public String bssid;
    public boolean isConnected;
    public String ip;
    public String speed;
    public String mac;
    public String id;
    public int strenth;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.bssid);
        dest.writeByte(this.isConnected ? (byte) 1 : (byte) 0);
        dest.writeString(this.ip);
        dest.writeString(this.speed);
        dest.writeString(this.mac);
        dest.writeString(this.id);
        dest.writeInt(this.strenth);
    }

    public WifiInfoBean() {
    }

    protected WifiInfoBean(Parcel in) {
        this.name = in.readString();
        this.bssid = in.readString();
        this.isConnected = in.readByte() != 0;
        this.ip = in.readString();
        this.speed = in.readString();
        this.mac = in.readString();
        this.id = in.readString();
        this.strenth = in.readInt();
    }

    public static final Creator<WifiInfoBean> CREATOR = new Creator<WifiInfoBean>() {
        @Override
        public WifiInfoBean createFromParcel(Parcel source) {
            return new WifiInfoBean(source);
        }

        @Override
        public WifiInfoBean[] newArray(int size) {
            return new WifiInfoBean[size];
        }
    };
}
