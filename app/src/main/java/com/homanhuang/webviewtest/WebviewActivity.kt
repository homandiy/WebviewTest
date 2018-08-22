package com.homanhuang.webviewtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.support.v4.view.ViewCompat.setOverScrollMode
import android.util.Log
import android.webkit.WebViewClient
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.R.attr.versionName
import android.content.pm.PackageInfo
import android.os.Build


class WebviewActivity : AppCompatActivity() {

    private var webview: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        webview = findViewById(R.id.myWebview) as WebView

        webview!!.webViewClient = WebViewClient()
        webview!!.settings.javaScriptEnabled = true
        webview!!.settings.domStorageEnabled = true
        webview!!.overScrollMode = WebView.OVER_SCROLL_NEVER

        val webViewPackageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            WebView.getCurrentWebViewPackage()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        webview!!.webChromeClient = object : WebChromeClient() {

            override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                consoleMessage?.apply {
                    Log.d("MYLOG", "${message()} -- From line ${lineNumber()} of ${sourceId()}")
                }
                return true
            }
        }

        val pdfUrl = "http://online.anyflip.com/bjin/gyfv/mobile/index.html"
        webview!!.loadUrl(pdfUrl)
    }
}
