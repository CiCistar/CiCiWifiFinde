package com.sjx.ciciwififinde.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sjx.ciciwififinde.R;
import com.sjx.ciciwififinde.bean.WifiInfoBean;
import java.util.List;

/**
 * Created by gsy on 17/10/16.
 */

public class WifiListAdapter extends BaseAdapter {

    private Context mContext;
    private List<WifiInfoBean> mList;


    public WifiListAdapter(Context context, List<WifiInfoBean> list) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public WifiInfoBean getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_wifi_list, viewGroup, false);
            viewHolder.mWifiName = convertView.findViewById(R.id.tv_wifi_name);
            viewHolder.mBSSID = convertView.findViewById(R.id.tv_bssid);
            viewHolder.mConnection = convertView.findViewById(R.id.tv_connected);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.update(getItem(position));
        return convertView;
    }

    public static class ViewHolder {

        TextView mWifiName;
        TextView mBSSID;
        TextView mConnection;

        public void update(WifiInfoBean wifiInfo) {
            mWifiName.setText(wifiInfo.name);
            mBSSID.setText(wifiInfo.bssid);
            mConnection.setText(wifiInfo.isConnected ? "已连接" : "未连接");
        }
    }
}
