package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.sjx.ciciwififinde.R;

/**
 * Created by gsy on 17/10/16.
 */

public class BasePager {

    protected Context mContext;
    protected FrameLayout mFlContent;
    protected View mRoot;

    public BasePager(Context context) {
        mContext = context;
        initView();
    }

    private void initView() {
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.fragemt_content_base, null);
        mFlContent = mRoot.findViewById(R.id.fl_base_content_tag);

    }

    public void initData() {

    }

    public void switchPage(int position) {

    }

    public View getRootView() {
        return mRoot;
    }
}
