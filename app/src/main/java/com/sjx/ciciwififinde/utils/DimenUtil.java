package com.sjx.ciciwififinde.utils;

import android.content.res.Resources;

/**
 * Dimen工具类
 */
public class DimenUtil {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static final float DENSITY;

    static {
        SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        DENSITY = Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(float pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
