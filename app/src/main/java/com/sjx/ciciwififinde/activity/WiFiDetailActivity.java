package com.sjx.ciciwififinde.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.sjx.ciciwififinde.R;
import com.sjx.ciciwififinde.adapter.ViewPagerAdapter;
import com.sjx.ciciwififinde.bean.WifiInfoBean;
import com.sjx.ciciwififinde.page.BasePager;
import com.sjx.ciciwififinde.page.ConnectionPage;
import com.sjx.ciciwififinde.page.TopologyPager;
import com.sjx.ciciwififinde.page.WifiDetailPager;
import com.sjx.ciciwififinde.page.WifiHistoryPager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gsy on 17/10/16.
 */

public class WiFiDetailActivity extends Activity implements OnClickListener {

    private TextView mWifiName;
    private TextView mBssid;
    private TextView mConnected;
    private ViewPager mWifiViewPager;
    private TextView mTvDetail;
    private TextView mTvHistory;
    private TextView mTvPhone;
    private TextView mTvConnection;
    private TextView mTvTemp;
    private WifiInfoBean mWifiInfoBean;
    private List<BasePager> mViewList = new ArrayList<>();
    private int mCurrentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mWifiInfoBean = (WifiInfoBean) bundle.get(HomeActivity.WIFI_INFO_KEY);
        } else {
            finish();
        }
        initView();
        setOnClick();
        initData();
    }

    private void initData() {
        mWifiName.setText(mWifiInfoBean.name);
        mBssid.setText(mWifiInfoBean.bssid);
        mConnected.setText(mWifiInfoBean.isConnected ? "已连接" : "未连接");

        mViewList.add(new WifiDetailPager(this));
        mViewList.add(new WifiHistoryPager(this));
        mViewList.add(new TopologyPager(this));
        mViewList.add(new ConnectionPage(this));
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, mViewList);
        mWifiViewPager.setAdapter(adapter);

    }

    private void setOnClick() {
        mTvDetail.setOnClickListener(this);
        mTvHistory.setOnClickListener(this);
        mTvPhone.setOnClickListener(this);
        mTvConnection.setOnClickListener(this);
        mWifiViewPager.setCurrentItem(0);
        mWifiViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mWifiName = findViewById(R.id.tv_wifi_name);
        mBssid = findViewById(R.id.tv_bssid);
        mConnected = findViewById(R.id.tv_connected);
        mWifiViewPager = findViewById(R.id.vp_wifi);
        mTvDetail = findViewById(R.id.tv_detail);
        mTvHistory = findViewById(R.id.tv_history);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvConnection = findViewById(R.id.tv_connection);
        mTvTemp = mTvDetail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail:
                mCurrentIndex = 0;
                setTextColor(mTvDetail);
                break;
            case R.id.tv_history:
                mCurrentIndex = 1;
                setTextColor(mTvHistory);
                break;
            case R.id.tv_phone:
                mCurrentIndex = 2;
                setTextColor(mTvPhone);
                break;
            case R.id.tv_connection:
                mCurrentIndex = 3;
                setTextColor(mTvConnection);
                break;

            default:
                break;
        }
        switchPage();
    }

    private void setTextColor(TextView view) {
        mTvTemp.setTextColor(Color.BLACK);
        view.setTextColor(Color.parseColor("#ff33b5e5"));
        mTvTemp = view;
    }

    private void switchPage() {
        mWifiViewPager.setCurrentItem(mCurrentIndex);
    }
}
