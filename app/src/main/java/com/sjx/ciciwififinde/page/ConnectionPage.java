package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.View;
import com.sjx.ciciwififinde.R;

/**
 * 连接对应的页面
 */
public class ConnectionPage extends BasePager {

    public ConnectionPage(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        View view = View.inflate(mContext, R.layout.layout_connection, null);
        mFlContent.removeAllViews();
        mFlContent.addView(view);
    }
}
