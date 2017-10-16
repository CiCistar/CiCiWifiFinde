package com.sjx.ciciwififinde.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import com.sjx.ciciwififinde.R;
import com.sjx.ciciwififinde.callback.MyConstant;
import com.sjx.ciciwififinde.utils.SharedPreferenceUtil;
import java.util.Map;

/**
 * 用户注册界面
 */
public class RegisterActivity extends Activity {

    private static final String TAG = "RegisterActivity";
    private EditText mUserName;                                         // 用户名
    private EditText mUserPassword;                                     // 密码
    private EditText mPasswordConfirm;                                  // 确认密码
    private View mBtnRegister;                                          // 注册按钮
    private View mBtnFindPassword;                                      // 查询密码按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisetr);
        initView();
        setOnclick();
    }

    private void setOnclick() {
        mBtnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmRegister();
            }
        });
        mBtnFindPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                findPassword();
            }
        });
    }

    /**
     * 验证注册信息是否合法
     */
    private void confirmRegister() {
        String userName;
        String userPassword;
        String passwordConfirm;
        if (!TextUtils.isEmpty(mUserName.getText())) {
            userName = mUserName.getText().toString();
        } else {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(mUserPassword.getText())) {
            userPassword = mUserPassword.getText().toString();
        } else {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (!TextUtils.isEmpty(mPasswordConfirm.getText())) {
            passwordConfirm = mPasswordConfirm.getText().toString();
        } else {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_LONG).show();
            return;
        }

        if (userPassword.equals(passwordConfirm)) {
            try {
                Map<String, String> userMap = (Map<String, String>) SharedPreferenceUtil.get(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_SP);
                if (userMap.containsKey(userName)) {
                    Toast.makeText(this, userName + " 用户已存在", Toast.LENGTH_LONG).show();
                    return;
                }
                userMap.put(userName, userPassword);
                SharedPreferenceUtil.save(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_SP, userMap);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "confirmRegister e" + e.toString());
                Toast.makeText(this, "注册失败，发生未知异常" + e.toString(), Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(this, "注册成功，请返回登录", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 根据输入的用户名查询密码
     */
    private void findPassword() {
        String userName;
        if (!TextUtils.isEmpty(mUserName.getText())) {
            userName = mUserName.getText().toString();
        } else {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            Map<String, String> userMap = (Map<String, String>) SharedPreferenceUtil.get(MyConstant.SAVE_FILE_KEY_SP, MyConstant.SAVE_USER_SP);
            if (userMap.containsKey(userName)) {
                Toast.makeText(this, userName + " 密码：" + userMap.get(userName), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "该用户不存在", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "findPassword e" + e.toString());
            Toast.makeText(this, "查询失败，发生未知异常" + e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mUserName = findViewById(R.id.et_user_name);
        mUserPassword = findViewById(R.id.et_user_password);
        mPasswordConfirm = findViewById(R.id.et_user_password_confirm);
        mBtnRegister = findViewById(R.id.btn_register);
        mBtnFindPassword = findViewById(R.id.btn_find_password);
    }
}
