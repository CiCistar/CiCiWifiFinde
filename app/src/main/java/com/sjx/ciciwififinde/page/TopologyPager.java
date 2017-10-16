package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.View;
import com.sjx.ciciwififinde.R;

/**
 * 拓扑图页面
 */
public class TopologyPager extends BasePager {

    public TopologyPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        View view = View.inflate(mContext, R.layout.layout_topology, null);
        mFlContent.removeAllViews();
        mFlContent.addView(view);
    }
}
