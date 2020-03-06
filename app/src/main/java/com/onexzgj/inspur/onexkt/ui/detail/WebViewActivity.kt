package com.onexzgj.inspur.onexkt.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.onexzgj.inspur.onexkt.R
import com.onexzgj.inspur.onexkt.utils.*
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * Web详情展示页面
 */
class WebViewActivity : AppCompatActivity() {

    private var wxtitle: String? = null
    private var link: String? = null
    private var author: String? = null
    private var id: Int? = -1
    private var loadUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StateBar.fitSystemBar(this)
        setContentView(R.layout.activity_web_view)

        val bundle: Bundle? = intent.extras
        bundle?.getString("")

        loadUrl = bundle?.getString(URL)
        id = bundle?.getInt(ID)
        author = bundle?.getString(AUTHOR)
        link = bundle?.getString(LINK)
        wxtitle = bundle?.getString(TITLE)

        tb_toolbar.title = wxtitle
        wv_webview.loadUrl(loadUrl)
    }
}
