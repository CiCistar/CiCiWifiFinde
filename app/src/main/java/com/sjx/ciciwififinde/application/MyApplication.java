package com.sjx.ciciwififinde.application;

import android.app.Application;
import com.sjx.ciciwififinde.callback.MyConstant;
import com.sjx.ciciwififinde.utils.SharedPreferenceUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用的application，初始化必要信息
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferenceUtil.setContext(this);
        Map<String, String> origin = new HashMap<>();
        origin.put("root", "123456");
        SharedPreferenceUtil.save(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_SP, origin);
    }
}
