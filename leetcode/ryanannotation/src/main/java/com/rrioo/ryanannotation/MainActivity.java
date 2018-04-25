package com.rrioo.ryanannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ryan.annotation.OnClick;
import com.ryan.annotation.R;
import com.ryan.annotation.ViewId;
import com.ryan.core.Jugg;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainActivity extends AppCompatActivity {


    @ViewId(R.id.tv)
    TextView mTv;


    @OnClick(R.id.tv)
    void clickText() {
        System.out.println("click ");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Jugg.inject(this);
        System.out.println("mTv :" + mTv.getText().toString());
    }

    void doInBackground() {
        for (int i = 0; i < 100; i++) {
            System.out.println("test " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
