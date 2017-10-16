package com.sjx.ciciwififinde.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import com.sjx.ciciwififinde.R;
import com.sjx.ciciwififinde.callback.MyConstant;
import com.sjx.ciciwififinde.utils.SharedPreferenceUtil;
import java.util.Map;

/**
 * 登录界面
 */
public class LoginActivity extends Activity {

    private View mRegisterTip;                      // 注册按钮
    private EditText mUserName;                     // 用户名
    private EditText mPassword;                     // 密码
    private View mBtnLogin;                         // 登录按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        setonClick();
    }

    /**
     * 设置点击事件
     */
    private void setonClick() {
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPassword();
            }
        });
        mRegisterTip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mRegisterTip = findViewById(R.id.main_register_tip);
        mUserName = findViewById(R.id.et_user_name);
        mPassword = findViewById(R.id.et_user_password);
        mBtnLogin = findViewById(R.id.btn_login);
    }

    /**
     * 验证密码是否正确
     * 赶时间，这里使用本地文件存储，后期改为服务端的数据库
     */
    private void confirmPassword() {
        String text;
        String passWord;
        if (!TextUtils.isEmpty(mUserName.getText())) {
            text = mUserName.getText().toString();
        } else {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(mPassword.getText())) {
            passWord = mPassword.getText().toString();
        } else {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
            return;
        }

        Map<String, String> loginInfo = (Map<String, String>) SharedPreferenceUtil.get(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_SP);
        if (loginInfo != null) {
            for (Map.Entry<String, String> user : loginInfo.entrySet()) {
                if (text.equals(user.getKey())) {
                    if (passWord.equals(user.getValue())) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(this, HomeActivity.class));
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
            }
            Toast.makeText(this, "用户名不存在", Toast.LENGTH_LONG).show();
        }
    }

}
