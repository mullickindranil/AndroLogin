package com.example.indoapps.printtouch;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class registration_page extends AppCompatActivity {
    ProgressDialog progressDialog;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;




    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.registration_page);
        progressDialog = new ProgressDialog(registration_page.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Loading .. Please Wait");
        progressDialog.setTitle("Print Touch");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();




        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (progressDialog.getProgress() <= progressDialog.getMax()) {
                        Thread.sleep(15);

                        handler.sendMessage(handler.obtainMessage());
                        if (progressDialog.getProgress() == progressDialog.getMax())
                            progressDialog.dismiss();
                    }
                } catch (Exception e) {
                }
            }
        }).start();

    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressDialog.incrementProgressBy(1);
        }
    };
}