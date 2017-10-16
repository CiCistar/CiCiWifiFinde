package com.sjx.ciciwififinde.activity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.sjx.ciciwififinde.R;

/**
 * Created by gsy on 17/10/16.
 */

public class SplashActivity extends Activity {
    private static final int PERMISSIONS_CODE = 1;
    private View mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        mTextView = findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasExternalPermission()) jumpToNext();
    }

    /**
     * 动态存储卡申请权限
     */
    public boolean hasExternalPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
            WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                WRITE_EXTERNAL_STORAGE,
                ACCESS_FINE_LOCATION,
                READ_PHONE_STATE
            }, PERMISSIONS_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSIONS_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(SplashActivity.this,"用户赋予权限",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SplashActivity.this, "需要权限才可以正常使用，请到设置中打开", Toast.LENGTH_LONG).show();
            }
            jumpToNext();
        }
    }

    private void jumpToNext() {
        mTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
