package com.sinjon.ticket.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sinjon.ticket.R;
import com.sinjon.ticket.pojo.Good;

/**
 * @author SongJian
 * @Date 2017/7/6
 * @Email songjian0x00@163.com
 */
public class TicketActivity extends AppCompatActivity {
    private Toolbar toolbar_ticket;
    private WebView webview;
    private Good good;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        good = (Good) getIntent().getSerializableExtra("good");
        initView();
    }

    private void initView() {
        initToolbar();
        initWebview();
    }
    private void initToolbar() {
        toolbar_ticket = (Toolbar) findViewById(R.id.toolbar_ticket);
        setSupportActionBar(toolbar_ticket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_ticket.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initWebview() {
        webview = (WebView) findViewById(R.id.webview);

        WebSettings webSettings = webview.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        if (good != null) {
            webview.loadUrl(good.getTicket());
        }

        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if( url.startsWith("http:") || url.startsWith("https:") ) {
                    return false;
                }else {
                    // Otherwise allow the OS to handle things like tel, mailto, etc.
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity( intent );
                    finish();
                }
                return true;
            }
        });
    }

}
