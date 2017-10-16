package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.View;
import com.sjx.ciciwififinde.R;

/**
 * Created by gsy on 17/10/16.
 */

public class WifiDetailPager extends BasePager {

    public WifiDetailPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        View view = View.inflate(mContext, R.layout.layout_wifi_detail, null);
        mFlContent.removeAllViews();
        mFlContent.addView(view);
    }

    @Override
    public void switchPage(int position) {
        super.switchPage(position);
    }
}
