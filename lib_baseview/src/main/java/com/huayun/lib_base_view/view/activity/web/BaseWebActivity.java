package com.huayun.lib_base_view.view.activity.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.huayun.lib_base_view.R;
import com.huayun.lib_base_view.R2;
import com.huayun.lib_base_view.view.activity.BaseActivityBusiness;
import com.huayun.lib_base_view.widget.CustomTitle;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;

import butterknife.BindView;

/**
 * 添加到路由处理
 * 添加非 网络url加载处理
 */
public abstract class BaseWebActivity extends BaseActivityBusiness {

    protected String url;

    @BindView(R2.id.ll_parent)
    protected LinearLayout llParent;
    @BindView(R2.id.tv_title)
    protected CustomTitle tvTitle;

    protected AgentWeb mAgentWeb;
    protected boolean isBack = true;
    protected int errorPage = R.layout.web_error_page;//错误页面

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    protected void initWebView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llParent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()//使用默认指示器
                .setWebChromeClient(mWebChromeClient)
                .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(errorPage, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go(url);
    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //WebView本身自带的标题
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }

        @Override
        public void onPageFinished(WebView view, String url) {
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();// 接受所有网站的证书
        }
    };


    /**
     * Html文本方式加载
     *
     * @param title
     * @param content
     */
    protected void initWebTextContent(String title, String content) {
        StringBuilder sb = new StringBuilder();
        // 拼接一段HTML代码
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> " + title + " </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(content);
        sb.append("</body>");
        sb.append("</html>");
        mAgentWeb.getUrlLoader().loadDataWithBaseURL(null, sb.toString(), "text/html", "utf-8", null);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isBack) {
            if (mAgentWeb.handleKeyEvent(keyCode, event)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}