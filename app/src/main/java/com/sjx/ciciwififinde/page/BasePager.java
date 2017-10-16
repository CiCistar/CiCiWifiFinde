package com.sjx.ciciwififinde.page;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.sjx.ciciwififinde.R;

/**
 * home界面ViewPager的page的基类
 */
public abstract class BasePager {

    Context mContext;
    FrameLayout mFlContent;
    private View mRoot;

    BasePager(Context context) {
        mContext = context;
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRoot = LayoutInflater.from(mContext).inflate(R.layout.fragemt_content_base, null);
        mFlContent = mRoot.findViewById(R.id.fl_base_content_tag);
    }

    /**
     * 初始化data，
     */
    public abstract void initData();

    /**
     * 返回rootView
     *
     * @return mRoot
     */
    public View getRootView() {
        return mRoot;
    }
}
