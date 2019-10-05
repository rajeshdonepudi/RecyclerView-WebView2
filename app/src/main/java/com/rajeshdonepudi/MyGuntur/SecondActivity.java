package com.rajeshdonepudi.MyGuntur;

/**
 * Author : DONEPUDI RAJESH
 * <p>
 * EMAIL ID : rajeshdonepudi1@gmail.com
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import static com.rajeshdonepudi.MyGuntur.MainActivity.IMAGE;
import static com.rajeshdonepudi.MyGuntur.MainActivity.TEXT;
import static com.rajeshdonepudi.MyGuntur.MainActivity.URL;

public class SecondActivity extends AppCompatActivity {
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String image = intent.getStringExtra(TEXT);
        String text = intent.getStringExtra(IMAGE);
        String url = intent.getStringExtra(URL);

        ImageView imageView = (ImageView) findViewById(R.id.imageV);
        TextView textView = (TextView) findViewById(R.id.textV);

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(image)
                .into(imageView);

        textView.setText(text);

        mWebview = (WebView) findViewById(R.id.webview);

        mWebview.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        mWebview.loadUrl(url);


    }

    public void backButton(View view) {

        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
