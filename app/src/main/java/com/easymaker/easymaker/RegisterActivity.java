package com.easymaker.easymaker;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.easymaker.easymaker.adapters.swipe_adapter;

public class RegisterActivity extends Activity {

    ViewPager viewPager;
    swipe_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Pantalla completa
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().hide();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new swipe_adapter(this);
        viewPager.setAdapter(adapter);
    }
}
