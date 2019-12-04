package dk.nodes.template.presentation.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import dk.nodes.template.presentation.R
import dk.nodes.template.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_chat.*
import timber.log.Timber

class ChatFragment : BaseFragment() {

    private var mainContext: Context? = null
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.e("Created ChatFragmentView")

        webView = web_view
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("https://m.facebook.com/thomas.tjenerteamet")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                ChatFragment().apply {

                }
    }

}