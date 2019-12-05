package dk.nodes.template.presentation.ui.menu

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : BaseFragment() {

    private var mainContext: Context? = null
    //private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chat_btn.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(mainContext, Uri.parse("https://m.facebook.com/thomas.tjenerteamet"))
        }

        faq_btn.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(mainContext, Uri.parse("http://www.tjenerteamet.dk/faq-app.html"))
        }

        /*webView = web_view
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://m.facebook.com/thomas.tjenerteamet")*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                MenuFragment().apply {
                }
    }

}