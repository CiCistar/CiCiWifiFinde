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

public class MainActivity extends Activity {

    private View mRegisteTip;
    private EditText mUserName;
    private EditText mPassword;
    private View mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        setonClick();
    }

    private void setonClick() {
        mBtnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPassword();
            }
        });
        mRegisteTip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        mRegisteTip = findViewById(R.id.main_register_tip);
        mUserName = findViewById(R.id.et_user_name);
        mPassword = findViewById(R.id.et_user_password);
        mBtnLogin = findViewById(R.id.btn_login);
    }

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

        Map<String, String> loginInfo = (Map<String, String>) SharedPreferenceUtil.get(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_NAME_SP);
        if (loginInfo != null) {
            for (Map.Entry<String, String> user : loginInfo.entrySet()) {
                if (text.equals(user.getKey())) {
                    if (passWord.equals(user.getValue())) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
                        startHomeActivity();
                    } else {
                        Toast.makeText(this, "密码错误", Toast.LENGTH_LONG).show();
                    }
                    return;
                }
            }
            Toast.makeText(this, "用户名不存在", Toast.LENGTH_LONG).show();
        }
    }

    private void startHomeActivity() {
        startActivity(new Intent(this, HomeActivity.class));
    }

}
