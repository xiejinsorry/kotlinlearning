package com.example.xiejin.kotlinlearning;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiejin.kotlinlearning.widget.MyTextView;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestActivity extends AppCompatActivity {
    private MyTextView mTestTView;
    private Handler mHandler = new MyHandler(this);
    private static final int MSG_SET = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mTestTView = findViewById(R.id.tv_test);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mHandler.obtainMessage(MSG_SET, "adfafgadqqtY").sendToTarget();
            }
        });
    }

    public static class MyHandler extends Handler {
        private WeakReference<TestActivity> mWeak;

        public MyHandler(TestActivity activity) {
            mWeak = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_SET && mWeak != null && mWeak.get() != null) {
                mWeak.get().mTestTView.setText(msg.obj.toString());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}
