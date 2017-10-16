package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.View;
import com.sjx.ciciwififinde.R;

/**
 * Created by gsy on 17/10/16.
 */

public class WifiHistoryPager extends BasePager {

    public WifiHistoryPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        View view = View.inflate(mContext, R.layout.layout_wifi_connected_history, null);
        mFlContent.removeAllViews();
        mFlContent.addView(view);
    }
}
