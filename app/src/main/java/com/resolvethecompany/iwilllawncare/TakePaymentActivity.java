package com.resolvethecompany.iwilllawncare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class TakePaymentActivity extends AppCompatActivity {

    WebView webView;
    TextView returnHome;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_payment);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        returnHome = findViewById(R.id.returnButton_TextView);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://buy.stripe.com/8wMaI40m7cH4aOcbIJ");

        returnHome.setOnClickListener(view -> {
            Intent backHome = new Intent(TakePaymentActivity.this, Services_Activity.class);
            startActivity(backHome);
        });
    }

    @Override
    public void onBackPressed() {
        //do nothing
        Intent backHome =
                new
                        Intent(TakePaymentActivity.this,
                        Services_Activity.class);
        startActivity(backHome);
    }
}