package com.sjx.ciciwififinde.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.sjx.ciciwififinde.page.BasePager;
import java.util.List;

/**
 * Created by gsy on 17/10/16.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private List<BasePager> mViewList;

    public ViewPagerAdapter(Context context, List<BasePager> views) {
        mViewList = views;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BasePager basePager = mViewList.get(position);
        View root = basePager.getRootView();
        container.addView(root);
        basePager.initData();
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
