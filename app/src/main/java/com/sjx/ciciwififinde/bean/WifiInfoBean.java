package com.sjx.ciciwififinde.bean;

import android.os.Parcel;

/**
 * 无线的信息包装类，实现Parcelable，方便bundle传输
 */
public class WifiInfoBean implements android.os.Parcelable {

    public String name;                                         // 无线名字
    public String bssid;                                        // bssid
    public boolean isConnected;                                 // 是否已连接
    public String ip;                                           // ip
    public String speed;                                        // 速度
    public String mac;                                          // mac 地址
    public String id;                                           // id
    public int strenth;                                         // 信号强度

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
