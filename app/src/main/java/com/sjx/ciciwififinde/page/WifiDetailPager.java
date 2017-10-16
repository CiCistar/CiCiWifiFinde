package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.View;
import com.sjx.ciciwififinde.R;

/**
 * 详情对应的页面
 */
public class WifiDetailPager extends BasePager {

    public WifiDetailPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        View view = View.inflate(mContext, R.layout.layout_wifi_detail, null);
        mFlContent.removeAllViews();
        mFlContent.addView(view);
    }
}
