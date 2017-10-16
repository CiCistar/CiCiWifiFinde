package com.sjx.ciciwififinde.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.sjx.ciciwififinde.R;
import com.sjx.ciciwififinde.adapter.WifiListAdapter;
import com.sjx.ciciwififinde.bean.WifiInfoBean;
import com.sjx.ciciwififinde.utils.DimenUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {

    public static final String WIFI_INFO_KEY = "wifi_info_key";
    private ListView mWifiList;
    private TextView mTvListBtn;
    private TextView mTvMapBtn;
    private MapView mMapView;
    private View mCurrentIndex;
    private AMap mAMap;
    private List<WifiInfoBean> mWifiInfoList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arround_wifi);
        initView();
        mMapView.onCreate(savedInstanceState);
        setOnclick();
        initListData();
    }

    private void initListData() {
        for (int i = 0; i < 10; i++) {
            WifiInfoBean bean = new WifiInfoBean();
            bean.name = "第" + i + "个wifi";
            bean.bssid = "0000:0000:0000:0000";
            mWifiInfoList.add(bean);
        }

        WifiListAdapter adapter = new WifiListAdapter(this, mWifiInfoList);
        mWifiList.setAdapter(adapter);
    }

    private void setOnclick() {
        mCurrentIndex.setOnClickListener(new LocationIndex());
        mTvListBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setMapVisible(false);
                setWifiListVisible(true);
                mTvListBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                mTvListBtn.setTextColor(Color.WHITE);
                mTvMapBtn.setBackgroundColor(Color.WHITE);
                mTvMapBtn.setTextColor(Color.BLACK);
                mCurrentIndex.setVisibility(View.GONE);
            }
        });
        mTvMapBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setMapVisible(true);
                setWifiListVisible(false);
                mTvMapBtn.setBackgroundColor(Color.parseColor("#ff33b5e5"));
                mTvMapBtn.setTextColor(Color.WHITE);
                mTvListBtn.setBackgroundColor(Color.WHITE);
                mTvListBtn.setTextColor(Color.BLACK);
            }
        });
        mWifiList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, WiFiDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(WIFI_INFO_KEY, mWifiInfoList.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mWifiList = findViewById(R.id.lv_wifi);
        mTvListBtn = findViewById(R.id.tv_list);
        mTvMapBtn = findViewById(R.id.tv_map);
        mMapView = findViewById(R.id.map);
        mCurrentIndex = findViewById(R.id.activity_main_current_index);
        mMapView.setTranslationX(DimenUtil.SCREEN_WIDTH);
        mAMap = mMapView.getMap();
    }

    private void setWifiListVisible(boolean visible) {
        mWifiList.animate()
            .translationX(visible ? 0 : -DimenUtil.SCREEN_WIDTH)
            .setInterpolator(new DecelerateInterpolator())
            .setDuration(500)
            .start();
    }

    private void setMapVisible(final boolean visible) {
        mMapView.animate()
            .translationX(visible ? 0 : DimenUtil.SCREEN_WIDTH)
            .setInterpolator(new DecelerateInterpolator())
            .setDuration(500)
            .withEndAction(new Runnable() {
                @Override
                public void run() {
                    mCurrentIndex.setVisibility(visible ? View.VISIBLE : View.GONE);
                }
            })
            .start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    private class LocationIndex implements OnClickListener {

        final int PLANE_MAP = 0;
        final int SLOPING_MAP = 1;
        int currentMode = PLANE_MAP;
        int zoomLevel = 0;

        @Override
        public void onClick(View v) {
            MyLocationStyle myLocationStyle;
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
            myLocationStyle.strokeColor(Color.parseColor("#36a9ae"));
            myLocationStyle.radiusFillColor(Color.parseColor("#2036a9ae"));
            switch (currentMode) {
                case PLANE_MAP:
                    currentMode = SLOPING_MAP;
                    ((TextView) v).setText("当前\n位置");
                    zoomLevel = 17;
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);// 定位一次，且将视角移动到地图中心点。
                    break;

                case SLOPING_MAP:
                    currentMode = PLANE_MAP;
                    ((TextView) v).setText("连续\n定位");
                    zoomLevel = 19;
                    myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);// 连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
                    break;

                default:
                    break;
            }
            mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
            mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
            mAMap.moveCamera(CameraUpdateFactory.zoomTo(zoomLevel));
        }
    }

}
