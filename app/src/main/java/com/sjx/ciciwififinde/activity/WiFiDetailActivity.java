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
 * 无线的详细信息界面
 */
public class WiFiDetailActivity extends Activity implements OnClickListener {

    private static final int PAGE_FIRST = 0;                                    // 第一个页面
    private static final int PAGE_SECOND = 1;                                   // 第二个页面
    private static final int PAGE_THIRD = 2;                                    // 第三个页面
    private static final int PAGE_FOUR = 3;                                     // 第四个页面
    private View mIvBack;                                                       // 返回按钮
    private TextView mWifiName;                                                 // 无线名字
    private TextView mBssid;                                                    // BSSID
    private TextView mConnected;                                                // 是否连接
    private ViewPager mWifiViewPager;                                           // 四个page的ViewPager
    private TextView mTvDetail;                                                 // 详细信息按钮
    private TextView mTvHistory;                                                // 历史按钮
    private TextView mTvPhone;                                                  // 手机按钮
    private TextView mTvConnection;                                             // 连接按钮
    private TextView mTvTemp;                                                   // 缓存上一个按钮状态，用来更新上次选择的按钮的信息
    private WifiInfoBean mWifiInfoBean;                                         // 包装无线信息的类
    private List<BasePager> mViewList;                                          // 存储ViewPager使用的页面
    private int mCurrentIndex;                                                  // ViewPager当期展示的页面

    {
        mViewList = new ArrayList<>();
    }

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

    /**
     * 初始化各个控件的状态
     */
    private void initData() {
        mWifiName.setText(mWifiInfoBean.name);
        mBssid.setText(mWifiInfoBean.bssid);
        mConnected.setText(mWifiInfoBean.isConnected ? "已连接" : "未连接");
        mViewList.add(new WifiDetailPager(this));
        mViewList.add(new WifiHistoryPager(this));
        mViewList.add(new TopologyPager(this));
        mViewList.add(new ConnectionPage(this));
        mWifiViewPager.setAdapter(new ViewPagerAdapter(mViewList));
    }

    /**
     * 设置点击事件
     */
    private void setOnClick() {
        mTvDetail.setOnClickListener(this);
        mTvHistory.setOnClickListener(this);
        mTvPhone.setOnClickListener(this);
        mTvConnection.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mWifiViewPager.setCurrentItem(PAGE_FIRST);
        mWifiViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // TODO gsy 17/10/16  滑动到第一个时显示侧边菜单
            }

            @Override
            public void onPageSelected(int position) {
                TextView textView = null;
                switch (position) {
                    case PAGE_FIRST:
                        textView = mTvDetail;
                        break;
                    case PAGE_SECOND:
                        textView = mTvHistory;
                        break;
                    case PAGE_THIRD:
                        textView = mTvPhone;
                        break;
                    case PAGE_FOUR:
                        textView = mTvConnection;
                        break;
                }
                if (textView != null) {
                    setTextColor(textView);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mIvBack = findViewById(R.id.iv_back);
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
                mCurrentIndex = PAGE_FIRST;
                setTextColor(mTvDetail);
                break;
            case R.id.tv_history:
                mCurrentIndex = PAGE_SECOND;
                setTextColor(mTvHistory);
                break;
            case R.id.tv_phone:
                mCurrentIndex = PAGE_THIRD;
                setTextColor(mTvPhone);
                break;
            case R.id.tv_connection:
                mCurrentIndex = PAGE_FOUR;
                setTextColor(mTvConnection);
                break;
            case R.id.iv_back:
                finish();
                break;

            default:
                break;
        }
        switchPage();
    }

    /**
     * 设置点击按钮的颜色，将上一次点击的按钮置为默认颜色
     *
     * @param view 当前点击的按钮
     */
    private void setTextColor(TextView view) {
        mTvTemp.setTextColor(Color.BLACK);
        view.setTextColor(Color.parseColor("#ff33b5e5"));
        mTvTemp = view;
    }

    /**
     * 更改ViewPager显示的page
     */
    private void switchPage() {
        mWifiViewPager.setCurrentItem(mCurrentIndex);
    }
}
